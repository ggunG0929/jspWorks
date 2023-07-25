<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03_third</title>
</head>
<body>
<h1>03_third</h1>

<%!
	// declaration: 클래스 정의부
	String name = "나는문어";
	int age = 49;
	boolean marriage = true;
	
	void meth_1() {
		System.out.println("meth_1() 실행");
	}
	
	//System.out.println("정의부에서 실행구문 불가");		// error
	
	public void jspInit(){
		System.out.println("jspInit() 실행");
		//out.println("str:"+str);
	}
	
	public void jspDestroy(){	// jsp 수정시에 출력함
		System.out.println("jspDestroy() 실행");
	}
%>
<%!
	//int age = 23;	// error
	int [] arr = {11, 22, 33, 44, 55, 66};
%>
<%
	// scriptlet-서비스
	System.out.println("scriptlet 실행:"+age);
	int age = 23;	// 지역변수 멤버변수와 중첩가능
	System.out.println("scriptlet 실행:"+age);
	//int age = 17; // 지역변수 중첩 불가
	String str = "아기상어";
//메소드 정의 불가 : 스크립트릿은 서비스인 메소드이므로 메소드 중첩 정의 불가
//	void meth_2() {
//		System.out.println("meth_1() 실행");
//	}
	out.println("str:"+str);
%>

</body>
</html>