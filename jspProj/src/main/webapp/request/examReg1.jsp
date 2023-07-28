<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>examReg</title>
</head>
<body>
<h1>examReg</h1>
<%
	String [] pname = request.getParameterValues("pname");
	// pname = ["한가인","두가인","삼가인","사가인","오가인"]
	String [] title = "kor,eng,mat".split(",");
	// title = ["kor", "eng", "mat"]
	int [][] arr = new int[title.length][pname.length];
	/* 한가인 두가인 삼가인 사가인 오가인 
	국어 0,0  0,1  0,2  0,3  0,4
	수학 1,0
	영어 2,0
	*/

	for(int i=0; i<title.length; i++) {
		String [] str = request.getParameterValues(title[i]);
		// str[kor[i], eng[i], mat[i]]
		for(int j=0; j<str.length; j++) {
			arr[i][j] = Integer.parseInt(str[j]); 
			// arr 0,0 = 한가인국어점수 / arr 0,1= 두가인국어점수 ... 
		}
		// arr 0,0 = 한가인국어점수
		// arr 1,0 = 한가인수학점수
		// arr 2,0 = 한가인영어점수
		//
	}
	
	/* 한가인 두가인 삼가인 사가인 오가인 
	국어 0,0  0,1  0,2  0,3  0,4
	수학 1,0
	영어 2,0
	*/
	
	/*   총점   평균  등수
	한가인 0,0  0,1  0,2
	두가인 1,0
	삼가인 2,0
	...
	*/
	int [][] calc = new int[pname.length][title.length];
	for(int n=0; n<pname.length; n++){
		// 총점이니까 초기값 0 지정
		arr[n][0] = 0;
		for(int s=0; s<title.length; s++){
			calc[n][0] += arr[s][n];
		}
		// 평균
		calc[n][1] = calc[n][0]/title.length;
	} 
	// 등수
	for(int me=0; me<pname.length; me++){
		// 등수 초기값 1
		calc[me][2] = 1;	
		for(int you=0; you<pname.length; you++){
			// 평균값 비교
			if(calc[me][1] < calc[you][1] ){
				calc[me][2]++;	
			}
		}
	}
%>
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
		<% for(int i=0; i<pname.length; i++) { %>
		<tr>
			<td><%=pname[i] %></td>
			<% for(int j=0; j<calc.length; j++) { %>
			<td></td>
			<td></td>
			<td></td>
			<% } %>
		</tr>
		<% } %>
		<tr>
			<td colspan=4 align="right"><input type="submit" value="전송"></td>
		</tr>
	</table>
</body>
</html>

<%-- 
	



<%
for(int r = 1; r<=pname.length; r++){
	
	for(int i = 0; i<pname.length; i++){ 
		if(r == res[i][2]) {%>	


		<tr>
			<td><%=pname[i] %> <%=r %></td>
			<% for(int j = 0; j<arr.length; j++){ %>				
				<td><%=arr[j][i] %></td>
			<%} 
			 for(int j = 0; j<res[i].length; j++){ %>				
				<td><%=res[i][j] %></td>
			<%} %>		
		</tr>
		
<%}}}%>	
	
--%>