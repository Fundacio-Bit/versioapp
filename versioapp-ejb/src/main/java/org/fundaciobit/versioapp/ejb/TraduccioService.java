
package org.fundaciobit.versioapp.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.versioapp.persistence.TraduccioJPA;
import org.fundaciobit.versioapp.persistence.TraduccioIJPAManager;
import org.fundaciobit.versioapp.model.dao.ITraduccioManager;

import org.fundaciobit.versioapp.model.entity.Traduccio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TraduccioService extends TraduccioIJPAManager,ITraduccioManager {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/TraduccioEJB!org.fundaciobit.versioapp.ejb.TraduccioService";

    public TraduccioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Traduccio instance, FitxerService fitxerEjb) throws I18NException;
}
