package org.fundaciobit.versioapp.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.versioapp.model.entity.EntornAplicacio;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.versioapp.model.fields.EntornAplicacioFields;
import org.fundaciobit.versioapp.model.fields.AplicacioFields;
import org.fundaciobit.versioapp.model.fields.EntornFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EntornAplicacioValidator<I extends EntornAplicacio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements EntornAplicacioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public EntornAplicacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.versioapp.model.dao.IAplicacioManager __aplicacioManager
    ,org.fundaciobit.versioapp.model.dao.IEntornManager __entornManager
    ,org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,APLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(APLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTORNID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTORNID)));

    // Check size
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (aplicacioid, entornid)
      if (__vr.getFieldErrorCount(APLICACIOID) == 0 && __vr.getFieldErrorCount(ENTORNID) == 0) {
        java.lang.Long __aplicacioid = __target__.getAplicacioID();
        java.lang.Long __entornid = __target__.getEntornid();
        Long __count_ = null;
        try { __count_ = __entornAplicacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(APLICACIOID.equal(__aplicacioid), ENTORNID.equal(__entornid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(APLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__aplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(APLICACIOID)));
            __vr.rejectValue(ENTORNID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entornid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTORNID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (aplicacioid, entornid)
      if (__vr.getFieldErrorCount(APLICACIOID) == 0 && __vr.getFieldErrorCount(ENTORNID) == 0 && __vr.getFieldErrorCount(ENTORNAPLICACIOID) == 0) {
        java.lang.Long __aplicacioid = __target__.getAplicacioID();
        java.lang.Long __entornid = __target__.getEntornid();
        java.lang.Long __entornaplicacioid = __target__.getEntornAplicacioID();
        Long __count_ = null;
        try { __count_ = __entornAplicacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(APLICACIOID.equal(__aplicacioid), ENTORNID.equal(__entornid), ENTORNAPLICACIOID.notEqual(__entornaplicacioid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(APLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__aplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(APLICACIOID)));
            __vr.rejectValue(ENTORNID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entornid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTORNID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(APLICACIOID) == 0) {
      java.lang.Long __aplicacioid = __target__.getAplicacioID();
      Long __count_ = null;
      try { __count_ = __aplicacioManager.count(AplicacioFields.APLICACIOID.equal(__aplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(APLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("aplicacio.aplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("aplicacio.aplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__aplicacioid)));
      }
    }

    if (__vr.getFieldErrorCount(ENTORNID) == 0) {
      java.lang.Long __entornid = __target__.getEntornid();
      Long __count_ = null;
      try { __count_ = __entornManager.count(EntornFields.ENTORNID.equal(__entornid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTORNID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entorn.entorn"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entorn.entornID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entornid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}