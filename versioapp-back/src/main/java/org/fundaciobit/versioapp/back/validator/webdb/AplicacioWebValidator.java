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
import org.fundaciobit.versioapp.persistence.validator.AplicacioValidator;

import org.fundaciobit.versioapp.back.form.webdb.AplicacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.versioapp.model.entity.Aplicacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AplicacioWebValidator extends AbstractWebValidator<AplicacioForm, Aplicacio>
     implements Validator, AplicacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AplicacioValidator<Aplicacio> validator = new AplicacioValidator<Aplicacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.versioapp.ejb.AplicacioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.AplicacioService aplicacioEjb;



  public AplicacioWebValidator() {
    super();    
  }
  
  @Override
  public Aplicacio getBeanOfForm(AplicacioForm form) {
    return  form.getAplicacio();
  }

  @Override
  public Class<AplicacioForm> getClassOfForm() {
    return AplicacioForm.class;
  }

  @Override
  public void validate(AplicacioForm __form, Aplicacio __bean, Errors errors) {

    WebValidationResult<AplicacioForm> wvr;
    wvr = new WebValidationResult<AplicacioForm>(errors);

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


  public void validate(AplicacioForm __form, Aplicacio __bean, Errors errors,
    WebValidationResult<AplicacioForm> wvr, boolean isNou) {

    BeanValidatorResult<Aplicacio> __vr = new BeanValidatorResult<Aplicacio>();
    validator.validate(__vr, __bean,
      isNou, aplicacioEjb);

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

  public AplicacioValidator<Aplicacio> getValidator() {
    return validator;
  }

  public void setValidator(AplicacioValidator<Aplicacio> validator) {
    this.validator = validator;
  }

}