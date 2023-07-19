<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jdbc 연동(oracle)</title>
</head>
<body>
	<%
		String driverClass = "oracle.jdbc.OracleDriver";	// 드라이버 이름(오라클)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	// db 경로(위치)
		String username = "c##mydb";	// user 이름
		String password = "mydb";		// 비밀번호
		
		Connection conn = null;		// import
		
		try{
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, password);
			//out.println("db 연결 객체 생성: " + conn);	// conn = oracle.jdbc.driver.T4CConnection@7c620425
		} catch(Exception e) {
			out.println("db 연결에 실패했습니다.");
			out.println(e.getMessage());
		} finally {
			
		}
	%>
</body>
</html>