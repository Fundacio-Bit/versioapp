package org.fundaciobit.versioapp.model.dao;

import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IVersioManager extends org.fundaciobit.genapp.common.query.ITableManager<Versio, Long> {


	public Versio create( long _entornAplicacioID_, java.lang.String _versio_, java.lang.String _build_, java.sql.Timestamp _data_, java.lang.String _altresDades_) throws I18NException;

	public Versio findByPrimaryKey(long _versioID_);

	public void delete(long _versioID_);

}
