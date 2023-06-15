
package org.fundaciobit.versioapp.logic;


import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.versioapp.ejb.VersioService;
import org.fundaciobit.versioapp.model.entity.Versio;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface VersioLogicaService extends VersioService {

    public static final String JNDI_NAME = "java:app/versioapp-ejb/VersioLogicaEJB!org.fundaciobit.versioapp.logic.VersioLogicaService";

    
    public String refreshVersio(String domini, String contextPath, String versioGuardada,
            Long entornAplicacio) throws Exception;
    
    
    public List<Versio> getVersionsMajors() throws I18NException;
    
    
    public void refreshAllVersions() throws I18NException;

}
