package org.fundaciobit.versioapp.back.controller.webdb;

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
import org.fundaciobit.versioapp.back.form.webdb.AplicacioForm;

import org.fundaciobit.versioapp.back.validator.webdb.AplicacioWebValidator;

import org.fundaciobit.versioapp.persistence.AplicacioJPA;
import org.fundaciobit.versioapp.model.entity.Aplicacio;
import org.fundaciobit.versioapp.model.fields.*;

/**
 * Controller per gestionar un Aplicacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/aplicacio")
@SessionAttributes(types = { AplicacioForm.class, AplicacioFilterForm.class })
public class AplicacioController
    extends org.fundaciobit.versioapp.back.controller.VersioAppBaseController<Aplicacio, java.lang.Long> implements AplicacioFields {

  @EJB(mappedName = org.fundaciobit.versioapp.ejb.AplicacioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.AplicacioService aplicacioEjb;

  @Autowired
  private AplicacioWebValidator aplicacioWebValidator;

  @Autowired
  protected AplicacioRefList aplicacioRefList;

  /**
   * Llistat de totes Aplicacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AplicacioFilterForm ff;
    ff = (AplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Aplicacio de forma paginada
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
    llistat(mav, request, getAplicacioFilterForm(pagina, mav, request));
    return mav;
  }

  public AplicacioFilterForm getAplicacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AplicacioFilterForm aplicacioFilterForm;
    aplicacioFilterForm = (AplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(aplicacioFilterForm == null) {
      aplicacioFilterForm = new AplicacioFilterForm();
      aplicacioFilterForm.setContexte(getContextWeb());
      aplicacioFilterForm.setEntityNameCode(getEntityNameCode());
      aplicacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      aplicacioFilterForm.setNou(true);
    } else {
      aplicacioFilterForm.setNou(false);
    }
    aplicacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return aplicacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Aplicacio de forma paginada
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
      @ModelAttribute AplicacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAplicacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Aplicacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Aplicacio> llistat(ModelAndView mav, HttpServletRequest request,
     AplicacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Aplicacio> aplicacio = processarLlistat(aplicacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("aplicacioItems", aplicacio);

    mav.addObject("aplicacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, aplicacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, aplicacio);

    return aplicacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AplicacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Aplicacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AplicacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Aplicacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_APLICACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Aplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAplicacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AplicacioForm aplicacioForm = getAplicacioForm(null, false, request, mav);
    mav.addObject("aplicacioForm" ,aplicacioForm);
    fillReferencesForForm(aplicacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AplicacioForm getAplicacioForm(AplicacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AplicacioForm aplicacioForm;
    if(_jpa == null) {
      aplicacioForm = new AplicacioForm(new AplicacioJPA(), true);
    } else {
      aplicacioForm = new AplicacioForm(_jpa, false);
      aplicacioForm.setView(__isView);
    }
    aplicacioForm.setContexte(getContextWeb());
    aplicacioForm.setEntityNameCode(getEntityNameCode());
    aplicacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return aplicacioForm;
  }

  public void fillReferencesForForm(AplicacioForm aplicacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Aplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAplicacioPost(@ModelAttribute AplicacioForm aplicacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AplicacioJPA aplicacio = aplicacioForm.getAplicacio();

    try {
      preValidate(request, aplicacioForm, result);
      getWebValidator().validate(aplicacioForm, result);
      postValidate(request,aplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        aplicacio = create(request, aplicacio);
        createMessageSuccess(request, "success.creation", aplicacio.getAplicacioID());
        aplicacioForm.setAplicacio(aplicacio);
        return getRedirectWhenCreated(request, aplicacioForm);
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

  @RequestMapping(value = "/view/{aplicacioID}", method = RequestMethod.GET)
  public ModelAndView veureAplicacioGet(@PathVariable("aplicacioID") java.lang.Long aplicacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAplicacioGet(aplicacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewAplicacioGet(@PathVariable("aplicacioID") java.lang.Long aplicacioID,
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
    AplicacioJPA aplicacio = findByPrimaryKey(request, aplicacioID);

    if (aplicacio == null) {
      createMessageWarning(request, "error.notfound", aplicacioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, aplicacioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AplicacioForm aplicacioForm = getAplicacioForm(aplicacio, __isView, request, mav);
      aplicacioForm.setView(__isView);
      if(__isView) {
        aplicacioForm.setAllFieldsReadOnly(ALL_APLICACIO_FIELDS);
        aplicacioForm.setSaveButtonVisible(false);
        aplicacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(aplicacioForm, request, mav);
      mav.addObject("aplicacioForm", aplicacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Aplicacio existent
   */
  @RequestMapping(value = "/{aplicacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAplicacioGet(@PathVariable("aplicacioID") java.lang.Long aplicacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAplicacioGet(aplicacioID,
        request, response, false);
  }



  /**
   * Editar un Aplicacio existent
   */
  @RequestMapping(value = "/{aplicacioID}/edit", method = RequestMethod.POST)
  public String editarAplicacioPost(@ModelAttribute AplicacioForm aplicacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AplicacioJPA aplicacio = aplicacioForm.getAplicacio();

    try {
      preValidate(request, aplicacioForm, result);
      getWebValidator().validate(aplicacioForm, result);
      postValidate(request, aplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        aplicacio = update(request, aplicacio);
        createMessageSuccess(request, "success.modification", aplicacio.getAplicacioID());
        status.setComplete();
        return getRedirectWhenModified(request, aplicacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          aplicacio.getAplicacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, aplicacioForm, __e);
    }

  }


  /**
   * Eliminar un Aplicacio existent
   */
  @RequestMapping(value = "/{aplicacioID}/delete")
  public String eliminarAplicacio(@PathVariable("aplicacioID") java.lang.Long aplicacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Aplicacio aplicacio = this.findByPrimaryKey(request, aplicacioID);
      if (aplicacio == null) {
        String __msg = createMessageError(request, "error.notfound", aplicacioID);
        return getRedirectWhenDelete(request, aplicacioID, new Exception(__msg));
      } else {
        delete(request, aplicacio);
        createMessageSuccess(request, "success.deleted", aplicacioID);
        return getRedirectWhenDelete(request, aplicacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", aplicacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, aplicacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AplicacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAplicacio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __aplicacioID, Throwable e) {
    java.lang.Long aplicacioID = (java.lang.Long)__aplicacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (aplicacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(aplicacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "aplicacio.aplicacio";
  }

  public String getEntityNameCodePlural() {
    return "aplicacio.aplicacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("aplicacio.aplicacioID");
  }

  @InitBinder("aplicacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("aplicacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "aplicacio.aplicacioID");
  }

  public AplicacioWebValidator getWebValidator() {
    return aplicacioWebValidator;
  }


  public void setWebValidator(AplicacioWebValidator __val) {
    if (__val != null) {
      this.aplicacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Aplicacio
   */
  @RequestMapping(value = "/{aplicacioID}/cancel")
  public String cancelAplicacio(@PathVariable("aplicacioID") java.lang.Long aplicacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, aplicacioID);
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,AplicacioForm aplicacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AplicacioForm aplicacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AplicacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AplicacioFilterForm filterForm,  List<Aplicacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AplicacioForm aplicacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AplicacioForm aplicacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long aplicacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long aplicacioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "aplicacioFormWebDB";
  }

  public String getTileList() {
    return "aplicacioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Aplicacio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AplicacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long aplicacioID) throws I18NException {
    return (AplicacioJPA) aplicacioEjb.findByPrimaryKey(aplicacioID);
  }


  public AplicacioJPA create(HttpServletRequest request, AplicacioJPA aplicacio)
    throws I18NException, I18NValidationException {
    return (AplicacioJPA) aplicacioEjb.create(aplicacio);
  }


  public AplicacioJPA update(HttpServletRequest request, AplicacioJPA aplicacio)
    throws I18NException, I18NValidationException {
    return (AplicacioJPA) aplicacioEjb.update(aplicacio);
  }


  public void delete(HttpServletRequest request, Aplicacio aplicacio) throws I18NException {
    aplicacioEjb.delete(aplicacio);
  }

} // Final de Classe

