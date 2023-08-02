<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 과목 수
	int s = 3;
	//s 값을 가져올 수 있다면(값이 있고 +비어있지 않으면) - 학생 수 변경시 s값이 비어있어서 형변환실패 오류가 생김
	if(request.getParameter("s")!=null && !request.getParameter("s").isEmpty()) {	// java.lang.NumberFormatException: For input string: ""
		// s 값을 정수형으로 가져옴
		s = Integer.parseInt(request.getParameter("s"));
	}
	// 학생 수
	int n = 5;
	if(request.getParameter("n")!=null) {
		n = Integer.parseInt(request.getParameter("n"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>examForm</title>
<script type="text/javascript">
	function sChange(){
		var s = document.getElementById("subject").value;
		var intS = parseInt(s);
		// s값이 문자인 경우 or s값이 0 이하인 경우
		if(isNaN(intS) || intS<=0) {
			alert("과목 수는 1 이상의 숫자여야 합니다.");
			s.select();
		}else {			
			examForm.action="?"
			examForm.submit();	
		}
	}
	function nChange(){
		examForm.action="?"
		examForm.submit();	
	}
</script>
</head>
<body>
<h1>examForm</h1>
<%
	// 매번 입력하기 번거로워 점수에 랜덤값 부여
	Random r = new Random();
%>
<form action="examReg2.jsp" name="examForm">
	<table>
		<tr>
			<td>과목 수: <input type="text" name="s" id="subject" placeholder="<%=s %>" style="width: 30px">
				<input type="button" onclick="sChange()" value="확인"></td>
			<td>학생 수: <select name="n" onchange="nChange()">
				<%	
					for(int i=5; i<=30; i+=5) { 
					String sel = "";
					if(i==n) {
						sel = "selected";
					}
				%>
					<option <%=sel %>><%=i %></option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td>이름	과목</td>
 			<% for(int i=0; i<s; i++) { %>
			<td><input type="text" name="title" value="sub<%=i+1 %>"/></td>
 			<% } %>
		</tr>
		<% for(int i=0; i<n; i++) { %>
		<tr>
			<td><input type="text" name="pname" value="학생<%=i+1 %>" /></td>
			<% for(int j=0; j<s; j++) { %>
			<td><input type="text" name="jumsu" value="<%=r.nextInt(40,100) %>"/></td>
			<% } %>
		</tr>
		<% } %>
		<tr>
			<!-- 과목 수를 많이 늘릴 경우 전송 버튼을 누르기 불편 -->
			<%-- <td colspan=<%=s+1 %> align="right"><input type="submit" value="전송"></td> --%>
			<td><input type="submit" value="전송"></td>
		</tr>
	</table>
</form>
</body>
</html>