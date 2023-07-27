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
	int stuList[] = {0, 0, 0, 0, 0};
	int ksum=0;
	int esum=0;
	int msum=0;
	int	nsum=0;
	for(int i=0; i<5; i++) {
		int knum=Integer.parseInt(kor[i]);
		ksum += knum;
		int ennum=Integer.parseInt(eng[i]);
		esum += ennum;
		int mnum=Integer.parseInt(math[i]);
		msum += mnum;
		nsum += knum + ennum + mnum;
		stuList[i] = knum + ennum + mnum;
	}
%>
<h1>subReg</h1>
	<table>
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
			<td><%=stuList[i] %></td>
			<td><%=stuList[i]/3 %></td>
		</tr>
	<% } %>
		<tr>
			<td>과목별 합계</td>
			<td><%=ksum %></td>
			<td><%=esum %></td>
			<td><%=msum %></td>
			<td><%=nsum %></td>
			<td></td>
		</tr>
	</table>
</body>
</html>