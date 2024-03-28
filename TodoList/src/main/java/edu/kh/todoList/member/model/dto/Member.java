package edu.kh.todoList.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// ToString 쓰고 ctrl spacebar 하면 lombok 임포트됨

@AllArgsConstructor // 모든 필드에 대한 매개변수 생성자
@NoArgsConstructor // 기본생성자
@Getter
@Setter
@ToString
public class Member {
	
	// 필드
	// lombok 라이브러리 : getter/setter, 생성자, toString() 자동완성 라이브러리
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberNickname;
	private String enrollDate;
	private String memberDeleteFlag;
}
