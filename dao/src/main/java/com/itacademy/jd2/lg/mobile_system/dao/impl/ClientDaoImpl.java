package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.IClientDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Client;

public class ClientDaoImpl extends AbstractDaoImpl implements IClientDao {

	public ClientDaoImpl() {
		super();
	}

	@Override
	public List<Client> getAll() {
		List<Client> result = new ArrayList<>();

		try (Connection c = getConnection(); Statement stmt = c.createStatement()) {
			System.out.println("Opened database successfully");

			ResultSet rs = stmt.executeQuery("select * from client");

			while (rs.next()) {
				Client client = mapToClient(rs);
				result.add(client);
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;
	}

	public Client get(Integer id) {
		Client result = null;

		try (Connection c = getConnection(); Statement stmt = c.createStatement()) {
			System.out.println("Opened database successfully");

			ResultSet rs = stmt.executeQuery("select * from client where id=" + id);

			if (rs.next()) {
				result = mapToClient(rs);
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;
	}

	private Client mapToClient(ResultSet rs) throws SQLException {
		Client result;
		result = new Client();
		result.setId(rs.getInt("id"));
		result.setLastName(rs.getString("last_name"));
		result.setFirstName(rs.getString("first_name"));
		result.setTariffId(rs.getInt("tariff_id"));
		return result;
	}


	@Override
	public void insert(Client client) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void update(Client client) {
		throw new RuntimeException("not implemented");
	}

	@Override
	protected String getTableName() {
		return "client";
	}

}
