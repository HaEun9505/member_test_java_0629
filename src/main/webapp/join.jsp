<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<form action="joinOk.jsp" method="post">
		아이디 : <input type="text" name="memberId"><br><br>
		비밀번호 : <input type="password" name="memberPw"><br><br>
		이름 : <input type="text" name="memberName"><br><br>
		이메일 : <input type="text" name="memberEmail"><br><br>
		전화번호 : <select name="phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="016">016</option>
			<option value="017">017</option>		
		</select> - 
		<input type="text" name="phone2" size="10">-
		<input type="text" name="phone3" size="10"><br><br>
		성별 : <input type="radio" name="memberGender" value="man">남 &nbsp;
		<input type="radio" name="memberGender" value="woman">여<br><br>
		<hr>
		<input type="submit" value="회원가입">
		<input type="reset" value="취소">
	</form>
</body>
</html>