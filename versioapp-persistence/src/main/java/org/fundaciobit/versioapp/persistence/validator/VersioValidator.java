package org.fundaciobit.versioapp.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.versioapp.model.entity.Versio;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.versioapp.model.fields.VersioFields;
import org.fundaciobit.versioapp.model.fields.EntornAplicacioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class VersioValidator<I extends Versio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements VersioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public VersioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager
    ,org.fundaciobit.versioapp.model.dao.IVersioManager __versioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ENTORNAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTORNAPLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VERSIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VERSIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATA)));

    // Check size
    if (__vr.getFieldErrorCount(VERSIO) == 0) {
      java.lang.String __versio = __target__.getVersio();
      if (__versio!= null && __versio.length() > 100) {
        __vr.rejectValue(VERSIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VERSIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(BUILD) == 0) {
      java.lang.String __build = __target__.getBuild();
      if (__build!= null && __build.length() > 100) {
        __vr.rejectValue(BUILD, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(BUILD)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(ALTRESDADES) == 0) {
      java.lang.String __altresdades = __target__.getAltresDades();
      if (__altresdades!= null && __altresdades.length() > 4096) {
        __vr.rejectValue(ALTRESDADES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ALTRESDADES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(4096)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ENTORNAPLICACIOID) == 0) {
      java.lang.Long __entornaplicacioid = __target__.getEntornAplicacioID();
      Long __count_ = null;
      try { __count_ = __entornAplicacioManager.count(EntornAplicacioFields.ENTORNAPLICACIOID.equal(__entornaplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTORNAPLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entornAplicacio.entornAplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entornAplicacio.entornAplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entornaplicacioid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}