<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>-- 세션 유효 시간 --</h3>
<%
	// 현재 세션 최대 시간 가져옴
	int time = session.getMaxInactiveInterval() / 60;	// 초기준으로 나오기때문에 60으로 나누어 분으로 환산

	out.println("<p>" + session.getMaxInactiveInterval() + "초</p>");
	out.println("<p>세션 유효 시간: " + time + "분</p>");
%>
<h3>-- 세션 시간 변경 --</h3>
<%
	session.setMaxInactiveInterval(300);	// 300초(5분)로 변경
	time = session.getMaxInactiveInterval() / 60;
	
	out.println("<p>" + session.getMaxInactiveInterval() + "초</p>");
	out.println("<p>세션 유효 시간: " + time + "분</p>");
%>