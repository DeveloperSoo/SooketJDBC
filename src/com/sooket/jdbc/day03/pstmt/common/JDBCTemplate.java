package com.sooket.jdbc.day03.pstmt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
		private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		private static final String USERNAME = "KH";
		private static final String PASSWORD = "KH";
		// Connection에 싱글톤을 적용하지 않은 이유
		// Connection 싱글톤을 적용하려는 이유는  Conncection Pool(연결을 생성해서 Pooll에 넣고
		// 재사용할 수 있는 기술)을 사용하려고 하는 것이지만 싱글톤을 적용하고 Connection Pool이
		// 동작하는 코드는 없기 때문에 적용하지 않음.
		// Connection을 만드는 메소드를 가지고 있는 JDBCTemplate에 싱글톤을 적용하여 사용함.
		private static JDBCTemplate instance;

		
		private JDBCTemplate () {
			
			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		public static JDBCTemplate getInstance() {
			if(instance == null) {
				instance = new JDBCTemplate();
			}
			return instance;
		}
		
		
		
		public Connection getConnection() throws SQLException {
			Connection conn = null;	
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
				
			return conn;
		}
}
