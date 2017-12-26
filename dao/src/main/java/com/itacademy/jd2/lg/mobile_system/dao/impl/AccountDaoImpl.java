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

import com.itacademy.jd2.lg.mobile_system.dao.IAccountDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;

public class AccountDaoImpl extends AbstractDaoImpl implements IAccountDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);
	public static final IAccountDao ACCOUNT_DAO = new AccountDaoImpl();

	private AccountDaoImpl() {
		super();
	}

	public Account get(Integer id) {
		return this.<Account>executeWithConnection(new DBAction<Account>() {

			@Override
			public Account execute(Connection c, Statement stmt) throws SQLException {
				Account account = null;
				String sqlGet = "select * from account where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGet);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					account = mapToAccount(rs);
					LOGGER.debug("read account from the database: {}", account.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return account from db: {}", account);
				return account;
			}

		});

	}

	@Override
	public int insert(Account account) {
		String sqlInsert = "insert into account (id,email,password, created, modified) values (?,?,?,?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, account.getId());
			preparedStatement.setString(2, account.getEmail());
			preparedStatement.setString(3, account.getPassword());
			preparedStatement.setTimestamp(4, account.getCreated());
			preparedStatement.setTimestamp(5, account.getModified());
			preparedStatement.executeUpdate();
			LOGGER.info("insert account from db:{}", account);
			ResultSet rs = preparedStatement.getGeneratedKeys();
			LOGGER.debug("created ResulSet");
			rs.next();
			int id = rs.getInt("id");
			LOGGER.debug("return generated key {}", id);
			rs.close();
			LOGGER.debug("ResulSet closed");
			return id;
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public void update(Account account) {
		String sqlUpdate = "update account set email=?, password=?, modified=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection(); PreparedStatement preparedStatement = c.prepareStatement(sqlUpdate)) {
			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setTimestamp(3, account.getModified());
			preparedStatement.setInt(4, account.getId());
			preparedStatement.executeUpdate();
			LOGGER.info("update account:{}", account);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public List<Account> getAll() {
		return this.<List<Account>>executeWithConnection(new DBAction<List<Account>>() {

			@Override
			public List<Account> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = "select * from account";
				LOGGER.debug("get all account SQL:{}", sqlGetAll);
				List<Account> listAccount = sqlGetAllAccount(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listAccount);
				return listAccount;
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
	protected String getTableName() {
		String tableName = "account";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<Account> getAll(int limit, int offset) {
		return this.<List<Account>>executeWithConnection(new DBAction<List<Account>>() {

			@Override
			public List<Account> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from account limit %s offset %s", limit, offset);
				LOGGER.debug("get all account SQL:{}", sqlGetAll);
				List<Account> listAccount = sqlGetAllAccount(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listAccount);
				return listAccount;
			}

		});

	}

	private List<Account> sqlGetAllAccount(String sql, Statement stmt) throws SQLException {
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