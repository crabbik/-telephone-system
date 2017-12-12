package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itacademy.jd2.lg.mobile_system.dao.AbstractDao;
import com.itacademy.jd2.lg.mobile_system.dao.IClientDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Client;

public class ClientDaoImpl extends AbstractDao implements IClientDao {

	public ClientDaoImpl() {
		super();
	}

	public Client get(Integer id) {
		Client result = null;

		try (Connection c = getConnection();
				Statement stmt = c.createStatement()) {
			System.out.println("Opened database successfully");

			ResultSet rs = stmt.executeQuery("select * from book where id="
					+ id);

			if (rs.next()) {
				result = new Client();
				result.setId(rs.getInt("id"));
				result.setLastName(rs.getString("last_name"));
				result.setFirstName(rs.getString("first_name"));
				result.setTariffId(rs.getInt("tariff_id"));
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;
	}

	public void save(Client client) {
		throw new RuntimeException("not implemented");

	}

}
