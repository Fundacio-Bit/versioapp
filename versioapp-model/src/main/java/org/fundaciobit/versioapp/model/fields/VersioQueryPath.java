
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class VersioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public VersioQueryPath() {
  }

  protected VersioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField VERSIOID() {
    return new LongField(getQueryPath(), VersioFields.VERSIOID);
  }

  public LongField ENTORNAPLICACIOID() {
    return new LongField(getQueryPath(), VersioFields.ENTORNAPLICACIOID);
  }

  public StringField VERSIO() {
    return new StringField(getQueryPath(), VersioFields.VERSIO);
  }

  public StringField BUILD() {
    return new StringField(getQueryPath(), VersioFields.BUILD);
  }

  public TimestampField DATA() {
    return new TimestampField(getQueryPath(), VersioFields.DATA);
  }

  public StringField ALTRESDADES() {
    return new StringField(getQueryPath(), VersioFields.ALTRESDADES);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (VersioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntornAplicacioQueryPath ENTORNAPLICACIO() {
    return new EntornAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return VersioQueryPath.this.getQueryPath() + "entornAplicacio" + ".";
      }
    });
  }

}
