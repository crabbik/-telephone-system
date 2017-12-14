package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.exception.DBConfigLoadException;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;
import com.itacademy.jd2.lg.mobile_system.dao.exception.UnexpectedResultException;

public abstract class AbstractDaoImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractDaoImpl.class);
	private Properties dbProps = new Properties();;

	public AbstractDaoImpl() {
		super();
		LOGGER.info("start load config");
		try {
			Class.forName("org.postgresql.Driver");
			InputStream cpResource = this.getClass().getClassLoader()
					.getResourceAsStream("db.properties");
			dbProps.load(cpResource);
		} catch (ClassNotFoundException | IOException e) {
			throw new DBConfigLoadException(e);
		}
		LOGGER.info("DB config loaded successfully:{}", dbProps.toString());
	}

	public void remove(Integer id) {
		int rowsUpdated;

		try (Connection c = getConnection();
				Statement stmt = c.createStatement()) {

			rowsUpdated = stmt.executeUpdate(String.format(
					"delete from %s where id=%s", getTableName(), id));

		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}

		if (rowsUpdated != 1) {
			throw new UnexpectedResultException(
					"unexpected number of updated rows:" + rowsUpdated);
		}
	}

	protected abstract String getTableName();

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				String.format("jdbc:postgresql://%s:%s/%s",
						dbProps.getProperty("host"),
						dbProps.getProperty("port"),
						dbProps.getProperty("dbname")),
				dbProps.getProperty("user"), dbProps.getProperty("password"));
	}
}