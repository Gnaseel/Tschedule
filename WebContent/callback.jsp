<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
</head>
<body>
<input type="button" id="loginBtn" value="login">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
<script>
	$(document).ready(function(){
		$("#resultJuso").append("<input type=\"button\" name=\"abc\" id = \"abc\" value=\"이 경로 사용하기\"></br>");
	})
	
	
	$(document).on("click","#abc",function(){
		alert("in save path");
		//window.location("DBFrontController/signUp.DBdo");
	});
</script>
	<div id='resultJuso'></div>
</body>
</html>