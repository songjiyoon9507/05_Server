package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp.model.dto.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/elTest")
public class ELTestController extends HttpServlet {
	
	// a 태그 요청 (get 방식)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jsp 경로는 어디 기준? webapp 기준
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/el/elTest.jsp");
		dispatcher.forward(req, resp);
	}
	
	// elTest.jsp 에서 요청 온 form 태그 요청 (Post 방식)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 새로운 값을 req 에 세팅
		req.setAttribute("address", "서울시 중구 남대문로 120");
		req.setAttribute("score", 100);
		
		List<String> strList = new ArrayList<String>();
		
		strList.add("가가가");
		strList.add("나나나");
		strList.add("다다다");
		
		req.setAttribute("strList", strList);
		
		// Book 객체 Attribute 세팅
		Book book = new Book("어린왕자", "생택쥐베리", 4000);
		
		req.setAttribute("book", book);
		
		// null 과 비어있다
		List<String> list1 = null; // null
		// null 은 참조하는 애가 없다
		List<String> list2 = new ArrayList<String>(); // 비어있음
		// 주소가 있음 참조하고 있는 애가 있음 Heap 영역에 ArrayList 참조 안이 비어있을 뿐
		
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		
		// 요청 위임
		RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/views/el/elResult.jsp");
		dis.forward(req, resp);
	}
}
