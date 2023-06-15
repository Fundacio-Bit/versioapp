
package org.fundaciobit.versioapp.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.versioapp.back.form.VersioAppBaseFilterForm;

import org.fundaciobit.versioapp.model.fields.EntornFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntornFilterForm extends VersioAppBaseFilterForm implements EntornFields {

  private java.lang.Long entornIDDesde;

  public java.lang.Long getEntornIDDesde() {
    return this.entornIDDesde;
  }

  public void setEntornIDDesde(java.lang.Long entornIDDesde) {
    this.entornIDDesde = entornIDDesde;
  }


  private java.lang.Long entornIDFins;

  public java.lang.Long getEntornIDFins() {
    return this.entornIDFins;
  }

  public void setEntornIDFins(java.lang.Long entornIDFins) {
    this.entornIDFins = entornIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String domini;

  public java.lang.String getDomini() {
    return this.domini;
  }

  public void setDomini(java.lang.String domini) {
    this.domini = domini;
  }


  private java.lang.Integer ordreDesde;

  public java.lang.Integer getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Integer ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Integer ordreFins;

  public java.lang.Integer getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Integer ordreFins) {
    this.ordreFins = ordreFins;
  }


  public EntornFilterForm() {
  }
  
  public EntornFilterForm(EntornFilterForm __toClone) {
    super(__toClone);
    this.entornIDDesde = __toClone.entornIDDesde;
    this.entornIDFins = __toClone.entornIDFins;
    this.nom = __toClone.nom;
    this.domini = __toClone.domini;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
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


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ORDRE )};


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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
