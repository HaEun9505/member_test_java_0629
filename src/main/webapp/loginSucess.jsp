<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<%
		//세션에서 값 불러오기
		String id = (String)session.getAttribute("id");
		String pw = (String)session.getAttribute("pw");
		String name = (String)session.getAttribute("name");
	%>
	<%= name %>님 환영합니다.<br>
	아이디 [<%= id %>]로 로그인 하셨습니다.<br><br>
	<a href="logout.jsp">로그아웃</a>&nbsp;&nbsp;&nbsp;
	<a href="modify.jsp">회원정보수정</a>
	<br><br>
	<hr>
	<form action="deleteOk.jsp">
		아이디 : <input type="text" name="userId" value="<%= id %>" readonly>
		<!-- readonly : 읽기 전용 -->
		<input type="submit" value="회원탈퇴">
	</form>
</body>
</html>