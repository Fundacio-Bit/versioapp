package org.fundaciobit.versioapp.persistence.validator;

import org.fundaciobit.versioapp.persistence.AplicacioJPA;
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
public class AplicacioBeanValidator 
      extends AbstractBeanValidator<AplicacioJPA> {


  // EJB's
  protected final org.fundaciobit.versioapp.model.dao.IAplicacioManager __aplicacioManager;


  public final AplicacioValidator<AplicacioJPA> _validator;


  public AplicacioBeanValidator(org.fundaciobit.versioapp.model.dao.IAplicacioManager __aplicacioManager) { 
    this.__aplicacioManager = __aplicacioManager;
    _validator = new AplicacioValidator<AplicacioJPA>();
  }

  public AplicacioBeanValidator(AplicacioValidator<AplicacioJPA> _validator,
     org.fundaciobit.versioapp.model.dao.IAplicacioManager __aplicacioManager) {
    this.__aplicacioManager = __aplicacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AplicacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AplicacioJPA> _bvr_ = new BeanValidatorResult<AplicacioJPA>();
    _validator.validate(_bvr_, target, isNou, __aplicacioManager);
    return _bvr_.getErrors();
  }
}
