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

import com.itacademy.jd2.lg.mobile_system.dao.ITariffItemDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;

public class TariffItemDaoImpl extends AbstractDaoImpl implements
		ITariffItemDao {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TariffItemDaoImpl.class);
	public static final ITariffItemDao TARIFFITEM_DAO = new TariffItemDaoImpl();

	private TariffItemDaoImpl() {
		super();
	}

	public TariffItem get(Integer id) {
		return this
				.<TariffItem> executeWithConnection(new DBAction<TariffItem>() {

					@Override
					public TariffItem execute(Connection c, Statement stmt)
							throws SQLException {
						TariffItem tariffItem = null;
						String sqlGet = "select * from \"tariffItem\" where id="
								+ id;
						ResultSet rs = stmt.executeQuery(sqlGet);
						LOGGER.debug("created ResultSet");
						if (rs.next()) {
							tariffItem = mapToTariffItem(rs);
							LOGGER.debug(
									"read tariffItem from the database: {}",
									tariffItem.toString());
						}
						rs.close();
						LOGGER.debug("ResultSet closed");
						LOGGER.info("return tariffItem from db: {}", tariffItem);
						return tariffItem;
					}

				});

	}

	@Override
	public void insert(TariffItem tariffItem) {
		String sqlInsert = "insert into \"tariffItem\" (id,tariff_id, service_id, cost, deleted, created, modified) values (?,?,?,?,?,?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(
						sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, tariffItem.getId());
			preparedStatement.setInt(2, tariffItem.getTariffId());
			preparedStatement.setInt(3, tariffItem.getServiceId());
			preparedStatement.setInt(4, tariffItem.getCost());
			preparedStatement.setBoolean(5, tariffItem.isDeleted());
			preparedStatement.setTimestamp(6, tariffItem.getCreated());
			preparedStatement.setTimestamp(7, tariffItem.getModified());
			preparedStatement.executeUpdate();
			LOGGER.info("insert tariffItem from db:{}", tariffItem);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public void update(TariffItem tariffItem) {
		String sqlUpdate = "update \"tariffItem\" set tariff_id=?, service_id=?, cost=?, deleted=?, modified=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c
						.prepareStatement(sqlUpdate)) {
			preparedStatement.setInt(1, tariffItem.getTariffId());
			preparedStatement.setInt(2, tariffItem.getServiceId());
			preparedStatement.setInt(3, tariffItem.getCost());
			preparedStatement.setBoolean(4, tariffItem.isDeleted());
			preparedStatement.setTimestamp(5, tariffItem.getModified());
			preparedStatement.setInt(6, tariffItem.getId());
			preparedStatement.executeUpdate();
			LOGGER.info("update tariffItem:{}", tariffItem);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public List<TariffItem> getAll() {
		return this
				.<List<TariffItem>> executeWithConnection(new DBAction<List<TariffItem>>() {

					@Override
					public List<TariffItem> execute(Connection c, Statement stmt)
							throws SQLException {
						String sqlGetAll = "select * from \"tariffItem\"";
						LOGGER.debug("get all tariffItem SQL:{}", sqlGetAll);
						List<TariffItem> listTariffItem = sqlGetAllTariffItem(
								sqlGetAll, stmt);
						LOGGER.info(
								"received a list of data from the database:{}",
								listTariffItem);
						return listTariffItem;
					}

				});

	}

	private TariffItem mapToTariffItem(ResultSet rs) throws SQLException {
		TariffItem tariffItem = new TariffItem();
		tariffItem.setId(rs.getInt("id"));
		tariffItem.setTariffId(rs.getInt("tariff_id"));
		tariffItem.setServiceId(rs.getInt("service_id"));
		tariffItem.setCost(rs.getInt("cost"));
		tariffItem.setDeleted(rs.getBoolean("deleted"));
		tariffItem.setCreated(rs.getTimestamp("created"));
		tariffItem.setModified(rs.getTimestamp("modified"));
		return tariffItem;
	}

	@Override
	protected String getTableName() {
		String tableName = "\"tariffItem\"";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<TariffItem> getAll(int limit, int offset) {
		return this
				.<List<TariffItem>> executeWithConnection(new DBAction<List<TariffItem>>() {

					@Override
					public List<TariffItem> execute(Connection c, Statement stmt)
							throws SQLException {
						String sqlGetAll = String
								.format("select * from \"tariffItem\" limit %s offset %s",
										limit, offset);
						LOGGER.debug("get all tariffItem SQL:{}", sqlGetAll);
						List<TariffItem> listTariffItem = sqlGetAllTariffItem(
								sqlGetAll, stmt);
						LOGGER.info(
								"received a list of data from the database:{}",
								listTariffItem);
						return listTariffItem;
					}

				});

	}

	private List<TariffItem> sqlGetAllTariffItem(String sql, Statement stmt)
			throws SQLException {
		List<TariffItem> listTariffItem = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			TariffItem tariffItem = mapToTariffItem(rs);
			LOGGER.debug("read tariffItem from the database: {}", tariffItem);
			listTariffItem.add(tariffItem);
			LOGGER.debug("tariffItem added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listTariffItem;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}