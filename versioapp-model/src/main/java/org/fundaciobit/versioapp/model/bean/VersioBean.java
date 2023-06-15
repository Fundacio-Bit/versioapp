
package org.fundaciobit.versioapp.model.bean;

import org.fundaciobit.versioapp.model.entity.Versio;


public class VersioBean implements Versio {



	long versioID;// PK
	long entornAplicacioID;
	java.lang.String versio;
	java.lang.String build;
	java.sql.Timestamp data;
	java.lang.String altresDades;


  /** Constructor Buit */
  public VersioBean() {
  }

  /** Constructor amb tots els camps  */
  public VersioBean(long versioID , long entornAplicacioID , java.lang.String versio , java.lang.String build , java.sql.Timestamp data , java.lang.String altresDades) {
    this.versioID=versioID;
    this.entornAplicacioID=entornAplicacioID;
    this.versio=versio;
    this.build=build;
    this.data=data;
    this.altresDades=altresDades;
}
  /** Constructor sense valors autoincrementals */
  public VersioBean(long entornAplicacioID , java.lang.String versio , java.lang.String build , java.sql.Timestamp data , java.lang.String altresDades) {
    this.entornAplicacioID=entornAplicacioID;
    this.versio=versio;
    this.build=build;
    this.data=data;
    this.altresDades=altresDades;
}
  /** Constructor dels valors Not Null */
  public VersioBean(long versioID , long entornAplicacioID , java.lang.String versio , java.sql.Timestamp data) {
    this.versioID=versioID;
    this.entornAplicacioID=entornAplicacioID;
    this.versio=versio;
    this.data=data;
}
  public VersioBean(Versio __bean) {
    this.setVersioID(__bean.getVersioID());
    this.setEntornAplicacioID(__bean.getEntornAplicacioID());
    this.setVersio(__bean.getVersio());
    this.setBuild(__bean.getBuild());
    this.setData(__bean.getData());
    this.setAltresDades(__bean.getAltresDades());
	}

	public long getVersioID() {
		return(versioID);
	};
	public void setVersioID(long _versioID_) {
		this.versioID = _versioID_;
	};

	public long getEntornAplicacioID() {
		return(entornAplicacioID);
	};
	public void setEntornAplicacioID(long _entornAplicacioID_) {
		this.entornAplicacioID = _entornAplicacioID_;
	};

	public java.lang.String getVersio() {
		return(versio);
	};
	public void setVersio(java.lang.String _versio_) {
		this.versio = _versio_;
	};

	public java.lang.String getBuild() {
		return(build);
	};
	public void setBuild(java.lang.String _build_) {
		this.build = _build_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public java.lang.String getAltresDades() {
		return(altresDades);
	};
	public void setAltresDades(java.lang.String _altresDades_) {
		this.altresDades = _altresDades_;
	};



  // ======================================

  public static VersioBean toBean(Versio __bean) {
    if (__bean == null) { return null;}
    VersioBean __tmp = new VersioBean();
    __tmp.setVersioID(__bean.getVersioID());
    __tmp.setEntornAplicacioID(__bean.getEntornAplicacioID());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setBuild(__bean.getBuild());
    __tmp.setData(__bean.getData());
    __tmp.setAltresDades(__bean.getAltresDades());
		return __tmp;
	}



}
