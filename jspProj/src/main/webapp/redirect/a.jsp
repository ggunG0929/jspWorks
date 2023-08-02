<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirect > a</title>
</head>
<body>
<h1>redirect > a</h1>
<script type="text/javascript">
	alert("띠용");
	location.href="b.jsp";
</script>
<%
	request.setAttribute("pname", "aaa");
	response.sendRedirect("b.jsp?age=26");	// alert 창 없이 바로 넘어감
%>
</body>
</html>