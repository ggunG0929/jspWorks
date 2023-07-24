<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/productValidation.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-4">
		<h2 class="text-left mx-4 my-4">상품 수정</h2>
		<div class="row mx-5">
			<form action="/updateProduct.do" method="post" encType="multipart/form-data" name="newProduct">
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">상품 코드</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="productId" id="productId" class="form-control" value="${product.productId }">
					</div>
				</div>				
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">상품명</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="pname" id="pname" class="form-control" value="${product.pname }">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">가격</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="unitPrice" id="unitPrice" class="form-control" value="${product.unitPrice }">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">상품 설명</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<textarea rows="2" cols="50" name="description" id="description" class="form-control">${product.description }</textarea>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">제조사</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="manufacturer" id="manufacturer" class="form-control" value="${product.manufacturer }">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">분류</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="category" id="category" class="form-control" value="${product.category }">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">재고수</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="unitsInStock" id="unitsInStock" class="form-control" value="${product.unitsInStock }">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3"></label>
					<div class="col-lg-4 col-sm-7 my-2">
					<c:if test="${product.condition eq 'New' }">
						<label><input type="radio" name="condition" value="New" checked>신상품</label>
						<label><input type="radio" name="condition" value="Old">중고품</label>
					</c:if>
					<c:if test="${product.condition eq 'Old' }">
						<label><input type="radio" name="condition" value="New">신상품</label>
						<label><input type="radio" name="condition" value="Old" checked>중고품</label>
					</c:if>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">상품 이미지</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="file" name="productImage" id="productImage" class="form-control" value="${product.productImage }">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<div class="col-lg-6 my-5" align="center">
						<input type="button" value="저장" class="btn btn-primary" onclick="checkAddProduct()">
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>