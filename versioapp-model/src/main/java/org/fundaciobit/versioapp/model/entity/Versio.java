package org.fundaciobit.versioapp.model.entity;

public interface Versio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getVersioID();
	public void setVersioID(long _versioID_);

	public long getEntornAplicacioID();
	public void setEntornAplicacioID(long _entornAplicacioID_);

	public java.lang.String getVersio();
	public void setVersio(java.lang.String _versio_);

	public java.lang.String getBuild();
	public void setBuild(java.lang.String _build_);

	public java.sql.Timestamp getData();
	public void setData(java.sql.Timestamp _data_);

	public java.lang.String getAltresDades();
	public void setAltresDades(java.lang.String _altresDades_);



  // ======================================

}
