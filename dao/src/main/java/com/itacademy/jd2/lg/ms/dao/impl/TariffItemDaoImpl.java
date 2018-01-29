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

import com.itacademy.jd2.lg.ms.dao.ITariffItemDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;

@Repository
public class TariffItemDaoImpl extends AbstractDaoImpl implements ITariffItemDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TariffItemDaoImpl.class);
	public static final ITariffItemDao TARIFFITEM_DAO = new TariffItemDaoImpl();

	private TariffItemDaoImpl() {
	}

	@Override
	public TariffItem get(Integer id) {
		return executeWithConnection(new StatementAction<TariffItem>() {
			@Override
			public TariffItem execute(Statement stmt) throws SQLException {
				TariffItem tariffItem = null;
				String sqlGetTariffItem = "select * from tariff_item where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGetTariffItem);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					tariffItem = mapToTariffItem(rs);
					LOGGER.debug("read tariffItem from the database: {}", tariffItem.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return tariffItem from db: {}", tariffItem);
				return tariffItem;
			}

		});

	}

	@Override
	public int insert(TariffItem tariffItem) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setInt(1, tariffItem.getId());
				pStmt.setInt(2, tariffItem.getTariffId());
				pStmt.setInt(3, tariffItem.getServiceId());
				pStmt.setInt(4, tariffItem.getCost());
				pStmt.setBoolean(5, tariffItem.isDeleted());
				pStmt.setTimestamp(6, tariffItem.getCreated());
				pStmt.setTimestamp(7, tariffItem.getModified());
				pStmt.executeUpdate();
				LOGGER.info("insert tariffItem from db:{}", tariffItem);
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
				String sqlInsertTariffItem = "insert into tariff_item (id,tariff_id, service_id, cost, deleted, created, modified) values (?,?,?,?,?,?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertTariffItem);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertTariffItem,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});

	}

	@Override
	public void update(TariffItem tariffItem) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, tariffItem.getTariffId());
				preparedStatement.setInt(2, tariffItem.getServiceId());
				preparedStatement.setInt(3, tariffItem.getCost());
				preparedStatement.setBoolean(4, tariffItem.isDeleted());
				preparedStatement.setTimestamp(5, tariffItem.getModified());
				preparedStatement.setInt(6, tariffItem.getId());
				preparedStatement.executeUpdate();
				LOGGER.info("update tariffItem:{}", tariffItem);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdateTariffItem = "update tariff_item set tariff_id=?, service_id=?, cost=?, deleted=?, modified=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateTariffItem);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateTariffItem);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<TariffItem> getAll() {
		return executeWithConnection(new StatementAction<List<TariffItem>>() {

			@Override
			public List<TariffItem> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from tariff_item";
				LOGGER.debug("get all tariffItem SQL:{}", sqlGetAll);
				List<TariffItem> listTariffItem = sqlGetAllTariffItem(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listTariffItem);
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
		String tableName = "tariff_item";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<TariffItem> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<TariffItem>>() {

			@Override
			public List<TariffItem> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from tariff_item limit %s offset %s", limit, offset);
				LOGGER.debug("get all tariffItem SQL:{}", sqlGetAll);
				List<TariffItem> listTariffItem = sqlGetAllTariffItem(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listTariffItem);
				return listTariffItem;
			}

		});

	}

	private List<TariffItem> sqlGetAllTariffItem(String sql, Statement stmt) throws SQLException {
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

}