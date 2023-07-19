<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jdbc 연동(mySQL)</title>
</head>
<body>
	<%
		// 태그를 쓰면 자바처럼 쓸 수 있음
		String driverClass = "com.mysql.cj.jdbc.Driver";		// 드라이버 이름(mySQL)
		String url = "jdbc:mysql://localhost:3306/mydb";		// db 경로
		String username = "root";		// user 이름
		String password = "root";		// 비밀번호
		
		Connection conn = null;		// import
		
		try{
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, password);
			//out.println("db 연결 객체 생성: " + conn);	// conn = com.mysql.cj.jdbc.ConnectionImpl@3d55a846
		} catch(Exception e) {
			out.println("db 연결에 실패했습니다.");
			out.println(e.getMessage());
		} finally {
			
		}
	%>
</body>
</html>