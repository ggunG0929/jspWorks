<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학점 처리</title>
<script>
	// 점수를 입력하지 않았거나, 문자인 경우 처리(name 속성으로 처리)
	function checkScore() {
//		alert("test");
		let form = document.form1;
		let score = form.score;
		
//		score.trim();	// trim() - 공백문자를 제거함
//		
// 		if(score.value == "" || isNaN(score.value)){		// 선생님 방식1. 공백은 처리되지만 숫자입력시 넘어가지 않는 오류발생
	
 		if(score.value == "" || isNaN(score.value) || score.value.trim() == ''){	// 선생님 방식2, 내 방식과 같은 결과
	
//	 	if(score.value.trim() == "" || isNaN(score.value)){	// 임의로 처리. 이렇게 하면 공백이 있어도 제거하여 처리(대신에 숫자 앞뒤에 공백이 있을경우 오류 500)
			alert("숫자를 입력해 주세요");
			score.focus();
			return false;
		}else{		// 임의로 숫자 범위를 넘어갔을때 그 다음 화면에서 예외처리를 해두었으므로 굳이 alert에 범위밖의 예외처리를 하지 않음
			form.submit();	// 괄호 넣어줘야함
		}
	}
</script>
</head>
<body>
	<h3>점수를 입력해 주세요</h3>
	<form action="scoreTest.jsp" method="get" name="form1">
		<p>점수: <input type="text" name="score">
				<input type="button" value="학점출력" onclick="checkScore()"></p>
	</form>
</body>
</html>