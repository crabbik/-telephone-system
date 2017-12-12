package com.itacademy.jd2.lg.mobile_system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.itacademy.jd2.lg.mobile_system.dao.exception.DBDriverNotFoundException;

public abstract class AbstractDao {

	public AbstractDao() {
		super();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DBDriverNotFoundException(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:postgresql://hostname:port/dbname", "username",
				"password");
	}
}
