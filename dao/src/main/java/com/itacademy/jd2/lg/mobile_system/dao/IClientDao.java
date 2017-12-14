package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Client;

public interface IClientDao {

	Client get(Integer id);

	void remove(Integer id);

	void insert(Client client);

	void update(Client client);

	List<Client> getAll();
}
