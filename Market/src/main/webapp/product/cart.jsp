<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<script src="resources/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-4">
		<h2 class="text-left mx-4 my-4">장바구니</h2>
		<div class="row mx-5">
			<table style="width: 100%">
				<tr>
				<!-- 임의로 패딩 -->
					<td align="right" style="padding: 20px">
						<a href="/productList.do" class="btn btn-secondary">&laquo; 쇼핑 계속하기</a>
					</td>
				</tr>
			</table>
			<div style="padding-top: 50px">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>상품</th>
							<th>가격</th>
							<th>수량</th>
							<th>소계</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cartList }" var="product">
							<tr>
								<td>${product.productId } - ${product.pname }</td>
								<td>${product.unitPrice }</td>
								<td>${product.quantity }</td>
								<td>${product.unitPrice*product.quantity }</td>
								<!-- 임의로 onclick 추가 -->
								<td><a href="/removeCart.do?productId=${product.productId }" class="badge bg-dark"
								 onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a></td>
							</tr>
						</c:forEach>
					</tbody>
					<!-- 임의로 tfoot, colspan, 버튼 위치변경 -->
					<tfoot>
							<tr>
								<th><a href="/deleteCart.do" class="btn btn-danger">비우기</a></th>
								<th colspan="2" style="text-align: center">총액</th>
								<th>${sum }</th>
								<th style="text-align: right"><a href="/shippingInfo.do?cartId=${cartId }" 
									class="btn btn-success">주문하기</a></th>
							</tr>
					</tfoot>
				</table>
				
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>