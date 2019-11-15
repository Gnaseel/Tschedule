<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.google.gson.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pathLog</title>
</head>
<body>
	<div><%=session.getAttribute("id")%> 님의 경로 기록</div>
	<div id="path">
		<%
			JsonArray jArr= (JsonArray)request.getAttribute("pathLog");
			for(int i=0;i<jArr.size();i++){
				out.print(jArr.get(i).toString());
			}
		%>
	</div>
</body>
</html>