package org.fundaciobit.versioapp.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.versioapp.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.versioapp.persistence.validator.EntornAplicacioValidator;

import org.fundaciobit.versioapp.back.form.webdb.EntornAplicacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.versioapp.model.entity.EntornAplicacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntornAplicacioWebValidator extends AbstractWebValidator<EntornAplicacioForm, EntornAplicacio>
     implements Validator, EntornAplicacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EntornAplicacioValidator<EntornAplicacio> validator = new EntornAplicacioValidator<EntornAplicacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.versioapp.ejb.AplicacioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.AplicacioService aplicacioEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.EntornService entornEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornAplicacioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.EntornAplicacioService entornAplicacioEjb;



  public EntornAplicacioWebValidator() {
    super();    
  }
  
  @Override
  public EntornAplicacio getBeanOfForm(EntornAplicacioForm form) {
    return  form.getEntornAplicacio();
  }

  @Override
  public Class<EntornAplicacioForm> getClassOfForm() {
    return EntornAplicacioForm.class;
  }

  @Override
  public void validate(EntornAplicacioForm __form, EntornAplicacio __bean, Errors errors) {

    WebValidationResult<EntornAplicacioForm> wvr;
    wvr = new WebValidationResult<EntornAplicacioForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(EntornAplicacioForm __form, EntornAplicacio __bean, Errors errors,
    WebValidationResult<EntornAplicacioForm> wvr, boolean isNou) {

    BeanValidatorResult<EntornAplicacio> __vr = new BeanValidatorResult<EntornAplicacio>();
    validator.validate(__vr, __bean,
      isNou, aplicacioEjb, entornEjb, entornAplicacioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EntornAplicacioValidator<EntornAplicacio> getValidator() {
    return validator;
  }

  public void setValidator(EntornAplicacioValidator<EntornAplicacio> validator) {
    this.validator = validator;
  }

}