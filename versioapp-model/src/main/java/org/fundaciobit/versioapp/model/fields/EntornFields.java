
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntornFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "ver_entorn";


  public static final String _TABLE_MODEL = "entorn";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENTORNID = new LongField(_TABLE_MODEL, "entornID", "entornid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DOMINI = new StringField(_TABLE_MODEL, "domini", "domini");
	 public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre");


  public static final Field<?>[] ALL_ENTORN_FIELDS = {
    ENTORNID,
    NOM,
    DOMINI,
    ORDRE
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTORNID
  };
}
