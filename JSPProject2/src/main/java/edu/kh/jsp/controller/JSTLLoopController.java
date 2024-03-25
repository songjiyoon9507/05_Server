package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp.model.dto.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jstl/loop")
public class JSTLLoopController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Book> bookList = new ArrayList<Book>();
		
		bookList.add(new Book("셈을 할 줄 아는 까막눈이 여자", "요나스 요나손", 13320));
		bookList.add(new Book("거인의 노트", "김익한", 16200));
		bookList.add(new Book("와카바소 셰어하우스입니다", "하타노 도모미", 14400));
		bookList.add(new Book("미루기의 기술", "존 페리", 10800));
		bookList.add(new Book("메리골드 마음 세탁소", "윤정은", 13500));
		bookList.add(new Book("달러구트 꿈 백화점 : 잠들어야만 입장 가능합니다", "이미예", 24840));
		
		req.setAttribute("bookList", bookList);
		
		req.getRequestDispatcher("/WEB-INF/views/jstl/loop.jsp").forward(req, resp);
	}
}
