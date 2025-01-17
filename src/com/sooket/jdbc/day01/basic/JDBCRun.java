package com.sooket.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기
	 * 6. 자원해제
	 */

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";

	public static void main(String[] args) {
		// DML JDBC
		String query = "INSERT INTO STUDENT_TBL VALUES('user02', 'pass02', '이올라', 'F', 23, 'user02@ola.com', '01082829393', '경기도 남양주시', '수영,클라이밍', DEFAULT)";
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3. Statement 생성
			Statement stmt = conn.createStatement();
			// 4. SQL전송 및 5. 결과받기
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println(result + "개 데이터가 저장되었습니다.");
			}else {
				System.out.println("데이터 저장이 완료되지 않았습니다.");
			}
			// 6. 자원해제
			stmt.close();
			conn.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
		
		public void dqlJDBC() {
		try {
			// 1. 드라이버 등록 (무조건 트라이캐치 해야함
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE"
					, "student"
					, "student");
			
			// 3. Statement 생성
			Statement stmt = conn.createStatement();
			
			// 4. SQL 전송 및 5. 결과 받기
			String query = "SELECT * FROM STUDENT_TBL";
			ResultSet rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				System.out.println("ID : " + rset.getString("Student_ID"));
				System.out.println("NAME : " + rset.getString("Student_NAME"));
				System.out.println("AGE : " + rset.getInt("AGE"));
				System.out.println("DATE : " + rset.getDate("ENROLL_DATE"));
				System.out.println("==================================");
			}
			
			// 6. 자원해제
			rset.close();
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
