<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String[] fruits = request.getParameterValues("fruit");
	
	/* 내답 - 임의로 선택하지 않았을 때 예외처리추가 */
	if(fruits != null) {
		out.print("선택한 과일: ");
		for(int i=0; i<fruits.length; i++) {
			out.println(fruits[i]);
		}
	}else {
		out.println("선택한 과일이 없습니다.");
	}
	
%>

<%-- <!-- 선생님 답 -->
<p>선택한 과일:
<%
	for(int i=0; i<fruits.length; i++) {
		out.println(fruits[i]);
	}
%>
</p> --%>