<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script src="resources/js/memberValidation.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript">
function checkID() {
	let mid = $('#mid').val();
	if(mid == "") {
		alert("아이디는 필수입력사항입니다.");
		return false;
	}
	// ajax 실행
	$.ajax({
		type: "post",
		url: "checkid",
		dataType: "text",
		data: {id: mid},		// 서블릿으로 id를 보냄
		success: function(data) {	// 서블릿에서 응답받음
			console.log(data);
			if($.trim(data) == 'usable') {	// $.trim() - 앞, 뒤 공백을 없애줌
				$("#btnChk").attr("value", "Y");	// 체크 버튼이 눌러짐
				$('#check').text("사용가능한 ID입니다.")
						   .css({'color': 'blue', 'padding': '5px 0 0 10px', 'margin-left': '140px'});
			}else {
				$('#check').text("이미 가입된 ID입니다.")
				   		   .css({'color': 'red', 'padding': '5px 0 0 10px', 'margin-left': '140px'});
			}
		},
		error: function() {
			alert("에러 발생!");
		}
	});
}
</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
		<h3 class="text-left mx-4 my-4">회원 가입</h3>
		<form class="row" action="/addMember.do" method="post" name="newMember">
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">아이디</label>
				<div class="col-lg-4 col-sm-7 my-2">
					<input type="text" name="mid" id="mid" class="form-control">
					<button type="button" id="btnChk" value="N" class="btn_check btn btn-warning"
					 onclick="checkID()">ID확인</button><span id="check"></span>
				</div>
			</div>						
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">비밀번호</label>
				<input class="col-lg-4 col-sm-7 my-2" type="password" name="passwd1"class="form-control">
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">비밀번호 확인</label>
				<input class="col-lg-4 col-sm-7 my-2" type="password" name="passwd2"class="form-control">
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">이름</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="mname"class="form-control">
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">성별</label>
				<div class="col-lg-4 col-sm-7 my-2">
				    <input type="radio" name="gender" value="여" checked>여
					<input type="radio" name="gender" value="남">남
			    </div>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">생일</label>
				<div  class="col-lg-4 col-sm-7 my-2">
					<input type="text" name="birthyy" placeholder="년(4자)" maxlength="4" size="6">
					<select name="birthmm">
						<option>월</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
					<input type="text" name="birthdd" placeholder="일" maxlength="2" size="4">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">이메일</label>
				<div class="col-lg-4 col-sm-7 my-2">
					<input type="text" name="email1" maxlength="30">@
					<select name="email2">
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">전화번호</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="phone"class="form-control">
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">주소</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="address">
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<div class="col-lg-6 my-5" align="center">
					<input type="button" value="가입" class="btn btn-success" onclick="checkMember()">
					<input type="reset" value="취소" class="btn btn-secondary">
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>