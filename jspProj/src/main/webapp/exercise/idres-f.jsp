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
	String name = request.getParameter("ename");
	String birth = request.getParameter("ebirth");
	String gender = request.getParameter("egender");

	// 디코드 안해도 됨
/* 	String name = URLDecoder.decode(ename,"UTF-8");
	String birth = URLDecoder.decode(ebirth,"UTF-8");
	String gender = URLDecoder.decode(egender,"UTF-8"); */
%>
<h1>외국인 회원가입폼</h1>
<form action="?">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" placeholder="입력해주세요" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" placeholder="입력해주세요" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" value="<%=name %>" /></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				 <input type="text" value="<%=birth.substring(0, 4) %>" style="width: 40px" />년
				 <input type="text" value="<%=birth.substring(4, 6) %>" style="width: 20px" />월
				 <input type="text" value="<%=birth.substring(6, 8) %>" style="width: 20px" />일
			 </td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				 <input type="radio"<% String fchk = gender.equals("여") ? "checked" : "disabled"; %><%=fchk %>/>여
				 <input type="radio"<% String mchk = gender.equals("남") ? "checked" : "disabled"; %><%=mchk %>/>남
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="등록"></td>
		</tr>
	</table>
</form>
</body>
</html>