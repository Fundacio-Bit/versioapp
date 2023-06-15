
package org.fundaciobit.versioapp.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.versioapp.model.fields.*;
import org.fundaciobit.versioapp.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EntornJPAManager
         extends AbstractJPAManager<Entorn, Long>
         implements EntornIJPAManager, IEntornManager, EntornFields {



    public static final TableName<Entorn> _TABLENAME =  new TableName<Entorn>("EntornJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EntornJPAManager() {
    }

    protected EntornJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EntornJPA. class;
    }



    public TableName<Entorn> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Entorn[] listToArray(List<Entorn> list)  {
        if(list == null) { return null; };
        return list.toArray(new Entorn[list.size()]);
    };

    public Entorn create( java.lang.String _nom_, java.lang.String _domini_, int _ordre_) throws I18NException {
        EntornJPA __bean =  new EntornJPA(_nom_,_domini_,_ordre_);
        return create(__bean);
    }



 public void delete(long _entornID_) {
   delete(findByPrimaryKey(_entornID_));
 }




    public Entorn findByPrimaryKey(long _entornID_) {
        return __em.find(EntornJPA.class, _entornID_);  
    }
    @Override
    protected Entorn getJPAInstance(Entorn __bean) {
        return convertToJPA(__bean);
    }


    public static EntornJPA convertToJPA(Entorn __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EntornJPA) {
        return (EntornJPA)__bean;
      }
      
      return EntornJPA.toJPA(__bean);
    }


}