
package org.fundaciobit.versioapp.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.versioapp.persistence.EntornAplicacioJPA;
import org.fundaciobit.versioapp.persistence.EntornAplicacioIJPAManager;
import org.fundaciobit.versioapp.model.dao.IEntornAplicacioManager;

import org.fundaciobit.versioapp.model.entity.EntornAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntornAplicacioService extends EntornAplicacioIJPAManager,IEntornAplicacioManager {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/EntornAplicacioEJB!org.fundaciobit.versioapp.ejb.EntornAplicacioService";

    public EntornAplicacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(EntornAplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
