package com.itacademy.jd2.lg.ms.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;
@Repository
public interface PreparedStatementAction<RETURN_TYPE> {
	RETURN_TYPE doWithPreparedStatement(PreparedStatement preparedStatement) throws SQLException;

	PreparedStatement prepareStatement(Connection c) throws SQLException;
}