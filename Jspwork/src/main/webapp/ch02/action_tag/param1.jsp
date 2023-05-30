<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그</title>
</head>
<body>
	<h2>액션 태그 - param</h2>
	<jsp:forward page="param1_data.jsp">
	<jsp:param value="admin" name="id"/>
	<jsp:param value="0000" name="pwd"/>
	</jsp:forward>	<!-- forward문 중간에는 주석도 쓰지말기 -->
</body>
</html>