package org.fundaciobit.versioapp.model.dao;

import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<Aplicacio, Long> {


	public Aplicacio create( java.lang.String _nom_, java.lang.String _contextPath_) throws I18NException;

	public Aplicacio findByPrimaryKey(long _aplicacioID_);

	public void delete(long _aplicacioID_);

}
