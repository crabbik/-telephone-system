package com.itacademy.jd2.lg.ms.dao.util;

import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;
@Repository
public interface  StatementActionVoid {
	void execute(Statement stmt) throws SQLException;
}