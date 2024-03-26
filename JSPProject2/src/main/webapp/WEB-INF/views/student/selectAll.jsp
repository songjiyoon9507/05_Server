<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%-- 지시자 태그 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>학생 전체 조회</title>
</head>

<body>
	<%-- 반복문 출력하려면 지시자 태그 가져와야함 --%>
	
	<h1>학생 전체 목록</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>학번</th>
			<th>이름</th>
			<th>학과</th>
			<th>주소</th>
		</tr>
		
		<c:forEach var="std" items="${stdList}" varStatus="vs">		
			<tr>
				<td>${vs.count}</td>
				<td>${std.studentNo}</td>
				<td>${std.studentName}</td>
				<td>${std.departmentName}</td>
				<td>${std.studentAddress}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>