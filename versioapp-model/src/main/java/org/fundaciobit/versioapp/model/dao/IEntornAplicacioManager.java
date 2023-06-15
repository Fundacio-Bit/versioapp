package org.fundaciobit.versioapp.model.dao;

import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntornAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<EntornAplicacio, Long> {


	public EntornAplicacio create( long _aplicacioID_, long _entornid_) throws I18NException;

	public EntornAplicacio findByPrimaryKey(long _entornAplicacioID_);

	public void delete(long _entornAplicacioID_);

}
