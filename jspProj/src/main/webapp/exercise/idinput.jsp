<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주민등록번호로 외국인, 성인, 미성년자 구분</title>
</head>
<body>
<h1>주민등록번호 입력</h1>
<form action="idres-a.jsp">
	<table border="">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>주민번호</td>
			<td><input type="text" name="birth" placeholder="6자리">
			-<input type="text" name="nation" placeholder="1자리">******</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="회원가입"></td>
		</tr>
	</table>
</form>
</body>
</html>