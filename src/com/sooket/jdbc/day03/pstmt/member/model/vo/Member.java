package com.sooket.jdbc.day03.pstmt.member.model.vo;

import java.sql.Date;


public class Member {
	// 필드는 SQL 테이블 컬럼이랑 무조건 일치시켜야함
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String Gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
	// 기본 생성자
	public Member() {}

	// MemberView 에서 사용할 매개변수 생성자
	public Member(String memberId, String memberPwd, String memberName, int age) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.age = age;
	}
	
	
	public Member(String memberId, String memberPwd, String memberName, String gender, int age) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.Gender = gender;
		this.age = age;
	}

	public Member(String memberId, String gender, String memberPwd, String email, String phone, String address, String hobby) {
		super();
		this.memberId = memberId;
		this.Gender = gender;
		this.memberPwd = memberPwd;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}
	
	public Member(String memberId, String memberPwd, String email, String phone, String address, String hobby) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}

	public Member(String memberId, String memberPwd, String memberName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.Gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	
	
	public String getMemberId() {
		return memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getGender() {
		return Gender;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getHobby() {
		return hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName + ", Gender="
				+ Gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}	
	
	
}
