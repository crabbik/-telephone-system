package com.itacademy.jd2.lg.mobile_system.dao.impl;

import com.itacademy.jd2.lg.mobile_system.dao.ISpentDao;

public class SpentDaoImp extends AbstractDaoImpl implements ISpentDao {

	public SpentDaoImp() {
		super();
	}

	@Override
	protected String getTableName() {
		return "spent";
	}

}
