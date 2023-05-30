<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- errorPage 속성 사용 -->
<%@ page errorPage="error_msg.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	<%		// 오류가 나는 상황 만들어줌 - 오류페이지로 넘어가는지 확인하려고
		String str = null;
		out.print(str.toString());
	%>
</body>
</html>