<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>examForm</title>
</head>
<body>
<h1>examForm</h1>
<%
	Random r = new Random();
	String [] nameList = "한가인,두가인,삼가인,사가인,오가인".split(",");
%>
<form action="examReg1.jsp">
	<table>
		<tr>
			<td>이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
		</tr>
	<%-- <c:forEach var="i" begin="0" end="4"> --%>
	<!-- i의 아이템이 nameList이므로 ${i}에서 nameList가 순서대로 나오고, 길이만큼 for문이 돈다 -->
	<c:forEach var="i" items="<%=nameList %>">
		<tr>
			<td><input type="pname" value="${ i }" /></td>
			<td><input type="kor" value="<%=r.nextInt(40,100) %>"/></td>
			<td><input type="eng" value="<%=r.nextInt(40,100) %>"/></td>
			<td><input type="mat" value="<%=r.nextInt(40,100) %>"/></td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan=4 align="right"><input type="submit" value="전송"></td>
		</tr>
	</table>
</form>
</body>
</html>