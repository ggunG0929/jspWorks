<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> --%>	<!-- 자동으로 했는데도 오류남 주소가 조금 다름 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	<!-- uri= 까지 치고 기다린 후 선택 prefix= 도 기다린 후 선택 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 예제</title>
<%
	// 모델 생성
	pageContext.setAttribute("cart", "계란");
%>
</head>
<body>
	<h3>${cart }</h3>
	
<%-- 	<%
		int num = 11;
		if(num % 2 == 0) {
			out.println("짝수");
		}else {
			out.println("홀수");
		}
	%> --%>
	
	<c:set var="num" value="11"></c:set>	<!-- c:까지 치고 기다린 후 선택 -->
	<c:if test="${num % 2 == 0}">
		<c:out value="${'짝수'}"></c:out>
	</c:if>
	<c:if test="${num % 2 == 1}">
		<c:out value="${'홀수'}"></c:out>
	</c:if>
	
	<br>
	<!-- 구구단 5단 -->
	<c:set var="dan" value="5"/>
	<c:forEach var="i" begin="1" end="9">
		<c:out value="${dan} x ${i} = ${dan*i}"/><br>
	</c:forEach>
	
	<br>
	<!-- 전체 구구단 -->
	<c:forEach var="dan" begin="1" end="9">
		<c:forEach var="i" begin="1" end="9">
			<c:out value="${dan} x ${i} = ${dan*i}"/><br>
		</c:forEach>
	</c:forEach>
</body>
</html>