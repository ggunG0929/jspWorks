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
	// form에서 값을 가져와 배열로 만듦
	String[] kor = request.getParameterValues("kor");
	String[] eng = request.getParameterValues("eng");
	String[] math = request.getParameterValues("math");
	String[] name = request.getParameterValues("sname");
//	int sumList[] = {0, 0, 0, 0, 0};
	int[] sumList = new int[5];
	
	// 총점
	for(int i=0; i<5; i++) {
		int knum=Integer.parseInt(kor[i]);
		int ennum=Integer.parseInt(eng[i]);
		int mnum=Integer.parseInt(math[i]);
		sumList[i] = knum + ennum + mnum;
	}
	
	// 등수
	// 총점으로 비교하는 것이 더 정확할 것 같아 총점으로 등수연산 
	int[] rankList = new int[5];
	for(int i=0; i<sumList.length; i++) {
		rankList[i] = 1;
		for(int j=0; j<sumList.length; j++) {
			if(sumList[i]<sumList[j]) {
				rankList[i]++;
			}
		}
	}
%>
<h1>subReg-noJava</h1>
	<table border="">
		<tr>
			<td>이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>총점</td>
			<td>평균</td>
			<td>등수</td>
		</tr>
		
<%-- 	<%	for(int i=0; i<5; i++) { %>
		<tr>
			<td><%=name[i] %></td>
			<td><%=kor[i] %></td>
			<td><%=eng[i] %></td>
			<td><%=math[i] %></td>
			<td><%=sumList[i] %></td>
			<td><%=sumList[i]/3 %></td>
			<td><%=rankList[i] %></td>
		</tr>
	<% } %> --%>
	
	<!-- 등수기준으로 출력 -->
	<%	for(int i=0; i<5; i++) { %>
		<%	for(int j=0; j<5; j++) { %>
			<% if(rankList[j] == i+1) { %>			
				<tr>
					<td><%=name[j] %></td>
					<td><%=kor[j] %></td>
					<td><%=eng[j] %></td>
					<td><%=math[j] %></td>
					<td><%=sumList[j] %></td>
					<td><%=sumList[j]/3 %></td>
					<td><%=rankList[j] %></td>
				</tr>
			<% } %>
		<% } %>
	<% } %>
	</table>
</body>
</html>