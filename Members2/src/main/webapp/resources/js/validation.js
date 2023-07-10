function checkID() {
	// alert("check");
	// ID 중복 확인
	let memberId = $('#memberId').val();
	if(memberId == "") {
		alert("아이디는 필수입력사항입니다.");
		return false;
	}
	// ajax 실행
	$.ajax({
		type: "post",
//		url: "http://localhost:8080/checkid",
		url: "checkid",
		dataType: "text",
		data: {id: memberId},		// 서블릿으로 id를 보냄
		success: function(data) {	// 서블릿에서 응답받음
			console.log(data);
			console.log($("btnChk").val())
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

function checkMember() {
//	alert("test");
	let form = document.member;
	let id = form.memberId.value;
	let pw1 = form.passwd1.value;
	let pw2 = form.passwd2.value;
	let name = form.name.value;
//	let check = document.getElementById("check").innerText;
	let btnChk = form.btnChk.value;		// 'Y' or 'N'
	
	// 정규 표현식
	let pw_pat1 = /[0-9]+/		// 숫자만
	let pw_pat2 = /[a-zA-Z]+/	// 영어만
	let pw_pat3 = /[~!@#$%^&*()_=]+/	// 특수문자만
	
	if(id.length<4 || id.length>15) {
		alert("아이디는 4~15자까지 입력 가능합니다.");
		form.memberId.select();
		return false;
	}else if(pw1.length<8||!pw_pat1.test(pw1)||!pw_pat2.test(pw1)||!pw_pat3.test(pw1)) {
		alert("비밀번호는 영문자, 숫자, 특수문자 포함 8자 이상입니다.");
		form.passwd1.select();
		return false;
	}else if(pw1 != pw2) {
		alert("비밀번호가 일치하지 않습니다.");
		form.passwd2.select();
		return false;
	}else if(name == "") {
		alert("이름은 필수입력사항입니다.");
		form.name.focus();
		return false;
	}else if(pw_pat1.test(name)||pw_pat3.test(name)) {
		alert("이름에는 특수문자나 숫자를 포함하지 않습니다.");
		form.name.focus();
		return false;
//	}else if(check != "사용가능한 ID입니다.") {
	}else if(btnChk == 'N') {
		alert("ID중복 체크가 필요합니다.");
		form.memberId.select();
		return false;
	}else{
		form.submit();
	}
}