<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 라이브러리를 현재 JSP에서 사용하겠다라는 JSP 지시자 작성 (필요한 것만 갖다 쓰면 됨)--%>

<%-- c == core (if, for문 등) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- fn == functions (길이, 자르기, 나누기) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 
    <%@ %> : 지시자 태그
    
    taglib : 태그 라이브러리 추가
    
    prefix : 접두사, 태그명 앞에 작성되는 단어 <c:set> <c:remove> <c:if> <c:forEach>
    
    uri(Uniform Resource Identifier, 통합 자원 식별자)
    -> 자원을 식별하는 고유 문자열

    (참고) url (Uniform Resource Locator)
           -> 자원의 위치를 나타내는 문자열(경로)
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL(Jsp Standard Tag Library)</title>
<%-- jsp 표준 태그 라이브러리 --%>
</head>
<body>
    <h1>JSTL(Jsp Standard Tag Library, JSP 표준 태그 라이브러리)</h1>

    <pre>
        JSP에서 자주 사용되거나 공통적으로 사용되는
        Java 코드 (if, for, 변수 선언, 형변환)를
        스크립틀릿 대신 html 태그 형식을 태그화하여
        표준으로 제공하는 라이브러리
        (if, for문 간단히 쓰고싶으면 사용)
    </pre>

    <h3>JSTL 라이브러리 사용 방법</h3>

    <ol>
        <li>
            /webapp/WEB-INF/lib 폴더에 jstl 라이브러리 파일(.jar) 추가
        </li>

        <li>
            JSTL 라이브러리를 사용하고자 하는 JSP 파일 상단에
            taglib JSP 지시자 태그를 추가
        </li>
    </ol>

    <hr>

    <h1>1. 변수 선언 (c:set 태그)</h1>

    <pre>
        - 변수 선언을 위한 태그

        - c:set 에 작성 가능한 속성
        1) var : 변수명(속성 key)
        2) value : 대입할 값
        3) scope : page, request, session, application 중 하나 지정 (기본값 page)
    </pre>
    
    <% 
    	// 스크립틀릿으로 page scope에 속성 세팅하는 법
    	// 스크립틀릿 안에는 java 주석 사용 가능
    	pageContext.setAttribute("num1", 10);
    %>
    
    <%-- JSTL로 page scope 에 속성 세팅하는 법 --%>
    <c:set var="num2" value="20" scope="page"/>
    
    num1 : ${num1}
    <br>
    num2 : ${num2}
    
    <hr>
    
    <h1>2. 변수 제거 (c:remove)</h1>
    <pre>
        - 변수 제거 : 내장 객체에 세팅된 속성을 제거
        (jsp 방법 : removeAttribute("num1"))

        - c:remove 속성
        1) var : 삭제할 변수명
        2) scope : 내장 객체 범위 (기본값 : 모든 scope)
    </pre>
    
    <%
      	pageContext.removeAttribute("num1");
    %>
      	
    num1 제거 확인 : ${num1}
      	
    <br>
      	
    <c:remove var="num2" />
      	
    num2 제거 확인 : ${num2}
    
    <hr>
    
    <h1>3. 변수 출력 (c:out 태그)</h1>

    <pre>
        \${key} EL 구문 비슷함

        - 단, escapeXml="true" (기본값) 설정 시
          html 태그가 해석 X

        - escapeXml="false" : html 태그 해석 O
    </pre>

	<c:set var="temp" value="<h1>html 코드 작성</h1>"/>
	
	html 태그 해석 X : <c:out value="${temp}"/>
	<%-- html 태그 해석 X : <h1>html 코드 작성</h1> --%>
	
	<br>
	
	html 태그 해석 O : <c:out value="${temp}" escapeXml="false" />
	<%-- html 태그 해석 O : html 코드 작성 --%>
	
	<hr><hr><hr>
	
	<%-- 상대 경로 : 현재 위치(경로)가 중요함
		 현재 경로 : http://localhost:8080/jstl/main
		 이동 경로 : http://localhost:8080/jstl/condition
		 => 맨 뒤가 바뀌는 거 
	 --%>
	<a href="condition">JSTL을 이용한 조건문(if / choose, when, otherwise)</a>
	<!-- /jstl/condition 이라는 요청 주소를 처리할 Controller 가 필요함
		 : JSTLConditionController -->
		 
	<br>
	
	<!-- 상대경로
	
		현재 경로 : http://localhost:8080/jstl/main
		목표 경로 : http://localhost:8080/jstl/loop
	 -->
	 
	<a href="loop">JSTL을 이용한 반복문</a>
	<%-- /jstl/loop 라는 요청을 처리할 Controller 가 필요함 : JSTLLoopController --%>
	
	<hr><hr><hr>
	
	<%-- DB 연결 방법 (조금 다름) java project 와 dynamic web project는 배포 방식이 다름
	webapp 폴더가 배포되는 거 war(웹 아카이브) 파일로 압축돼서 배포
	webapp 폴더 안에 WEB-INF 안에 lib 폴더에 ojdbc10.jar 파일 drag & drop
	src/main/java 안에 패키지 만들어서 JDBCTemplate 복사해오고
	driver.xml, query.xml 복사해옴
	xml 파일 path가 달라짐 JDBCTemplate에 경로 설정 방법 적어둠--%>
	
	<%-- 현재 경로 : localhost:8080/jstl/main
		목표 경로 : localhost:8080/jstl/student/selectAll
	 --%>
	<a href="student/selectAll">workbook에서 학생 전체 조회하기</a>
	<%-- student/selectAll 라는 요청을 처리할 Controller : SelectAllController --%>
	
	<%-- 현재 경로 : localhost:8080/jstl/main
		목표 경로 : localhost:8080/jstl/student/selectArch  --%>
	<a href="student/selectArch">workbook에서 건축공학과인 학생만 찾기</a>
	<%-- SelectArchController --%>
	
	<%-- 현재 경로 : localhost:8080/jstl/main
		목표 경로 : localhost:8080/jstl/student/selectOne  --%>
	<form action="student/selectOne" method="post">
		<input type="text" name="deptName">
		<button>찾기</button>
	</form>
	
	<%-- 작성한 학과의 학생만 조회 SelectOneController --%>
</body>
</html>