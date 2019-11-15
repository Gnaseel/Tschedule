<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
메인 화면
	<div>
		안녕하세요 <%=session.getAttribute("id") %> 님
	</div>
	<div>
		<input type="button" value="경로찾기 ㄱㄱ" id="findPath">
		<input type="button" value="구글 캘린더 연동" id="calender">
		<input type="button" value="마이페이지" id="myPage">
	</div>
	<div>
		<input type="button" value="로그아웃해봅시당" id="logOut">
	</div>
	<script>
		$('#findPath').click(function(){
			window.location("/Tschedule/MainMap.jsp");
		});
		$('#calender').click(function(){
		window.location("http://localhost:805/Tschedule/asdfasdf.jsp");
		});
		$('#myPage').click(function(){
		window.location("http://localhost:805/Tschedule/DBFrontController/getPath.DBdo");
		});
	</script>
</body>
</html>