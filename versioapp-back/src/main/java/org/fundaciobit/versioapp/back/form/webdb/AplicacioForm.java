package org.fundaciobit.versioapp.back.form.webdb;

import org.fundaciobit.versioapp.back.form.VersioAppBaseForm;
import org.fundaciobit.versioapp.persistence.AplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AplicacioForm extends VersioAppBaseForm {
  
  private AplicacioJPA aplicacio;
  
  public AplicacioForm() {
  }
  
  public AplicacioForm(AplicacioForm __toClone) {
    super(__toClone);
      this.aplicacio = __toClone.aplicacio;
  }
  
  public AplicacioForm(AplicacioJPA aplicacio, boolean nou) {
    super(nou);
    this.aplicacio = aplicacio;
  }
  
  public AplicacioJPA getAplicacio() {
    return aplicacio;
  }
  public void setAplicacio(AplicacioJPA aplicacio) {
    this.aplicacio = aplicacio;
  }
  
  
  
} // Final de Classe 
