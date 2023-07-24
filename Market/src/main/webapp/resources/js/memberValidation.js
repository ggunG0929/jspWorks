function checkMember() {
	let form = document.newMember;
	let id = form.mid.value;
	let pw1 = form.passwd1.value;
	let pw2 = form.passwd2.value;
	let name = form.mname.value;
//	let btnChk = form.btnChk.value;		// 'Y' or 'N'
	
	// 정규 표현식
	let pw_pat1 = /[0-9]+/		// 숫자만
	let pw_pat2 = /[a-zA-Z]+/	// 영어만
	let pw_pat3 = /[~!@#$%^&*()_=]+/	// 특수문자만
	
	if(id.length<4 || id.length>15) {
		alert("아이디는 4~15자까지 입력 가능합니다.");
		form.mid.select();
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
		form.mname.focus();
		return false;
	}else if(pw_pat1.test(name)||pw_pat3.test(name)) {
		alert("이름에는 특수문자나 숫자를 포함하지 않습니다.");
		form.mname.focus();
		return false;
//	}else if(btnChk == 'N') {
//		alert("ID중복 체크가 필요합니다.");
//		form.memberId.select();
//		return false;
	}else{
		form.submit();
	}
}