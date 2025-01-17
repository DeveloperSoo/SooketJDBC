package com.sooket.jdbc.day03.pstmt.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sooket.jdbc.day03.pstmt.member.model.vo.Member;

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



	// 회원가입 (Insert)
	public int insertMember(Connection conn, Member member) {
		String query = "INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, AGE)" 
				+  "VALUES('"
				+member.getMemberId()+"','"
				+member.getMemberPwd()+"','"
				+member.getMemberName()+"','"
				+member.getGender()+"',"
				+member.getAge()+")";
		
		query ="INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, AGE) VALUES(?, ?, ?, ?, ?)";
		int result = 0;
		//Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			//conn =  this.getConnection();
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			
			
			//result = stmt.executeUpdate(query);
			result = pstmt.executeUpdate();
			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 예외가 발생하든 안하든 실행문을 동작시켜줌.
			try {
				conn.close();
				stmt.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	
	// 회원정보 수정 (Update)
	public int updateMember(Connection conn, Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '"+member.getMemberPwd()
											+"' , EMAIL = '"+member.getEmail()
											+"', PHONE = '"+member.getPhone()
											+"', ADDRESS = '"+member.getAddress()
											+"', HOBBY = '"+member.getHobby()
											+"' WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		
		query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
		int result = 0;
		//Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
			try {
				//conn = this.getConnection();
				stmt = conn.createStatement();
				// 쿼리문 실행 코드 누락 주의!
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, member.getMemberPwd());
				pstmt.setString(2, member.getEmail());
				pstmt.setString(3, member.getPhone());
				pstmt.setString(4, member.getAddress());
				pstmt.setString(5, member.getHobby());
				pstmt.setString(6, member.getMemberId());
				
				//result = stmt.executeUpdate(query);
				result = pstmt.executeUpdate();
				
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					// finally에서 자원해제
					conn.close();
					stmt.close();		
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return result;
	}


	// 회원 탈퇴 (Delete)
	public int deleteMember(Connection conn, String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		//String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		query = "DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = LOWER(?)";
		int result = 0;
		//Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			//conn = this.getConnection();
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			//result = stmt.executeUpdate(query);
			result = pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return result;
	}


	// 전체 회원 조회 (SelectList)
	public List<Member> selectList(Connection conn) {
		List<Member> mList = new ArrayList<Member>();
		String query = "SELECT * FROM MEMBER_TBL ORDER BY 1";
		//Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 커넥션 생성시 주소를 계속 반복하지않기 위해 메소드를 한개 선언
			//conn = this.getConnection();
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
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
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

	// 검색한 회원 정보 조회 (SelectByOne)
	public Member selectOneById(Connection conn, String memberId) {
		/*
		 * 1. 쿼리문에 위치홀더(?)
		 * 2. PreparedStatement 생성 
		 * 3. 위치홀더에 값 셋팅
		 * 4. 쿼리문 실행(query문 전달X)
		 */
		
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?"; // 물음표로 위치홀더 표시
		Member member = null;
		//Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null; //선언을 하고
		ResultSet rset = null;
			
		try {
			//conn = this.getConnection();
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query); // 바로 쿼리문을 넣음 위에 stmt랑 비교
			pstmt.setString(1, memberId); // 쿼리문 실행 준비 완료
			// 앞에 숫자는 위치홀더의 위치이며 물음표 2개면 2개써야하는것
			
			
			//rset = stmt.executeQuery(query);	
			rset = pstmt.executeQuery();
			// 이미 앞에서 쿼리를 넣었기 때문에 출력할때 넣을 필요가 없음
			
			if(rset.next()) {
				// rset은 멤버로 변환이 되어야함
				member = this.rsetToMember(rset);				
			}
				
//		} catch (ClassNotFoundException e) {	
//			e.printStackTrace();
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
				pstmt.close();
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
