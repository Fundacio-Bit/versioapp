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
import org.fundaciobit.versioapp.back.form.webdb.EntornForm;

import org.fundaciobit.versioapp.back.validator.webdb.EntornWebValidator;

import org.fundaciobit.versioapp.persistence.EntornJPA;
import org.fundaciobit.versioapp.model.entity.Entorn;
import org.fundaciobit.versioapp.model.fields.*;

/**
 * Controller per gestionar un Entorn
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/entorn")
@SessionAttributes(types = { EntornForm.class, EntornFilterForm.class })
public class EntornController
    extends org.fundaciobit.versioapp.back.controller.VersioAppBaseController<Entorn, java.lang.Long> implements EntornFields {

  @EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.EntornService entornEjb;

  @Autowired
  private EntornWebValidator entornWebValidator;

  @Autowired
  protected EntornRefList entornRefList;

  /**
   * Llistat de totes Entorn
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EntornFilterForm ff;
    ff = (EntornFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Entorn de forma paginada
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
    llistat(mav, request, getEntornFilterForm(pagina, mav, request));
    return mav;
  }

  public EntornFilterForm getEntornFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EntornFilterForm entornFilterForm;
    entornFilterForm = (EntornFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(entornFilterForm == null) {
      entornFilterForm = new EntornFilterForm();
      entornFilterForm.setContexte(getContextWeb());
      entornFilterForm.setEntityNameCode(getEntityNameCode());
      entornFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      entornFilterForm.setNou(true);
    } else {
      entornFilterForm.setNou(false);
    }
    entornFilterForm.setPage(pagina == null ? 1 : pagina);
    return entornFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Entorn de forma paginada
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
      @ModelAttribute EntornFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEntornFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Entorn de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Entorn> llistat(ModelAndView mav, HttpServletRequest request,
     EntornFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Entorn> entorn = processarLlistat(entornEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("entornItems", entorn);

    mav.addObject("entornFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, entorn, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, entorn);

    return entorn;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EntornFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Entorn> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EntornFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Entorn> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ENTORN_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Entorn
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEntornGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EntornForm entornForm = getEntornForm(null, false, request, mav);
    mav.addObject("entornForm" ,entornForm);
    fillReferencesForForm(entornForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EntornForm getEntornForm(EntornJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EntornForm entornForm;
    if(_jpa == null) {
      entornForm = new EntornForm(new EntornJPA(), true);
    } else {
      entornForm = new EntornForm(_jpa, false);
      entornForm.setView(__isView);
    }
    entornForm.setContexte(getContextWeb());
    entornForm.setEntityNameCode(getEntityNameCode());
    entornForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return entornForm;
  }

  public void fillReferencesForForm(EntornForm entornForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Entorn
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEntornPost(@ModelAttribute EntornForm entornForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EntornJPA entorn = entornForm.getEntorn();

    try {
      preValidate(request, entornForm, result);
      getWebValidator().validate(entornForm, result);
      postValidate(request,entornForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entorn = create(request, entorn);
        createMessageSuccess(request, "success.creation", entorn.getEntornID());
        entornForm.setEntorn(entorn);
        return getRedirectWhenCreated(request, entornForm);
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

  @RequestMapping(value = "/view/{entornID}", method = RequestMethod.GET)
  public ModelAndView veureEntornGet(@PathVariable("entornID") java.lang.Long entornID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntornGet(entornID,
        request, response, true);
  }


  protected ModelAndView editAndViewEntornGet(@PathVariable("entornID") java.lang.Long entornID,
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
    EntornJPA entorn = findByPrimaryKey(request, entornID);

    if (entorn == null) {
      createMessageWarning(request, "error.notfound", entornID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, entornID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EntornForm entornForm = getEntornForm(entorn, __isView, request, mav);
      entornForm.setView(__isView);
      if(__isView) {
        entornForm.setAllFieldsReadOnly(ALL_ENTORN_FIELDS);
        entornForm.setSaveButtonVisible(false);
        entornForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(entornForm, request, mav);
      mav.addObject("entornForm", entornForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Entorn existent
   */
  @RequestMapping(value = "/{entornID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEntornGet(@PathVariable("entornID") java.lang.Long entornID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntornGet(entornID,
        request, response, false);
  }



  /**
   * Editar un Entorn existent
   */
  @RequestMapping(value = "/{entornID}/edit", method = RequestMethod.POST)
  public String editarEntornPost(@ModelAttribute EntornForm entornForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EntornJPA entorn = entornForm.getEntorn();

    try {
      preValidate(request, entornForm, result);
      getWebValidator().validate(entornForm, result);
      postValidate(request, entornForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entorn = update(request, entorn);
        createMessageSuccess(request, "success.modification", entorn.getEntornID());
        status.setComplete();
        return getRedirectWhenModified(request, entornForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          entorn.getEntornID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, entornForm, __e);
    }

  }


  /**
   * Eliminar un Entorn existent
   */
  @RequestMapping(value = "/{entornID}/delete")
  public String eliminarEntorn(@PathVariable("entornID") java.lang.Long entornID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Entorn entorn = this.findByPrimaryKey(request, entornID);
      if (entorn == null) {
        String __msg = createMessageError(request, "error.notfound", entornID);
        return getRedirectWhenDelete(request, entornID, new Exception(__msg));
      } else {
        delete(request, entorn);
        createMessageSuccess(request, "success.deleted", entornID);
        return getRedirectWhenDelete(request, entornID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", entornID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, entornID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EntornFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEntorn(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __entornID, Throwable e) {
    java.lang.Long entornID = (java.lang.Long)__entornID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (entornID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(entornID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "entorn.entorn";
  }

  public String getEntityNameCodePlural() {
    return "entorn.entorn.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("entorn.entornID");
  }

  @InitBinder("entornFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("entornForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "entorn.entornID");
  }

  public EntornWebValidator getWebValidator() {
    return entornWebValidator;
  }


  public void setWebValidator(EntornWebValidator __val) {
    if (__val != null) {
      this.entornWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Entorn
   */
  @RequestMapping(value = "/{entornID}/cancel")
  public String cancelEntorn(@PathVariable("entornID") java.lang.Long entornID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, entornID);
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

  public void preValidate(HttpServletRequest request,EntornForm entornForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EntornForm entornForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EntornFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EntornFilterForm filterForm,  List<Entorn> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EntornForm entornForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EntornForm entornForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long entornID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long entornID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "entornFormWebDB";
  }

  public String getTileList() {
    return "entornListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Entorn_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EntornJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long entornID) throws I18NException {
    return (EntornJPA) entornEjb.findByPrimaryKey(entornID);
  }


  public EntornJPA create(HttpServletRequest request, EntornJPA entorn)
    throws I18NException, I18NValidationException {
    return (EntornJPA) entornEjb.create(entorn);
  }


  public EntornJPA update(HttpServletRequest request, EntornJPA entorn)
    throws I18NException, I18NValidationException {
    return (EntornJPA) entornEjb.update(entorn);
  }


  public void delete(HttpServletRequest request, Entorn entorn) throws I18NException {
    entornEjb.delete(entorn);
  }

} // Final de Classe

