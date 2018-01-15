package com.itacademy.jd2.lg.ms.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;
@Repository
public interface StatementAction<RETURN_TYPE> {
	RETURN_TYPE execute(Statement stmt) throws SQLException;
}