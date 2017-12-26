package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface DBAction<RETURN_TYPE> {

	RETURN_TYPE execute(Connection c, Statement stmt) throws SQLException;
}
