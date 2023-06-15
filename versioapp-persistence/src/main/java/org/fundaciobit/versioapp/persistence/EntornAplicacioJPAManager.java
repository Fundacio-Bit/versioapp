
package org.fundaciobit.versioapp.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.versioapp.model.fields.*;
import org.fundaciobit.versioapp.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EntornAplicacioJPAManager
         extends AbstractJPAManager<EntornAplicacio, Long>
         implements EntornAplicacioIJPAManager, IEntornAplicacioManager, EntornAplicacioFields {



    public static final TableName<EntornAplicacio> _TABLENAME =  new TableName<EntornAplicacio>("EntornAplicacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EntornAplicacioJPAManager() {
    }

    protected EntornAplicacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EntornAplicacioJPA. class;
    }



    public TableName<EntornAplicacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public EntornAplicacio[] listToArray(List<EntornAplicacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new EntornAplicacio[list.size()]);
    };

    public EntornAplicacio create( long _aplicacioID_, long _entornid_) throws I18NException {
        EntornAplicacioJPA __bean =  new EntornAplicacioJPA(_aplicacioID_,_entornid_);
        return create(__bean);
    }



 public void delete(long _entornAplicacioID_) {
   delete(findByPrimaryKey(_entornAplicacioID_));
 }




    public EntornAplicacio findByPrimaryKey(long _entornAplicacioID_) {
        return __em.find(EntornAplicacioJPA.class, _entornAplicacioID_);  
    }
    @Override
    protected EntornAplicacio getJPAInstance(EntornAplicacio __bean) {
        return convertToJPA(__bean);
    }


    public static EntornAplicacioJPA convertToJPA(EntornAplicacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EntornAplicacioJPA) {
        return (EntornAplicacioJPA)__bean;
      }
      
      return EntornAplicacioJPA.toJPA(__bean);
    }


}