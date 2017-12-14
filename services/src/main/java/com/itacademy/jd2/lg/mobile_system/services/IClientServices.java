package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Client;

public interface IClientServices {
	Client get(Integer id);

	List<Client> getAll();

	void remove(Integer id);
}
