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
	private Properties dbProps;

	private static String DB_CONNECTION_STRING;

	public AbstractDaoImpl() {
		super();
		LOGGER.info("start load config");
		try {
			Class.forName("org.postgresql.Driver");
			dbProps = new Properties();
			InputStream cpResource = this.getClass().getClassLoader()
					.getResourceAsStream("db.properties");
			dbProps.load(cpResource);
			LOGGER.debug("load config");
			DB_CONNECTION_STRING = String.format("jdbc:postgresql://%s:%s/%s",
					dbProps.getProperty("host"), dbProps.getProperty("port"),
					dbProps.getProperty("dbname"));
		} catch (ClassNotFoundException | IOException e) {
			throw new DBConfigLoadException(e);
		}
		LOGGER.info("DB config loaded successfully:{}", dbProps.toString());
	}

	public void remove(Integer id) {
		int rowsUpdated;
		String sqlRemove = String.format("delete from %s where %s=%s",
				getTableName(), getIdName(), id);
		LOGGER.debug(
				"the name of the table and id data to delete db was obtained: {}",
				getTableName(), id);
		LOGGER.debug("remove SQL:{}", sqlRemove);

		try (Connection c = getConnection();
				Statement stmt = c.createStatement()) {

			rowsUpdated = stmt.executeUpdate(String.format(
					"delete from %s where id=%s", getTableName(), id));
			LOGGER.debug("returned the number of deleted data: {}", rowsUpdated);
			LOGGER.info("data remove from db");
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}

		if (rowsUpdated != 1) {
			throw new UnexpectedResultException(
					"unexpected number of updated rows:" + rowsUpdated);
		}
	}

	protected abstract String getTableName();

	protected abstract String getIdName();

	protected Connection getConnection() throws SQLException {
		LOGGER.debug("retrieve new db connection");
		return DriverManager.getConnection(DB_CONNECTION_STRING,
				dbProps.getProperty("user"), dbProps.getProperty("password"));
	}

	protected <T> T executeWithConnection(final DBAction<T> dbAction) {
		try (Connection c = getConnection();
				Statement stmt = c.createStatement()) {
			return dbAction.execute(c, stmt);
		} catch (final Exception e) {
			throw new SQLExecutionExecption(e);
		}

	}
}