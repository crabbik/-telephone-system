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

public class InvoiceDaoImpl extends AbstractDaoImpl implements IInvoiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDaoImpl.class);
	public static final IInvoiceDao INVOICE_DAO = new InvoiceDaoImpl();

	private InvoiceDaoImpl() {
	}

	public Invoice get(Integer id) {
		return executeWithConnection(new StatementAction<Invoice>() {

			@Override
			public Invoice execute(Statement stmt) throws SQLException {
				Invoice invoice = null;
				String sqlGetInvoice = "select * from invoice where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGetInvoice);
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
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {

				pStmt.setInt(1, invoice.getId());
				pStmt.setString(2, invoice.getType());
				pStmt.setInt(3, invoice.getQuantity());
				pStmt.setInt(4, invoice.getSum());
				pStmt.setInt(5, invoice.getMonth());
				pStmt.setInt(6, invoice.getYear());
				pStmt.executeUpdate();
				LOGGER.info("insert invoice from db:{}", invoice);
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
				String sqlInsertInvoice = "insert into invoice (id,type, quantity, sum, month, year) values (?,?,?,?,?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertInvoice);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertInvoice,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});

	}

	@Override
	public void update(Invoice invoice) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {

				preparedStatement.setString(1, invoice.getType());
				preparedStatement.setInt(2, invoice.getQuantity());
				preparedStatement.setInt(3, invoice.getSum());
				preparedStatement.setInt(4, invoice.getMonth());
				preparedStatement.setInt(5, invoice.getYear());
				preparedStatement.setInt(8, invoice.getId());
				preparedStatement.executeUpdate();
				LOGGER.info("update invoice:{}", invoice);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdateInvoice = "update invoice set type=?, quantity=?, sum=?, month=?, year=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdateInvoice);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateInvoice);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<Invoice> getAll() {
		return executeWithConnection(new StatementAction<List<Invoice>>() {
			@Override
			public List<Invoice> execute(Statement stmt) throws SQLException {
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
		return executeWithConnection(new StatementAction<List<Invoice>>() {
			@Override
			public List<Invoice> execute(Statement stmt) throws SQLException {
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