package com.sooket.jdbc.day03.pstmt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
		private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		private static final String USERNAME = "KH";
		private static final String PASSWORD = "KH";
	
		
		public Connection getConnection() throws ClassNotFoundException, SQLException {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			return conn;
		}
}
