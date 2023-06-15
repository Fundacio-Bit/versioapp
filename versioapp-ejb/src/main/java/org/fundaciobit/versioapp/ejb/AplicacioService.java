
package org.fundaciobit.versioapp.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.versioapp.persistence.AplicacioJPA;
import org.fundaciobit.versioapp.persistence.AplicacioIJPAManager;
import org.fundaciobit.versioapp.model.dao.IAplicacioManager;

import org.fundaciobit.versioapp.model.entity.Aplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AplicacioService extends AplicacioIJPAManager,IAplicacioManager {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/AplicacioEJB!org.fundaciobit.versioapp.ejb.AplicacioService";

    public AplicacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Aplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
