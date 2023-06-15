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
import org.fundaciobit.versioapp.back.form.webdb.VersioForm;

import org.fundaciobit.versioapp.back.validator.webdb.VersioWebValidator;

import org.fundaciobit.versioapp.persistence.VersioJPA;
import org.fundaciobit.versioapp.model.entity.Versio;
import org.fundaciobit.versioapp.model.fields.*;

/**
 * Controller per gestionar un Versio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/versio")
@SessionAttributes(types = { VersioForm.class, VersioFilterForm.class })
public class VersioController
    extends org.fundaciobit.versioapp.back.controller.VersioAppBaseController<Versio, java.lang.Long> implements VersioFields {

  @EJB(mappedName = org.fundaciobit.versioapp.ejb.VersioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.VersioService versioEjb;

  @Autowired
  private VersioWebValidator versioWebValidator;

  @Autowired
  protected VersioRefList versioRefList;

  // References 
  @Autowired
  protected EntornAplicacioRefList entornAplicacioRefList;

  /**
   * Llistat de totes Versio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    VersioFilterForm ff;
    ff = (VersioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Versio de forma paginada
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
    llistat(mav, request, getVersioFilterForm(pagina, mav, request));
    return mav;
  }

  public VersioFilterForm getVersioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    VersioFilterForm versioFilterForm;
    versioFilterForm = (VersioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(versioFilterForm == null) {
      versioFilterForm = new VersioFilterForm();
      versioFilterForm.setContexte(getContextWeb());
      versioFilterForm.setEntityNameCode(getEntityNameCode());
      versioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      versioFilterForm.setNou(true);
    } else {
      versioFilterForm.setNou(false);
    }
    versioFilterForm.setPage(pagina == null ? 1 : pagina);
    return versioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Versio de forma paginada
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
      @ModelAttribute VersioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getVersioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Versio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Versio> llistat(ModelAndView mav, HttpServletRequest request,
     VersioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Versio> versio = processarLlistat(versioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("versioItems", versio);

    mav.addObject("versioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, versio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, versio);

    return versio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(VersioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Versio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entornAplicacioID
    {
      _listSKV = getReferenceListForEntornAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntornAplicacioForEntornAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTORNAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTORNAPLICACIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    VersioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Versio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_VERSIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTORNAPLICACIOID, filterForm.getMapOfEntornAplicacioForEntornAplicacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Versio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearVersioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    VersioForm versioForm = getVersioForm(null, false, request, mav);
    mav.addObject("versioForm" ,versioForm);
    fillReferencesForForm(versioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public VersioForm getVersioForm(VersioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    VersioForm versioForm;
    if(_jpa == null) {
      versioForm = new VersioForm(new VersioJPA(), true);
    } else {
      versioForm = new VersioForm(_jpa, false);
      versioForm.setView(__isView);
    }
    versioForm.setContexte(getContextWeb());
    versioForm.setEntityNameCode(getEntityNameCode());
    versioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return versioForm;
  }

  public void fillReferencesForForm(VersioForm versioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (versioForm.getListOfEntornAplicacioForEntornAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntornAplicacioID(request, mav, versioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      versioForm.setListOfEntornAplicacioForEntornAplicacioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Versio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearVersioPost(@ModelAttribute VersioForm versioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    VersioJPA versio = versioForm.getVersio();

    try {
      preValidate(request, versioForm, result);
      getWebValidator().validate(versioForm, result);
      postValidate(request,versioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        versio = create(request, versio);
        createMessageSuccess(request, "success.creation", versio.getVersioID());
        versioForm.setVersio(versio);
        return getRedirectWhenCreated(request, versioForm);
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

  @RequestMapping(value = "/view/{versioID}", method = RequestMethod.GET)
  public ModelAndView veureVersioGet(@PathVariable("versioID") java.lang.Long versioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewVersioGet(versioID,
        request, response, true);
  }


  protected ModelAndView editAndViewVersioGet(@PathVariable("versioID") java.lang.Long versioID,
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
    VersioJPA versio = findByPrimaryKey(request, versioID);

    if (versio == null) {
      createMessageWarning(request, "error.notfound", versioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, versioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      VersioForm versioForm = getVersioForm(versio, __isView, request, mav);
      versioForm.setView(__isView);
      if(__isView) {
        versioForm.setAllFieldsReadOnly(ALL_VERSIO_FIELDS);
        versioForm.setSaveButtonVisible(false);
        versioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(versioForm, request, mav);
      mav.addObject("versioForm", versioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Versio existent
   */
  @RequestMapping(value = "/{versioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarVersioGet(@PathVariable("versioID") java.lang.Long versioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewVersioGet(versioID,
        request, response, false);
  }



  /**
   * Editar un Versio existent
   */
  @RequestMapping(value = "/{versioID}/edit", method = RequestMethod.POST)
  public String editarVersioPost(@ModelAttribute VersioForm versioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    VersioJPA versio = versioForm.getVersio();

    try {
      preValidate(request, versioForm, result);
      getWebValidator().validate(versioForm, result);
      postValidate(request, versioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        versio = update(request, versio);
        createMessageSuccess(request, "success.modification", versio.getVersioID());
        status.setComplete();
        return getRedirectWhenModified(request, versioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          versio.getVersioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, versioForm, __e);
    }

  }


  /**
   * Eliminar un Versio existent
   */
  @RequestMapping(value = "/{versioID}/delete")
  public String eliminarVersio(@PathVariable("versioID") java.lang.Long versioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Versio versio = this.findByPrimaryKey(request, versioID);
      if (versio == null) {
        String __msg = createMessageError(request, "error.notfound", versioID);
        return getRedirectWhenDelete(request, versioID, new Exception(__msg));
      } else {
        delete(request, versio);
        createMessageSuccess(request, "success.deleted", versioID);
        return getRedirectWhenDelete(request, versioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", versioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, versioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute VersioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarVersio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __versioID, Throwable e) {
    java.lang.Long versioID = (java.lang.Long)__versioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (versioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(versioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "versio.versio";
  }

  public String getEntityNameCodePlural() {
    return "versio.versio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("versio.versioID");
  }

  @InitBinder("versioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("versioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "versio.versioID");
  }

  public VersioWebValidator getWebValidator() {
    return versioWebValidator;
  }


  public void setWebValidator(VersioWebValidator __val) {
    if (__val != null) {
      this.versioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Versio
   */
  @RequestMapping(value = "/{versioID}/cancel")
  public String cancelVersio(@PathVariable("versioID") java.lang.Long versioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, versioID);
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


  public List<StringKeyValue> getReferenceListForEntornAplicacioID(HttpServletRequest request,
       ModelAndView mav, VersioForm versioForm, Where where)  throws I18NException {
    if (versioForm.isHiddenField(ENTORNAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (versioForm.isReadOnlyField(ENTORNAPLICACIOID)) {
      _where = EntornAplicacioFields.ENTORNAPLICACIOID.equal(versioForm.getVersio().getEntornAplicacioID());
    }
    return getReferenceListForEntornAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntornAplicacioID(HttpServletRequest request,
       ModelAndView mav, VersioFilterForm versioFilterForm,
       List<Versio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (versioFilterForm.isHiddenField(ENTORNAPLICACIOID)
       && !versioFilterForm.isGroupByField(ENTORNAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTORNAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Versio _item : list) {
        _pkList.add(_item.getEntornAplicacioID());
        }
        _w = EntornAplicacioFields.ENTORNAPLICACIOID.in(_pkList);
      }
    return getReferenceListForEntornAplicacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntornAplicacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entornAplicacioRefList.getReferenceList(EntornAplicacioFields.ENTORNAPLICACIOID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,VersioForm versioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,VersioForm versioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, VersioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, VersioFilterForm filterForm,  List<Versio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, VersioForm versioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, VersioForm versioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long versioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long versioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "versioFormWebDB";
  }

  public String getTileList() {
    return "versioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Versio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public VersioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long versioID) throws I18NException {
    return (VersioJPA) versioEjb.findByPrimaryKey(versioID);
  }


  public VersioJPA create(HttpServletRequest request, VersioJPA versio)
    throws I18NException, I18NValidationException {
    return (VersioJPA) versioEjb.create(versio);
  }


  public VersioJPA update(HttpServletRequest request, VersioJPA versio)
    throws I18NException, I18NValidationException {
    return (VersioJPA) versioEjb.update(versio);
  }


  public void delete(HttpServletRequest request, Versio versio) throws I18NException {
    versioEjb.delete(versio);
  }

} // Final de Classe

