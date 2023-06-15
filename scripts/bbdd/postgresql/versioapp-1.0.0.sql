--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2023-06-15 10:02:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2024 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 177 (class 1259 OID 114710)
-- Name: ver_aplicacio_seq; Type: SEQUENCE; Schema: public; Owner: versioapp
--

CREATE SEQUENCE public.ver_aplicacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ver_aplicacio_seq OWNER TO versioapp;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 180 (class 1259 OID 114718)
-- Name: ver_aplicacio; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_aplicacio (
    aplicacioid bigint DEFAULT nextval('public.ver_aplicacio_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    contextpath character varying(255) NOT NULL
);


ALTER TABLE public.ver_aplicacio OWNER TO versioapp;

--
-- TOC entry 178 (class 1259 OID 114712)
-- Name: ver_entorn_seq; Type: SEQUENCE; Schema: public; Owner: versioapp
--

CREATE SEQUENCE public.ver_entorn_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ver_entorn_seq OWNER TO versioapp;

--
-- TOC entry 181 (class 1259 OID 114725)
-- Name: ver_entorn; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_entorn (
    entornid bigint DEFAULT nextval('public.ver_entorn_seq'::regclass) NOT NULL,
    nom character varying(100) NOT NULL,
    domini character varying(255) NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.ver_entorn OWNER TO versioapp;

--
-- TOC entry 184 (class 1259 OID 114811)
-- Name: ver_entornaplicacio_seq; Type: SEQUENCE; Schema: public; Owner: versioapp
--

CREATE SEQUENCE public.ver_entornaplicacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ver_entornaplicacio_seq OWNER TO versioapp;

--
-- TOC entry 182 (class 1259 OID 114757)
-- Name: ver_entornaplicacio; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_entornaplicacio (
    entornaplicacioid bigint DEFAULT nextval('public.ver_entornaplicacio_seq'::regclass) NOT NULL,
    aplicacioid bigint NOT NULL,
    entornid bigint NOT NULL
);


ALTER TABLE public.ver_entornaplicacio OWNER TO versioapp;

--
-- TOC entry 171 (class 1259 OID 114665)
-- Name: ver_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: versioapp
--

CREATE SEQUENCE public.ver_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ver_fitxer_seq OWNER TO versioapp;

--
-- TOC entry 173 (class 1259 OID 114669)
-- Name: ver_fitxer; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_fitxer (
    fitxerid bigint DEFAULT nextval('public.ver_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.ver_fitxer OWNER TO versioapp;

--
-- TOC entry 174 (class 1259 OID 114677)
-- Name: ver_idioma; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.ver_idioma OWNER TO versioapp;

--
-- TOC entry 172 (class 1259 OID 114667)
-- Name: ver_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: versioapp
--

CREATE SEQUENCE public.ver_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ver_traduccio_seq OWNER TO versioapp;

--
-- TOC entry 175 (class 1259 OID 114682)
-- Name: ver_traduccio; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_traduccio (
    traduccioid bigint DEFAULT nextval('public.ver_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.ver_traduccio OWNER TO versioapp;

--
-- TOC entry 176 (class 1259 OID 114686)
-- Name: ver_traducciomap; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.ver_traducciomap OWNER TO versioapp;

--
-- TOC entry 179 (class 1259 OID 114716)
-- Name: ver_versio_seq; Type: SEQUENCE; Schema: public; Owner: versioapp
--

CREATE SEQUENCE public.ver_versio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ver_versio_seq OWNER TO versioapp;

--
-- TOC entry 183 (class 1259 OID 114775)
-- Name: ver_versio; Type: TABLE; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE TABLE public.ver_versio (
    versioid bigint DEFAULT nextval('public.ver_versio_seq'::regclass) NOT NULL,
    entornaplicacioid bigint NOT NULL,
    versio character varying(100) NOT NULL,
    build character varying(100),
    data timestamp without time zone NOT NULL,
    altresdades character varying(4096)
);


ALTER TABLE public.ver_versio OWNER TO versioapp;

--
-- TOC entry 1889 (class 2606 OID 114756)
-- Name: ver_aplicacio_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_aplicacio
    ADD CONSTRAINT ver_aplicacio_pk PRIMARY KEY (aplicacioid);


--
-- TOC entry 1892 (class 2606 OID 114730)
-- Name: ver_entorn_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_entorn
    ADD CONSTRAINT ver_entorn_pk PRIMARY KEY (entornid);


--
-- TOC entry 1896 (class 2606 OID 114774)
-- Name: ver_entornapli_entorn_apli_uk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_entornaplicacio
    ADD CONSTRAINT ver_entornapli_entorn_apli_uk UNIQUE (aplicacioid, entornid);


--
-- TOC entry 1899 (class 2606 OID 114762)
-- Name: ver_entornaplicacio_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_entornaplicacio
    ADD CONSTRAINT ver_entornaplicacio_pk PRIMARY KEY (entornaplicacioid);


--
-- TOC entry 1876 (class 2606 OID 114693)
-- Name: ver_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_fitxer
    ADD CONSTRAINT ver_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1879 (class 2606 OID 114695)
-- Name: ver_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_idioma
    ADD CONSTRAINT ver_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1882 (class 2606 OID 114697)
-- Name: ver_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_traduccio
    ADD CONSTRAINT ver_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1887 (class 2606 OID 114699)
-- Name: ver_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_traducciomap
    ADD CONSTRAINT ver_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1903 (class 2606 OID 114783)
-- Name: ver_versio_pk; Type: CONSTRAINT; Schema: public; Owner: versioapp; Tablespace: 
--

ALTER TABLE ONLY public.ver_versio
    ADD CONSTRAINT ver_versio_pk PRIMARY KEY (versioid);


--
-- TOC entry 1890 (class 1259 OID 114789)
-- Name: ver_aplicacio_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_aplicacio_pk_i ON public.ver_aplicacio USING btree (aplicacioid);


--
-- TOC entry 1893 (class 1259 OID 114790)
-- Name: ver_entorn_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_entorn_pk_i ON public.ver_entorn USING btree (entornid);


--
-- TOC entry 1894 (class 1259 OID 114792)
-- Name: ver_entornapli_aplica_fk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_entornapli_aplica_fk_i ON public.ver_entornaplicacio USING btree (aplicacioid);


--
-- TOC entry 1897 (class 1259 OID 114798)
-- Name: ver_entornapli_entornid_fk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_entornapli_entornid_fk_i ON public.ver_entornaplicacio USING btree (entornid);


--
-- TOC entry 1900 (class 1259 OID 114791)
-- Name: ver_entornaplicacio_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_entornaplicacio_pk_i ON public.ver_entornaplicacio USING btree (entornaplicacioid);


--
-- TOC entry 1877 (class 1259 OID 114700)
-- Name: ver_fitxer_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_fitxer_pk_i ON public.ver_fitxer USING btree (fitxerid);


--
-- TOC entry 1880 (class 1259 OID 114701)
-- Name: ver_idioma_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_idioma_pk_i ON public.ver_idioma USING btree (idiomaid);


--
-- TOC entry 1883 (class 1259 OID 114702)
-- Name: ver_traduccio_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_traduccio_pk_i ON public.ver_traduccio USING btree (traduccioid);


--
-- TOC entry 1884 (class 1259 OID 114703)
-- Name: ver_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_traducciomap_idiomaid_fk_i ON public.ver_traducciomap USING btree (idiomaid);


--
-- TOC entry 1885 (class 1259 OID 114704)
-- Name: ver_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_traducciomap_pk_i ON public.ver_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1901 (class 1259 OID 114805)
-- Name: ver_versio_entornapli_fk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_versio_entornapli_fk_i ON public.ver_versio USING btree (entornaplicacioid);


--
-- TOC entry 1904 (class 1259 OID 114799)
-- Name: ver_versio_pk_i; Type: INDEX; Schema: public; Owner: versioapp; Tablespace: 
--

CREATE INDEX ver_versio_pk_i ON public.ver_versio USING btree (versioid);


--
-- TOC entry 1906 (class 2606 OID 114768)
-- Name: ver_entornapli_aplicacio_fk; Type: FK CONSTRAINT; Schema: public; Owner: versioapp
--

ALTER TABLE ONLY public.ver_entornaplicacio
    ADD CONSTRAINT ver_entornapli_aplicacio_fk FOREIGN KEY (aplicacioid) REFERENCES public.ver_aplicacio(aplicacioid);


--
-- TOC entry 1907 (class 2606 OID 114793)
-- Name: ver_entornapli_entorn_entor_fk; Type: FK CONSTRAINT; Schema: public; Owner: versioapp
--

ALTER TABLE ONLY public.ver_entornaplicacio
    ADD CONSTRAINT ver_entornapli_entorn_entor_fk FOREIGN KEY (entornid) REFERENCES public.ver_entorn(entornid);


--
-- TOC entry 1905 (class 2606 OID 114705)
-- Name: ver_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: versioapp
--

ALTER TABLE ONLY public.ver_traducciomap
    ADD CONSTRAINT ver_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.ver_traduccio(traduccioid);


--
-- TOC entry 1908 (class 2606 OID 114806)
-- Name: ver_versio_entornapli_entor_fk; Type: FK CONSTRAINT; Schema: public; Owner: versioapp
--

ALTER TABLE ONLY public.ver_versio
    ADD CONSTRAINT ver_versio_entornapli_entor_fk FOREIGN KEY (entornaplicacioid) REFERENCES public.ver_entornaplicacio(entornaplicacioid);


--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: versioapp
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM versioapp;
GRANT ALL ON SCHEMA public TO versioapp;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-06-15 10:02:05

--
-- PostgreSQL database dump complete
--

