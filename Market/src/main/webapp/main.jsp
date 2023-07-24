<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome~</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container my-3">
		<h2>Green Store에 오신 걸 환영합니다.</h2>
		<!-- 부트스트랩 이미지캡 -->
		<div class="card mb-3">
		  <img src="/resources/images/store_4.jpg" class="card-img-top" alt="메인배너">
		  <div class="card-body">
		    <h5 class="card-title">자유를 만끽하세요</h5>
		    <p class="card-text">좋은 상품과 착한 가격을 만나보세요.</p>
		    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
		  </div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>