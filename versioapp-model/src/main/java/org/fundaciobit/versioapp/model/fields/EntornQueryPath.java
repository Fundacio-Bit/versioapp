
package org.fundaciobit.versioapp.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntornQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntornQueryPath() {
  }

  protected EntornQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ENTORNID() {
    return new LongField(getQueryPath(), EntornFields.ENTORNID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EntornFields.NOM);
  }

  public StringField DOMINI() {
    return new StringField(getQueryPath(), EntornFields.DOMINI);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), EntornFields.ORDRE);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntornFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntornAplicacioQueryPath ENTORNAPLICACIOS() {
    return new EntornAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntornQueryPath.this.getQueryPath() + "entornAplicacios" + ".";
      }
    });
  }
*/

}
