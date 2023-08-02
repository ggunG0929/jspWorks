<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward > b</title>
</head>
<body>
<h1>forward > b</h1>
<!-- ddd -->
pname: <%=request.getAttribute("pname") %><br/>
<!-- 17 -->
age: <%=request.getParameter("age") %><br/>
<!-- ccc -->
nick: <%=request.getParameter("nick") %><br/>
</body>
</html>