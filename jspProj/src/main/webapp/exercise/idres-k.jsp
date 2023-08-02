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
	String ename = request.getParameter("ename");
	String ebirth = request.getParameter("ebirth");
	String egender = request.getParameter("egender");

	String name = URLDecoder.decode(ename,"UTF-8");
	String birth = URLDecoder.decode(ebirth,"UTF-8");
	String gender = URLDecoder.decode(egender,"UTF-8");

	String y = birth.substring(0, 4);
	String m = birth.substring(4, 6);
	String d = birth.substring(6, 8);
%>
<h1>미성년자 회원가입폼</h1>
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
				 <input type="text" value="<%=y %>" />년
				 <input type="text" value="<%=m %>" />월
				 <input type="text" value="<%=d %>" />일
			 </td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				 <input type="radio" <% if(gender.equals("여")){ %>checked<% } %>/>여
				 <input type="radio" <% if(gender.equals("남")){ %>checked<% } %>/>남
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="등록"></td>
		</tr>
	</table>
</form>
</body>
</html>