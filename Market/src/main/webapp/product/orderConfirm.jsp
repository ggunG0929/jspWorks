<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-4">
		<h2 class="text-left mx-4 my-4">주문 내역</h2>
		<div class="row mx-5">
			<p class="text-center" style="font-size: 1.5rem">배송정보</p>
			<div class="col-lg-3 col-md-2"></div>
			<div class="col-lg-3 col-md-4">
				<p><strong>성명: ${shipping_name }</strong></p>
				<p>주소: ${shipping_addressName }</p>
				<p>우편번호: ${shipping_zipCode }</p>
			</div>
			<div class="col-lg-3 col-md-4" align="right">
				<p><em>배송일: ${shipping_shippingDate }</em></p>
			</div>
		</div>
		<div>
			<table class="table table-hover my-3">
				<tr>
					<th class="text-center">상품</th>
					<th class="text-center">수량</th>
					<th class="text-center">가격</th>
					<th class="text-center">소계</th>
				</tr>
				<c:forEach items="${cartList }" var="product">
				<tr>
					<td class="text-center">${product.pname }</td>
					<td class="text-center">${product.quantity }</td>
					<td class="text-center">${product.unitPrice }</td>
					<td class="text-center">${product.quantity*product.unitPrice }</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="3" class="text-end"><strong>총액: </strong></td>
					<td class="text-center text-danger"><strong>${sum }</strong></td>
				</tr>
			</table>
			<a href="/thanksCustomer.do" class="btn btn-success mx-3">주문 완료</a>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>