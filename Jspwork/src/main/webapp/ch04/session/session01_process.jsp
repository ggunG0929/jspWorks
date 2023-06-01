<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("userid");
	String userPw = request.getParameter("passwd");
	
//	if(userId.equals("corona") && userPw.equals("2019")) {
	if(userId.equals("today") && userPw.equals("2023")) {
		session.setAttribute("userId", userId);		// 세션 발급
		session.setAttribute("userPw", userPw);
//		out.println("로그인을 성공했습니다.");
	}else {
		out.println("<script>");
		out.println("alert('아이디나 비밀번호를 확인해 주세요')");	// 따옴표주의
		out.println("history.go(-1)");		// 이전 페이지로 이동
		out.println("</script>");
	}
%>
<!-- 세션 이름 가져오기(로그인 성공 후 다시 시도하면 로그인 실패시에도 corona님이 로그인한 상태입니다.를 출력함 - else에서 script를 추가) -->
<p><%=session.getAttribute("userId") %>님이 로그인한 상태입니다.</p>