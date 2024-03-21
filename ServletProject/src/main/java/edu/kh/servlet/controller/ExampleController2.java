package edu.kh.servlet.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet 관련 코드를 작성하기 위해서는 HttpServlet 클래스 반드시 상속 받아야함

/* @Override
 * 어노테이션 : 컴파일러가 읽는 주석
 * 
 * @WebServlet 어노테이션
 * -> 해당 클래스를 Servlet 으로 등록하고
 *    매핑할 주소를 연결하라고 지시하는 어노테이션
 * 
 * -> 서버 실행 시 자동으로 web.xml에
 *    <servlet> <servlet-mapping> 를 작성하는 효과
 * */

@WebServlet("/signUp")
public class ExampleController2 extends HttpServlet{
	
	// post 요청 받았을 때 처리하는 메서드 doPost()
	// POST 요청 처리 메서드 오버라이딩
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 원래 tomcat9 버전 때는
		// 전달받은 값을 얻어와서 확인 -> 한글이 깨지는 문제 발생!
		// 해결방법 : 전달받은 데이터의 문자인코딩을 서버에 맞게 변경
		// req.setCharacterEncoding("UTF-8");
		// -> tomcat10 버전 이상부터 자동으로 인코딩 처리를 해주기 때문에 추가적인 처리 필요 X
		
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		String inputName = req.getParameter("inputName");
		String intro = req.getParameter("intro");
		
		System.out.println(inputId);
		System.out.println(inputPw);
		System.out.println(inputName);
		System.out.println(intro);
		// http://localhost:8080/signUp
		// POST 방식이라서 url 에 요청 주소만 써있음
		/* << Console 창 >>
		user01
		pass01
		홍길동
		안녕하세요?
		*/
		
		// 응답화면 만들기
		// -> Java 에서 작성하기 너무 귀찮고 힘들다.
		// 누가 이것 좀 대신 만들어줘라
		// -> JSP에게 넘길 거
		// JSP가 대신 화면을 만들어줘라 (Servlet 이 JSP에게 요청을 위임)
		// (Java Server Page)
		// 자바에서 만드는 웹페이지
		
		// JSP가 대신 화면을 만들어 주기 위해선
		// Servlet 이 어떤 요청을 받았는지 알아야함
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/result.jsp");
		//                                                        webapp 폴더 기준
		
		dispatcher.forward(req, resp);
		
		// forward 굉장히 중요함
		// 요청이 왔을 때 페이지를 위임하는 거
		
		/* 클라이언트 Servlet             JSP
		 *   손님      웨이터             쉐프
		 *   ㅇ
		 *   ㅣ  요청           알바
		 *  ㅡㅡ ---->  난못해 ------->  result.jsp
		 *   ㅣ               dispatcher
		 *   ㅅ	               req(요청)
		 *  /  \
		 * */
	}
	
}