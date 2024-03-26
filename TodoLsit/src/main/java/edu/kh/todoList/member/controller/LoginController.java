package edu.kh.todoList.member.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.todoList.member.model.dto.Member;
import edu.kh.todoList.member.model.service.MemberService;
import edu.kh.todoList.todo.model.dto.Todo;
import edu.kh.todoList.todo.model.service.TodoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1. 입력 받은 값(파라미터) 얻어오기
			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			
			// 2. 서비스 객체 생성
			MemberService service = new MemberService();
			
			// 3. 로그인 서비스 호출 후 결과 반환받기
			Member loginMember = service.login(inputId, inputPw);
			
			// 4. Session 객체 생성
			HttpSession session = req.getSession();
			
			if(loginMember != null) { // 로그인 성공
				
				// 5. session 에 로그인한 회원 정보 세팅
				session.setAttribute("loginMember", loginMember);
				
				// 5-1. session 만료 시간 지정
				session.setMaxInactiveInterval(60 * 60); // 세션 생성 후 1시간으로 변경
				
				// -------------------------------------------------

				// 현재 로그인한 회원이 등록한 todoList 목록 조회하기
				TodoService todoService = new TodoService();
				
				List<Todo> todoList = todoService.selectAll(loginMember.getMemberNo());
				
				session.setAttribute("todoList", todoList);
				
				// -------------------------------------------------
				
				// 이미 만들어져 있는 응답 페이지 재요청
				// forword 와 redirect
				
				// 메인 페이지로 이동 재요청 ("/")
				resp.sendRedirect("/");
				
				// forword : 요청 처리 후 자체적인 화면(위임받은 jsp 포함)이 존재하여
				//           이를 이용해서 응답
				//           위임 시 request, response 객체를 함께 위임
				
				// redirect : 요청 처리 후 자체적인 화면이 없어서
				//            화면이 있는 다른 요청을 다시 호출(재요청)
				//            request, response 폐기됨 -> 재요청 후 응답받은 페이지에서 사용 불가
				
			} else { // 로그인 실패
				
				// 로그인 실패 메시지를 띄우려고함.
				// request, response 사용 안됨 session, application 은 사용 가능
				// redirect 사용 가능
				// -> 리다이렉트 요청 후 응답페이지에서 request 에 담긴 속성은 사용 불가
				// -> session 에 속성 세팅하면 재요청 페이지에서도 사용 가능
				
				session.setAttribute("message", "๑•́ㅿ•̀๑) ᔆᵒʳʳᵞ");
				session.setAttribute("alert", "아이디 또는 비밀번호 불일치");
				// redirect 로 request, response 폐기되면 session 쓰면 됨
				
				// 이전 페이지로 redirect (request, response 폐기됨)
				String referer = req.getHeader("referer");
				// -> referer : 각 페이지 방문 시 남는 흔적
				// request header 안에 들어있음
				// referer 전달해주면 지금 현재 주소에서 이전에 방문했던 주소를 돌려줌
				// request.getHeader() : 파라미터로 referer 키 전달 시 이전페이지 주소값 반환
				// http://localhost:8080/
				
				// 이전 페이지로 redirect
				resp.sendRedirect(referer);
			}
			

			
			
		} catch (Exception e) {
			System.out.println("[로그인 중 예외 발생]");
			e.printStackTrace();
		}
	
	}
}
