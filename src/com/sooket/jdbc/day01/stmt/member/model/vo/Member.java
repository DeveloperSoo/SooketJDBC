package com.sooket.jdbc.day01.stmt.member.model.vo;

import java.sql.Date;

public class Member {
	// rset에서 꺼내온 모든 값을 담아야 하기 때문에
	// 필드의 갯수는 컬럼의 갯수와 같음
	// 필드의 자료형은 컬럼의 자료형과 매핑을 해주어야함.
	// String - varchar2
	// int    - number
	// Date   - Date
	// TimeStamp - TimeStamp
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char   gender;
	private int    age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	// Date 형식은 꼭 java.sql.Date 임포트해야함
	private Date enrollDate;
	
	public Member() {}
	


	public Member(String memberId, String memberPwd, String memberName, char gender, int age, String email,
			String phone, String address, String hobby) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}

	public Member(String memberId, String memberPwd, String memberName
			, char gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
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

	public char getGender() {
		return gender;
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
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}
}
