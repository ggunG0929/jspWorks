<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/excp/ex02.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bbb</title>
</head>
<body>
<h1>bbb입니다.</h1>
<%
	//int a = 10/2;
	int a = 10/0;		// 에러
	out.println("정상 실행1: "+a+"<br/>");
%>
</body>
</html>