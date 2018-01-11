package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.itacademy.jd2.lg.mobile_system.dao.exception.DBConfigLoadException;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionException;
import com.itacademy.jd2.lg.mobile_system.dao.exception.UnexpectedResultException;

public abstract class AbstractDaoImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractDaoImpl.class);

	private static String DB_CONNECTION_STRING;

	@Value("${host}")
	private String dbHost;
	@Value("${port}")
	private String dbPort;
	@Value("${dbname}")
	private String dbName;
	@Value("${user}")
	private String dbUser;
	@Value("${password}")
	private String dbPassword;

	@PostConstruct
	private void init() {
		LOGGER.debug("start load config");
		try {
			Class.forName("org.postgresql.Driver");

			DB_CONNECTION_STRING = String.format("jdbc:postgresql://%s:%s/%s",
					dbHost, dbPort, dbName);

		} catch (ClassNotFoundException e) {

			throw new DBConfigLoadException(e);
		}

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
			throw new SQLExecutionException(e);
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
		return DriverManager.getConnection(DB_CONNECTION_STRING, dbUser,
				dbPassword);
	}

	protected <T> T executeWithConnection(final StatementAction<T> dbAction) {
		try (Connection c = getConnection();
				Statement stmt = c.createStatement()) {
			return dbAction.execute(stmt);
		} catch (final Exception e) {
			throw new SQLExecutionException(e);
		}

	}

	protected <T> T executeWithConnection(
			final PreparedStatementAction<T> dbAction) {
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = dbAction
						.prepareStatement(c)) {
			return dbAction.doWithPreparedStatement(preparedStatement);
		} catch (final Exception e) {
			throw new SQLExecutionException(e);
		}

	}

	protected void executeWithConnection(
			final PreparedStatementActionVoid dbAction) {
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = dbAction
						.prepareStatement(c)) {
			dbAction.doWithPreparedStatement(preparedStatement);
		} catch (final Exception e) {
			throw new SQLExecutionException(e);
		}

	}

}