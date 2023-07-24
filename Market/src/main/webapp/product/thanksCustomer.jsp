<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 완료</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-4">
		<h2 class="text-left mx-4 my-4">주문 완료</h2>
		<h2 class="alert alert-danger">주문해주셔서 감사합니다.</h2>
		<p>주문은 ${shipping_shippingDate }일에 배송될 예정입니다.</p>
		<p>주문 번호: ${shipping_cartId }</p>
		<p><a href="/productList.do" class="btn btn-secondary">상품 목록</a></p>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>