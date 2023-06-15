package org.fundaciobit.versioapp.persistence.validator;

import org.fundaciobit.versioapp.persistence.EntornAplicacioJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EntornAplicacioBeanValidator 
      extends AbstractBeanValidator<EntornAplicacioJPA> {


  // EJB's
  protected final org.fundaciobit.versioapp.model.dao.IAplicacioManager __aplicacioManager;

  protected final org.fundaciobit.versioapp.model.dao.IEntornManager __entornManager;

  protected final org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager;


  public final EntornAplicacioValidator<EntornAplicacioJPA> _validator;


  public EntornAplicacioBeanValidator(org.fundaciobit.versioapp.model.dao.IAplicacioManager __aplicacioManager,
     org.fundaciobit.versioapp.model.dao.IEntornManager __entornManager,
     org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager) { 
    this.__aplicacioManager = __aplicacioManager;
    this.__entornManager = __entornManager;
    this.__entornAplicacioManager = __entornAplicacioManager;
    _validator = new EntornAplicacioValidator<EntornAplicacioJPA>();
  }

  public EntornAplicacioBeanValidator(EntornAplicacioValidator<EntornAplicacioJPA> _validator,
     org.fundaciobit.versioapp.model.dao.IAplicacioManager __aplicacioManager,
     org.fundaciobit.versioapp.model.dao.IEntornManager __entornManager,
     org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager) {
    this.__aplicacioManager = __aplicacioManager;
    this.__entornManager = __entornManager;
    this.__entornAplicacioManager = __entornAplicacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EntornAplicacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EntornAplicacioJPA> _bvr_ = new BeanValidatorResult<EntornAplicacioJPA>();
    _validator.validate(_bvr_, target, isNou, __aplicacioManager, __entornManager, __entornAplicacioManager);
    return _bvr_.getErrors();
  }
}
