
package org.fundaciobit.versioapp.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.versioapp.persistence.VersioJPA;
import org.fundaciobit.versioapp.persistence.VersioIJPAManager;
import org.fundaciobit.versioapp.model.dao.IVersioManager;

import org.fundaciobit.versioapp.model.entity.Versio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface VersioService extends VersioIJPAManager,IVersioManager {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/VersioEJB!org.fundaciobit.versioapp.ejb.VersioService";

    public VersioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Versio instance, FitxerService fitxerEjb) throws I18NException;
}
