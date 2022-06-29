<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 완료</title>
</head>
<body>
	<%
		//세션에서 값 불러오기
		String name = (String)session.getAttribute("name");
	%>
	<%= name %>님 안녕히 가세요. 다시 회원 가입하시려면 아래 링크를 클릭하세요.<br>
	<a href="join.jsp">회원 가입 바로가기</a>
</body>
</html>