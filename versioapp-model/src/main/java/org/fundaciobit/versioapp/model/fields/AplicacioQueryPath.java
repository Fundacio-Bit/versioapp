
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AplicacioQueryPath() {
  }

  protected AplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField APLICACIOID() {
    return new LongField(getQueryPath(), AplicacioFields.APLICACIOID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), AplicacioFields.NOM);
  }

  public StringField CONTEXTPATH() {
    return new StringField(getQueryPath(), AplicacioFields.CONTEXTPATH);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntornAplicacioQueryPath ENTORNAPLICACIOS() {
    return new EntornAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AplicacioQueryPath.this.getQueryPath() + "entornAplicacios" + ".";
      }
    });
  }
*/

}
