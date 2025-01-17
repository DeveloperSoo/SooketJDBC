package com.sooket.jdbc.day03.pstmt.common;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
		// Connection에 싱글톤을 적용하지 않은 이유
		// Connection 싱글톤을 적용하려는 이유는  Conncection Pool(연결을 생성해서 Pooll에 넣고
		// 재사용할 수 있는 기술)을 사용하려고 하는 것이지만 싱글톤을 적용하고 Connection Pool이
		// 동작하는 코드는 없기 때문에 적용하지 않음.
		// Connection을 만드는 메소드를 가지고 있는 JDBCTemplate에 싱글톤을 적용하여 사용함.
		private static JDBCTemplate instance;

		
		private static final String FILE_NAME = "resources/dev.properties";
		private Properties prop;
		
		private JDBCTemplate () {
			
			try {
				Reader reader = new FileReader(FILE_NAME); 	// 스트림 열어서 파일 읽기
				prop = new Properties();					// 읽은 파일 사용하기
				prop.load(reader);							// 사용 준비 완료
				String driverName = prop.getProperty("driverName");
				
				
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			
				conn = DriverManager.getConnection(url, username, password);			
				
			return conn;
		}
}
