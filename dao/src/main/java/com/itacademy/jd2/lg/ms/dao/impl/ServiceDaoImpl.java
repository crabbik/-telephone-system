package com.itacademy.jd2.lg.ms.dao.impl;

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

import com.itacademy.jd2.lg.ms.dao.IServiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;

@Repository
public class ServiceDaoImpl extends AbstractDaoImpl implements IServiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDaoImpl.class);
	public static final IServiceDao SERVICE_DAO = new ServiceDaoImpl();

	private ServiceDaoImpl() {
	}

	public Service get(Integer id) {
		return executeWithConnection(new StatementAction<Service>() {
			@Override
			public Service execute(Statement stmt) throws SQLException {
				Service service = null;
				String sqlGetService = "select * from service where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGetService);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					service = mapToService(rs);
					LOGGER.debug("read service from the database: {}", service.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return service from db: {}", service);
				return service;
			}

		});

	}

	@Override
	public int insert(Service service) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, service.getId());
				pStmt.setString(2, service.getType());
				pStmt.setString(3, service.getUnit());
				pStmt.setBoolean(4, service.isDeleted());
				pStmt.setTimestamp(5, service.getCreated());
				pStmt.setTimestamp(6, service.getModified());
				pStmt.executeUpdate();
				LOGGER.info("insert service from db:{}", service);
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
				String sqlInsertService = "insert into service (id,type, unit, deleted, created, modified) values (?,?,?,?,?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertService);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertService,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});

	}

	@Override
	public void update(Service service) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, service.getType());
				preparedStatement.setString(2, service.getUnit());
				preparedStatement.setBoolean(3, service.isDeleted());
				preparedStatement.setTimestamp(4, service.getModified());
				preparedStatement.setInt(5, service.getId());
				preparedStatement.executeUpdate();
				LOGGER.info("update service:{}", service);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdateService = "update service set type=?, unit=?, deleted=?, modified=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateService);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateService);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<Service> getAll() {
		return executeWithConnection(new StatementAction<List<Service>>() {
			@Override
			public List<Service> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from service";
				LOGGER.debug("get all service SQL:{}", sqlGetAll);
				List<Service> listService = sqlGetAllService(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listService);
				return listService;
			}

		});

	}

	private Service mapToService(ResultSet rs) throws SQLException {
		Service service = new Service();
		service.setId(rs.getInt("id"));
		service.setType(rs.getString("type"));
		service.setUnit(rs.getString("unit"));
		service.setDeleted(rs.getBoolean("deleted"));
		service.setCreated(rs.getTimestamp("created"));
		service.setModified(rs.getTimestamp("modified"));
		return service;
	}

	@Override
	protected String getTableName() {
		String tableName = "service";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<Service> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<Service>>() {
			@Override
			public List<Service> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from service limit %s offset %s", limit, offset);
				LOGGER.debug("get all service SQL:{}", sqlGetAll);
				List<Service> listService = sqlGetAllService(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listService);
				return listService;
			}

		});

	}

	private List<Service> sqlGetAllService(String sql, Statement stmt) throws SQLException {
		List<Service> listService = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			Service service = mapToService(rs);
			LOGGER.debug("read service from the database: {}", service);
			listService.add(service);
			LOGGER.debug("service added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listService;
	}

}