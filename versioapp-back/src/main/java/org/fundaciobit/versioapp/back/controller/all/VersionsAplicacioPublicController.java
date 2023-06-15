package org.fundaciobit.versioapp.back.controller.all;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SelectDistinct;
import org.fundaciobit.genapp.common.query.SelectGroupBy;
import org.fundaciobit.genapp.common.query.SelectMax;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;

import org.fundaciobit.genapp.common.query.selectcolumn.Select7Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select7Values;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.versioapp.back.controller.admin.EntornAdminController;
import org.fundaciobit.versioapp.back.controller.webdb.AplicacioController;
import org.fundaciobit.versioapp.back.form.webdb.AplicacioFilterForm;
import org.fundaciobit.versioapp.back.form.webdb.AplicacioForm;
import org.fundaciobit.versioapp.model.entity.Aplicacio;
import org.fundaciobit.versioapp.model.entity.Versio;
import org.fundaciobit.versioapp.model.fields.AplicacioFields;
import org.fundaciobit.versioapp.model.fields.EntornAplicacioFields;
import org.fundaciobit.versioapp.model.fields.EntornFields;
import org.fundaciobit.versioapp.model.fields.VersioFields;
import org.fundaciobit.versioapp.model.fields.VersioQueryPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/public/versionsaplicacio")
@SessionAttributes(types = { AplicacioForm.class, AplicacioFilterForm.class })
public class VersionsAplicacioPublicController extends AplicacioController {

