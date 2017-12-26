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

import com.itacademy.jd2.lg.mobile_system.dao.IServiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Service;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;

public class ServiceDaoImpl extends AbstractDaoImpl implements IServiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDaoImpl.class);
	public static final IServiceDao SERVICE_DAO = new ServiceDaoImpl();

	private ServiceDaoImpl() {
		super();
	}

	public Service get(Integer id) {
		return this.<Service>executeWithConnection(new DBAction<Service>() {

			@Override
			public Service execute(Connection c, Statement stmt) throws SQLException {
				Service service = null;
				String sqlGet = "select * from service where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGet);
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
		String sqlInsert = "insert into service (id,type, unit, deleted, created, modified) values (?,?,?,?,?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, service.getId());
			preparedStatement.setString(2, service.getType());
			preparedStatement.setString(3, service.getUnit());
			preparedStatement.setBoolean(4, service.isDeleted());
			preparedStatement.setTimestamp(5, service.getCreated());
			preparedStatement.setTimestamp(6, service.getModified());
			preparedStatement.executeUpdate();
			LOGGER.info("insert service from db:{}", service);
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
	public void update(Service service) {
		String sqlUpdate = "update service set type=?, unit=?, deleted=?, modified=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection(); PreparedStatement preparedStatement = c.prepareStatement(sqlUpdate)) {
			preparedStatement.setString(1, service.getType());
			preparedStatement.setString(2, service.getUnit());
			preparedStatement.setBoolean(3, service.isDeleted());
			preparedStatement.setTimestamp(4, service.getModified());
			preparedStatement.setInt(5, service.getId());
			preparedStatement.executeUpdate();
			LOGGER.info("update service:{}", service);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public List<Service> getAll() {
		return this.<List<Service>>executeWithConnection(new DBAction<List<Service>>() {

			@Override
			public List<Service> execute(Connection c, Statement stmt) throws SQLException {
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
		return this.<List<Service>>executeWithConnection(new DBAction<List<Service>>() {

			@Override
			public List<Service> execute(Connection c, Statement stmt) throws SQLException {
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

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}