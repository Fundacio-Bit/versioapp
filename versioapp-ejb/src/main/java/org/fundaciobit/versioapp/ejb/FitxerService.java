
package org.fundaciobit.versioapp.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.versioapp.persistence.FitxerJPA;
import org.fundaciobit.versioapp.persistence.FitxerIJPAManager;
import org.fundaciobit.versioapp.model.dao.IFitxerManager;

import org.fundaciobit.versioapp.model.entity.Fitxer;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/FitxerEJB!org.fundaciobit.versioapp.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Fitxer instance, FitxerService fitxerEjb) throws I18NException;
}
