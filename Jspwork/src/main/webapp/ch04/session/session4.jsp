<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션 가져오기
	String id = (String)session.getAttribute("userId");
	String pw = (String)session.getAttribute("userPw");
	
	out.println("설정된 세션의 속성값[1]: " + id + "<br>");
	out.println("설정된 세션의 속성값[2]: " + pw + "<br>");
	
	// 세션 모두 삭제
	session.invalidate();
	
	// 임의로 추가
	out.println("세션값을 모두 삭제했습니다.");
%>

<%-- <%
	// 세션 가져오기	null이라 500(내부서버오류)
	id = (String)session.getAttribute("userId");
	pw = (String)session.getAttribute("userPw");
	
	out.println("설정된 세션의 속성값[1]: " + id + "<br>");
	out.println("설정된 세션의 속성값[2]: " + pw + "<br>");
	
	session.removeAttribute("userId");
%> --%>