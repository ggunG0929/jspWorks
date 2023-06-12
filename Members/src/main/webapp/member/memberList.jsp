<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<c:if test="${empty sessionId }">
		<script type="text/javascript">
			alert('로그인이 필요합니다')
			location.href = "/loginForm.do"
		</script>
	</c:if>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="memberlist">
		<h2>회원 목록</h2>
<%-- 	<h3>${memberList }</h3>		<!-- [member.Member@6008da0d] --> --%>
<%-- 	<c:forEach var="member" items="${memberList }">
		<p>아이디: <c:out value="${member.memberId }"></c:out>
	</c:forEach> --%>
		<table id="tbl_list">
			<thead>
				<tr>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이름</th>
					<th>성별</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberList }">
				<tr>
					<td>
						<a href = "/memberView.do?memberId=${member.memberId }">
							<c:out value="${member.memberId }"></c:out>
						</a>
					</td>
					<td><c:out value="${member.passwd }"></c:out></td>
					<td><c:out value="${member.name }"></c:out></td>
					<td><c:out value="${member.gender }"></c:out></td>
					<td><fmt:formatDate value="${member.joinDate }" pattern = "yyyy-MM-dd hh:mm:ss"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>