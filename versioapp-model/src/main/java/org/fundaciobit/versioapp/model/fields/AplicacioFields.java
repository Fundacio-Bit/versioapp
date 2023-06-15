
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AplicacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "ver_aplicacio";


  public static final String _TABLE_MODEL = "aplicacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField APLICACIOID = new LongField(_TABLE_MODEL, "aplicacioID", "aplicacioid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField CONTEXTPATH = new StringField(_TABLE_MODEL, "contextPath", "contextpath");


  public static final Field<?>[] ALL_APLICACIO_FIELDS = {
    APLICACIOID,
    NOM,
    CONTEXTPATH
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
APLICACIOID
  };
}
