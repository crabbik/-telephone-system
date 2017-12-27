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

import com.itacademy.jd2.lg.mobile_system.dao.IOperatorDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Operator;

public class OperatorDaoImpl extends AbstractDaoImpl implements IOperatorDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperatorDaoImpl.class);
	public static final IOperatorDao OPERATOR_DAO = new OperatorDaoImpl();

	private OperatorDaoImpl() {
	}

	public Operator get(Integer id) {
		return executeWithConnection(new StatementAction<Operator>() {
			@Override
			public Operator execute(Statement stmt) throws SQLException {
				Operator operator = null;
				String sqlGetOperator = "select * from operator where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGetOperator);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					operator = mapToOperator(rs);
					LOGGER.debug("read operator from the database: {}", operator.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return operator from db: {}", operator);
				return operator;
			}

		});

	}

	@Override
	public int insert(Operator operator) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, operator.getId());
				pStmt.setString(2, operator.getTitle());
				pStmt.setBoolean(3, operator.isDeleted());
				pStmt.setTimestamp(4, operator.getCreated());
				pStmt.setTimestamp(5, operator.getModified());
				pStmt.executeUpdate();
				LOGGER.info("insert operator from db:{}", operator);
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
				String sqlInsertOperator = "insert into operator (id,title, deleted, created, modified) values (?,?,?,?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertOperator);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertOperator,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});

	}

	@Override
	public void update(Operator operator) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, operator.getTitle());
				preparedStatement.setBoolean(2, operator.isDeleted());
				preparedStatement.setTimestamp(3, operator.getCreated());
				preparedStatement.setTimestamp(4, operator.getModified());
				preparedStatement.setInt(5, operator.getId());
				preparedStatement.executeUpdate();
				LOGGER.info("update operator:{}", operator);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdateOperator = "update operator set title=?, deleted=?, created=?, modified=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateOperator);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateOperator);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<Operator> getAll() {
		return executeWithConnection(new StatementAction<List<Operator>>() {
			@Override
			public List<Operator> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from operator";
				LOGGER.debug("get all operator SQL:{}", sqlGetAll);
				List<Operator> listOperator = sqlGetAllOperator(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listOperator);
				return listOperator;
			}

		});

	}

	private Operator mapToOperator(ResultSet rs) throws SQLException {
		Operator operator = new Operator();
		operator.setId(rs.getInt("id"));
		operator.setTitle(rs.getString("title"));
		operator.setDeleted(rs.getBoolean("deleted"));
		operator.setCreated(rs.getTimestamp("created"));
		operator.setModified(rs.getTimestamp("modified"));
		return operator;
	}

	@Override
	protected String getTableName() {
		String tableName = "operator";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<Operator> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<Operator>>() {
			@Override
			public List<Operator> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from operator limit %s offset %s", limit, offset);
				LOGGER.debug("get all operator SQL:{}", sqlGetAll);
				List<Operator> listOperator = sqlGetAllOperator(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listOperator);
				return listOperator;
			}

		});

	}

	private List<Operator> sqlGetAllOperator(String sql, Statement stmt) throws SQLException {
		List<Operator> listOperator = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			Operator operator = mapToOperator(rs);
			LOGGER.debug("read operator from the database: {}", operator);
			listOperator.add(operator);
			LOGGER.debug("operator added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listOperator;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}