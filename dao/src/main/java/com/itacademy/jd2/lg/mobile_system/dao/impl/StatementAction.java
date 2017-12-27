package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementAction<RETURN_TYPE> {
	RETURN_TYPE execute(Statement stmt) throws SQLException;
}