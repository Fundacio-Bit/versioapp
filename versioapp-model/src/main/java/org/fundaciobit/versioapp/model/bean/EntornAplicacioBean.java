
package org.fundaciobit.versioapp.model.bean;

import org.fundaciobit.versioapp.model.entity.EntornAplicacio;


public class EntornAplicacioBean implements EntornAplicacio {



	long entornAplicacioID;// PK
	long aplicacioID;
	long entornid;


  /** Constructor Buit */
  public EntornAplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public EntornAplicacioBean(long entornAplicacioID , long aplicacioID , long entornid) {
    this.entornAplicacioID=entornAplicacioID;
    this.aplicacioID=aplicacioID;
    this.entornid=entornid;
}
  /** Constructor sense valors autoincrementals */
  public EntornAplicacioBean(long aplicacioID , long entornid) {
    this.aplicacioID=aplicacioID;
    this.entornid=entornid;
}
  public EntornAplicacioBean(EntornAplicacio __bean) {
    this.setEntornAplicacioID(__bean.getEntornAplicacioID());
    this.setAplicacioID(__bean.getAplicacioID());
    this.setEntornid(__bean.getEntornid());
	}

	public long getEntornAplicacioID() {
		return(entornAplicacioID);
	};
	public void setEntornAplicacioID(long _entornAplicacioID_) {
		this.entornAplicacioID = _entornAplicacioID_;
	};

	public long getAplicacioID() {
		return(aplicacioID);
	};
	public void setAplicacioID(long _aplicacioID_) {
		this.aplicacioID = _aplicacioID_;
	};

	public long getEntornid() {
		return(entornid);
	};
	public void setEntornid(long _entornid_) {
		this.entornid = _entornid_;
	};



  // ======================================

  public static EntornAplicacioBean toBean(EntornAplicacio __bean) {
    if (__bean == null) { return null;}
    EntornAplicacioBean __tmp = new EntornAplicacioBean();
    __tmp.setEntornAplicacioID(__bean.getEntornAplicacioID());
    __tmp.setAplicacioID(__bean.getAplicacioID());
    __tmp.setEntornid(__bean.getEntornid());
		return __tmp;
	}



}
