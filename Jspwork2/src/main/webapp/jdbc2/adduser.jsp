<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user 추가</title>
</head>
<body>
	<%@ include file="dbconn.jsp" %>
	<%
		PreparedStatement pstmt = null;		// import
		
		try{	
//			String sql = "insert into myuser values (101, 'today123', '투데이', '010-1234-5678')";
//			String sql = "insert into myuser values (102, 'cloud123', '구름', '010-1111-2222')";
			String sql = "insert into myuser values (103, 'hangang', '한강', '010-1111-3333')";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	// 실행처리
			out.println("회원 추가 완료");
		} catch(Exception e) {
			out.println(e.getMessage());
		}
	%>
</body>
</html>