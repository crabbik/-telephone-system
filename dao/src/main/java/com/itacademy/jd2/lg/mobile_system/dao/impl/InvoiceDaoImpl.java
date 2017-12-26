package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.IInvoiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Invoice;

public class InvoiceDaoImpl extends AbstractDaoImpl implements IInvoiceDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDaoImpl.class);
	public static final IInvoiceDao INVOICE_DAO = new InvoiceDaoImpl();

	private InvoiceDaoImpl() {
		super();
	}

	public User get(Integer id) {
		return this.<User>executeWithConnection(new DBAction<User>() {

			@Override
			public User execute(Connection c, Statement stmt) throws SQLException {
				User user = null;
				String sqlGet = "select * from \"user\" where id=" + id;
				ResultSet rs = stmt.executeQuery(sqlGet);
				LOGGER.debug("created ResultSet");
				if (rs.next()) {
					user = mapToUser(rs);
					LOGGER.debug("read user from the database: {}", user.toString());
				}
				rs.close();
				LOGGER.debug("ResultSet closed");
				LOGGER.info("return user from db: {}", user);
				return user;
			}

		});

	}

	@Override
	public void insert(User user) {
		String sqlInsert = "insert into \"user\" (id,first_name, last_name, passport_serias, passport_number, city_id, phone_number_id, created, modified) values (?,?,?,?,?,?,?,?,?)";
		LOGGER.debug("insert SQL:{}", sqlInsert);
		try (Connection c = getConnection();
				PreparedStatement preparedStatement = c.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getPassportSerias());
			preparedStatement.setInt(5, user.getPassportNumber());
			preparedStatement.setInt(6, user.getCityId());
			preparedStatement.setInt(7, user.getPhoneNumberId());
			preparedStatement.setTimestamp(8, user.getCreated());
			preparedStatement.setTimestamp(9, user.getModified());
			preparedStatement.executeUpdate();
			LOGGER.info("insert user from db:{}", user);
		} catch (Exception e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void update(User user) {
		String sqlUpdate = "update \"user\" set first_name=?, last_name=?, passport_serias=?, passport_number=?, city_id=?, phone_number_id=?, modified=? where id=?";
		LOGGER.debug("update SQL: {}", sqlUpdate);
		try (Connection c = getConnection(); PreparedStatement preparedStatement = c.prepareStatement(sqlUpdate)) {
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getPassportSerias());
			preparedStatement.setInt(4, user.getPassportNumber());
			preparedStatement.setInt(5, user.getCityId());
			preparedStatement.setInt(6, user.getPhoneNumberId());
			preparedStatement.setTimestamp(7, user.getModified());
			preparedStatement.setInt(8, user.getId());
			preparedStatement.executeUpdate();
			LOGGER.info("update user:{}", user);
		} catch (Exception e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public List<User> getAll() {
		return this.<List<User>>executeWithConnection(new DBAction<List<User>>() {

			@Override
			public List<User> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = "select * from \"user\"";
				LOGGER.debug("get all user SQL:{}", sqlGetAll);
				List<User> listUser = sqlGetAllUser(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listUser);
				return listUser;
			}

		});

	}

	private User mapToUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setPassportSerias(rs.getString("passport_serias"));
		user.setPassportNumber(rs.getInt("passport_number"));
		user.setCityId(rs.getInt("city_id"));
		user.setPhoneNumberId(rs.getInt("phone_number_id"));
		user.setCreated(rs.getTimestamp("created"));
		user.setModified(rs.getTimestamp("modified"));
		return user;
	}

	@Override
	protected String getTableName() {
		String tableName = "\"user\"";
		LOGGER.debug("return table name to remove data:{}", tableName);
		return tableName;
	}

	@Override
	public List<User> getAll(int limit, int offset) {
		return this.<List<User>>executeWithConnection(new DBAction<List<User>>() {

			@Override
			public List<User> execute(Connection c, Statement stmt) throws SQLException {
				String sqlGetAll = String.format("select * from \"user\" limit %s offset %s", limit, offset);
				LOGGER.debug("get all user SQL:{}", sqlGetAll);
				List<User> listUser = sqlGetAllUser(sqlGetAll, stmt);
				LOGGER.info("received a list of data from the database:{}", listUser);
				return listUser;
			}

		});

	}

	private List<User> sqlGetAllUser(String sql, Statement stmt) throws SQLException {
		List<User> listUser = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(sql);
		LOGGER.debug("created ResulSet");
		while (rs.next()) {
			User user = mapToUser(rs);
			LOGGER.debug("read user from the database: {}", user);
			listUser.add(user);
			LOGGER.debug("user added from list");
		}
		rs.close();
		LOGGER.debug("ResulSet closed");
		return listUser;
	}

	@Override
	protected String getIdName() {
		String idName = "id";
		LOGGER.debug("return id name to remove data:{}", idName);
		return idName;
	}

}