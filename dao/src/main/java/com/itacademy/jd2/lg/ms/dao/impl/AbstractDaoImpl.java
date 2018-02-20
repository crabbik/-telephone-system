package com.itacademy.jd2.lg.ms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.itacademy.jd2.lg.ms.dao.exception.DBConfigLoadException;
import com.itacademy.jd2.lg.ms.dao.exception.SQLExecutionException;
import com.itacademy.jd2.lg.ms.dao.exception.UnexpectedResultException;
import com.itacademy.jd2.lg.ms.dao.util.PreparedStatementAction;
import com.itacademy.jd2.lg.ms.dao.util.PreparedStatementActionVoid;
import com.itacademy.jd2.lg.ms.dao.util.StatementAction;

public abstract class AbstractDaoImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDaoImpl.class);

	@Value("jdbc:postgresql://${host}:${port}/${dbname}")
	private String dbUrl;

	@Value("${user}")
	private String dbUser;
	@Value("${password}")
	private String dbPassword;

	@PostConstruct
	private void init() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DBConfigLoadException(e);
		}

	}

	public void remove(final Integer id) {
		int rowsUpdated;
		try (Connection c = getConnection(); Statement stmt = c.createStatement()) {

			rowsUpdated = stmt.executeUpdate(String.format("delete from %s where id=%s", getTableName(), id));

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

		if (rowsUpdated != 1) {
			throw new UnexpectedResultException("unexpected number of updated rows:" + rowsUpdated);
		}
	}

	protected abstract String getTableName();

	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

	protected Connection getConnection() throws SQLException {
		LOGGER.debug("retrieve new db connection");
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}

	protected <T> T executeWithConnection(final StatementAction<T> dbAction) {
		try (Connection c = getConnection(); Statement stmt = c.createStatement()) {
			return dbAction.execute(stmt);
		} catch (final Exception e) {
			throw new SQLExecutionException(e);
		}

	}

	protected <T> T executeWithConnection(final PreparedStatementAction<T> dbAction) {
		try (Connection c = getConnection(); PreparedStatement preparedStatement = dbAction.prepareStatement(c)) {
			return dbAction.doWithPreparedStatement(preparedStatement);
		} catch (final Exception e) {
			throw new SQLExecutionException(e);
		}

	}

	protected void executeWithConnection(final PreparedStatementActionVoid dbAction) {
		try (Connection c = getConnection(); PreparedStatement preparedStatement = dbAction.prepareStatement(c)) {
			dbAction.doWithPreparedStatement(preparedStatement);
		} catch (final Exception e) {
			throw new SQLExecutionException(e);
		}

	}

}