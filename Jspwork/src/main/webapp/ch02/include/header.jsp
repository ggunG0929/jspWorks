<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	<!-- 한글을 읽어와야 하기 때문에 인코딩이 있어야 함 -->
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head> -->
<%!
	// 전역 변수나 함수 위치
	int pageCount = 0;
	void addCount() {
		pageCount++;	// 증가된 상태로 호출되기 때문에 후치연산이어도 상관없음
	}
%>
<!-- <body> -->
	<%
		addCount();
	%>
	<p>이 사이트 방문은 <%=pageCount %>번째 입니다.</p>
<!-- </body>
</html> -->