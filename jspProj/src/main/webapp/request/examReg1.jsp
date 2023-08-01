<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>examReg</title>
</head>
<body>
<h1>examReg-자바연결X</h1>
<%
	String [] pname = request.getParameterValues("pname");
	// pname = ["한가인","두가인","삼가인","사가인","오가인"]
	String [] title = "kor,eng,mat".split(",");
	// title = ["kor", "eng", "mat"]

	/* 한가인 두가인 삼가인 사가인 오가인 
	국어 0,0  0,1  0,2  0,3  0,4
	영어 1,0
	수학 2,0
	*/
	int [][] jum = new int[title.length][pname.length];

	for(int i=0; i<title.length; i++) {
		String [] str = request.getParameterValues(title[i]);
		// str[kor[i], eng[i], mat[i]]
		for(int j=0; j<str.length; j++) {
			jum[i][j] = Integer.parseInt(str[j]); 
		}
	}
	
	/* 한가인 두가인 삼가인 사가인 오가인 
	총점 0,0  0,1  0,2  0,3  0,4
	평균 1,0
	등수 2,0
	*/
 	int [][] calc = new int[3][pname.length];
	
	for(int n=0; n<pname.length; n++){
		// 총점이니까 초기값 0 지정
		calc[0][n] = 0;
		for(int s=0; s<title.length; s++){
			calc[0][n] += jum[s][n];
			// 평균
			calc[1][n] = calc[0][n]/title.length;
			}
	} 
	// 등수
	for(int me=0; me<pname.length; me++){
		// 등수 초기값 1
		calc[2][me] = 1;	
		for(int you=0; you<pname.length; you++){
			// 평균값 비교
			if(calc[1][me] < calc[1][you] ){
				calc[2][me]++;	
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
 			<% for(int j=0; j<jum.length; j++) { %>
			<td><%=jum[j][i] %></td>
			<% } %>
 			<% for(int k=0; k<calc.length; k++) { %>
			<td><%=calc[k][i] %></td>
			<% } %>
		</tr>
		<% } %>
	</table>
</body>
</html>