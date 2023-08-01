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
	// 매번 입력하기 번거로우니 임시로 이름과 랜덤값 부여
	Random r = new Random();
	String [] nameList = "한가인,두가인,삼가인,사가인,오가인".split(",");
%>
<form action="examReg3.jsp">
	<table>
		<tr>
			<td>이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
		</tr>
		<!-- i가 0부터 4까지 for문 -->
		<%-- <c:forEach var="i" begin="0" end="4"> --%>
	<!-- i의 아이템이 nameList이므로 ${i}에서 nameList가 순서대로 나오고, 길이만큼 for문이 돈다 -->
	<c:forEach var="i" items="<%=nameList %>">
		<tr>
			<td><input type="text" name="pname" value="${ i }" /></td>
			<td><input type="text" name="kor" value="<%=r.nextInt(40,100) %>"/></td>
			<td><input type="text" name="eng" value="<%=r.nextInt(40,100) %>"/></td>
			<td><input type="text" name="mat" value="<%=r.nextInt(40,100) %>"/></td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan=4 align="right"><input type="submit" value="전송"></td>
		</tr>
	</table>
</form>
</body>
</html>