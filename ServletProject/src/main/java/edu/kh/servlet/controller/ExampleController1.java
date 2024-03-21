package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
// Tomcat 9 이전은 javax
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Controller : 요청(Request)에 따라 어떤 Service 를 호출할지 "제어"하고,
//              어떻게 응답(Response)을 할지 "제어"하는 역할
// 시작과 끝
public class ExampleController1 extends HttpServlet{
	/* 클라이언트 --요청--> 서버(Controller -> Service -> DAO -> DB) */
	
	// Servlet 이용하기 위해서 상속
	
	// 상속 받아서 ctrl + space 누르면 오버라이딩 된 메서드들 뜸
	
	// doGet() 메서드
	// -> GET 방식 요청을 처리하는 메서드
	// HttpServlet이 정의하고 있는 메서드를 오버라이딩하여 사용
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* HttpServlet 을 상속 받으면 사용할 수 있는 객체
		 * 
		 * Servlet Container 가 만들어줌
		 * HttpServletRequest req : 요청 관련 정보
		 * 
		 * HttpServletResponse resp : 응답 관련 정보
		 * */
		
		// HttpServletRequest
		// - 클라이언트 요청 시 생성되는 객체
		// - 클라이언트의 데이터 + 요청 관련 데이터
		
		// HttpServletResponse
		// - 클라이언트 요청 시 서버에서 생성하는 객체
		// - 서버가 클라이언트에게 응답하기 위한 방법을 제공하는 객체
		
		// web.xml 에서 맵핑해주고 옴
		
		System.out.println("=== 이름, 나이를 입력받아 처리하는 코드 ===");
		
		// 요청 시 입력된 이름, 나이를 전달받아오기
		
		// Parameter : 매개변수 == 다른 곳의 값을 전달받아 올 때 사용
		// req.getParameter("name 속성값");
		// -> 요청 시 전달된 데이터 중
		//    name 속성값이 일치하는 데이터의 value 를 얻어와
		//    String 형태로 반환
		
		// HTML 에서 얻어오는 모든 값은 String
		String name = req.getParameter("inputName");
		String age = req.getParameter("inputAge");
		
		System.out.println("입력 받은 이름 : " + name);
		System.out.println("입력 받은 나이 : " + age);
		
		// Get 요청 url/요청주소?name=value&name=value 노출됨
		// http://localhost:8080/example1?inputName=홍길동&inputAge=20
		
		/*
		<< Console 창 >>
		=== 이름, 나이를 입력받아 처리하는 코드 ===
		입력 받은 이름 : 홍길동
		입력 받은 나이 : 20
		*/
		
		// 서버 -> 클라이언트 응답하기
		// HttpServletResponse 객체 이용
		
		// 응답하는 문서의 형식과 문자인코딩을 지정
		resp.setContentType("text/html; charset=UTF-8");
		
		// 서버 -> 클라이언트로 연결되는 스트림 얻어오기
		// 서버에서 클라이언트로 응답하는 방법은 응답 페이지 만들어서 보냄
		PrintWriter out = resp.getWriter(); // 서버 -> 클라이언트에게 쓰는 것(출력)
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
			out.println("<title>서버 응답 결과</title>");
		out.println("</head>");
		
		out.println("<body>");
			out.println("<h1>");
			out.println(name + "님의 나이는 " + age + "세 입니다.");
			out.println("</h1>");
		out.println("</body>");
		
		out.println("</html>");
		
		// 응답하는 문서의 형식과 문자인코딩 지정 안해줬을 때
		// HTML 한글 깨져서 들어옴
		// �띻만�숇떂�� �섏씠�� 20�� �낅땲��.
		
		// contenttype 지정해주고 f5 눌러도 안 바뀔 때
		// ctrl + shift + r (강력 새로고침)
		// 홍길동님의 나이는 20세 입니다.
		
	}
	
}
