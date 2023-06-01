<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 이걸 풀어줘야 한글이 뜸 -->
<%
	// 입력된 자료를 받아올 때 한글 인코딩 - 근데 왜 안깨지지? - reauest1이 html이 아니라 jsp여서? - 오늘 하니까 깨짐
	request.setCharacterEncoding("utf-8");

	// 폼에 입력된 자료 받아오기
	String name = request.getParameter("uname");
%>
<p>이름: <%=name %></p>