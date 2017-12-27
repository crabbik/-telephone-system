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

import com.itacademy.jd2.lg.mobile_system.dao.IServiceHistoryDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.ServiceHistory;

public class ServiceHistoryDaoImpl extends AbstractDaoImpl implements IServiceHistoryDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHistoryDaoImpl.class);
	public static final IServiceHistoryDao SERVICEHISTORY_DAO = new ServiceHistoryDaoImpl();

	private ServiceHistoryDaoImpl() {
	}

	public ServiceHistory get(Integer id) {
		return executeWithConnection(new StatementAction<ServiceHistory>() {
			@Override
			public ServiceHistory execute(Statement stmt) throws SQLException {
				ServiceHistory serviceHistory = null;
				String sqlGetServiceHistory = "select * from service_history where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGetServiceHistory);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					serviceHistory = mapToServiceHistory(rs);
					LOGGER.debug("read serviceHistory from the database: {}", serviceHistory.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return serviceHistory from db: {}", serviceHistory);
				return serviceHistory;
			}

		});

	}

	@Override
	public int insert(ServiceHistory serviceHistory) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, serviceHistory.getId());
				pStmt.setDate(2, serviceHistory.getData());
				pStmt.setInt(3, serviceHistory.getTariffItemId());
				pStmt.setInt(4, serviceHistory.getQuantity());
				pStmt.setInt(5, serviceHistory.getSum());
				pStmt.setInt(6, serviceHistory.getPhoneNumberId());
				pStmt.executeUpdate();
				LOGGER.info("insert serviceHistory from db:{}", serviceHistory);
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
				String sqlInsertServiceHistory = "insert into service_history (id,date, tariff_item_id, quantity, sum, phone_number_id) values (?,?,?,?,?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertServiceHistory);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertServiceHistory,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});

	}

	@Override
	public void update(ServiceHistory serviceHistory) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setDate(1, serviceHistory.getData());
				preparedStatement.setInt(2, serviceHistory.getTariffItemId());
				preparedStatement.setInt(3, serviceHistory.getQuantity());
				preparedStatement.setInt(4, serviceHistory.getSum());
				preparedStatement.setInt(5, serviceHistory.getPhoneNumberId());
				preparedStatement.setInt(6, serviceHistory.getId());
				preparedStatement.executeUpdate();
				LOGGER.info("update serviceHistory:{}", serviceHistory);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdateServiceHistory = "update service_history set date=?, tariff_item_id=?,quantity=?, sum=?, phone_number_id=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateServiceHistory);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateServiceHistory);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<ServiceHistory> getAll() {
		return executeWithConnection(new StatementAction<List<ServiceHistory>>() {
			@Override
			public List<ServiceHistory> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from service_history";
				LOGGER.debug("get all serviceHistory SQL:{}", sqlGetAll);
				List<ServiceHistory> listServiceHistory = sqlGetAllServiceHistory(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listServiceHistory);
				return listServiceHistory;
			}

		});

	}

	private ServiceHistory mapToServiceHistory(ResultSet rs) throws SQLException {
		ServiceHistory serviceHistory = new ServiceHistory();
		serviceHistory.setId(rs.getInt("id"));
		serviceHistory.setData(rs.getDate("date"));
		serviceHistory.setTariffItemId(rs.getInt("tariff_item_id"));
		serviceHistory.setQuantity(rs.getInt("quantity"));
		serviceHistory.setSum(rs.getInt("sum"));
		serviceHistory.setPhoneNumberId(rs.getInt("phone_number_id"));
		return serviceHistory;
	}

	@Override
	protected String getTableName() {
		String tableName = "service_history";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<ServiceHistory> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<ServiceHistory>>() {
			@Override
			public List<ServiceHistory> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from service_history limit %s offset %s", limit, offset);
				LOGGER.debug("get all serviceHistory SQL:{}", sqlGetAll);
				List<ServiceHistory> listServiceHistory = sqlGetAllServiceHistory(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listServiceHistory);
				return listServiceHistory;
			}

		});

	}

	private List<ServiceHistory> sqlGetAllServiceHistory(String sql, Statement stmt) throws SQLException {
		List<ServiceHistory> listServiceHistory = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			ServiceHistory serviceHistory = mapToServiceHistory(rs);
			LOGGER.debug("read serviceHistory from the database: {}", serviceHistory);
			listServiceHistory.add(serviceHistory);
			LOGGER.debug("serviceHistory added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listServiceHistory;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}