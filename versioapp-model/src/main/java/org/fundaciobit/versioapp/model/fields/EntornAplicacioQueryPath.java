
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntornAplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntornAplicacioQueryPath() {
  }

  protected EntornAplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ENTORNAPLICACIOID() {
    return new LongField(getQueryPath(), EntornAplicacioFields.ENTORNAPLICACIOID);
  }

  public LongField APLICACIOID() {
    return new LongField(getQueryPath(), EntornAplicacioFields.APLICACIOID);
  }

  public LongField ENTORNID() {
    return new LongField(getQueryPath(), EntornAplicacioFields.ENTORNID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntornAplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public VersioQueryPath VERSIOS() {
    return new VersioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntornAplicacioQueryPath.this.getQueryPath() + "versios" + ".";
      }
    });
  }
*/

  public AplicacioQueryPath APLICACIO() {
    return new AplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntornAplicacioQueryPath.this.getQueryPath() + "aplicacio" + ".";
      }
    });
  }

  public EntornQueryPath ENTORN() {
    return new EntornQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntornAplicacioQueryPath.this.getQueryPath() + "entorn" + ".";
      }
    });
  }

}
