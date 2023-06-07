<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 쿠키 가져오기
	Cookie[] cookies = request.getCookies();
	out.println("현재 설정된 쿠키의 개수 => " + cookies.length + "<br>");		// 6개
	out.println("=====================<br>");
	
	for(int i=0; i<cookies.length; i++) {
//		out.println(cookies[i] + "<br>");	// 주소값
		out.println("쿠키이름: " + cookies[i].getName() + "<br>");			// userId userPw JsessionID username-localhost-8888~8890
		out.println("쿠키값: " + cookies[i].getValue() + "<br>");
		out.println("=====================<br>");
	}
%>