<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보</title>
<script>
	function addToCart(){
 		if(confirm('상품을 장바구니에 추가하시겠습니까?')) {
			document.addForm.submit();	// 폼을 전송
		}else{
			document.addForm.reset();
		}
	}
	// 임의로 기능을 바꿈. 무조건 장바구니에 담고 컨펌에 의해 장바구니로 이동하도록
	function addToCart2(event){
		event.preventDefault();
		location.href="/addCart.do?productId=${product.productId}";
		if(confirm('상품을 장바구니에 담았습니다.\n장바구니로 이동하시겠습니까?')) {
			location.href="/cart.do";
		}
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-4">
		<h2 class="text-left mx-4 my-4">상품 정보</h2>
		<div class="row mx-5" align="center">
			<div class="col-5">
				<img src="./upload/${product.productImage }" style="width:100%">
			</div>
			<div class="col-6 my-auto">
				<h3>${product.pname }</h3>
				<p>${product.description }</p>
				<p><b>상품 코드</b>: <span class="badge bg-dark">${product.productId }</span></p>
				<p><b>제조사</b>: ${product.manufacturer }</p>
				<p><b>분류</b>: ${product.category}</p>
				<p><b>재고</b>: ${product.unitsInStock }</p>
				<p>${product.unitPrice }원</p>
				<p>
					<form action="/addCart.do?productId=${product.productId }" method="post" name="addForm" id="addForm">
 
						<a href="#" onclick="addToCart()" class="btn btn-info">상품 주문</a>
						<a href="/cart.do" class="btn btn-warning">장바구니</a>
						<a href="/productList.do" class="btn btn-secondary">&laquo; 상품 목록</a>
						<!-- 임의로 자바스크립트를 변경했으므로 버튼을 통합함 -->
						<input type="submit" onclick="addToCart2(event)" class="btn btn-warning" value="장바구니">
					</form>
				</p>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>