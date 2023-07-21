<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<script src="resources/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3" align="center">
		<%
			String error = request.getParameter("error");
			if(error != null) {
				out.println("<div class='alert alert-danger'>");
				out.println("아이디와 비밀번호를 확인해 주세요");
				out.println("</div>");
			}
		%>
		<h2 class="my-5">Please LogIn</h2>
		<form action="j_security_check" method="post">
			<div class="form-group row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4 my-2">
					<input type="text" name="j_username" class="form-control" placeholder="ID" required autofocus>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4 my-2">
					<input type="password" name="j_password" class="form-control" placeholder="Password" required>
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