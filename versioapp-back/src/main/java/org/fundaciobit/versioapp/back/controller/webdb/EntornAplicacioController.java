package org.fundaciobit.versioapp.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.fundaciobit.versioapp.back.form.webdb.*;
import org.fundaciobit.versioapp.back.form.webdb.EntornAplicacioForm;

import org.fundaciobit.versioapp.back.validator.webdb.EntornAplicacioWebValidator;

import org.fundaciobit.versioapp.persistence.EntornAplicacioJPA;
import org.fundaciobit.versioapp.model.entity.EntornAplicacio;
import org.fundaciobit.versioapp.model.fields.*;

/**
 * Controller per gestionar un EntornAplicacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/entornAplicacio")
@SessionAttributes(types = { EntornAplicacioForm.class, EntornAplicacioFilterForm.class })
public class EntornAplicacioController
    extends org.fundaciobit.versioapp.back.controller.VersioAppBaseController<EntornAplicacio, java.lang.Long> implements EntornAplicacioFields {

  @EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornAplicacioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.EntornAplicacioService entornAplicacioEjb;

  @Autowired
  private EntornAplicacioWebValidator entornAplicacioWebValidator;

  @Autowired
  protected EntornAplicacioRefList entornAplicacioRefList;

  // References 
  @Autowired
  protected AplicacioRefList aplicacioRefList;

  // References 
  @Autowired
  protected EntornRefList entornRefList;

  /**
   * Llistat de totes EntornAplicacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EntornAplicacioFilterForm ff;
    ff = (EntornAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar EntornAplicacio de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getEntornAplicacioFilterForm(pagina, mav, request));
    return mav;
  }

  public EntornAplicacioFilterForm getEntornAplicacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EntornAplicacioFilterForm entornAplicacioFilterForm;
    entornAplicacioFilterForm = (EntornAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(entornAplicacioFilterForm == null) {
      entornAplicacioFilterForm = new EntornAplicacioFilterForm();
      entornAplicacioFilterForm.setContexte(getContextWeb());
      entornAplicacioFilterForm.setEntityNameCode(getEntityNameCode());
      entornAplicacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      entornAplicacioFilterForm.setNou(true);
    } else {
      entornAplicacioFilterForm.setNou(false);
    }
    entornAplicacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return entornAplicacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar EntornAplicacio de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute EntornAplicacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEntornAplicacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de EntornAplicacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<EntornAplicacio> llistat(ModelAndView mav, HttpServletRequest request,
     EntornAplicacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<EntornAplicacio> entornAplicacio = processarLlistat(entornAplicacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("entornAplicacioItems", entornAplicacio);

    mav.addObject("entornAplicacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, entornAplicacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, entornAplicacio);

    return entornAplicacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EntornAplicacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<EntornAplicacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field aplicacioID
    {
      _listSKV = getReferenceListForAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfAplicacioForAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(APLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, APLICACIOID, false);
      };
    }

    // Field entornid
    {
      _listSKV = getReferenceListForEntornid(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntornForEntornid(_tmp);
      if (filterForm.getGroupByFields().contains(ENTORNID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTORNID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EntornAplicacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<EntornAplicacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ENTORNAPLICACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(APLICACIOID, filterForm.getMapOfAplicacioForAplicacioID());
    __mapping.put(ENTORNID, filterForm.getMapOfEntornForEntornid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou EntornAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEntornAplicacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EntornAplicacioForm entornAplicacioForm = getEntornAplicacioForm(null, false, request, mav);
    mav.addObject("entornAplicacioForm" ,entornAplicacioForm);
    fillReferencesForForm(entornAplicacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EntornAplicacioForm getEntornAplicacioForm(EntornAplicacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EntornAplicacioForm entornAplicacioForm;
    if(_jpa == null) {
      entornAplicacioForm = new EntornAplicacioForm(new EntornAplicacioJPA(), true);
    } else {
      entornAplicacioForm = new EntornAplicacioForm(_jpa, false);
      entornAplicacioForm.setView(__isView);
    }
    entornAplicacioForm.setContexte(getContextWeb());
    entornAplicacioForm.setEntityNameCode(getEntityNameCode());
    entornAplicacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return entornAplicacioForm;
  }

  public void fillReferencesForForm(EntornAplicacioForm entornAplicacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (entornAplicacioForm.getListOfAplicacioForAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAplicacioID(request, mav, entornAplicacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      entornAplicacioForm.setListOfAplicacioForAplicacioID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (entornAplicacioForm.getListOfEntornForEntornid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntornid(request, mav, entornAplicacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      entornAplicacioForm.setListOfEntornForEntornid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou EntornAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEntornAplicacioPost(@ModelAttribute EntornAplicacioForm entornAplicacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EntornAplicacioJPA entornAplicacio = entornAplicacioForm.getEntornAplicacio();

    try {
      preValidate(request, entornAplicacioForm, result);
      getWebValidator().validate(entornAplicacioForm, result);
      postValidate(request,entornAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entornAplicacio = create(request, entornAplicacio);
        createMessageSuccess(request, "success.creation", entornAplicacio.getEntornAplicacioID());
        entornAplicacioForm.setEntornAplicacio(entornAplicacio);
        return getRedirectWhenCreated(request, entornAplicacioForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{entornAplicacioID}", method = RequestMethod.GET)
  public ModelAndView veureEntornAplicacioGet(@PathVariable("entornAplicacioID") java.lang.Long entornAplicacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntornAplicacioGet(entornAplicacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewEntornAplicacioGet(@PathVariable("entornAplicacioID") java.lang.Long entornAplicacioID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    EntornAplicacioJPA entornAplicacio = findByPrimaryKey(request, entornAplicacioID);

    if (entornAplicacio == null) {
      createMessageWarning(request, "error.notfound", entornAplicacioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, entornAplicacioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EntornAplicacioForm entornAplicacioForm = getEntornAplicacioForm(entornAplicacio, __isView, request, mav);
      entornAplicacioForm.setView(__isView);
      if(__isView) {
        entornAplicacioForm.setAllFieldsReadOnly(ALL_ENTORNAPLICACIO_FIELDS);
        entornAplicacioForm.setSaveButtonVisible(false);
        entornAplicacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(entornAplicacioForm, request, mav);
      mav.addObject("entornAplicacioForm", entornAplicacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un EntornAplicacio existent
   */
  @RequestMapping(value = "/{entornAplicacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEntornAplicacioGet(@PathVariable("entornAplicacioID") java.lang.Long entornAplicacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntornAplicacioGet(entornAplicacioID,
        request, response, false);
  }



  /**
   * Editar un EntornAplicacio existent
   */
  @RequestMapping(value = "/{entornAplicacioID}/edit", method = RequestMethod.POST)
  public String editarEntornAplicacioPost(@ModelAttribute EntornAplicacioForm entornAplicacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EntornAplicacioJPA entornAplicacio = entornAplicacioForm.getEntornAplicacio();

    try {
      preValidate(request, entornAplicacioForm, result);
      getWebValidator().validate(entornAplicacioForm, result);
      postValidate(request, entornAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entornAplicacio = update(request, entornAplicacio);
        createMessageSuccess(request, "success.modification", entornAplicacio.getEntornAplicacioID());
        status.setComplete();
        return getRedirectWhenModified(request, entornAplicacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          entornAplicacio.getEntornAplicacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, entornAplicacioForm, __e);
    }

  }


  /**
   * Eliminar un EntornAplicacio existent
   */
  @RequestMapping(value = "/{entornAplicacioID}/delete")
  public String eliminarEntornAplicacio(@PathVariable("entornAplicacioID") java.lang.Long entornAplicacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      EntornAplicacio entornAplicacio = this.findByPrimaryKey(request, entornAplicacioID);
      if (entornAplicacio == null) {
        String __msg = createMessageError(request, "error.notfound", entornAplicacioID);
        return getRedirectWhenDelete(request, entornAplicacioID, new Exception(__msg));
      } else {
        delete(request, entornAplicacio);
        createMessageSuccess(request, "success.deleted", entornAplicacioID);
        return getRedirectWhenDelete(request, entornAplicacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", entornAplicacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, entornAplicacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EntornAplicacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEntornAplicacio(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __entornAplicacioID, Throwable e) {
    java.lang.Long entornAplicacioID = (java.lang.Long)__entornAplicacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (entornAplicacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(entornAplicacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "entornAplicacio.entornAplicacio";
  }

  public String getEntityNameCodePlural() {
    return "entornAplicacio.entornAplicacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("entornAplicacio.entornAplicacioID");
  }

  @InitBinder("entornAplicacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("entornAplicacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "entornAplicacio.entornAplicacioID");
  }

  public EntornAplicacioWebValidator getWebValidator() {
    return entornAplicacioWebValidator;
  }


  public void setWebValidator(EntornAplicacioWebValidator __val) {
    if (__val != null) {
      this.entornAplicacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de EntornAplicacio
   */
  @RequestMapping(value = "/{entornAplicacioID}/cancel")
  public String cancelEntornAplicacio(@PathVariable("entornAplicacioID") java.lang.Long entornAplicacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, entornAplicacioID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }


  public List<StringKeyValue> getReferenceListForAplicacioID(HttpServletRequest request,
       ModelAndView mav, EntornAplicacioForm entornAplicacioForm, Where where)  throws I18NException {
    if (entornAplicacioForm.isHiddenField(APLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (entornAplicacioForm.isReadOnlyField(APLICACIOID)) {
      _where = AplicacioFields.APLICACIOID.equal(entornAplicacioForm.getEntornAplicacio().getAplicacioID());
    }
    return getReferenceListForAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForAplicacioID(HttpServletRequest request,
       ModelAndView mav, EntornAplicacioFilterForm entornAplicacioFilterForm,
       List<EntornAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entornAplicacioFilterForm.isHiddenField(APLICACIOID)
       && !entornAplicacioFilterForm.isGroupByField(APLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(APLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (EntornAplicacio _item : list) {
        _pkList.add(_item.getAplicacioID());
        }
        _w = AplicacioFields.APLICACIOID.in(_pkList);
      }
    return getReferenceListForAplicacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAplicacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return aplicacioRefList.getReferenceList(AplicacioFields.APLICACIOID, where );
  }


  public List<StringKeyValue> getReferenceListForEntornid(HttpServletRequest request,
       ModelAndView mav, EntornAplicacioForm entornAplicacioForm, Where where)  throws I18NException {
    if (entornAplicacioForm.isHiddenField(ENTORNID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (entornAplicacioForm.isReadOnlyField(ENTORNID)) {
      _where = EntornFields.ENTORNID.equal(entornAplicacioForm.getEntornAplicacio().getEntornid());
    }
    return getReferenceListForEntornid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntornid(HttpServletRequest request,
       ModelAndView mav, EntornAplicacioFilterForm entornAplicacioFilterForm,
       List<EntornAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entornAplicacioFilterForm.isHiddenField(ENTORNID)
       && !entornAplicacioFilterForm.isGroupByField(ENTORNID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTORNID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (EntornAplicacio _item : list) {
        _pkList.add(_item.getEntornid());
        }
        _w = EntornFields.ENTORNID.in(_pkList);
      }
    return getReferenceListForEntornid(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntornid(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entornRefList.getReferenceList(EntornFields.ENTORNID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,EntornAplicacioForm entornAplicacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EntornAplicacioForm entornAplicacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EntornAplicacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EntornAplicacioFilterForm filterForm,  List<EntornAplicacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EntornAplicacioForm entornAplicacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EntornAplicacioForm entornAplicacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long entornAplicacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long entornAplicacioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "entornAplicacioFormWebDB";
  }

  public String getTileList() {
    return "entornAplicacioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "EntornAplicacio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EntornAplicacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long entornAplicacioID) throws I18NException {
    return (EntornAplicacioJPA) entornAplicacioEjb.findByPrimaryKey(entornAplicacioID);
  }


  public EntornAplicacioJPA create(HttpServletRequest request, EntornAplicacioJPA entornAplicacio)
    throws I18NException, I18NValidationException {
    return (EntornAplicacioJPA) entornAplicacioEjb.create(entornAplicacio);
  }


  public EntornAplicacioJPA update(HttpServletRequest request, EntornAplicacioJPA entornAplicacio)
    throws I18NException, I18NValidationException {
    return (EntornAplicacioJPA) entornAplicacioEjb.update(entornAplicacio);
  }


  public void delete(HttpServletRequest request, EntornAplicacio entornAplicacio) throws I18NException {
    entornAplicacioEjb.delete(entornAplicacio);
  }

} // Final de Classe

