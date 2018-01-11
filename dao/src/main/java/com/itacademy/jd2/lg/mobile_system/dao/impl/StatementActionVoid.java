package com.itacademy.jd2.lg.mobile_system.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;
@Repository
public interface  StatementActionVoid {
	void execute(Statement stmt) throws SQLException;
}