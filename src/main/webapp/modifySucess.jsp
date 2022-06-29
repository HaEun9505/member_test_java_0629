<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 성공</title>
</head>
<body>
	<%
		//세션에 저장
		String mid = (String)session.getAttribute("id");
	%>
	<%= mid %>님의 정보수정이 정상 완료되었습니다.<br><br>
	<a href="logout.jsp">로그아웃</a>&nbsp;&nbsp;&nbsp;
	<a href="modify.jsp">회원정보수정</a>
</body>
</html>