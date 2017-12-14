package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.itacademy.jd2.lg.mobile_system.dao.exception.DBDriverNotFoundException;

public abstract class AbstractDaoImpl {

	public AbstractDaoImpl() {
		super();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DBDriverNotFoundException(e);
		}
	}

	public void remove(Integer id) {

		try (Connection c = getConnection(); Statement stmt = c.createStatement()) {

			int rowsUpdated = stmt.executeUpdate(String.format("delete from %s where id=%s", getTableName(), id));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract String getTableName();

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "password");
	}
}
