package org.fundaciobit.versioapp.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.versioapp.back.form.VersioAppBaseForm;
import org.fundaciobit.versioapp.persistence.VersioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class VersioForm extends VersioAppBaseForm {
  
  private VersioJPA versio;
  
  public VersioForm() {
  }
  
  public VersioForm(VersioForm __toClone) {
    super(__toClone);
      this.versio = __toClone.versio;
    this.listOfEntornAplicacioForEntornAplicacioID = __toClone.listOfEntornAplicacioForEntornAplicacioID;
  }
  
  public VersioForm(VersioJPA versio, boolean nou) {
    super(nou);
    this.versio = versio;
  }
  
  public VersioJPA getVersio() {
    return versio;
  }
  public void setVersio(VersioJPA versio) {
    this.versio = versio;
  }
  
  
  private List<StringKeyValue> listOfEntornAplicacioForEntornAplicacioID;

  public List<StringKeyValue> getListOfEntornAplicacioForEntornAplicacioID() {
    return this.listOfEntornAplicacioForEntornAplicacioID;
  }

  public void setListOfEntornAplicacioForEntornAplicacioID(List<StringKeyValue> listOfEntornAplicacioForEntornAplicacioID) {
    this.listOfEntornAplicacioForEntornAplicacioID = listOfEntornAplicacioForEntornAplicacioID;
  }



  
} // Final de Classe 
