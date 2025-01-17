package com.sooket.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.sooket.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.sooket.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {
	private MemberDAO mDao;
	
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	

	// DML (INSERT) 할건데 그렇게 하면 숫자(행의 갯수) 가 리턴되기 때문
	public int insertMember(Member member) {
		int result = mDao.insertMember(member);
		
		return result;
	}


	public List<Member> selectList() {
		List<Member> mList = mDao.selectList();
		// vView에서 mList 사용할 수 있도록 리턴
		return mList;
	}


	public Member searchOneById(String memberId) {
		Member member = mDao.selectOneById(memberId);
		
		return member;
	}


	public int deleteMember(String memberId) {
		int result = mDao.deleteMember(memberId);
		
		return result;
	}



	public int updateMember(Member member) {
		int result = mDao.updateMember(member);
		return result;
	}

}
