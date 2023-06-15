
package org.fundaciobit.versioapp.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.versioapp.ejb.VersioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.versioapp.model.fields.VersioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class VersioRefList extends RefListBase
    implements VersioFields {

  @EJB(mappedName = VersioService.JNDI_NAME)
  private VersioService versioEjb;

  public VersioRefList(VersioRefList __clone) {
    super(__clone);
    this.versioEjb = __clone.versioEjb;
  }
  public VersioRefList() {
    setSelects(new Select<?>[] { ENTORNAPLICACIOID.select, VERSIO.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = versioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
