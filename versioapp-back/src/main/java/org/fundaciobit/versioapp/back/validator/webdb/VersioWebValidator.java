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
import org.fundaciobit.versioapp.persistence.validator.VersioValidator;

import org.fundaciobit.versioapp.back.form.webdb.VersioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.versioapp.model.entity.Versio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class VersioWebValidator extends AbstractWebValidator<VersioForm, Versio>
     implements Validator, VersioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected VersioValidator<Versio> validator = new VersioValidator<Versio>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornAplicacioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.EntornAplicacioService entornAplicacioEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.versioapp.ejb.VersioService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.VersioService versioEjb;



  public VersioWebValidator() {
    super();    
  }
  
  @Override
  public Versio getBeanOfForm(VersioForm form) {
    return  form.getVersio();
  }

  @Override
  public Class<VersioForm> getClassOfForm() {
    return VersioForm.class;
  }

  @Override
  public void validate(VersioForm __form, Versio __bean, Errors errors) {

    WebValidationResult<VersioForm> wvr;
    wvr = new WebValidationResult<VersioForm>(errors);

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


  public void validate(VersioForm __form, Versio __bean, Errors errors,
    WebValidationResult<VersioForm> wvr, boolean isNou) {

    BeanValidatorResult<Versio> __vr = new BeanValidatorResult<Versio>();
    validator.validate(__vr, __bean,
      isNou, entornAplicacioEjb, versioEjb);

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

  public VersioValidator<Versio> getValidator() {
    return validator;
  }

  public void setValidator(VersioValidator<Versio> validator) {
    this.validator = validator;
  }

}