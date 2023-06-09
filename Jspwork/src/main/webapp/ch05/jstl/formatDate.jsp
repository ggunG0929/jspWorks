<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl - 날짜 표기</title>
</head>
<body>
	<h2>날짜를 다양한 형식으로 표기</h2>
	<jsp:useBean id="now" class="java.util.Date"/>		<!-- Fri Jun 09 11:14:24 KST 2023 -->
	<p>현재 날짜 및 시간: ${now }
	<p><fmt:formatDate value="${now }" type="date"/>	<!-- 2023. 6. 9. -->
	<p><fmt:formatDate value="${now }" type="time"/>	<!-- 오전 11:14:24 -->
	<p><fmt:formatDate value="${now }" pattern="yyyy-MM-dd hh:mm:ss a"/>
	
	<!-- 임의로 추가해서 봄 -->
	<p><fmt:formatDate value="${now }" type="both"/>					<!-- 2023. 6. 9. 오전 11:20:47 -->
	<p><fmt:formatDate value="${now }" type="both" dateStyle="full"/>	<!-- 2023년 6월 9일 금요일 오전 11:20:47 -->
	<p><fmt:formatDate value="${now }" type="both" timeStyle="full"/>	<!-- 2023. 6. 9. 오전 11시 20분 47초 대한민국 표준시 -->
</body>
</html>