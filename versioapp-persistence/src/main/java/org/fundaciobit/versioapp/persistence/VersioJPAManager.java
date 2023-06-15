
package org.fundaciobit.versioapp.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.versioapp.model.fields.*;
import org.fundaciobit.versioapp.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class VersioJPAManager
         extends AbstractJPAManager<Versio, Long>
         implements VersioIJPAManager, IVersioManager, VersioFields {



    public static final TableName<Versio> _TABLENAME =  new TableName<Versio>("VersioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public VersioJPAManager() {
    }

    protected VersioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return VersioJPA. class;
    }



    public TableName<Versio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Versio[] listToArray(List<Versio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Versio[list.size()]);
    };

    public Versio create( long _entornAplicacioID_, java.lang.String _versio_, java.lang.String _build_, java.sql.Timestamp _data_, java.lang.String _altresDades_) throws I18NException {
        VersioJPA __bean =  new VersioJPA(_entornAplicacioID_,_versio_,_build_,_data_,_altresDades_);
        return create(__bean);
    }



 public void delete(long _versioID_) {
   delete(findByPrimaryKey(_versioID_));
 }




    public Versio findByPrimaryKey(long _versioID_) {
        return __em.find(VersioJPA.class, _versioID_);  
    }
    @Override
    protected Versio getJPAInstance(Versio __bean) {
        return convertToJPA(__bean);
    }


    public static VersioJPA convertToJPA(Versio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof VersioJPA) {
        return (VersioJPA)__bean;
      }
      
      return VersioJPA.toJPA(__bean);
    }


}