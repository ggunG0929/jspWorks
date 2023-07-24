<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송 정보</title>
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/validation.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-4">
		<h2 class="text-left mx-4 my-4">배송 정보</h2>
		<div class="row mx-5">
			<form action="/processShippingInfo.do" method="post">
				<input type="hidden" name="cartId" value="${cartId }">
				<div class="form-group row">
					<!-- 임의로 col추가 -->				
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">성명</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="name" class="form-control">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">배송일</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="shippingDate" class="form-control" placeholder="yyyy/mm/dd">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">국가명</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="country" class="form-control">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">우편번호</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="zipCode" class="form-control">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<label class="col-lg-2 col-sm-3">주소</label>
					<div class="col-lg-4 col-sm-7 my-2">
						<input type="text" name="addressName" class="form-control">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-3"></div>
					<div class="col-lg-6 my-5" align="center">
						<input type="submit" value="등록" class="btn btn-primary">
						<a href="/checkOutCancel.do" class="btn btn-secondary">취소</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>