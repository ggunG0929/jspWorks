<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>errorPage</title>
</head>
<body>
<h1>errorPage처리 입니다.</h1>
<!-- isErrorPage="true" -->
<%=exception.getMessage() %>
</body>
</html>