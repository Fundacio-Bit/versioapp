
package org.fundaciobit.versioapp.model.bean;

import org.fundaciobit.versioapp.model.entity.Aplicacio;


public class AplicacioBean implements Aplicacio {



	long aplicacioID;// PK
	java.lang.String nom;
	java.lang.String contextPath;


  /** Constructor Buit */
  public AplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public AplicacioBean(long aplicacioID , java.lang.String nom , java.lang.String contextPath) {
    this.aplicacioID=aplicacioID;
    this.nom=nom;
    this.contextPath=contextPath;
}
  /** Constructor sense valors autoincrementals */
  public AplicacioBean(java.lang.String nom , java.lang.String contextPath) {
    this.nom=nom;
    this.contextPath=contextPath;
}
  public AplicacioBean(Aplicacio __bean) {
    this.setAplicacioID(__bean.getAplicacioID());
    this.setNom(__bean.getNom());
    this.setContextPath(__bean.getContextPath());
	}

	public long getAplicacioID() {
		return(aplicacioID);
	};
	public void setAplicacioID(long _aplicacioID_) {
		this.aplicacioID = _aplicacioID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getContextPath() {
		return(contextPath);
	};
	public void setContextPath(java.lang.String _contextPath_) {
		this.contextPath = _contextPath_;
	};



  // ======================================

  public static AplicacioBean toBean(Aplicacio __bean) {
    if (__bean == null) { return null;}
    AplicacioBean __tmp = new AplicacioBean();
    __tmp.setAplicacioID(__bean.getAplicacioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setContextPath(__bean.getContextPath());
		return __tmp;
	}



}
