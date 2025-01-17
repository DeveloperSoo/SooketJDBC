package com.sooket.jdbc.day03.pstmt.member.model.service;

import java.sql.*;
import java.util.List;

import com.sooket.jdbc.day03.pstmt.common.JDBCTemplate;
import com.sooket.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.sooket.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberService {
	private JDBCTemplate jdbcTemplate; 
	private MemberDAO mDao;
	
	
	public MemberService () {
		jdbcTemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO();
		
	}
	
	
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.insertMember(conn, member);
			
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}


	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = null;
			
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.deleteMember(conn, memberId);
			
		}  catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return result;
	}


	public int updateMember(Member member) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.updateMember(conn, member);		
		}  catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}	
		return result;
	}


	
	
	public List<Member> selectList() {
		Connection conn = null;
		List<Member>mList = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			mList = mDao.selectList(conn);
		}  catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return mList;
	}

	

	public Member selectOneById(String memberId) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			member = mDao.selectOneById(conn, memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return member;
	}
}
