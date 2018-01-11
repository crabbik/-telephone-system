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
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.lg.mobile_system.dao.IAccountDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account;

@Repository
public class AccountDaoImpl extends AbstractDaoImpl implements IAccountDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountDaoImpl.class);
	public static final IAccountDao ACCOUNT_DAO = new AccountDaoImpl();

	private AccountDaoImpl() {
	}

	@Override
	protected String getTableName() {
		String tableName = "account";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public Account get(Integer id) {
		return executeWithConnection(new StatementAction<Account>() {

			@Override
			public Account execute(Statement stmt) throws SQLException {
				Account account = null;
				String sqlGetAccount = "select * from account where id=" + id;
				LOGGER.debug("execute {}", sqlGetAccount);
				ResultSet rs = stmt.executeQuery(sqlGetAccount);

				if (rs.next()) {
					account = mapToAccount(rs);
					LOGGER.debug("read account from the database: {}",
							account.toString());
				}
				rs.close();
				// LOGGER.debug("ResultSet closed");
				LOGGER.info("return account from db: {}", account);
				return account;
			}

		});

	}

	private Account mapToAccount(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setEmail(rs.getString("email"));
		account.setPassword(rs.getString("password"));
		account.setCreated(rs.getTimestamp("created"));
		account.setModified(rs.getTimestamp("modified"));
		return account;
	}

	@Override
	public int insert(Account account) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {
			@Override
			public PreparedStatement prepareStatement(Connection c)
					throws SQLException {
				String sqlInsert = "insert into account (email,password, created, modified) values (?,?,?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsert);
				PreparedStatement preparedStatement = c.prepareStatement(
						sqlInsert, Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt)
					throws SQLException {

				// pStmt.setInt(1, account.getId());
				pStmt.setString(1, account.getEmail());
				pStmt.setString(2, account.getPassword());
				pStmt.setTimestamp(3, account.getCreated());
				pStmt.setTimestamp(4, account.getModified());
				pStmt.executeUpdate();
				LOGGER.info("insert account from db:{}", account);
				ResultSet rs = pStmt.getGeneratedKeys();
				LOGGER.debug("created ResulSet");
				rs.next();
				int id = rs.getInt("id");
				LOGGER.debug("return generated key {}", id);
				rs.close();
				LOGGER.debug("ResulSet closed");
				return id;
			}

		});

	}

	@Override
	public void update(Account account) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(
					PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, account.getEmail());
				preparedStatement.setString(2, account.getPassword());
				preparedStatement.setTimestamp(3, account.getModified());
				preparedStatement.setInt(4, account.getId());
				preparedStatement.executeUpdate();
				LOGGER.info("update account:{}", account);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c)
					throws SQLException {

				String sqlUpdateAccount = "update account set email=?, password=?, modified=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateAccount);
				PreparedStatement preparedStatement = c
						.prepareStatement(sqlUpdateAccount);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<Account> getAll() {
		return executeWithConnection(new StatementAction<List<Account>>() {

			@Override
			public List<Account> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from account";
				LOGGER.debug("get all account SQL:{}", sqlGetAll);
				List<Account> listAccount = sqlGetAllAccount(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}",
						listAccount);
				return listAccount;
			}

		});

	}

	@Override
	public List<Account> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<Account>>() {

			@Override
			public List<Account> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format(
						"select * from account limit %s offset %s", limit,
						offset);
				LOGGER.debug("get all account SQL:{}", sqlGetAll);
				List<Account> listAccount = sqlGetAllAccount(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}",
						listAccount);
				return listAccount;
			}

		});

	}

	private List<Account> sqlGetAllAccount(String sql, Statement stmt)
			throws SQLException {
		List<Account> listAccount = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			Account account = mapToAccount(rs);
			LOGGER.debug("read account from the database: {}", account);
			listAccount.add(account);
			LOGGER.debug("account added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listAccount;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}