    @EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornService.JNDI_NAME)
    protected org.fundaciobit.versioapp.ejb.EntornService entornEjb;

    @EJB(mappedName = org.fundaciobit.versioapp.logic.VersioLogicaService.JNDI_NAME)
    protected org.fundaciobit.versioapp.logic.VersioLogicaService versioLogicaEjb;

    @Override
    public String getTileList() {
        return isAdmin() ? "versionsaplicacioListAdmin" : "versionsaplicacioListPublic";
    }

    public static final String POSICIO_BY_ENTORNID_SESSION = "POSICIO_BY_ENTORNID_SESSION";

    @Override
    public AplicacioFilterForm getAplicacioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        AplicacioFilterForm appFilterForm = super.getAplicacioFilterForm(pagina, mav, request);
        if (appFilterForm.isNou()) {

            if (isAdmin()) {

                appFilterForm.addAdditionalButton(new AdditionalButton("fas fa-sync",
                        "=Refrescar Totes Versions XYZ ZZZ", getContextWeb() + "/refreshall", "btn-info"));

            } else {
                appFilterForm.setEditButtonVisible(false);
                appFilterForm.setDeleteButtonVisible(false);
                appFilterForm.setAddButtonVisible(false);
            }

            appFilterForm.addHiddenField(APLICACIOID);
            appFilterForm.setDeleteSelectedButtonVisible(false);
            appFilterForm.setVisibleMultipleSelection(false);
            appFilterForm.setVisibleExportList(false);
            appFilterForm.setItemsPerPage(-1); // TOTS

            {
                SelectMultipleStringKeyValue entornsSMSKV = new SelectMultipleStringKeyValue(
                        EntornFields.ENTORNID.select, EntornFields.NOM.select);

                List<StringKeyValue> entorns = entornEjb.executeQuery(entornsSMSKV, new OrderBy(EntornFields.ORDRE));

                // Donat un entornID retorna la posicio de la columna 
                Map<Long, Integer> posicioByEntornID = new HashMap<Long, Integer>();

                int pos = 1;
                for (StringKeyValue skv : entorns) {

                    AdditionalField<Long, String> additionalField = new AdditionalField<Long, String>();
                    additionalField.setCodeName("=Entorn<br/>" + skv.getValue());
                    additionalField.setPosition(pos); // XYZ ZZZ 
                    additionalField.setEscapeXml(false);
                    // Els valors s'ompliran al mètode postList()
                    additionalField.setValueMap(new HashMap<Long, String>());

                    appFilterForm.addAdditionalField(additionalField);

                    posicioByEntornID.put(Long.parseLong(skv.getKey()), pos);

                    pos++;
                }

                request.getSession().setAttribute(POSICIO_BY_ENTORNID_SESSION, posicioByEntornID);
            }

        }

        return appFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, AplicacioFilterForm filterForm,
            List<Aplicacio> listAplicacio) throws I18NException {

        // NETEJA INICIAL
        Map<Long, Integer> posicioByEntornID = (Map<Long, Integer>) request.getSession()
                .getAttribute(POSICIO_BY_ENTORNID_SESSION);
        for (Integer posicio : posicioByEntornID.values()) {
            
            if (posicio != null) {
              Map<Long, String> entornN = (Map<Long, String>) filterForm.getAdditionalField(posicio).getValueMap();
              entornN.clear();
            }
        }

        // OMplir les columnes de cada ENTORN per Aplicacio
        {
            List<Timestamp> dates = new ArrayList<Timestamp>();

            {

                SelectGroupBy<Long> groupBy = new SelectGroupBy<Long>(VersioFields.ENTORNAPLICACIOID);

                SelectMax<Timestamp> maxDate = new SelectMax<Timestamp>(VersioFields.DATA);

                Select2Columns<Timestamp, Long> s2c;
                s2c = new Select2Columns<Timestamp, Long>(maxDate, groupBy);

                List<Select2Values<Timestamp, Long>> list = this.versioLogicaEjb.executeQuery(s2c);

                for (Select2Values<Timestamp, Long> v : list) {
                    dates.add(v.getValue1());
                }
            }

            SelectDistinct<Long> entornaplicacioid = new SelectDistinct<Long>(VersioFields.ENTORNAPLICACIOID);
            // DISTINCT entornaplicacioid,  versio, build, data,
            Select7Columns<Long, Long, String, String, Timestamp, Long, Long> s7c;
            s7c = new Select7Columns<Long, Long, String, String, Timestamp, Long, Long>(entornaplicacioid,
                    VersioFields.VERSIOID.select, VersioFields.VERSIO.select, VersioFields.BUILD.select,
                    VersioFields.DATA.select, new VersioQueryPath().ENTORNAPLICACIO().APLICACIOID().select,
                    new VersioQueryPath().ENTORNAPLICACIO().ENTORNID().select);

            List<Select7Values<Long, Long, String, String, Timestamp, Long, Long>> list;
            list = this.versioLogicaEjb.executeQuery(s7c, VersioFields.DATA.in(dates));

            List<String> entornsAplicacioActuals = new ArrayList<String>();

            for (Select7Values<Long, Long, String, String, Timestamp, Long, Long> v : list) {

                java.lang.String versio = v.getValue3();
                Long aplicacioID = v.getValue6();
                Long entornID = v.getValue7();
                /*
                Long entornAplicacioID = v.getValue1();
                
                
                java.lang.String build = v.getValue4();
                java.sql.Timestamp data = v.getValue5();
                
                java.lang.String altresDades = null;
                versions.add(new VersioBean(entornAplicacioID, versio, build, data, altresDades));
                
                
                */
                Integer posicio = posicioByEntornID.get(entornID);

                Map<Long, String> entornN = (Map<Long, String>) filterForm.getAdditionalField(posicio).getValueMap();

                // XYZ ZZZ colocar bé data i build
                StringBuilder str = new StringBuilder();

                if (isAdmin()) {
                    Long versioID = v.getValue2();
                    str.append("<b>" + versio + "</b>");
                    str.append("&nbsp;&nbsp;<a href=\"" + request.getContextPath() + getContextWeb() + "/refresh/"
                            + versioID + "\"><i class=\"fas fa-sync\"></i></a>");
                    str.append("&nbsp;&nbsp;<a href=\"" + request.getContextPath() + EntornAdminController.CONTEXTWEB
                            + "/" + entornID + "/edit" + "\"><i class=\"fas fa-edit\"></i></a>");
                    entornsAplicacioActuals.add(aplicacioID + "_" + entornID);
                } else {
                    str.append("<b>" + versio + "</b>");
                }
                entornN.put(aplicacioID, str.toString()); // + " - " + build);

            }

            // Afegim boto per crear parells Aplicació-Entorn que no existeixen
            if (isAdmin()) {

                List<Long> entornsID = entornEjb.executeQuery(EntornFields.ENTORNID, new OrderBy(EntornFields.ORDRE));

                for (Aplicacio app : listAplicacio) {
                    for (Long ent : entornsID) {

                        if (!entornsAplicacioActuals.contains(app + "_" + ent)) {

                            Long count = entornAplicacioEjb.count(Where.AND(EntornAplicacioFields.ENTORNID.equal(ent),
                                    EntornAplicacioFields.APLICACIOID.equal(app.getAplicacioID())));
                            Integer posicio = posicioByEntornID.get(ent);
                            
                            if (posicio == null) {
                                continue;
                            }

                            Map<Long, String> entornN = (Map<Long, String>) filterForm.getAdditionalField(posicio)
                                    .getValueMap();

                            if (count == 0) {
                                String str = "<a href=\"" + request.getContextPath() + this.getContextWeb()
                                        + "/afegiraplicacioentorn/" + app.getAplicacioID() + "/" + ent
                                        + "\"><i class=\"fas fa-plus-circle\"></i></a>";

                                entornN.put(app.getAplicacioID(), str.toString()); // + " - " + build);
                            } else {
                                if (entornN.get(app.getAplicacioID()) == null) {
                                    String str = "&nbsp;&nbsp;<a href=\"" + request.getContextPath() + getContextWeb()
                                            + "/refresh/" + app.getAplicacioID() + "/" + ent
                                            + "\"><i class=\"fas fa-sync\"></i></a>";
                                    entornN.put(app.getAplicacioID(), str.toString()); // + " - " + build);
                                }
                            }

                        }

                    }
                }
            }

        }

    }

    @EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornAplicacioService.JNDI_NAME)
    protected org.fundaciobit.versioapp.ejb.EntornAplicacioService entornAplicacioEjb;

    @RequestMapping(value = "/afegiraplicacioentorn/{aplicacioID}/{entornID}")
    public String afegirAplicacioEntorn(@PathVariable("aplicacioID") java.lang.Long aplicacioID,
            @PathVariable("entornID") java.lang.Long entornID, HttpServletRequest request,
            HttpServletResponse response) {

        //log.info("refreshVersio() => versioID " + versioID);
        try {
            entornAplicacioEjb.create(aplicacioID, entornID);

            // XYZ ZZZ OK Creats !!!1

        } catch (Exception e) {
            // TODO: handle exception XYZ I18NException

            HtmlUtils.saveMessageError(request,
                    "Error intentant crear l'Aplicació Entorn [App:  " + aplicacioID + "| Entorn: " + entornID + " ] ");
        }

        return "redirect:" + getContextWeb() + "/list";

    }

    @RequestMapping(value = "/refresh/{versioID}")
    public String refreshVersio(@PathVariable("versioID") java.lang.Long versioID, HttpServletRequest request,
            HttpServletResponse response) {

        log.info("refreshVersio() => versioID " + versioID);

        Versio v2 = this.versioLogicaEjb.findByPrimaryKey(versioID);

        try {

            Select2Columns<String, String> s2c;
            s2c = new Select2Columns<String, String>(new VersioQueryPath().ENTORNAPLICACIO().ENTORN().DOMINI().select,
                    new VersioQueryPath().ENTORNAPLICACIO().APLICACIO().CONTEXTPATH().select);

            Select2Values<String, String> values;
            values = this.versioLogicaEjb.executeQueryOne(s2c, VersioFields.VERSIOID.equal(versioID));

            String domini = values.getValue1();
            String contextPath = values.getValue2();

            String versioGuardada = v2.getVersio();

            Long entornAplicacio = v2.getEntornAplicacioID();

            refreshVersio(request, domini, contextPath, versioGuardada, entornAplicacio);

        } catch (I18NException e) {
            final String msg = "Error intentant obtenir informació de la versió  " + versioID + ": " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @RequestMapping(value = "/refresh/{aplicacioID}/{entornID}")
    public String refreshVersio(@PathVariable("aplicacioID") java.lang.Long aplicacioID,
            @PathVariable("entornID") java.lang.Long entornID, HttpServletRequest request,
            HttpServletResponse response) {

        log.info("refreshVersio() => aplicacioID " + aplicacioID + "&& entornID " + entornID);

        try {

            // XYZ ZZZ Millorar amb un Select3Columns
            String domini = entornEjb.executeQueryOne(EntornFields.DOMINI, EntornFields.ENTORNID.equal(entornID));
            String contextPath = aplicacioEjb.executeQueryOne(AplicacioFields.CONTEXTPATH,
                    AplicacioFields.APLICACIOID.equal(aplicacioID));
            Long entornAplicacio = entornAplicacioEjb.executeQueryOne(EntornAplicacioFields.ENTORNAPLICACIOID,
                    Where.AND(EntornAplicacioFields.APLICACIOID.equal(aplicacioID),
                            EntornAplicacioFields.ENTORNID.equal(entornID)));

            String versioGuardada = null;

            refreshVersio(request, domini, contextPath, versioGuardada, entornAplicacio);

        } catch (I18NException e) {
            final String msg = "Error intentant refrescar versió de Aplicacio " + aplicacioID + " i entorn "
                    + I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    protected void refreshVersio(HttpServletRequest request, String domini, String contextPath, String versioGuardada,
            Long entornAplicacio) {

        try {
            HtmlUtils.saveMessageSuccess(request,
                    this.versioLogicaEjb.refreshVersio(domini, contextPath, versioGuardada, entornAplicacio));
        } catch (Exception e) {
            final String msg = "Error cridant a la URL per obtenir la versió: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
    }

    @RequestMapping(value = "/refreshall")
    public String refreshAll(HttpServletRequest request, HttpServletResponse response) {

        try {
            versioLogicaEjb.refreshAllVersions();

        } catch (Exception e) {
            final String msg = "Error cridant al refresc global: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";

    }

    public boolean isAdmin() {
        return false;
    }

    // Mètodes a sobreescriure 

    @Override
    public boolean isActiveFormNew() {
        return isAdmin();
    }

    @Override
    public boolean isActiveFormEdit() {
        return isAdmin();
    }

    @Override
    public boolean isActiveDelete() {
        return isAdmin();
    }

    @Override
    public boolean isActiveFormView() {
        return isAdmin();
    }

}
