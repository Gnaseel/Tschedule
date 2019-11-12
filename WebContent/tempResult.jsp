<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<p>access token = <%= request.getAttribute("access_token") %></p>
 <div> 구글 캘린더에 추가하기</div><input type="button">
</body>
</html>