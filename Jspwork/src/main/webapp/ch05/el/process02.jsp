<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<style>
	#container{width: 80%; margin: 30px auto; text-align: center;}
	table{width: 600px; margin: 0 auto;}
	table, th, td{border: 1px solid #ccc; border-collapse: collapse;}
	table th, td{padding: 10px;}
	table thead{background: #eee;}
</style>
</head>
					<!-- MVC-control역할 - 액션태그 -->
<jsp:useBean id="member" class="member.Member"/>
<jsp:setProperty property="userid" name="member"/>
<jsp:setProperty property="passwd" name="member"/>
<jsp:setProperty property="uname" name="member"/>
<body>
	<div id="container">
		<h3>회원 정보</h3>
		<hr>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이 름</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<!-- MVC-model(데이터)역할 -->
					<td><%=member.getUserid() %></td>
					<td><%=member.getPasswd() %></td>
					<td><%=member.getUname() %></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>