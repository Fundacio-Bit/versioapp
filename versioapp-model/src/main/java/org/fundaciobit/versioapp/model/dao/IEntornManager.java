package org.fundaciobit.versioapp.model.dao;

import org.fundaciobit.versioapp.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntornManager extends org.fundaciobit.genapp.common.query.ITableManager<Entorn, Long> {


	public Entorn create( java.lang.String _nom_, java.lang.String _domini_, int _ordre_) throws I18NException;

	public Entorn findByPrimaryKey(long _entornID_);

	public void delete(long _entornID_);

}
