<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="gugudan" class="gugudan.GuguBean" />	<!-- <쓰고 jsp:usebean 선택 -->
<h3>구구단 출력하기</h3>
<%
	int dan = Integer.parseInt(request.getParameter("dan"));

	// 자바 클래스의 메서드 호출(times() 호출)
//	gugudan.times(dan);		/* void일 경우만 */
	int[] result = gugudan.times(dan);	/* 계산 결과값이 */
	
	for(int i=1; i<10; i++) {
		out.println(dan + " X " + i + " = " + result[i-1] + "<br>");
	}
%>