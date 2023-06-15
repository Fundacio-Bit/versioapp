
package org.fundaciobit.versioapp.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.versioapp.model.fields.*;
import org.fundaciobit.versioapp.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class AplicacioJPAManager
         extends AbstractJPAManager<Aplicacio, Long>
         implements AplicacioIJPAManager, IAplicacioManager, AplicacioFields {



    public static final TableName<Aplicacio> _TABLENAME =  new TableName<Aplicacio>("AplicacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public AplicacioJPAManager() {
    }

    protected AplicacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return AplicacioJPA. class;
    }



    public TableName<Aplicacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Aplicacio[] listToArray(List<Aplicacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Aplicacio[list.size()]);
    };

    public Aplicacio create( java.lang.String _nom_, java.lang.String _contextPath_) throws I18NException {
        AplicacioJPA __bean =  new AplicacioJPA(_nom_,_contextPath_);
        return create(__bean);
    }



 public void delete(long _aplicacioID_) {
   delete(findByPrimaryKey(_aplicacioID_));
 }




    public Aplicacio findByPrimaryKey(long _aplicacioID_) {
        return __em.find(AplicacioJPA.class, _aplicacioID_);  
    }
    @Override
    protected Aplicacio getJPAInstance(Aplicacio __bean) {
        return convertToJPA(__bean);
    }


    public static AplicacioJPA convertToJPA(Aplicacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof AplicacioJPA) {
        return (AplicacioJPA)__bean;
      }
      
      return AplicacioJPA.toJPA(__bean);
    }


}