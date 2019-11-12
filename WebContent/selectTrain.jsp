<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.aio.ex.parsing.AIOParsing"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Main</title>
</head>
<body>
	<form action = "FrontController/search.do" method = "post">
		출발지<select name="depPlaceId">
			<option value="NAT010000">서울역</option>
			<option value="NAT014445">부산역</option>
			<option value="NAT010971">천안역</option>
			<option value="NAT030879">익산역</option>
		</select>
		도착지<select name="arrPlaceId">
			<option value="NAT010000">서울역</option>
			<option value="NAT014445">부산역</option>
			<option value="NAT010971">천안역</option>
			<option value="NAT030879">익산역</option>
		</select>
		</br><input type="text" value="2019" name="year">년
		</br><input type="text" value="10" name="month">월
		</br>
		<select name="day">
			<%
			for(int i=0;i<31;i++)
			{
				if(i<10)
					out.println("<option value= \"0"+i+"\">"+i+"</option>");
				else
					out.println("<option value=\""+i+"\">"+i+"</option>");
			}
			%>
		</select>일
		<input type="submit" value="전송">
	</form>
	
	<%
		out.println("temp ");
	%>
</body>
</html>