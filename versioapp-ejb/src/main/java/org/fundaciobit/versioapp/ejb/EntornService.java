
package org.fundaciobit.versioapp.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.versioapp.persistence.EntornJPA;
import org.fundaciobit.versioapp.persistence.EntornIJPAManager;
import org.fundaciobit.versioapp.model.dao.IEntornManager;

import org.fundaciobit.versioapp.model.entity.Entorn;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntornService extends EntornIJPAManager,IEntornManager {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/EntornEJB!org.fundaciobit.versioapp.ejb.EntornService";

    public EntornJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Entorn instance, FitxerService fitxerEjb) throws I18NException;
}
