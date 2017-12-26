package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.IAccountDetailsDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.AccountDetails;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;

public class AccountDetailsDaoImpl extends AbstractDaoImpl implements
		IAccountDetailsDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountDetailsDaoImpl.class);
	public static final IAccountDetailsDao ACCOUNTDETAILS_DAO = new AccountDetailsDaoImpl();

	private AccountDetailsDaoImpl() {
		super();
	}

	public AccountDetails get(Integer id) {
		return this
				.<AccountDetails> executeWithConnection(new DBAction<AccountDetails>() {

					@Override
					public AccountDetails execute(Connection c, Statement stmt)
							throws SQLException {
						AccountDetails accountDetails = null;
						String sqlGet = "select * from \"accountDetails\" where id="
								+ id;
						ResultSet rs = stmt.executeQuery(sqlGet);
						LOGGER.debug("created ResultSet");
						if (rs.next()) {
							accountDetails = mapToAccountDetails(rs);
							LOGGER.debug(
									"read accountDetails from the database: {}",
									accountDetails.toString());
						}
						rs.close();
						LOGGER.debug("ResultSet closed");
						LOGGER.info("return accountDetails from db: {}",
								accountDetails);
						return accountDetails;
					}

				});

	}

	@Override
	public void insert(AccountDetails accountDetails) {
		String sqlInsert = "insert into \"accountDetails\" (id, last_name,first_name, created, modified) values (?,?,?,?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(
						sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, accountDetails.getId());
			preparedStatement.setString(2, accountDetails.getLastName());
			preparedStatement.setString(3, accountDetails.getFirstName());
			preparedStatement.setTimestamp(4, accountDetails.getCreated());
			preparedStatement.setTimestamp(5, accountDetails.getModified());
			preparedStatement.executeUpdate();
			LOGGER.info("insert accountDetails from db:{}", accountDetails);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public void update(AccountDetails accountDetails) {
		String sqlUpdate = "update \"accountDetails\" set  last_name=?,first_name=?,  modified=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c
						.prepareStatement(sqlUpdate)) {
			preparedStatement.setString(1, accountDetails.getLastName());
			preparedStatement.setString(2, accountDetails.getFirstName());
			preparedStatement.setTimestamp(3, accountDetails.getModified());
			preparedStatement.setInt(4, accountDetails.getId());
			preparedStatement.executeUpdate();
			LOGGER.info("update accountDetails:{}", accountDetails);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public List<AccountDetails> getAll() {
		return this
				.<List<AccountDetails>> executeWithConnection(new DBAction<List<AccountDetails>>() {

					@Override
					public List<AccountDetails> execute(Connection c,
							Statement stmt) throws SQLException {
						String sqlGetAll = "select * from \"accountDetails\"";
						LOGGER.debug("get all accountDetails SQL:{}", sqlGetAll);
						List<AccountDetails> listAccountDetails = sqlGetAllUser(
								sqlGetAll, stmt);
						LOGGER.info(
								"received a list of data from the database:{}",
								listAccountDetails);
						return listAccountDetails;
					}

				});

	}

	private AccountDetails mapToAccountDetails(ResultSet rs)
			throws SQLException {
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setId(rs.getInt("id"));
		accountDetails.setLastName(rs.getString("last_name"));
		accountDetails.setFirstName(rs.getString("first_name"));
		accountDetails.setCreated(rs.getTimestamp("created"));
		accountDetails.setModified(rs.getTimestamp("modified"));
		return accountDetails;
	}

	@Override
	protected String getTableName() {
		String tableName = "\"accountDetails\"";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<AccountDetails> getAll(int limit, int offset) {
		return this
				.<List<AccountDetails>> executeWithConnection(new DBAction<List<AccountDetails>>() {

					@Override
					public List<AccountDetails> execute(Connection c,
							Statement stmt) throws SQLException {
						String sqlGetAll = String
								.format("select * from \"accountDetails\" limit %s offset %s",
										limit, offset);
						LOGGER.debug("get all accountDetails SQL:{}", sqlGetAll);
						List<AccountDetails> listAccountDetails = sqlGetAllUser(
								sqlGetAll, stmt);
						LOGGER.info(
								"received a list of data from the database:{}",
								listAccountDetails);
						return listAccountDetails;
					}

				});

	}

	private List<AccountDetails> sqlGetAllUser(String sql, Statement stmt)
			throws SQLException {
		List<AccountDetails> listAccountDetails = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			AccountDetails accountDetails = mapToAccountDetails(rs);
			LOGGER.debug("read accountDetails from the database: {}",
					accountDetails);
			listAccountDetails.add(accountDetails);
			LOGGER.debug("accountDetails added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listAccountDetails;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}