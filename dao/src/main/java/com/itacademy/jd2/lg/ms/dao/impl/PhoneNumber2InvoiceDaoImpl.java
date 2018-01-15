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

import com.itacademy.jd2.lg.ms.dao.IPhoneNumber2InvoiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber2Invoice;
@Repository
public class PhoneNumber2InvoiceDaoImpl extends AbstractDaoImpl implements IPhoneNumber2InvoiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumber2InvoiceDaoImpl.class);
	public static final IPhoneNumber2InvoiceDao PHONENUMBER_2_INVOICE_DAO = new PhoneNumber2InvoiceDaoImpl();

	private PhoneNumber2InvoiceDaoImpl() {
	}

	public PhoneNumber2Invoice get(Integer id) {
		return executeWithConnection(new StatementAction<PhoneNumber2Invoice>() {
			@Override
			public PhoneNumber2Invoice execute(Statement stmt) throws SQLException {
				PhoneNumber2Invoice phoneNumber2Invoice = null;
				String sqlGetPhoneNumber2Invoice = "select * from phoneNumber_2_invoice where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGetPhoneNumber2Invoice);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					phoneNumber2Invoice = mapToPhoneNumber2Invoice(rs);
					LOGGER.debug("read phoneNumber2Invoice from the database: {}", phoneNumber2Invoice.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return phoneNumber2Invoice from db: {}", phoneNumber2Invoice);
				return phoneNumber2Invoice;
			}

		});

	}

	@Override
	public int insert(PhoneNumber2Invoice phoneNumber2Invoice) {
		return executeWithConnection(new PreparedStatementAction<Integer>() {

			@Override
			public Integer doWithPreparedStatement(PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, phoneNumber2Invoice.getInvoiceId());
				pStmt.setInt(2, phoneNumber2Invoice.getPhoneNumberId());
				pStmt.executeUpdate();
				LOGGER.info("insert account from db: {}", phoneNumber2Invoice);
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
				String sqlInsertPhoneNumber2Invoice = "insert into phoneNumber_2_invoice (invoice_id,phone_number_id) values (?,?)";
				LOGGER.debug("insert SQL:{}", sqlInsertPhoneNumber2Invoice);
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsertPhoneNumber2Invoice,
						Statement.RETURN_GENERATED_KEYS);
				return preparedStatement;
			}
		});

	}

	@Override
	public void update(PhoneNumber2Invoice phoneNumber2Invoice) {
		executeWithConnection(new PreparedStatementActionVoid() {

			@Override
			public void doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, phoneNumber2Invoice.getInvoiceId());
				preparedStatement.setInt(4, phoneNumber2Invoice.getPhoneNumberId());
				preparedStatement.executeUpdate();
				LOGGER.info("update phoneNumber2Invoice:{}", phoneNumber2Invoice);
			}

			@Override
			public PreparedStatement prepareStatement(Connection c) throws SQLException {
				String sqlUpdatePhoneNumber2Invoice = "update phoneNumber_2_invoice set invoice_id=?, phone_number_id=? where id=?";
				LOGGER.debug("update SQL: {}", sqlUpdatePhoneNumber2Invoice);
				PreparedStatement preparedStatement = c.prepareStatement(sqlUpdatePhoneNumber2Invoice);
				return preparedStatement;
			}

		});

	}

	@Override
	public List<PhoneNumber2Invoice> getAll() {
		return executeWithConnection(new StatementAction<List<PhoneNumber2Invoice>>() {
			@Override
			public List<PhoneNumber2Invoice> execute(Statement stmt) throws SQLException {
				String sqlGetAll = "select * from phoneNumber_2_invoice";
				LOGGER.debug("get all phoneNumber2Invoice SQL:{}", sqlGetAll);
				List<PhoneNumber2Invoice> listPhoneNumber2Invoice = sqlGetAllPhoneNumber2Invoice(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listPhoneNumber2Invoice);
				return listPhoneNumber2Invoice;
			}

		});

	}

	private PhoneNumber2Invoice mapToPhoneNumber2Invoice(ResultSet rs) throws SQLException {
		PhoneNumber2Invoice phoneNumber2Invoice = new PhoneNumber2Invoice();
		phoneNumber2Invoice.setInvoiceId(rs.getInt("invoice_id"));
		phoneNumber2Invoice.setPhoneNumberId(rs.getInt("phone_number_id"));
		return phoneNumber2Invoice;
	}

	@Override
	protected String getTableName() {
		String tableName = "phoneNumber_2_invoice";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<PhoneNumber2Invoice> getAll(int limit, int offset) {
		return executeWithConnection(new StatementAction<List<PhoneNumber2Invoice>>() {
			@Override
			public List<PhoneNumber2Invoice> execute(Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from phoneNumber_2_invoice limit %s offset %s", limit,
						offset);
				LOGGER.debug("get all user SQL:{}", sqlGetAll);
				List<PhoneNumber2Invoice> listPhoneNumber2Invoice = sqlGetAllPhoneNumber2Invoice(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listPhoneNumber2Invoice);
				return listPhoneNumber2Invoice;
			}

		});

	}

	private List<PhoneNumber2Invoice> sqlGetAllPhoneNumber2Invoice(String sql, Statement stmt) throws SQLException {
		List<PhoneNumber2Invoice> listPhoneNumber2Invoice = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			PhoneNumber2Invoice phoneNumber2Invoice = mapToPhoneNumber2Invoice(rs);
			LOGGER.debug("read phoneNumber2Invoice from the database: {}", phoneNumber2Invoice);
			listPhoneNumber2Invoice.add(phoneNumber2Invoice);
			LOGGER.debug("phoneNumber2Invoice added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listPhoneNumber2Invoice;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}