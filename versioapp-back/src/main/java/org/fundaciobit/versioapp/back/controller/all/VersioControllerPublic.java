package org.fundaciobit.versioapp.back.controller.all;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;


import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.versioapp.back.controller.webdb.VersioController;
import org.fundaciobit.versioapp.back.form.webdb.EntornAplicacioRefList;
import org.fundaciobit.versioapp.back.form.webdb.VersioFilterForm;
import org.fundaciobit.versioapp.back.form.webdb.VersioForm;
import org.fundaciobit.versioapp.model.entity.Versio;
import org.fundaciobit.versioapp.model.fields.EntornAplicacioQueryPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/public/versions")
@SessionAttributes(types = { VersioForm.class, VersioFilterForm.class })
public class VersioControllerPublic extends VersioController {
    
    

    @EJB(mappedName = org.fundaciobit.versioapp.logic.VersioLogicaService.JNDI_NAME)
    protected org.fundaciobit.versioapp.logic.VersioLogicaService versioLogicaEjb;

    @Override
    public String getTileList() {
        return "versioListPublic";
    }

    @PostConstruct
    public void init() {
        entornAplicacioRefList = new EntornAplicacioRefList(entornAplicacioRefList);

        Select<String> select1 = new EntornAplicacioQueryPath().APLICACIO().NOM().select;
        Select<String> select2 = new EntornAplicacioQueryPath().ENTORN().NOM().select;

        entornAplicacioRefList.setSelects(new Select<?>[] { select1, select2 });
        entornAplicacioRefList.setSeparator(" - ");

    }
    

    @EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornAplicacioService.JNDI_NAME)
    protected org.fundaciobit.versioapp.ejb.EntornAplicacioService entornAplicacioEjb;

    
    /*
    @Override
    public List<StringKeyValue> getReferenceListForEntornAplicacioID(HttpServletRequest request,
            ModelAndView mav, VersioForm versioForm, Where where)  throws I18NException {
        
         return getReferenceListForEntornAplicacioID(request, mav, where);
       }

    @Override
       public List<StringKeyValue> getReferenceListForEntornAplicacioID(HttpServletRequest request,
            ModelAndView mav, VersioFilterForm versioFilterForm,
            List<Versio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {

         return getReferenceListForEntornAplicacioID(request, mav, where);
       }
    
    
    
    
    @Override
    public List<StringKeyValue> getReferenceListForEntornAplicacioID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        SelectMultipleStringKeyValue sm = new SelectMultipleStringKeyValue(EntornAplicacioFields.ENTORNAPLICACIOID.select,
                new EntornAplicacioQueryPath().APLICACIO().NOM().select,
                new EntornAplicacioQueryPath().ENTORN().NOM().select);

        List<StringKeyValue> list = entornAplicacioEjb.executeQuery(sm); 
        
        if (list.isEmpty()) {
            log.warn("EntornAplicacioID LIST està buit");
        } else {
        
            for (StringKeyValue skv : list) {
                log.warn("EntornAplicacioID[" + skv.getKey() + "] => " + skv.getValue());
            }
        }
        
        return list;
        
        
        
    }
    */

    /*
    SELECT DISTINCT entornaplicacioid,  versio, build, data,
    FROM ver_versio
    WHERE  data IN (SELECT  MAX(data) FROM ver_versio GROUP BY entornaplicacioid)
    */
    @Override
    public List<Versio> executeSelect(ITableManager<Versio, Long> ejb, Where where, final OrderBy[] orderBy,
            final Integer itemsPerPage, final int inici) throws I18NException {

        
        return this.versioLogicaEjb.getVersionsMajors();
        
        /*
        List<Timestamp> dates = new ArrayList<Timestamp>();
        {

            SelectGroupBy<Long> groupBy = new SelectGroupBy<Long>(VersioFields.ENTORNAPLICACIOID);

            SelectMax<Timestamp> maxDate = new SelectMax<Timestamp>(VersioFields.DATA);

            Select2Columns<Timestamp, Long> s2c;
            s2c = new Select2Columns<Timestamp, Long>(maxDate, groupBy);

            List<Select2Values<Timestamp, Long>> list = this.versioEjb.executeQuery(s2c);

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
        list = this.versioEjb.executeQuery(s4c, Where.AND(DATA.in(dates), where), orderBy);

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
*/
    }

    @Override
    public VersioFilterForm getVersioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        VersioFilterForm versioFilterForm = super.getVersioFilterForm(pagina, mav, request);
        if (versioFilterForm.isNou()) {
            versioFilterForm.setDeleteSelectedButtonVisible(false);
            versioFilterForm.setEditButtonVisible(false);
            versioFilterForm.setDeleteButtonVisible(false);
            versioFilterForm.setAddButtonVisible(false);
            versioFilterForm.setVisibleMultipleSelection(false);

            versioFilterForm.addHiddenField(ALTRESDADES);
            versioFilterForm.addHiddenField(VERSIOID);
            
            versioFilterForm.setItemsPerPage(-1); // TOTS
            
            versioFilterForm.setVisibleExportList(false);
            
            
            //versioFilterForm.addGroupByField(ENTORNAPLICACIOID);
            
            versioFilterForm.addFilterByField(ENTORNAPLICACIOID);
            
            
            

        }

        return versioFilterForm;
    }

}
