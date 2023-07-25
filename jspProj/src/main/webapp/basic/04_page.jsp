<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpJspPage page2 = (HttpJspPage)page;
	Enumeration en = this.getInitParameterNames();	// import
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04_page</title>
</head>
<body>
<h1>04_page</h1>
<!-- 모두 org.apache.jsp.basic._04_005fpage_jsp@5fc47a18라고 출력, 클래스형태 -->
this : <%=this %><br/>
page : <%=page %><br/>
page2 : <%=page2 %><br/>

<!-- Jasper JSP 2.3 엔진 -->
<%-- servletInfo: <%=page.getServletInfo() %><br/> //오류남 --%>
servletInfo: <%=page2.getServletInfo() %><br/>
<!-- jsp -->
servletName: <%=this.getServletName() %><br/>
<!-- fork<br> xpoweredBy -->
<%
	while(en.hasMoreElements()) {
		out.println(en.nextElement()+"<br/>");
	}
%>
</body>
</html>