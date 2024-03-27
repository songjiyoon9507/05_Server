<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>버스 예약 프로그램</title>
</head>
<body>
	<main>
		<c:choose>
			<%-- 로그인 안 했을 때 : 로그인/회원가입 --%>
			<c:when test="${empty sessionScope.loginMember}">
				
				<%-- 버스 전체 목록 보여주기 --%>
				
				
				<h1>버스 예약 로그인</h1>
				<form action="/login" method="post">
					<div>
						<p>ID</p>
						<input type="text" name="inputId">
					</div>
					<div>
						<p>PASSWORD</p>
						<input type="password" name="inputPw">
					</div>
					<button>로그인</button>
					
					<a href="/signup">회원가입</a>
				</form>
			</c:when>
			
			<%-- 로그인 했다면 현재 로그인한 사람의 예약 정보 출력 --%>
			
		</c:choose>
	</main>
</body>
</html>