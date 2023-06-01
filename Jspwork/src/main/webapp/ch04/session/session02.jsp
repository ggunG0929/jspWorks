<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 같은 서버에서 session01의 로그인을 성공한 후에 해야 null이 아니고 corona / 2019로 나옴
	// 세션 가져오기
	String id = (String)session.getAttribute("userId");
	String pw = (String)session.getAttribute("userPw");

	out.println("설정된 세션의 속성값[1]: " + id + "<br>");
	out.println("설정된 세션의 속성값[2]: " + pw + "<br>");
%>