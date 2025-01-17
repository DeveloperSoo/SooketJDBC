package com.sooket.jdbc.day03.pstmt.member.controller;

import java.util.List;

import com.sooket.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.sooket.jdbc.day03.pstmt.member.model.service.MemberService;
import com.sooket.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberController {
	//private MemberDAO mDao;
	private MemberService mService;
	
	public MemberController() {
		//mDao = new MemberDAO();
		mService = new MemberService();
	}
	
	

	// DML (INSERT) 할건데 그렇게 하면 숫자(행의 갯수) 가 리턴되기 때문
	public int insertMember(Member member) {
		//int result = mDao.insertMember(member);
		int result = mService.insertMember(member);
		
		return result;
	}


	public int updateMember(Member member) {
		//int result = mDao.updateMember(member);
		int result = mService.updateMember(member);
		return result;
	}



	public int deleteMember(String memberId) {
		//int result = mDao.deleteMember(memberId);
		int result = mService.deleteMember(memberId);
		
		return result;
	}



	public List<Member> selectList() {
		//List<Member> mList = mDao.selectList();
		List<Member> mList = mService.selectList();
		// View에서 mList 사용할 수 있도록 리턴
		return mList;
	}


	public Member searchOneById(String memberId) {
		//Member member = mDao.selectOneById(memberId);
		Member member = mService.selectOneById(memberId);
		
		return member;
	}

}
