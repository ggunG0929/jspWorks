<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>369</title>
</head>
<body>
<%
/* 	for(int a=1; a<101; a++) {
		if(((a%10)==3 || (a%10)==6 || (a%10)==9) && ((a/10)==3 || (a/10)==6 || (a/10)==9))  {
			out.println("<br>짝짝");
		}else if((a%10)==3 || (a%10)==6 || (a%10)==9)  {
			out.println("<br>짝");
		}else if((a/10)==3 || (a/10)==6 || (a/10)==9)  {
			out.println("짝");
		}else{
			out.println("<br>"+a);
		}
	} */
/* 	for(int a=1; a<101; a++) {
		if(a/10!=0 && (a/10)%3==0)  {
			out.println("<br>짝");
			if(a%10!=0 &&(a%10)%3==0){
				out.println("짝");
			}
		}else if(a%10!=0 &&(a%10)%3==0)  {
			out.println("<br>짝");
		}else{
			out.println("<br>"+a);
		}
	} */
/* 	for(int a=1; a<1001; a++) {
		if((a%1000)/100!=0 && ((a%1000)/100)%3==0) {
			out.println("<br>"+a+": 짝");
			if((a%100)/10!=0 && ((a%100)/10)%3==0)  {
				out.println("짝");
				if(a%10!=0 && (a%10)%3==0){
					out.println("짝");
				}
			}
		}else if((a%100)/10!=0 && ((a%100)/10)%3==0)  {
			out.println("<br>"+a+": 짝");
			if(a%10!=0 && (a%10)%3==0){
				out.println("짝");
			}
		}else if(a%10!=0 && (a%10)%3==0)  {
			out.println("<br>"+a+": 짝");
		}else{
			out.println("<br>"+a);
		}
	} */
	int n=1234;
	for(int a=1; a<n+1; a++) {
		String str = "";
		int num = a;
		while(true) {
			int one = num % 10;
			num /= 10;
			if(one!=0 && one%3==0) {
				str += " 짝";
			}
			if(num == 0){
				break;
			}
		}
		if(str.equals("")) {
			str += a;
		}
		out.println("<br>"+str);
	}
%>
</body>
</html>