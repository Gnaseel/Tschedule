<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Date"%>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title>11</title>
  </head>
   <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
   
  <body>
 	
 	<a href="DBmanager?a=111">go</a>
 	<input type="button" id="target" value="button" />
 	<% 
 		Date d = new Date();
 		SimpleDateFormat s = new SimpleDateFormat("yyyymmdd");
 		System.out.println("time"+s.format(d));
 	%>
 	<script>
 	var i =3;	
 	document.getElementById("target").addEventListener("click",function(){temp(3);});
 	function temp(data){
 		alert(data);
 	}
 		$(document).ready(function(){
 			
 			alert("in");
 			goWbs("ssss","xxxxx");
 			alert("in1");
 			var str=$("#frm").serialize();
 			
 			/*
 			alert(str);
 			$.ajax({
 				type:"post",
 				data:str,
 				url:"http://localhost:805/Tschedule/FrontController/search.do",
 				dataType:"JSON",
 				success:function(response){
 					//alert("s");
 				}
 			});*/
 			
 			
 		});
		
 		function goWbs(x, y){ 
 			
 			
 			$('input[name="depPlaceId"]').val(x);
 			$('input[name="arrPlaceId"]').val(y);
 			$('input[name="depPlandTime"]').val(y);
 			}

 			
 	</script>
 	<form id="frm" method="POST">
 			    <input type="hidden" name="depPlaceId" />
 			    <input type="hidden" name="arrPlaceId" />
 			    <input type="hidden" name="depPlandTime" />
 			</form>
 	<div id = "resultJuso"></div>
  </body>
</html>

