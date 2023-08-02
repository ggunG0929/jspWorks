<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include > a</title>
</head>
<body>
<!-- 출력됨 -->
<h1>include > a</h1>
<script type="text/javascript">
	alert("띠용");	// 작동함
</script>
<%
	request.setAttribute("pname", "fff");
%>
<!-- b.jsp가 삽입됨 -->
<jsp:include page="b.jsp?age=34" />
</body>
</html>