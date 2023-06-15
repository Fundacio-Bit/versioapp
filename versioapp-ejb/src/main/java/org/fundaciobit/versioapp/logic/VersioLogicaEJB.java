package org.fundaciobit.versioapp.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectDistinct;
import org.fundaciobit.genapp.common.query.SelectGroupBy;
import org.fundaciobit.genapp.common.query.SelectMax;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;
import org.fundaciobit.genapp.common.query.selectcolumn.Select3Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select3Values;
import org.fundaciobit.genapp.common.query.selectcolumn.Select4Values;
import org.fundaciobit.genapp.common.query.selectcolumn.Select5Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select5Values;
import org.fundaciobit.versioapp.ejb.VersioEJB;
import org.fundaciobit.versioapp.model.bean.VersioBean;
import org.fundaciobit.versioapp.model.entity.Versio;

import org.fundaciobit.versioapp.model.fields.EntornAplicacioFields;
import org.fundaciobit.versioapp.model.fields.EntornAplicacioQueryPath;
import org.fundaciobit.versioapp.model.fields.VersioFields;
import org.fundaciobit.versioapp.persistence.VersioJPA;

/**
 * 
 * @author anadal
 *
 */
@PermitAll
@Stateless
public class VersioLogicaEJB extends VersioEJB implements VersioLogicaService {

    @EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornAplicacioService.JNDI_NAME)
    protected org.fundaciobit.versioapp.ejb.EntornAplicacioService entornAplicacioEjb;

    @Schedules({ @Schedule(minute = "10", hour = "5") })
    public void refreshAllVersions() throws I18NException {

        final Locale locale = new Locale("ca");

        try {

            Map<Long, String> versionsMajorsPerEntornAplicacioID = new HashMap<Long, String>();
            {
                List<Versio> versions = this.getVersionsMajors();
                for (Versio v : versions) {
                    versionsMajorsPerEntornAplicacioID.put(v.getEntornAplicacioID(), v.getVersio());
                }
            }

            Select<Long> entornAplicacioIDS = EntornAplicacioFields.ENTORNAPLICACIOID.select;
            Select<String> dominiS = new EntornAplicacioQueryPath().ENTORN().DOMINI().select;
            Select<String> contextPathS = new EntornAplicacioQueryPath().APLICACIO().CONTEXTPATH().select;

            Select3Columns<Long, String, String> s3c = new Select3Columns<Long, String, String>(entornAplicacioIDS,
                    dominiS, contextPathS);

            List<Select3Values<Long, String, String>> values = entornAplicacioEjb.executeQuery(s3c);

            int count = 1;
            for (Select3Values<Long, String, String> select3Values : values) {

                Long entornAplicacioID = select3Values.getValue1();
                String domini = select3Values.getValue2();
                String contextPath = select3Values.getValue3();

                log.info("REFRESH[" + count + "] => Start " + domini + contextPath);

                String versioGuardada = versionsMajorsPerEntornAplicacioID.get(entornAplicacioID);
                try {
                    final String result = refreshVersio(domini, contextPath, versioGuardada, entornAplicacioID);
                    log.info("REFRESH[" + count + "] => Result:  " + result);

                } catch (Exception e) {
                    final String msg = "Error intentant refrescar del domini " + domini + " i context " + contextPath
                            + ": " + e.getMessage();
                    log.info("REFRESH[" + count + "] => ERROR:  " + msg);
                    log.error(msg, e);

                }
            }

        } catch (I18NException e) {
            final String msg = "Error intentant fer un refresc global: " + I18NCommonUtils.getMessage(e, locale);
            log.error(msg, e);
        }

    }

    @PermitAll
    @Override
    public String refreshVersio(String domini, String contextPath, String versioGuardada, Long entornAplicacio)
            throws Exception {
        String url = domini + contextPath + "/public/versio";

        String versioNovaAndBuildNou = readToString(url);
        log.info("Versio Refrescada => |" + versioNovaAndBuildNou + "|");

        String versioNova;
        String buildNou;

        int pos = versioNovaAndBuildNou.indexOf('|');

        if (pos == -1) {
            versioNova = versioNovaAndBuildNou;
            buildNou = null;
        } else {
            versioNova = versioNovaAndBuildNou.substring(0, pos).trim();
            buildNou = versioNovaAndBuildNou.substring(pos + 1).trim();
        }

        if (versioGuardada == null || !versioGuardada.equals(buildNou)) {

            VersioJPA ver = new VersioJPA();
            ver.setBuild(buildNou);
            ver.setData(new Timestamp(System.currentTimeMillis()));
            ver.setEntornAplicacioID(entornAplicacio);
            ver.setVersio(versioNova);

            this.create(ver);

            return "S'ha cridat a " + url + " i s'ha actualitzat la versió a " + versioNovaAndBuildNou;

        } else {
            return "S'ha cridat a " + url + " i s'ha obtingut la versió " + versioNovaAndBuildNou + "(No es fa res)";
        }

    }

    public static String readToString(String targetURL) throws IOException {
        URL url = new URL(targetURL);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder stringBuilder = new StringBuilder();

        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }

        bufferedReader.close();
        return stringBuilder.toString().trim();
    }

    @PermitAll
    @Override
    public List<Versio> getVersionsMajors() throws I18NException {

        List<Timestamp> dates = new ArrayList<Timestamp>();
        {

            SelectGroupBy<Long> groupBy = new SelectGroupBy<Long>(VersioFields.ENTORNAPLICACIOID);

            SelectMax<Timestamp> maxDate = new SelectMax<Timestamp>(VersioFields.DATA);

            Select2Columns<Timestamp, Long> s2c;
            s2c = new Select2Columns<Timestamp, Long>(maxDate, groupBy);

            List<Select2Values<Timestamp, Long>> list = this.executeQuery(s2c);

            for (Select2Values<Timestamp, Long> v : list) {
                dates.add(v.getValue1());
            }
        }

        SelectDistinct<Long> entornaplicacioid = new SelectDistinct<Long>(ENTORNAPLICACIOID);
        // DISTINCT entornaplicacioid,  versio, build, data,
        Select5Columns<Long, String, String, Timestamp, Long> s4c;
        s4c = new Select5Columns<Long, String, String, Timestamp, Long>(entornaplicacioid, VERSIO.select, BUILD.select,
                DATA.select, ENTORNAPLICACIOID.select);

        List<Select5Values<Long, String, String, Timestamp, Long>> list;
        list = this.executeQuery(s4c, DATA.in(dates));

        List<Versio> versions = new ArrayList<Versio>();

        for (Select4Values<Long, String, String, Timestamp> v : list) {

            Long entornAplicacioID = v.getValue1();
            java.lang.String versio = v.getValue2();
            java.lang.String build = v.getValue3();
            java.sql.Timestamp data = v.getValue4();
            java.lang.String altresDades = null;
            versions.add(new VersioBean(entornAplicacioID, versio, build, data, altresDades));
        }

        return versions;

        //javax.persistence.Query query = this.versioEjb.getEntityManager().createNativeQuery("SELECT DISTINCT entornaplicacioid, data, versio FROM ver_versio  WHERE  data IN (SELECT  MAX(data) FROM ver_versio GROUP BY entornaplicacioid)");

        //SubQuery<Versio, Timestamp> subquery = this.versioEjb.getSubQuery(new SelectMax<TimeStamp>(VersioFields.DATA), where), where)

    }
}
