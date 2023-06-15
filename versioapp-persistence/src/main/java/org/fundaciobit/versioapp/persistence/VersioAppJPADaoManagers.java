package org.fundaciobit.versioapp.persistence;

import org.fundaciobit.versioapp.model.*;
import org.fundaciobit.versioapp.model.dao.*;
import javax.persistence.EntityManager;

public final class VersioAppJPADaoManagers implements IVersioAppDaoManagers{

   private final AplicacioJPAManager ver_aplicacio;
   private final EntornJPAManager ver_entorn;
   private final EntornAplicacioJPAManager ver_entornaplicacio;
   private final FitxerJPAManager ver_fitxer;
   private final IdiomaJPAManager ver_idioma;
   private final TraduccioJPAManager ver_traduccio;
   private final VersioJPAManager ver_versio;

  public  VersioAppJPADaoManagers(EntityManager __em) {
    this.ver_aplicacio = new AplicacioJPAManager(__em);
    this.ver_entorn = new EntornJPAManager(__em);
    this.ver_entornaplicacio = new EntornAplicacioJPAManager(__em);
    this.ver_fitxer = new FitxerJPAManager(__em);
    this.ver_idioma = new IdiomaJPAManager(__em);
    this.ver_traduccio = new TraduccioJPAManager(__em);
    this.ver_versio = new VersioJPAManager(__em);
  }

    public IAplicacioManager getAplicacioManager() {
        return this.ver_aplicacio;
    };

    public IEntornManager getEntornManager() {
        return this.ver_entorn;
    };

    public IEntornAplicacioManager getEntornAplicacioManager() {
        return this.ver_entornaplicacio;
    };

    public IFitxerManager getFitxerManager() {
        return this.ver_fitxer;
    };

    public IIdiomaManager getIdiomaManager() {
        return this.ver_idioma;
    };

    public ITraduccioManager getTraduccioManager() {
        return this.ver_traduccio;
    };

    public IVersioManager getVersioManager() {
        return this.ver_versio;
    };


}