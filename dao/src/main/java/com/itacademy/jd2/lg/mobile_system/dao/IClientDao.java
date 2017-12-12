package com.itacademy.jd2.lg.mobile_system.dao;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Client;

public interface IClientDao {

	Client get(Integer id);

	void save(Client client);

}
