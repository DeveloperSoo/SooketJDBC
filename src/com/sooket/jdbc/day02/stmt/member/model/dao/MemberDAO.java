package com.sooket.jdbc.day02.stmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sooket.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	/*
	 * 여기서 JDBC 코딩 할거임
	 * 1. 드라이버 등록
	 * 2. DBMS 연결 생성
	 * 3. Statemant 생성
	 * 4. SQL 전송
	 * 5. 결과받기
	 * 6. 자원해제
	 */
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "KH";
	private static final String PASSWORD = "KH";



	// 회원가입
	public int insertMember(Member member) {
		String query = "INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, AGE)" 
				+  "VALUES('"
				+member.getMemberId()+"','"
				+member.getMemberPwd()+"','"
				+member.getMemberName()+"','"
				+member.getGender()+"',"
				+member.getAge()+")";
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn =  this.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 예외가 발생하든 안하든 실행문을 동작시켜줌.
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// 회원정보 수정
	public int updateMember(Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '"+member.getMemberPwd()
											+"' , EMAIL = '"+member.getEmail()
											+"', PHONE = '"+member.getPhone()
											+"', ADDRESS = '"+member.getAddress()
											+"', HOBBY = '"+member.getHobby()
											+"' WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
			try {
				conn = this.getConnection();
				stmt = conn.createStatement();
				// 쿼리문 실행 코드 누락 주의!
				result = stmt.executeUpdate(query);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					// finally에서 자원해제
					conn.close();
					stmt.close();			
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		return result;
	}


	// 회원 탈퇴
	public int deleteMember(String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		//String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return result;
	}


	// 전체 회원 조회
	public List<Member> selectList() {
		List<Member> mList = new ArrayList<Member>();
		String query = "SELECT * FROM MEMBER_TBL ORDER BY 1";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 커넥션 생성시 주소를 계속 반복하지않기 위해 메소드를 한개 선언
			conn = this.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// rset에 테이블 형대로 데이터가 있으나 그대로 사용못함
			// rset을 member에 담아주는 코드를 작성해주어야함.
			// 그럴때 사용하는 rset의 메소트가 next(), getString(), ...이 있으.ㅁ
			
			while(rset.next()) {		
				Member member = this.rsetToMember(rset);
				// while문이 동작하면서 도믄 레코드에 대한 정보를 
				// mList에 담을 수 있도록 add 해줌
				mList.add(member);
			}

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mList;
	}

	// 검색한 회원 정보 조회
	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		Member member = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				// rset은 멤버로 변환이 되어야함
				member = this.rsetToMember(rset);				
			}
			rset.close();
			stmt.close();
			conn.close();				
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}


	private Member rsetToMember(ResultSet rset) throws SQLException {
		
		String memberId 	= rset.getString("MEMBER_ID");
		String memberPwd 	= rset.getString("MEMBER_PWD");
		String memberName 	= rset.getString("MEMBER_NAME");
		String gender 		= rset.getString("GENDER");
		int    age 			= rset.getInt("AGE");
		String email 		= rset.getString("EMAIL");
		String phone 		= rset.getString("PHONE");
		String address 		= rset.getString("ADDRESS");
		String hobby		= rset.getString("HOBBY");
		Date   enrollDate 	= rset.getDate("ENROLL_DATE");
		Member member = new Member(memberId,memberPwd,memberName
				,gender,age,email
				,phone,address,hobby,enrollDate);
		
		return member;
	}



	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
		return conn;
	}
}
