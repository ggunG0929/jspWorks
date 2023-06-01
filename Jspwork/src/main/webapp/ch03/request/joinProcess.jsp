<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");	// 안쓰면 이름이 깨짐(한글이라)
	
	String id = request.getParameter("userid");
	String pw = request.getParameter("passwd");
	String name = request.getParameter("uname");
	String phone1 = request.getParameter("phone1");
	String phone2 = request.getParameter("phone2");
	String phone3 = request.getParameter("phone3");
	String gender = request.getParameter("gender");
	String[] hobby = request.getParameterValues("hobby");
	String comment = request.getParameter("comment");	// 줄바꿈해서 입력해도 출력때는 옆으로 간격을 두고 뜸
%>
<p>아이디: <%=id %></p>
<p>비밀번호: <%=pw %></p>
<p>이름: <%=name %></p>
<p>연락처: <%=phone1 %> - <%=phone2 %> - <%=phone3 %></p>
<p>성별: <%=gender %></p>
<p>취미: 
<%
	if(hobby != null) {		// 임의로 null처리함
		for(int i=0; i<hobby.length; i++) {
			out.println(hobby[i]);
		}
 	}else {
		out.println("없음");
	}
%></p>
<p>코멘트: <%=comment %></p>