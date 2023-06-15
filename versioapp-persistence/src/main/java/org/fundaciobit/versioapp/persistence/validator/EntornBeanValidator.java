package org.fundaciobit.versioapp.persistence.validator;

import org.fundaciobit.versioapp.persistence.EntornJPA;
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
public class EntornBeanValidator 
      extends AbstractBeanValidator<EntornJPA> {


  // EJB's
  protected final org.fundaciobit.versioapp.model.dao.IEntornManager __entornManager;


  public final EntornValidator<EntornJPA> _validator;


  public EntornBeanValidator(org.fundaciobit.versioapp.model.dao.IEntornManager __entornManager) { 
    this.__entornManager = __entornManager;
    _validator = new EntornValidator<EntornJPA>();
  }

  public EntornBeanValidator(EntornValidator<EntornJPA> _validator,
     org.fundaciobit.versioapp.model.dao.IEntornManager __entornManager) {
    this.__entornManager = __entornManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EntornJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EntornJPA> _bvr_ = new BeanValidatorResult<EntornJPA>();
    _validator.validate(_bvr_, target, isNou, __entornManager);
    return _bvr_.getErrors();
  }
}
