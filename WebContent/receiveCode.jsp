<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="hppts://code.jqeury.com/jquery-3.4.1.min.js"></script>
<body>
	<style>
	input{
		width:300px;
	}
	</style>
	<a href = "login.html">login</a>
	<%
		String code=request.getParameter("code");
	%>
	<form action="http://localhost:805/Tschedule/Redirect" method="get" enctype="application/x-www-form-urlencoded">
		code : 				<input type="text" name="code" 			value="<%=code%>"><br>
		client_id : 		<input type="text" name="client_id" 	value="814235019489-jl6sj0r8j6n3v7o9if0m97598c8dkvv4.apps.googleusercontent.com"></br>
		client_secret : 	<input type="text" name="client_secret" value="jk_DKMZVqEbwlCHj5jfm-zA_"><br>
		redirect_uri : 		<input type="text" name="redirect_uri"	value="http://localhost:805/Tschedule/receiveCode.jsp"><br>
		grant_type : 		<input type="text" name="grant_type" 	value="authorization_code"><br>
		<input type="submit" value="submit">
	</form>
	<script>
	/*
	POST /oauth2/v4/token HTTP/1.1
	Host: www.googleapis.com
	Content-Type: application/x-www-form-urlencoded

	code=4/P7q7W91a-oMsCeLvIaQm6bTrgtp7&
	client_id=your_client_id&
	client_secret=your_client_secret&
	redirect_uri=https://oauth2.example.com/code&
	grant_type=authorization_code
	*/
	</script>
</body>
</html>


