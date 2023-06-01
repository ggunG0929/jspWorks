<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션 가져오기
	String id = (String)session.getAttribute("userId");
	String pw = (String)session.getAttribute("userPw");
	
	out.println("설정된 세션의 속성값[1]: " + id + "<br>");
	out.println("설정된 세션의 속성값[2]: " + pw + "<br>");
	
	session.removeAttribute("userId");		// 세션 이름이 "userId"인 경우를 삭제
%>

<h3>세션(아이디)을 삭제한 후</h3>
<%
	// 세션 가져오기
	id = (String)session.getAttribute("userId");
	pw = (String)session.getAttribute("userPw");
	
	out.println("설정된 세션의 속성값[1]: " + id + "<br>");	// null
	out.println("설정된 세션의 속성값[2]: " + pw + "<br>");
	
	session.removeAttribute("userId");
%>