<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="DBFrontController/signUp.DBdo" method="post">
		name <br><input type="text" name="name"><br>
		id <br><input type="text" name="id"><br>
		password <br><input type="password" name="password"><br>
		<input type="submit" value="회원 가입">
	</form>
</body>
</html>