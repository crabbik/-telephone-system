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

import com.itacademy.jd2.lg.mobile_system.dao.IAccount2RoleDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Account2Role;

public class Account2RoleDaoImpl extends AbstractDaoImpl implements IAccount2RoleDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Account2RoleDaoImpl.class);
	public static final IAccount2RoleDao ACCOUNT2ROLE_DAO = new Account2RoleDaoImpl();

	private Account2RoleDaoImpl() {
	}

	@Override
	protected String getTableName() {
		String tableName = "account_2_role";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	public Account2Role get(Integer id) {
		return executeWithConnection(new StatementAction<Account2Role>() {

			@Override
			public Account2Role execute(Statement stmt) throws SQLException {
				Account2Role account2Role = null;
				String sqlGet = "select * from account_2_role where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGet);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					account2Role = mapToAccount2Role(rs);
					LOGGER.debug("read Account2Role from the database: {}", account2Role.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return Account2Role from db: {}", account2Role);
				return account2Role;
			}
		});
	}

	private Account2Role mapToAccount2Role(ResultSet rs) throws SQLException {
		Account2Role account2Role = new Account2Role();
		account2Role.setAccountId(rs.getInt("account_id"));
		account2Role.setRoleId(rs.getInt("role_id"));
		return account2Role;
	}

	@Override
	public void update(Account2Role account2Role) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {

				preparedStatement.setInt(1, account2Role.getAccountId());
				preparedStatement.setInt(2, account2Role.getRoleId());
				preparedStatement.executeUpdate();
				LOGGER.info("update Account2Role:{}", account2Role);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdateAccount2Role = "update account_2_role set account_id=?, role_id=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateAccount2Role);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateAccount2Role);
				return preparedStatement;

			}
		});

	}

	@Override
	public int insert(Account2Role account2Role) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setInt(1, account2Role.getAccountId());
				pStmt.setInt(2, account2Role.getRoleId());
				pStmt.executeUpdate();
				LOGGER.info("insert Account2Role from db:{}", account2Role);
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
				String sqlInsertaccount2Role = "insert into account_2_role (account_id,role_id) values (?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertaccount2Role);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertaccount2Role,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});
	}

	@Override
	public List<Account2Role> getAll() {
		return executeWithConnection(new StatementAction<List<Account2Role>>() {

			@Override
			public List<Account2Role> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from account_2_role";
				LOGGER.debug("get all Account2Role SQL:{}", sqlGetAll);
				List<Account2Role> listAccount2Role = sqlGetAllAccount2Role(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listAccount2Role);
				return listAccount2Role;
			}

		});

	}

	@Override
	public List<Account2Role> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<Account2Role>>() {

			@Override
			public List<Account2Role> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from account_2_role limit %s offset %s", limit, offset);
				LOGGER.debug("get all Account2Role SQL:{}", sqlGetAll);
				List<Account2Role> listAccount2Role = sqlGetAllAccount2Role(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listAccount2Role);
				return listAccount2Role;
			}

		});

	}

	private List<Account2Role> sqlGetAllAccount2Role(String sql, Statement stmt) throws SQLException {
		List<Account2Role> listAccount2Role = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			Account2Role account2Role = mapToAccount2Role(rs);
			LOGGER.debug("read Account2Role from the database: {}", account2Role);
			listAccount2Role.add(account2Role);
			LOGGER.debug("Account2Role added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listAccount2Role;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}
