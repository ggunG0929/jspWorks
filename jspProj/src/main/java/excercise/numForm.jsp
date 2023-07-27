<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cnt = 5;
	if(request.getParameter("cnt")!=null) {
		cnt = Integer.parseInt(request.getParameter("cnt"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>numForm</title>
<script type="text/javascript">
	function cntChange(){
//		alert("test");
		regForm.action="?"
		regForm.submit();	
	}
</script>
</head>
<body>
cnt: <%=cnt %>
<h1>numForm</h1>
<form action="numReg.jsp" name="regForm">
	<table border="">
		<tr>
			<td colspan="2" align="right">
				칸 수:
				<select name="cnt" id="" onchange="cntChange()">
				<%	for(int i=5; i<=30; i+=5) { 
					String sel = "";
					if(i==cnt) {
						sel = "selected";
					}
				%>
					<option <%=sel %>><%=i %></option>
				<% } %>
				</select>
			</td>
		</tr>
	<%	for(int i=0; i<10; i++) { %>
		<tr>
			<td><%=i+1 %></td>
			<td><input type="text" name="no" value="<%=(int)(Math.random()*100)+10 %>" /></td>
		</tr>
	<% } %>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="계산" /></td>
		</tr>
	</table>
</form>
</body>
</html>