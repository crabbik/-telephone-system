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

import com.itacademy.jd2.lg.mobile_system.dao.IPhoneNumber2InvoiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.PhoneNumber2Invoice;
import com.itacademy.jd2.lg.mobile_system.dao.exception.SQLExecutionExecption;

public class PhoneNumber2InvoiceDaoImpl extends AbstractDaoImpl implements IPhoneNumber2InvoiceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumber2InvoiceDaoImpl.class);
	public static final IPhoneNumber2InvoiceDao PHONENUMBER_2_INVOICE_DAO = new PhoneNumber2InvoiceDaoImpl();

	private PhoneNumber2InvoiceDaoImpl() {
		super();
	}

	public PhoneNumber2Invoice get(Integer id) {
		return this.<PhoneNumber2Invoice>executeWithConnection(new DBAction<PhoneNumber2Invoice>() {

			@Override
			public PhoneNumber2Invoice execute(Connection c, Statement stmt) throws SQLException {
				PhoneNumber2Invoice phoneNumber2Invoice = null;
				String sqlGet = "select * from phoneNumber_2_invoice where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGet);
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
	public void insert(PhoneNumber2Invoice phoneNumber2Invoice) {
		String sqlInsert = "insert into phoneNumber_2_invoice (invoice_id,phone_number_id) values (?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, phoneNumber2Invoice.getInvoiceId());
			preparedStatement.setInt(2, phoneNumber2Invoice.getPhoneNumberId());
			preparedStatement.executeUpdate();
			LOGGER.info("insert phoneNumber2Invoice from db:{}", phoneNumber2Invoice);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public void update(PhoneNumber2Invoice phoneNumber2Invoice) {
		String sqlUpdate = "update phoneNumber_2_invoice set invoice_id=?, phone_number_id=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection(); PreparedStatement preparedStatement = c.prepareStatement(sqlUpdate)) {
			preparedStatement.setInt(1, phoneNumber2Invoice.getInvoiceId());
			preparedStatement.setInt(4, phoneNumber2Invoice.getPhoneNumberId());
			preparedStatement.executeUpdate();
			LOGGER.info("update phoneNumber2Invoice:{}", phoneNumber2Invoice);
		} catch (Exception e) {
			throw new SQLExecutionExecption(e);
		}
	}

	@Override
	public List<PhoneNumber2Invoice> getAll() {
		return this.<List<PhoneNumber2Invoice>>executeWithConnection(new DBAction<List<PhoneNumber2Invoice>>() {

			@Override
			public List<PhoneNumber2Invoice> execute(Connection c, Statement stmt) throws SQLException {
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
		return this.<List<PhoneNumber2Invoice>>executeWithConnection(new DBAction<List<PhoneNumber2Invoice>>() {

			@Override
			public List<PhoneNumber2Invoice> execute(Connection c, Statement stmt) throws SQLException {
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