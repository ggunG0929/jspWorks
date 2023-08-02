<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirect > b</title>
</head>
<body>
<h1>redirect > b</h1>
<!-- null -->
pname: <%=request.getAttribute("pname") %><br/>
<!-- 26 넘어옴 -->
age: <%=request.getParameter("age") %><br/>
<!-- null a에 준거라 안옴 -->
nick: <%=request.getParameter("nick") %><br/>
</body>
</html>