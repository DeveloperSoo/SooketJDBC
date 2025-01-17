package com.sooket.jdbc.day02.stmt.member.view;

import java.util.*;

import com.sooket.jdbc.day02.stmt.member.controller.MemberController;
import com.sooket.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {
	private static final String SUCCES_MSG = "[서비스 성공]";
	private static final String FAIL_MSG = "[서비스 실패]";
	private static final String NO_DATA_FOUND = "[데이터가 없습니다.]";
	private static final String END_MSG = "[프로그램 종료.]";
	private static final String WRONG_NUM = "[번호를 다시 입력할 기회를 주지]";
	private Scanner sc;
	private MemberController manage;
	
	public MemberView() {
		sc = new Scanner(System.in);
		manage = new MemberController();
	}
	

	public void startProgram() {
		finish:
			
		while(true) {
			int menu = this.showMainMenu();
			
			switch(menu) {

			case 1 :
				// 회원정보 입력
				Member member = this.inputMember();
				// 회원정보 저장
				int result = manage.insertMember(member);
				// 결과 여부 체크
				if(result > 0) {
					//성공
					this.showMessage(SUCCES_MSG);
				}else {
					//실패
					this.showMessage(FAIL_MSG);
				}				
				break;
				
				
			case 2 :
				// 회원 정보 수정
				// 아이디, 비번, 이름, 성별, 나이, 이메일, 폰, 주소, 취미
				// -> 비번, 이메일, 폰, 주소, 취미
				// UPDATE MEMBER_TBL  
				// SET MEMBER_PWD = 'qwer1234', EMAIL = 'khuser01@naver.com
				// , PHONE = '01046175296', 
				// ADDRESS = '경기도 남양주시' , HOBBY = '코딩, 수영' 
				// WHERE MEMBER_ID = 'user04';
				// - 데이터가 있을 경우 수정, 없으면 NO_DATA_FOUND 출력
				String memberId = this.inputMemberId("수정");
				member = manage.searchOneById(memberId);
				if(member != null) {
					// 정보수정
					member = this.modifyMember(memberId);
					result = manage.updateMember(member);
					if(result > 0) {
						this.showMessage(SUCCES_MSG);
					}else {
						this.showMessage(FAIL_MSG);
					}
					
				} else {
					this.showMessage(NO_DATA_FOUND);
				}
				
				break;
				
			case 3 : 
				memberId = this.inputMemberId("탈퇴");
				result = manage.deleteMember(memberId);
				if(result > 0) {
					this.showMessage(SUCCES_MSG);
				}else {
					this.showMessage(FAIL_MSG);
				}
				break;
				
			case 4 : 
				List<Member>mList = manage.selectList();
				this.viewAllMember(mList);
				break;
				
			case 5 : 
				// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = 'user01';
				// SELECT를 실행한 결과는 딱 1개 나옴 List가 아닌
				// Member로 받아야함.
				memberId = this.inputMemberId("검색");
				member = manage.searchOneById(memberId);
				if(member != null) {
					this.viewOneMember(member);
				}else {
					this.showMessage(NO_DATA_FOUND);
				}
				break;
				
			case 0 :
				this.showMessage(END_MSG);
				break finish;
				
			default : 
				this.showMessage(WRONG_NUM);
				break ;	
			}			
		}				
	}

	// 수정할 회원정보 입력 받기
	private Member modifyMember(String memberId) {
		System.out.println("======수정할 정보 입력 ======");
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();		
		System.out.print("폰 : ");
		String phone = sc.next();
		System.out.print("주소 : "); // next()는 띄어쓰기, 개행 입력 불가능(처리X) 띄어쓰기가 있으니까 nextLine으로
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		// 입력한 데이터를 객체 초기화함. 객체 생성됨
		Member member = new Member(memberId, memberPwd, email,phone,address,hobby);
		
		// 호출한 곳에서 쓸 수 있도록 member 리턴함.
		return member;
	}

	// 메인메뉴 출력임
	private int showMainMenu() {
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 정보 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 회원 정보 조회(ALL)");
		System.out.println("5. 회원 검색(아이디)");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >> ");
		int choice = sc.nextInt();
		return choice;
	}

	
	private void showMember(Member member) {
		System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+
				member.getGender()+"\t\t"+member.getAge()+"\t\t"
				+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());	
	}


	private void viewOneMember(Member member) {
		System.out.println("검색한 정보 출력");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		this.showMember(member);

	}


	// 전체 회원 목록 출력
	private void viewAllMember(List<Member> mList) {
		System.out.println("====== 회원 정보 출력 ======");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		// for ~ each 문으로 전체 List 순방
		for(Member member : mList) {
				this.showMember(member);
		}
	}


	private void showMessage(String msg) {
		System.out.println(msg);
	}


	private String inputMemberId(String category) {
		System.out.print(category+"할 아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}


	private Member inputMember() {
		System.out.println("====== 회원 정보 입력 ====== ");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M,F) : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		Member member = new Member(memberId, memberPwd, memberName, gender , age);
		return member;
	}
}
