<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/excp/ex02.jsp"%>
    <!-- 에러페이지를 추가해도 try catch가 우선순위 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>aaa</title>
</head>
<body>
<h1>aaa입니다.</h1>
<%
	try{
		int a = 10;
		//int a = 10/0;	// 오류: java.lang.ArithmeticException: / by zero
		out.println("정상 실행1: "+a+"<br/>");
		
		int [] arr = {11, 22, 33, 44};
		out.println("정상 실행2: "+arr[1]+"<br/>");
		//out.println("정상 실행 2"+arr[7]+"<br/>");		// 오류: java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 4
	
		//a = Integer.parseInt("1234");
		a = Integer.parseInt("123a4");	// 오류: For input string
		out.println("정상 실행3: "+a+"<br/>");
		
	}catch(ArithmeticException e){
		out.println("수학적 에러 처리: "+e.getMessage());
		
	}catch(ArrayIndexOutOfBoundsException e){
		out.println("인덱스 에러 처리: "+e.getMessage());
		
	}catch(Exception e){
		out.println("일반적 에러 처리: "+e.getMessage());
	}
%>
</body>
</html>