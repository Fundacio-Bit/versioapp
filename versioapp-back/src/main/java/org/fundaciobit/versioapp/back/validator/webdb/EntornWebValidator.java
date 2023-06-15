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
import org.fundaciobit.versioapp.persistence.validator.EntornValidator;

import org.fundaciobit.versioapp.back.form.webdb.EntornForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.versioapp.model.entity.Entorn;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntornWebValidator extends AbstractWebValidator<EntornForm, Entorn>
     implements Validator, EntornFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EntornValidator<Entorn> validator = new EntornValidator<Entorn>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.versioapp.ejb.EntornService.JNDI_NAME)
  protected org.fundaciobit.versioapp.ejb.EntornService entornEjb;



  public EntornWebValidator() {
    super();    
  }
  
  @Override
  public Entorn getBeanOfForm(EntornForm form) {
    return  form.getEntorn();
  }

  @Override
  public Class<EntornForm> getClassOfForm() {
    return EntornForm.class;
  }

  @Override
  public void validate(EntornForm __form, Entorn __bean, Errors errors) {

    WebValidationResult<EntornForm> wvr;
    wvr = new WebValidationResult<EntornForm>(errors);

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


  public void validate(EntornForm __form, Entorn __bean, Errors errors,
    WebValidationResult<EntornForm> wvr, boolean isNou) {

    BeanValidatorResult<Entorn> __vr = new BeanValidatorResult<Entorn>();
    validator.validate(__vr, __bean,
      isNou, entornEjb);

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

  public EntornValidator<Entorn> getValidator() {
    return validator;
  }

  public void setValidator(EntornValidator<Entorn> validator) {
    this.validator = validator;
  }

}