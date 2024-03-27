<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link rel="stylesheet" href="/resources/css/main.css">
	
	<title>To do List 수정</title>
</head>
<body>
	<main>
		<h1>To do 수정하기</h1>
		<h1>(๑・‿・๑)</h1>
		<form action="/update" method="post">
			<%-- post 는 쿼리 스트링 안쓰는 게 좋음
			action="/update?todoNo=${todo.todoNo}" 이거 대신
			<input name="todoNo" value="${todo.todoNo}" type="hidden">
			 --%>
			<p>제목</p>
			<input type="text" name="title" value="${todo.todoTitle}" required>
			
			<p>메모</p>
			<textarea  name="memo" style="resize: none; font-size: 18px;"
			rows="5" cols="20">${todo.todoMemo}</textarea>
		
			<input name="todoNo" value="${todo.todoNo}" type="hidden">
		
			<br>
			<button class="update-btn">수정하기</button>
		</form>
	</main>
</body>
</html>