
package org.fundaciobit.versioapp.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.versioapp.back.form.VersioAppBaseFilterForm;

import org.fundaciobit.versioapp.model.fields.EntornAplicacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntornAplicacioFilterForm extends VersioAppBaseFilterForm implements EntornAplicacioFields {

  private java.lang.Long entornAplicacioIDDesde;

  public java.lang.Long getEntornAplicacioIDDesde() {
    return this.entornAplicacioIDDesde;
  }

  public void setEntornAplicacioIDDesde(java.lang.Long entornAplicacioIDDesde) {
    this.entornAplicacioIDDesde = entornAplicacioIDDesde;
  }


  private java.lang.Long entornAplicacioIDFins;

  public java.lang.Long getEntornAplicacioIDFins() {
    return this.entornAplicacioIDFins;
  }

  public void setEntornAplicacioIDFins(java.lang.Long entornAplicacioIDFins) {
    this.entornAplicacioIDFins = entornAplicacioIDFins;
  }


  private java.lang.Long aplicacioIDDesde;

  public java.lang.Long getAplicacioIDDesde() {
    return this.aplicacioIDDesde;
  }

  public void setAplicacioIDDesde(java.lang.Long aplicacioIDDesde) {
    this.aplicacioIDDesde = aplicacioIDDesde;
  }


  private java.lang.Long aplicacioIDFins;

  public java.lang.Long getAplicacioIDFins() {
    return this.aplicacioIDFins;
  }

  public void setAplicacioIDFins(java.lang.Long aplicacioIDFins) {
    this.aplicacioIDFins = aplicacioIDFins;
  }


  private java.lang.Long entornidDesde;

  public java.lang.Long getEntornidDesde() {
    return this.entornidDesde;
  }

  public void setEntornidDesde(java.lang.Long entornidDesde) {
    this.entornidDesde = entornidDesde;
  }


  private java.lang.Long entornidFins;

  public java.lang.Long getEntornidFins() {
    return this.entornidFins;
  }

  public void setEntornidFins(java.lang.Long entornidFins) {
    this.entornidFins = entornidFins;
  }


  public EntornAplicacioFilterForm() {
  }
  
  public EntornAplicacioFilterForm(EntornAplicacioFilterForm __toClone) {
    super(__toClone);
    this.entornAplicacioIDDesde = __toClone.entornAplicacioIDDesde;
    this.entornAplicacioIDFins = __toClone.entornAplicacioIDFins;
    this.aplicacioIDDesde = __toClone.aplicacioIDDesde;
    this.aplicacioIDFins = __toClone.aplicacioIDFins;
    this.entornidDesde = __toClone.entornidDesde;
    this.entornidFins = __toClone.entornidFins;
    this.mapOfAplicacioForAplicacioID = __toClone.mapOfAplicacioForAplicacioID;
    this.mapOfEntornForEntornid = __toClone.mapOfEntornForEntornid;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfAplicacioForAplicacioID;

  public Map<String, String> getMapOfAplicacioForAplicacioID() {
    return this.mapOfAplicacioForAplicacioID;
  }

  public void setMapOfAplicacioForAplicacioID(Map<String, String> mapOfAplicacioForAplicacioID) {
    this.mapOfAplicacioForAplicacioID = mapOfAplicacioForAplicacioID;
  }



  private Map<String, String> mapOfEntornForEntornid;

  public Map<String, String> getMapOfEntornForEntornid() {
    return this.mapOfEntornForEntornid;
  }

  public void setMapOfEntornForEntornid(Map<String, String> mapOfEntornForEntornid) {
    this.mapOfEntornForEntornid = mapOfEntornForEntornid;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
