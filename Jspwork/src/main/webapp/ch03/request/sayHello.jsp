<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 쿼리 스트링 연습
/* 	int cnt = Integer.parseInt(request.getParameter("cnt"));	// cnt가 null이면 500 내부서버오류 */
	// cnt를 입력할 수 있는 코드
	int cnt = 0;
	if(request.getParameter("cnt") != null) {	// 객체이므로 null로 비교
		cnt = Integer.parseInt(request.getParameter("cnt"));
	}
	for(int i=1; i<=cnt; i++) {
		out.println("안녕~ JSP<br>");
	}

%>
<h3>주소표시줄에 ?cnt=숫자 를 추가하세요</h3>
<p>현재 cnt는 <%=cnt %></p>