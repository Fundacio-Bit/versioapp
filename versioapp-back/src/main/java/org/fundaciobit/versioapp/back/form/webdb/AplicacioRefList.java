
package org.fundaciobit.versioapp.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.versioapp.ejb.AplicacioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.versioapp.model.fields.AplicacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AplicacioRefList extends RefListBase
    implements AplicacioFields {

  @EJB(mappedName = AplicacioService.JNDI_NAME)
  private AplicacioService aplicacioEjb;

  public AplicacioRefList(AplicacioRefList __clone) {
    super(__clone);
    this.aplicacioEjb = __clone.aplicacioEjb;
  }
  public AplicacioRefList() {
    setSelects(new Select<?>[] { NOM.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = aplicacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
