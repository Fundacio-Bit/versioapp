
package org.fundaciobit.versioapp.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.versioapp.back.form.VersioAppBaseFilterForm;

import org.fundaciobit.versioapp.model.fields.VersioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class VersioFilterForm extends VersioAppBaseFilterForm implements VersioFields {

  private java.lang.Long versioIDDesde;

  public java.lang.Long getVersioIDDesde() {
    return this.versioIDDesde;
  }

  public void setVersioIDDesde(java.lang.Long versioIDDesde) {
    this.versioIDDesde = versioIDDesde;
  }


  private java.lang.Long versioIDFins;

  public java.lang.Long getVersioIDFins() {
    return this.versioIDFins;
  }

  public void setVersioIDFins(java.lang.Long versioIDFins) {
    this.versioIDFins = versioIDFins;
  }


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


  private java.lang.String versio;

  public java.lang.String getVersio() {
    return this.versio;
  }

  public void setVersio(java.lang.String versio) {
    this.versio = versio;
  }


  private java.lang.String build;

  public java.lang.String getBuild() {
    return this.build;
  }

  public void setBuild(java.lang.String build) {
    this.build = build;
  }


  private java.sql.Timestamp dataDesde;

  public java.sql.Timestamp getDataDesde() {
    return this.dataDesde;
  }

  public void setDataDesde(java.sql.Timestamp dataDesde) {
    this.dataDesde = dataDesde;
  }


  private java.sql.Timestamp dataFins;

  public java.sql.Timestamp getDataFins() {
    return this.dataFins;
  }

  public void setDataFins(java.sql.Timestamp dataFins) {
    this.dataFins = dataFins;
  }


  private java.lang.String altresDades;

  public java.lang.String getAltresDades() {
    return this.altresDades;
  }

  public void setAltresDades(java.lang.String altresDades) {
    this.altresDades = altresDades;
  }


  public VersioFilterForm() {
  }
  
  public VersioFilterForm(VersioFilterForm __toClone) {
    super(__toClone);
    this.versioIDDesde = __toClone.versioIDDesde;
    this.versioIDFins = __toClone.versioIDFins;
    this.entornAplicacioIDDesde = __toClone.entornAplicacioIDDesde;
    this.entornAplicacioIDFins = __toClone.entornAplicacioIDFins;
    this.versio = __toClone.versio;
    this.build = __toClone.build;
    this.dataDesde = __toClone.dataDesde;
    this.dataFins = __toClone.dataFins;
    this.altresDades = __toClone.altresDades;
    this.mapOfEntornAplicacioForEntornAplicacioID = __toClone.mapOfEntornAplicacioForEntornAplicacioID;
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
  private Map<String, String> mapOfEntornAplicacioForEntornAplicacioID;

  public Map<String, String> getMapOfEntornAplicacioForEntornAplicacioID() {
    return this.mapOfEntornAplicacioForEntornAplicacioID;
  }

  public void setMapOfEntornAplicacioForEntornAplicacioID(Map<String, String> mapOfEntornAplicacioForEntornAplicacioID) {
    this.mapOfEntornAplicacioForEntornAplicacioID = mapOfEntornAplicacioForEntornAplicacioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
