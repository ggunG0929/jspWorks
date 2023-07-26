<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eee</title>
</head>
<body>
<h1>eee입니다.</h1>
<%
	int [] arr = {11, 22, 33, 44};
	//out.println("정상 실행2: "+arr[1]+"<br/>");
	out.println("정상 실행 2"+arr[7]+"<br/>");		// 오류: java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 4
%>
</body>
</html>