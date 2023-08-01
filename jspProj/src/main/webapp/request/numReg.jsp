<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>numReg</title>
</head>
<body>
<%
	String[] noArr = request.getParameterValues("no");
	int sum=0;
	for(String no : noArr) {
		try{
			int num=Integer.parseInt(no);
			if(num % 2 == 0){
				sum += num;
			}
		}catch(Exception e){
			
		}
	}
%>
<h1>numReg</h1>
no: <%=request.getParameter("no") %><br>
noArr: <%=Arrays.toString(noArr) %><br>
sum(짝수만): <%=sum %>
</body>
</html>