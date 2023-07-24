<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
		<h3 class="text-left mx-4 my-4">회원 정보</h3>
			<div class="row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">아이디</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="mid" value="${member.mid }" readonly>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">비밀번호</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="passwd" value="${member.passwd }" readonly>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">이름</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="mname" value="${member.mname }" readonly>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">성별</label>
				<div class="col-lg-4 col-sm-7 my-2">
					<c:if test="${member.gender eq '여'}">
					    <input type="radio" name="gender" value="여" checked>여
						<input type="radio" name="gender" value="남">남
				    </c:if>
					<c:if test="${member.gender eq '남'}">
					    <input type="radio" name="gender" value="여" >여
						<input type="radio" name="gender" value="남" checked>남
				    </c:if>
			    </div>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">생일</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="birth" value="${member.birth }" readonly>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">이메일</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="email" value="${member.email }" readonly>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">전화번호</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="phone" value="${member.phone }" readonly>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">주소</label>
				<input class="col-lg-4 col-sm-7 my-2" type="text" name="address" value="${member.address }" readonly>
			</div>
			<div class="form-group row">
				<div class="col-lg-3"></div>
				<label class="col-lg-2 col-sm-3">가입일</label>
				<div class="col-lg-4 col-sm-7 my-2" style="padding-top: 0.5rem">
					<%-- <input type="text" name="regDate" class="form-control" value="${member.regDate }" readonly> --%>
					<fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
		</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>