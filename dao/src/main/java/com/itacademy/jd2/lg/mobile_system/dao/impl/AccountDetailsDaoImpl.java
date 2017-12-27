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

public class AccountDetailsDaoImpl extends AbstractDaoImpl implements IAccountDetailsDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDetailsDaoImpl.class);
	public static final IAccountDetailsDao ACCOUNTDETAILS_DAO = new AccountDetailsDaoImpl();

	private AccountDetailsDaoImpl() {
	}

	public AccountDetails get(Integer id) {
		return executeWithConnection(new StatementAction<AccountDetails>() {
			@Override
			public AccountDetails execute(Statement stmt) throws SQLException {
				AccountDetails accountDetails = null;
				String sqlGetAccountDetails = "select * from account_details where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGetAccountDetails);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					accountDetails = mapToAccountDetails(rs);
					LOGGER.debug("read accountDetails from the database: {}", accountDetails.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return accountDetails from db: {}", accountDetails);
				return accountDetails;
			}

		});

	}

	@Override
	public int insert(AccountDetails accountDetails) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, accountDetails.getId());
				pStmt.setString(2, accountDetails.getLastName());
				pStmt.setString(3, accountDetails.getFirstName());
				pStmt.setTimestamp(4, accountDetails.getCreated());
				pStmt.setTimestamp(5, accountDetails.getModified());
				pStmt.executeUpdate();
				LOGGER.info("insert accountDetails from db:{}", accountDetails);
				ResultSet rs = pStmt.getGeneratedKeys();
				LOGGER.debug("created ResulSet");
				rs.next();
				int id = rs.getInt("id");
				LOGGER.debug("return generated key {}", id);
				rs.close();
				LOGGER.debug("ResulSet closed");
				return id;
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlInsertAccountDetails = "insert into account_details (id, last_name,first_name, created, modified) values (?,?,?,?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertAccountDetails);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertAccountDetails,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});

	}

	@Override
	public void update(AccountDetails accountDetails) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {

				preparedStatement.setString(1, accountDetails.getLastName());
				preparedStatement.setString(2, accountDetails.getFirstName());
				preparedStatement.setTimestamp(3, accountDetails.getModified());
				preparedStatement.setInt(4, accountDetails.getId());
				preparedStatement.executeUpdate();
				LOGGER.info("update accountDetails:{}", accountDetails);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdateAccountDetails = "update account_details set  last_name=?,first_name=?,  modified=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateAccountDetails);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateAccountDetails);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<AccountDetails> getAll() {
		return executeWithConnection(new StatementAction<List<AccountDetails>>() {
			@Override
			public List<AccountDetails> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from account_details";
				LOGGER.debug("get all accountDetails SQL:{}", sqlGetAll);
				List<AccountDetails> listAccountDetails = sqlGetAllAccountDetails(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listAccountDetails);
				return listAccountDetails;
			}

		});

	}

	private AccountDetails mapToAccountDetails(ResultSet rs) throws SQLException {
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
		String tableName = "account_details";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<AccountDetails> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<AccountDetails>>() {
			@Override
			public List<AccountDetails> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from account_details limit %s offset %s", limit, offset);
				LOGGER.debug("get all accountDetails SQL:{}", sqlGetAll);
				List<AccountDetails> listAccountDetails = sqlGetAllAccountDetails(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listAccountDetails);
				return listAccountDetails;
			}

		});

	}

	private List<AccountDetails> sqlGetAllAccountDetails(String sql, Statement stmt) throws SQLException {
		List<AccountDetails> listAccountDetails = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			AccountDetails accountDetails = mapToAccountDetails(rs);
			LOGGER.debug("read accountDetails from the database: {}", accountDetails);
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