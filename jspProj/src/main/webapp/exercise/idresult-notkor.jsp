<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
</head>
<body>
<%
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");

	String dname = URLDecoder.decode(name,"UTF-8");
	String dbirth = URLDecoder.decode(birth,"UTF-8");

	String y = dbirth.substring(0, 2);
	String m = dbirth.substring(2, 4);
	String d = dbirth.substring(4, 6);
%>
<h1>외국인 회원</h1>
<form action="?">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" value="<%=dname %>" /></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				 <input type="text" value="<%=y %>" />년
				 <input type="text" value="<%=m %>" />월
				 <input type="text" value="<%=d %>" />일
			 </td>
		</tr>
	</table>
</form>
</body>
</html>