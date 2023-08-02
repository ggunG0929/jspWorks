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
<form action="idresult-adult.jsp">
	<ul>
		<li>이름: <input type = "text" name="name"></li>
		<li>주민등록 번호: 
			<input type="text" name="birth" placeholder="6자리">
			-<input type="text" name="nation" placeholder="1자리">******
		</li>
	</ul>
	<input type="submit" value="확인">
</form>
</body>
</html>