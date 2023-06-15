
package org.fundaciobit.versioapp.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.versioapp.ejb.EntornAplicacioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.versioapp.model.fields.EntornAplicacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntornAplicacioRefList extends RefListBase
    implements EntornAplicacioFields {

  @EJB(mappedName = EntornAplicacioService.JNDI_NAME)
  private EntornAplicacioService entornAplicacioEjb;

  public EntornAplicacioRefList(EntornAplicacioRefList __clone) {
    super(__clone);
    this.entornAplicacioEjb = __clone.entornAplicacioEjb;
  }
  public EntornAplicacioRefList() {
    setSelects(new Select<?>[] { ENTORNAPLICACIOID.select, APLICACIOID.select, ENTORNID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = entornAplicacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
