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

import com.itacademy.jd2.lg.mobile_system.dao.IInvoiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;

public class InvoiceDaoImpl extends AbstractDaoImpl implements IInvoiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDaoImpl.class);
	public static final IInvoiceDao INVOICE_DAO = new InvoiceDaoImpl();

	private InvoiceDaoImpl() {
		super();
	}

	public Invoice get(Integer id) {
		return this.<Invoice>executeWithConnection(new DBAction<Invoice>() {

			@Override
			public Invoice execute(Connection c, Statement stmt) throws SQLException {
				Invoice invoice = null;
				String sqlGet = "select * from invoice where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGet);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					invoice = mapToInvoice(rs);
					LOGGER.debug("read invoice from the database: {}", invoice.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return invoice from db: {}", invoice);
				return invoice;
			}

		});

	}

	@Override
	public int insert(Invoice invoice) {
		String sqlInsert = "insert into invoice (id,type, quantity, sum, month, year) values (?,?,?,?,?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, invoice.getId());
			preparedStatement.setString(2, invoice.getType());
			preparedStatement.setInt(3, invoice.getQuantity());
			preparedStatement.setInt(4, invoice.getSum());
			preparedStatement.setInt(5, invoice.getMonth());
			preparedStatement.setInt(6, invoice.getYear());
			preparedStatement.executeUpdate();
			LOGGER.info("insert invoice from db:{}", invoice);
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
	public void update(Invoice invoice) {
		String sqlUpdate = "update invoice set type=?, quantity=?, sum=?, month=?, year=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection(); PreparedStatement preparedStatement = c.prepareStatement(sqlUpdate)) {
			preparedStatement.setString(1, invoice.getType());
			preparedStatement.setInt(2, invoice.getQuantity());
			preparedStatement.setInt(3, invoice.getSum());
			preparedStatement.setInt(4, invoice.getMonth());
			preparedStatement.setInt(5, invoice.getYear());
			preparedStatement.setInt(8, invoice.getId());
			preparedStatement.executeUpdate();
			LOGGER.info("update invoice:{}", invoice);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public List<Invoice> getAll() {
		return this.<List<Invoice>>executeWithConnection(new DBAction<List<Invoice>>() {

			@Override
			public List<Invoice> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = "select * from invoice";
				LOGGER.debug("get all invoice SQL:{}", sqlGetAll);
				List<Invoice> listInvoice = sqlGetAllInvoice(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listInvoice);
				return listInvoice;
			}

		});

	}

	private Invoice mapToInvoice(ResultSet rs) throws SQLException {
		Invoice invoice = new Invoice();
		invoice.setId(rs.getInt("id"));
		invoice.setType(rs.getString("type"));
		invoice.setQuantity(rs.getInt("quantity"));
		invoice.setSum(rs.getInt("sum"));
		invoice.setMonth(rs.getInt("month"));
		invoice.setYear(rs.getInt("year"));
		return invoice;
	}

	@Override
	protected String getTableName() {
		String tableName = "invoice";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<Invoice> getAll(int limit, int offset) {
		return this.<List<Invoice>>executeWithConnection(new DBAction<List<Invoice>>() {

			@Override
			public List<Invoice> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from invoice limit %s offset %s", limit, offset);
				LOGGER.debug("get all invoice SQL:{}", sqlGetAll);
				List<Invoice> listInvoice = sqlGetAllInvoice(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listInvoice);
				return listInvoice;
			}

		});

	}

	private List<Invoice> sqlGetAllInvoice(String sql, Statement stmt) throws SQLException {
		List<Invoice> listInvoice = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			Invoice invoice = mapToInvoice(rs);
			LOGGER.debug("read invoice from the database: {}", invoice);
			listInvoice.add(invoice);
			LOGGER.debug("invoice added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listInvoice;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}