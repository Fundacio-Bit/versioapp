
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntornAplicacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "ver_entornaplicacio";


  public static final String _TABLE_MODEL = "entornAplicacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENTORNAPLICACIOID = new LongField(_TABLE_MODEL, "entornAplicacioID", "entornaplicacioid");  // PK
	 public static final LongField APLICACIOID = new LongField(_TABLE_MODEL, "aplicacioID", "aplicacioid");
	 public static final LongField ENTORNID = new LongField(_TABLE_MODEL, "entornid", "entornid");


  public static final Field<?>[] ALL_ENTORNAPLICACIO_FIELDS = {
    ENTORNAPLICACIOID,
    APLICACIOID,
    ENTORNID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTORNAPLICACIOID
  };
}
