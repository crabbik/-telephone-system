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

import com.itacademy.jd2.lg.mobile_system.dao.ITariffDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;

public class TariffDaoImpl extends AbstractDaoImpl implements ITariffDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TariffDaoImpl.class);
	public static final ITariffDao TARIFF_DAO = new TariffDaoImpl();

	private TariffDaoImpl() {
		super();
	}

	public Tariff get(Integer id) {
		return this.<Tariff>executeWithConnection(new DBAction<Tariff>() {

			@Override
			public Tariff execute(Connection c, Statement stmt) throws SQLException {
				Tariff tariff = null;
				String sqlGet = "select * from tariff where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGet);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					tariff = mapToTariff(rs);
					LOGGER.debug("read tariff from the database: {}", tariff.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return tariff from db: {}", tariff);
				return tariff;
			}

		});

	}

	private Tariff mapToTariff(ResultSet rs) throws SQLException {
		Tariff tariff = new Tariff();
		tariff.setId(rs.getInt("id"));
		tariff.setOperatorId(rs.getInt("operator_id"));
		tariff.setName(rs.getString("name"));
		tariff.setDeleted(rs.getBoolean("deleted"));
		tariff.setCreated(rs.getTimestamp("created"));
		tariff.setModified(rs.getTimestamp("modified"));
		return tariff;
	}

	@Override
	public void update(Tariff tariff) {
		String sqlUpdate = "update tariff set operator_id=?, name=?, deleted=?, modified=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection(); PreparedStatement preparedStatement = c.prepareStatement(sqlUpdate)) {
			preparedStatement.setInt(1, tariff.getOperatorId());
			preparedStatement.setString(2, tariff.getName());
			preparedStatement.setBoolean(3, tariff.isDeleted());
			preparedStatement.setTimestamp(4, tariff.getModified());
			preparedStatement.setInt(5, tariff.getId());
			preparedStatement.executeUpdate();
			LOGGER.info("update tariff:{}", tariff);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public int insert(Tariff tariff) {
		String sqlInsert = "insert into tariff (id,operator_id, name, deleted, created, modified) values (?,?,?,?,?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, tariff.getId());
			preparedStatement.setInt(2, tariff.getOperatorId());
			preparedStatement.setString(3, tariff.getName());
			preparedStatement.setBoolean(4, tariff.isDeleted());
			preparedStatement.setTimestamp(5, tariff.getCreated());
			preparedStatement.setTimestamp(6, tariff.getModified());
			preparedStatement.executeUpdate();
			LOGGER.info("insert tariff from db:{}", tariff);
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
	public List<Tariff> getAll() {
		return this.<List<Tariff>>executeWithConnection(new DBAction<List<Tariff>>() {

			@Override
			public List<Tariff> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = "select * from tariff";
				LOGGER.debug("get all tariff SQL:{}", sqlGetAll);
				List<Tariff> listTariff = sqlGetAllTariff(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listTariff);
				return listTariff;
			}

		});

	}

	@Override
	protected String getTableName() {
		String tableName = "tariff";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<Tariff> getAll(int limit, int offset) {
		return this.<List<Tariff>>executeWithConnection(new DBAction<List<Tariff>>() {

			@Override
			public List<Tariff> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from tariff limit %s offset %s", limit, offset);
				LOGGER.debug("get all tariff SQL:{}", sqlGetAll);
				List<Tariff> listTariff = sqlGetAllTariff(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listTariff);
				return listTariff;
			}

		});

	}

	private List<Tariff> sqlGetAllTariff(String sql, Statement stmt) throws SQLException {
		List<Tariff> listTariff = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			Tariff tariff = mapToTariff(rs);
			LOGGER.debug("read tariff from the database: {}", tariff);
			listTariff.add(tariff);
			LOGGER.debug("tariff added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listTariff;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}