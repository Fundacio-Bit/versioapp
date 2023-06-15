
package org.fundaciobit.versioapp.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.versioapp.persistence.IdiomaJPA;
import org.fundaciobit.versioapp.persistence.IdiomaIJPAManager;
import org.fundaciobit.versioapp.model.dao.IIdiomaManager;

import org.fundaciobit.versioapp.model.entity.Idioma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/IdiomaEJB!org.fundaciobit.versioapp.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Idioma instance, FitxerService fitxerEjb) throws I18NException;
}
