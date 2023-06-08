<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<style>
	#container{width: 80%; margin: 30px auto; text-align: center;}
	select{padding: 5px;}
</style>
</head>
<jsp:useBean id="product" class="product.Product" scope="session"/>		<!-- scope 저장기능 저장해두었다가 연결되도록. 현재 파일에서는 없어도 무방 -->
<body>
	<div id="container">
		<h2>상품 목록</h2>
		<hr>
		<form action="selProduct.jsp" method="post">
			<select name="select">
		<!-- 	<option>수박</option>
				<option>참외</option> -->
				<c:forEach var="product" items="${product.productList }">
					<option><c:out value="${product }"></c:out></option>
				</c:forEach>
			</select>
			<button type="submit">선택</button>
		</form>
	</div>
</body>
</html>