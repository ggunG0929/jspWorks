<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>별</title>
</head>
<body>
<%
	for(int i=0; i<13; i++) {
		if(i==0 || i==12) {
			for(int j=0; j<19; j++) {
				if(j==9) {
					out.println("*");
				}else {
					out.println("_");
				}
			}
		}
		if(i==1 || i==11) {
			for(int j=0; j<19; j++) {
				if(j>=8 && j<=10) {
					out.println("*");
				}else {
					out.println("_");
				}
			}
		}
		if(i==2 || i==10) {
			for(int j=0; j<19; j++) {
				if(j>=7 && j<=11) {
					out.println("*");
				}else {
					out.println("_");
				}
			}
		}
		if(i==3 || i==9) {
			for(int j=0; j<19; j++) {
				out.println("*");
				}
		}
		if(i==4 || i==8) {
			for(int j=0; j<19; j++) {
				if(j>=1 && j<=17) {
					out.println("*");
				}else {
					out.println("_");
				}
			}
		}
		if(i==5 || i==7) {
			for(int j=0; j<19; j++) {
				if(j>=2 && j<=16) {
					out.println("*");
				}else {
					out.println("_");
				}
			}
		}
		if(i==6) {
			for(int j=0; j<19; j++) {
				if(j>=3 && j<=15) {
					out.println("*");
				}else {
					out.println("_");
				}
			}
		}
		out.println(i+"<br>");
	}
	
	out.println("<br><br>");

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
	
	out.println("<br><br>");

	for(int i=0; i<13; i++) {
		int sev = i % 7;
		if(sev < 3){			
			for(int j=0; j<9-i; j++) {
				out.println("_");
			}
			for(int k=0; k<2*i+1; k++){
				out.println("*");
			}
			out.println("<br>");
		}else {
			for(int j=0; j<i-3; j++) {
				out.println("_");
			}
			for(int k=0; k<26-(2*i+1); k++){
				out.println("*");
			}
			out.println("<br>");
		}
	}
	
	out.println("<br><br>");
%>
</body>
</html>