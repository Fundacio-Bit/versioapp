package org.fundaciobit.versioapp.model;

import org.fundaciobit.versioapp.model.dao.*;

public interface IVersioAppDaoManagers {
	public IAplicacioManager getAplicacioManager();
	public IEntornManager getEntornManager();
	public IEntornAplicacioManager getEntornAplicacioManager();
	public IFitxerManager getFitxerManager();
	public IIdiomaManager getIdiomaManager();
	public ITraduccioManager getTraduccioManager();
	public IVersioManager getVersioManager();

}