
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface VersioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "ver_versio";


  public static final String _TABLE_MODEL = "versio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField VERSIOID = new LongField(_TABLE_MODEL, "versioID", "versioid");  // PK
	 public static final LongField ENTORNAPLICACIOID = new LongField(_TABLE_MODEL, "entornAplicacioID", "entornaplicacioid");
	 public static final StringField VERSIO = new StringField(_TABLE_MODEL, "versio", "versio");
	 public static final StringField BUILD = new StringField(_TABLE_MODEL, "build", "build");
	 public static final TimestampField DATA = new TimestampField(_TABLE_MODEL, "data", "data");
	 public static final StringField ALTRESDADES = new StringField(_TABLE_MODEL, "altresDades", "altresdades");


  public static final Field<?>[] ALL_VERSIO_FIELDS = {
    VERSIOID,
    ENTORNAPLICACIOID,
    VERSIO,
    BUILD,
    DATA,
    ALTRESDADES
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
VERSIOID
  };
}
