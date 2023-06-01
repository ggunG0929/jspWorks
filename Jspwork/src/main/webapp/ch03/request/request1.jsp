<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 객체 예제</title>
</head>
<body>
	<form action="process.jsp" method="post">
	<!-- process로 넘어갔을 때(get방식) http://localhost:8080/ch03/request/process.jsp?uname=관리자(쿼리스트링) -->
	<!-- 				 (post방식) http://localhost:8080/ch03/request/process.jsp -->
		<p>
			<label for="uname">이름 </label>
			<input type="text" id="uname" name="uname">
		</p>
		<button type="submit">등록</button>
	</form>
</body>
</html>