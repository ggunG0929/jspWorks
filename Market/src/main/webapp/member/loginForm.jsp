<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3" align="center">
		<h3 class="my-5">Please LogIn</h3>
		<c:if test="${not empty error }">
			<div class="alert alert-danger">
				${error }
			</div>
		</c:if>
		<form action="/processLogin.do" method="post">
			<div class="form-group row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4 my-2">
					<input type="text" name="mid" class="form-control" placeholder="ID" required autofocus>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4 my-2">
					<input type="password" name="passwd" class="form-control" placeholder="Password" required>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4 my-5 d-grid">	
					<button type="submit" class="btn btn-success">로그인</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>