<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>star</title>
</head>
<body>
<%
	for(int i=0; i<3; i++) {
		for(int j=0; j<9-i; j++) {
			out.println("_");
		}
		for(int k=0; k<2*i+1; k++){
			out.println("*");
		}
		out.println("<br>");
	}
	for(int i=3; i<7; i++) {
		for(int j=0; j<i-3; j++) {
			out.println("_");
		}
		for(int k=0; k<26-(2*i+1); k++){
			out.println("*");
		}
		out.println("<br>");
	}
	for(int i=7; i<10; i++) {
		for(int j=0; j<9-i; j++) {
			out.println("_");
		}
		for(int k=0; k<(2*i+1); k++){
			out.println("*");
		}
		out.println("<br>");
	}
	for(int i=10; i<13; i++) {
		for(int j=0; j<i-3; j++) {
			out.println("_");
		}
		for(int k=0; k<26-(2*i+1); k++){
			out.println("*");
		}
		out.println("<br>");
	}
%>
</body>
</html>