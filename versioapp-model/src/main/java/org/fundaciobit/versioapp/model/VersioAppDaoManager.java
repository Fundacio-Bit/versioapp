package org.fundaciobit.versioapp.model;

public class VersioAppDaoManager {
  
  private static IVersioAppDaoManagers instance = null;
  
  public static void setDaoManagers(IVersioAppDaoManagers managers) {
    instance = managers;
  }
  
  public static IVersioAppDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode VersioAppDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
