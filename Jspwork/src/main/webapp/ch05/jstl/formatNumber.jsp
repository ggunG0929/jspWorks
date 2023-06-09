<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	<!-- 위에서부터 찾기. fmt -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숫자 표기</title>
</head>
<body>
	<!-- 천단위 구분기호 표시 -->
	<p>숫자: <fmt:formatNumber value="3200100"/>
	<p><fmt:formatNumber value="3200100" type="number"/>
	<p><fmt:formatNumber value="3200123.4567" pattern="#,##0.00"/>	<!-- 3,200,123.46 -->
	
	<!-- 통화 -->
	<p><fmt:formatNumber value="3200100" type="currency"/>						<!-- 한국원화 -->
	<p><fmt:formatNumber value="3200100" type="currency" currencySymbol="\\"/>	<!-- 한국원화 -->
	<p><fmt:formatNumber value="3200100" type="currency" currencySymbol="$"/>	<!-- 달러 -->
	
	<!-- 퍼센트 -->
	<p><fmt:formatNumber value="0.25" type="percent"/>
</body>
</html>