<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "org.json.simple.JSONObject" %>
<%@ page import = "org.json.simple.JSONArray" %>
<%@ page import = "com.aio.ex.parsing.AIOParsing" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<body>
<style type="text/css">
.div_tr { display:table-row; }
.div_tc { display:table-cell; border:1px solid #ddd; border-width:0 1px 1px 0; height:100px; vertical-align:middle; }
}
</style>
	<script>
	$(document).ready(function(){
		$("#resultJuso").html("");
		$("#resultJuso").append("<div display=\"table-row\">");
		$("#resultJuso").append("<div display=\"table-cell\><p>���ۿ�</p></div>");
		$("#resultJuso").append("<div display=\"table-cell\><p>���ۿ�</p></div>");
		$("#resultJuso").append("<div display=\"table-cell\><p>���� ����</p></div>");
		$("#resultJuso").append("<div display=\"table-cell\><p>�ð�</p></div>");
		$("#resultJuso").append("<div display=\"table-cell\><p>���</p></div>");
		$("#resultJuso").append("</div>");
		for(var i=0;i<8;i++){
			$("#resultJuso").append("<div display=\"table-row\>");
			$("#resultJuso").append("<div display=\"table-cell\><p>"+i+"</p></div>");
			$("#resultJuso").append("<div display=\"table-cell\><p>"+i+"</p></div>");
			$("#resultJuso").append("<div display=\"table-cell\><p>"+i+"</p></div>");
			$("#resultJuso").append("<div display=\"table-cell\><p>"+i+"</p></div>");
			$("#resultJuso").append("<div display=\"table-cell\><p>"+i+"</p></div>");
			$("#resultJuso").append("</div>");
			}
	});
	
	</script>
	
	<div id = "resultJuso" display="table">����������������</div>

</table>
</body>
</html>