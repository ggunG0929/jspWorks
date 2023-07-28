<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>subReg</title>
</head>
<body>
<%	
	String[] kor = request.getParameterValues("kor");
	String[] eng = request.getParameterValues("eng");
	String[] math = request.getParameterValues("math");
	String[] name = request.getParameterValues("sname");
//	int sumList[] = {0, 0, 0, 0, 0};
	int[] sumList = new int[5];
	
	// 총점
	int ksum=0;
	int esum=0;
	int msum=0;
	int	nsum=0;
	for(int i=0; i<5; i++) {
		int knum=Integer.parseInt(kor[i]);
		int ennum=Integer.parseInt(eng[i]);
		int mnum=Integer.parseInt(math[i]);
		sumList[i] = knum + ennum + mnum;
	}
%>
<h1>subReg</h1>
	<table border="">
		<tr>
			<td>이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>총점</td>
			<td>평균</td>
		</tr>
	<%	for(int i=0; i<5; i++) { %>
		<tr>
			<td><%=name[i] %></td>
			<td><%=kor[i] %></td>
			<td><%=eng[i] %></td>
			<td><%=math[i] %></td>
			<td><%=sumList[i] %></td>
			<td><%=sumList[i]/3 %></td>
		</tr>
	<% } %>
	</table>
</body>
</html>