

CREATE SEQUENCE ver_fitxer_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE ver_traduccio_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;

CREATE TABLE ver_fitxer (
    fitxerid bigint DEFAULT nextval('ver_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);

CREATE TABLE ver_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);

CREATE TABLE ver_traduccio (
    traduccioid bigint DEFAULT nextval('ver_traduccio_seq'::regclass) NOT NULL
);

CREATE TABLE ver_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);

ALTER TABLE ONLY ver_fitxer
    ADD CONSTRAINT ver_fitxer_pk PRIMARY KEY (fitxerid);

ALTER TABLE ONLY ver_idioma
    ADD CONSTRAINT ver_idioma_pk PRIMARY KEY (idiomaid);

ALTER TABLE ONLY ver_traduccio
    ADD CONSTRAINT ver_traduccio_pk PRIMARY KEY (traduccioid);

ALTER TABLE ONLY ver_traducciomap
    ADD CONSTRAINT ver_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);

CREATE INDEX ver_fitxer_pk_i ON ver_fitxer USING btree (fitxerid);

CREATE INDEX ver_idioma_pk_i ON ver_idioma USING btree (idiomaid);

CREATE INDEX ver_traduccio_pk_i ON ver_traduccio USING btree (traduccioid);

CREATE INDEX ver_traducciomap_idiomaid_fk_i ON ver_traducciomap USING btree (idiomaid);

CREATE INDEX ver_traducciomap_pk_i ON ver_traducciomap USING btree (traducciomapid);

ALTER TABLE ONLY ver_traducciomap
    ADD CONSTRAINT ver_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES ver_traduccio(traduccioid);

INSERT INTO ver_idioma(idiomaid, nom, ordre) VALUES ('ca', 'Català', 0);
INSERT INTO ver_idioma(idiomaid, nom, ordre) VALUES ('es', 'Castellano', 1);
INSERT INTO ver_idioma(idiomaid, nom, ordre) VALUES ('en', 'English', 2);
    
    
