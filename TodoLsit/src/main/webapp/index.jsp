<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%-- webapp 안에 resources 폴더 만들고 css 폴더 만들고 main.css 넣음 --%>
	<%-- webapp 폴더 기준으로 경로 작성 --%>
	<link rel="stylesheet" href="/resources/css/main.css">
	<%-- css, js 파일 연결할 때 webapp 폴더 기준 --%>
	
	<title>To do List</title>
	<%-- 메인 페이지가 되는 부분 webapp 폴더 누르고 만들어줘야함 --%>
</head>
<body>

	<main>
		
		<c:choose>
			<%-- 로그인을 하지 않았다면 : 로그인/회원가입 입력 폼 출력 --%>
			<c:when test="${empty sessionScope.loginMember}">
				<h1>٩( *˙0˙*)۶</h1>
				<h1>To Do List L O G I N</h1>
				
				<form action="/login" method="post" class="login-form">
					<div>
						<p>ID</p>
						<input type="text" name="inputId">
					</div>
					<div>
						<p>PASSWORD</p>
						<input type="password" name="inputPw">
					</div>
					
					<%-- ๑°⌓°๑ --%>
					<%-- ๑•́ㅿ•̀๑) ᔆᵒʳʳᵞ --%>
					<%-- ( •︠ˍ•︡  ) --%>
					
					<button>L O G I N</button>
					
					<a href="/signup" class="signup">S I G N U P</a>
				</form>
			</c:when>
			
			<%-- 로그인을 했다면 : 현재 로그인한 사람의 투두리스트 출력 --%>
			<c:otherwise>
				<h1>✿˘◡˘✿</h1>
				<h1>${sessionScope.loginMember.memberNickname}의 To do List</h1>
				<c:choose>
					<%-- 투두리스트가 없다면 --%>
					<c:when test="${empty todoList}">
						<h2>할 일이 하나도 없네요</h2>
						<h2>๑°⌓°๑</h2>
					</c:when>
					<%-- 투두리스트가 있다면 --%>
					<c:otherwise>
						<table>
							<c:forEach var="todo" items="${todoList}">
								<tr>
									<td>${todo.todoTitle}</td>
									<td>(${todo.todoMemo})</td>
									<td>${todo.todoDate}</td>
									<td><a href="/update?todoNo=${todo.todoNo}" class="update-btn">수정</a></td>
									<td><a href="/delete?todoNo=${todo.todoNo}"
										onclick="return confirm('정말 삭제하시겠습니까? ๑ó⌓ò๑');"
									class="delete-btn" >삭제</a></td>
								</tr>							
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
				
				<div class="button-div">
					<a href="/insert" class="insert-btn">등록하기</a>
					<a href="/logout" class="logout-btn">로그아웃</a>
				</div>
				
			</c:otherwise>
			
		</c:choose>
		
	</main>

	<%-- ojdbc10.jar, jakarta 3개, lombok.jar lib 폴더에 넣어주고 시작 --%>
	<%-- lombok 라이브러리가 필드만 작성하면 생성자 getter/setter 다 만들어줌 --%>
	<%-- lombok 이 eclipse 인식하는 과정 필요
		이클립스 끄고 실행파일 있는 곳에 lombok 넣고 powershell --%>
		
	<%-- session 에 message 가 존재할 경우 --%>
	<%-- not empty : 비어있지 않을 경우 true --%>
	<%-- 먼저 el 읽고 script 읽음 --%>
	<c:if test="${not empty sessionScope.message}">
		<script>
		// EL/JSTL 구문이 자바스크립트보다 먼저 해석되는데
		// 문자열이 들어가있는 데이터의 경우
		// 따옴표가 없는 상태이니 붙여줘야한다.
			alert('${message}'+ "\n" + '${alert}');
		</script>
		
		<%-- 
			session 에 message 를 추가하면
			브라우저 종료 또는 만료전까지 계속 메세지가 출력된다.
			-> 1회 출력 후 session 에서 message 삭제
		 --%>
		 <c:remove var="message" scope="session"/>
		 <c:remove var="alert" scope="session"/>
		 
	</c:if>
</body>
</html>