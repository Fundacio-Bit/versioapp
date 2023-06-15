package org.fundaciobit.versioapp.persistence.validator;

import org.fundaciobit.versioapp.persistence.VersioJPA;
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
public class VersioBeanValidator 
      extends AbstractBeanValidator<VersioJPA> {


  // EJB's
  protected final org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager;

  protected final org.fundaciobit.versioapp.model.dao.IVersioManager __versioManager;


  public final VersioValidator<VersioJPA> _validator;


  public VersioBeanValidator(org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager,
     org.fundaciobit.versioapp.model.dao.IVersioManager __versioManager) { 
    this.__entornAplicacioManager = __entornAplicacioManager;
    this.__versioManager = __versioManager;
    _validator = new VersioValidator<VersioJPA>();
  }

  public VersioBeanValidator(VersioValidator<VersioJPA> _validator,
     org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager __entornAplicacioManager,
     org.fundaciobit.versioapp.model.dao.IVersioManager __versioManager) {
    this.__entornAplicacioManager = __entornAplicacioManager;
    this.__versioManager = __versioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(VersioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<VersioJPA> _bvr_ = new BeanValidatorResult<VersioJPA>();
    _validator.validate(_bvr_, target, isNou, __entornAplicacioManager, __versioManager);
    return _bvr_.getErrors();
  }
}
