package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.IClientDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Client;
import com.itacademy.jd2.lg.mobile_system.dao.impl.ClientDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IClientServices;

public class ClientServiceImpl implements IClientServices {

	private IClientDao dao = new ClientDaoImpl();

	@Override
	public Client get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Client> getAll() {
		return dao.getAll();
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

}
