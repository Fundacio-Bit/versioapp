package org.fundaciobit.versioapp.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.versioapp.back.form.VersioAppBaseForm;
import org.fundaciobit.versioapp.persistence.EntornAplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EntornAplicacioForm extends VersioAppBaseForm {
  
  private EntornAplicacioJPA entornAplicacio;
  
  public EntornAplicacioForm() {
  }
  
  public EntornAplicacioForm(EntornAplicacioForm __toClone) {
    super(__toClone);
      this.entornAplicacio = __toClone.entornAplicacio;
    this.listOfAplicacioForAplicacioID = __toClone.listOfAplicacioForAplicacioID;
    this.listOfEntornForEntornid = __toClone.listOfEntornForEntornid;
  }
  
  public EntornAplicacioForm(EntornAplicacioJPA entornAplicacio, boolean nou) {
    super(nou);
    this.entornAplicacio = entornAplicacio;
  }
  
  public EntornAplicacioJPA getEntornAplicacio() {
    return entornAplicacio;
  }
  public void setEntornAplicacio(EntornAplicacioJPA entornAplicacio) {
    this.entornAplicacio = entornAplicacio;
  }
  
  
  private List<StringKeyValue> listOfAplicacioForAplicacioID;

  public List<StringKeyValue> getListOfAplicacioForAplicacioID() {
    return this.listOfAplicacioForAplicacioID;
  }

  public void setListOfAplicacioForAplicacioID(List<StringKeyValue> listOfAplicacioForAplicacioID) {
    this.listOfAplicacioForAplicacioID = listOfAplicacioForAplicacioID;
  }



  private List<StringKeyValue> listOfEntornForEntornid;

  public List<StringKeyValue> getListOfEntornForEntornid() {
    return this.listOfEntornForEntornid;
  }

  public void setListOfEntornForEntornid(List<StringKeyValue> listOfEntornForEntornid) {
    this.listOfEntornForEntornid = listOfEntornForEntornid;
  }



  
} // Final de Classe 
