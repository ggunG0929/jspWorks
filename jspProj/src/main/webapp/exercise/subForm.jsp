<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cnt = 5;
	if(request.getParameter("cnt")!=null) {
		cnt = Integer.parseInt(request.getParameter("cnt"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>subForm</title>
</head>
<body>
<h1>subForm</h1>
<form action="subReg.jsp" name="regForm2">
	<table>
		<tr>
			<td>이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>개인당 합계</td>
		</tr>
	<%	for(int i=0; i<5; i++) { %>
		<tr>
			<td><input type="text" name="sname" /></td>
			<td><input type="text" name="kor" value="<%=(int)(Math.random()*100) %>"/></td>
			<td><input type="text" name="eng" value="<%=(int)(Math.random()*100) %>"/></td>
			<td><input type="text" name="math" value="<%=(int)(Math.random()*100) %>"/></td>
			<td></td>
		</tr>
	<% } %>
		<tr>
			<td>과목별 합계</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit" value="입력" /></td>
		</tr>
	</table>
</form>
</body>
</html>