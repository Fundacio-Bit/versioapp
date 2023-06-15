
package org.fundaciobit.versioapp.model.bean;

import org.fundaciobit.versioapp.model.entity.Entorn;


public class EntornBean implements Entorn {



	long entornID;// PK
	java.lang.String nom;
	java.lang.String domini;
	int ordre;


  /** Constructor Buit */
  public EntornBean() {
  }

  /** Constructor amb tots els camps  */
  public EntornBean(long entornID , java.lang.String nom , java.lang.String domini , int ordre) {
    this.entornID=entornID;
    this.nom=nom;
    this.domini=domini;
    this.ordre=ordre;
}
  /** Constructor sense valors autoincrementals */
  public EntornBean(java.lang.String nom , java.lang.String domini , int ordre) {
    this.nom=nom;
    this.domini=domini;
    this.ordre=ordre;
}
  public EntornBean(Entorn __bean) {
    this.setEntornID(__bean.getEntornID());
    this.setNom(__bean.getNom());
    this.setDomini(__bean.getDomini());
    this.setOrdre(__bean.getOrdre());
	}

	public long getEntornID() {
		return(entornID);
	};
	public void setEntornID(long _entornID_) {
		this.entornID = _entornID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDomini() {
		return(domini);
	};
	public void setDomini(java.lang.String _domini_) {
		this.domini = _domini_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};



  // ======================================

  public static EntornBean toBean(Entorn __bean) {
    if (__bean == null) { return null;}
    EntornBean __tmp = new EntornBean();
    __tmp.setEntornID(__bean.getEntornID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDomini(__bean.getDomini());
    __tmp.setOrdre(__bean.getOrdre());
		return __tmp;
	}



}
