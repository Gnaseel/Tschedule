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
		�����<select name="depPlaceId">
			<option value="NAT010000">���￪</option>
			<option value="NAT014445">�λ꿪</option>
			<option value="NAT010971">õ�ȿ�</option>
			<option value="NAT030879">�ͻ꿪</option>
		</select>
		������<select name="arrPlaceId">
			<option value="NAT010000">���￪</option>
			<option value="NAT014445">�λ꿪</option>
			<option value="NAT010971">õ�ȿ�</option>
			<option value="NAT030879">�ͻ꿪</option>
		</select>
		</br><input type="text" value="2019" name="year">��
		</br><input type="text" value="10" name="month">��
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
		</select>��
		<input type="submit" value="����">
	</form>
	
	<%
		out.println("temp ");
	%>
</body>
</html>