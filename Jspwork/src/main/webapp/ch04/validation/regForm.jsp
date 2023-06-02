<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
	#container{width: 75%; margin: 0 auto;}
	h2{background: orange; text-align: center; padding: 15px 0; color: #eee; border-radius: 5px;}
	#regForm, #result{width: 50%; margin: 0 auto; background: lightyellow; padding: 15px 20px; border-radius: 10px;}	/* 임의로 css를 result에 동일하게 적용 */
	#result{display: none;}	/* 임의로 css에서 display를 none으로 함 */
	#result h3{text-align: center;}
</style>
<script src="../../resources/js/signUp.js"></script>
</head>
<body>
	<div id=container>
		<h2>회원 가입</h2>
		<hr>
		<div id="regForm">
			<form action="" method="post" name="form1">
				<label>이름</label>
				<input type="text" name="name" size="40">
				<hr>
				<label>이메일</label>
				<input type="email" name="email" size="40">
				<p><button type="button" onclick="signUp()">가입</button></p>
			</form>
		</div>
		<div id="result">
			<h3>가입 정보</h3>
			<hr>
			<p>이름: <span id="rname"></span></p>
			<p>이메일: <span id="rmail"></span></p>
		</div>
<!-- 		<script>
			document.getElementById('result').style.display = "none";	// 선생님은 자바스크립트로 디스플레이를 none으로 함
		</script> -->
	</div>
</body>
</html>