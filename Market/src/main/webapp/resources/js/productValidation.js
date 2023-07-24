/* 상품등록폼 유효성 검사 */
function checkAddProduct() {
	let id = document.getElementById("productId");
	let name = document.getElementById("pname");
	let price = document.getElementById("unitPrice");
	let stock = document.getElementById("unitsInStock");
	
	// 정규표현식 - {}사이 ,다음에 띄어쓰기 X
	let regExpId = /^P[0-9]{4,11}/
	
	// 상품 아이디 - "P1234"처럼 P와 숫자를 조합하여 5~12자로 입력하도록
	if(!regExpId.test(id.value)) {
		alert("[상품 코드]\nP와 숫자를 조합하여 5~12자로 입력하세요\n첫글자는 반드시 P로 시작하세요");
		id.focus();
		id.select();
		return false;
	
	// 상품명 - 4~20자로 입력
	}else if(name.value.length < 4 || name.value.length > 20) {
		alert("[상품명]\n4~20자로 입력하세요");
		name.focus();
		name.select();
		return false;
	
	// 상품가격 - 숫자만 입력, 음수 입력 불가
	}else if(price.value.length == 0 || isNaN(price.value)) {
		alert("[상품가격]\n숫자만 입력하세요");
		price.focus();
		price.select();
		return false;
	}else if(price.value < 0) {
		alert("[상품가격]\n음수는 입력할 수 없습니다");
		price.focus();
		price.select();
		return false;
		
	// 재고수량 - 숫자만 입력, 음수 입력 불가
	}else if(stock.value.length == 0 || isNaN(stock.value)) {
		alert("[재고수량]\n숫자만 입력하세요");
		stock.focus();
		stock.select();
		return false;
	}else if(stock.value < 0) {
		alert("[재고수량]\n음수는 입력할 수 없습니다");
		stock.focus();
		stock.select();
		return false;
	
	}else {
		document.newProduct.submit();
	}
}