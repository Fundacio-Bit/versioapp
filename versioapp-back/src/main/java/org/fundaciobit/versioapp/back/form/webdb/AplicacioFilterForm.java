
package org.fundaciobit.versioapp.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.versioapp.back.form.VersioAppBaseFilterForm;

import org.fundaciobit.versioapp.model.fields.AplicacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AplicacioFilterForm extends VersioAppBaseFilterForm implements AplicacioFields {

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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String contextPath;

  public java.lang.String getContextPath() {
    return this.contextPath;
  }

  public void setContextPath(java.lang.String contextPath) {
    this.contextPath = contextPath;
  }


  public AplicacioFilterForm() {
  }
  
  public AplicacioFilterForm(AplicacioFilterForm __toClone) {
    super(__toClone);
    this.aplicacioIDDesde = __toClone.aplicacioIDDesde;
    this.aplicacioIDFins = __toClone.aplicacioIDFins;
    this.nom = __toClone.nom;
    this.contextPath = __toClone.contextPath;
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
