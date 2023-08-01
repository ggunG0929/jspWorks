<%@page import="oops_p.Exam2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>examReg-자바연결</title>
</head>
<body>
<h1>examReg-자바연결</h1>
<%
	String [] pname = request.getParameterValues("pname");
	// pname = ["학생(i+1)"]
	
	String [] title = request.getParameterValues("title");
	// title = ["sub(i+1)"]
	
	String [] jumsu = request.getParameterValues("jumsu");
	// jumsu = [학생1sub1, 학생1sub2, 학생1sub3,
	//			학생2sub1, 학생2sub2, 학생2sub3, ...]

/* 	// jumsu를 이중배열 jum2로 -- 이중배열을 거치지 않고 바로 연산으로 범위를 정해 java로 보낼 수 있지 않을까?
	int[][] jum2 = new int[pname.length][title.length];
	int index = 0;
	for(int i=0; i<pname.length; i++) {
	    for(int j=0; j<title.length; j++) {
	        jum2[i][j] = Integer.parseInt(jumsu[index++]);
	    }
	}
	// jum2에서 해당 학생의 점수만 jum으로
    Exam2[] Students = new Exam2[pname.length];
    for(int i=0; i<pname.length; i++) {
	    int[] jum = new int[title.length];	// 초기화
    	for(int j=0; j<title.length; j++) {
    		jum[j] = jum2[i][j];	// The local variable jum may not have been initialized
    	}
        Students[i] = new Exam2(pname[i], jum);
    } */

    // jumsu에서 해당학생의 점수만 jum으로
	Exam2[] Students = new Exam2[pname.length];	
	int index = 0;
	for(int i=0; i<pname.length; i++) {
	    int[] jum = new int[title.length]; // 초기화
    	for(int j=0; j<title.length; j++) {
    		// jumsu를 학생(i)마다 과목(j)순서대로 과목 수 크기의 배열로 저장
	        jum[j] = Integer.parseInt(jumsu[index++]);
	    }
	    Students[i] = new Exam2(pname[i], jum);
	}
    // 등수 연산 메소드 호출
    for (Exam2 student : Students) {
        student.rankCalc(Students);
    }

%>
	<table border="" style="border-collapse: collapse">
		<tr>
			<td>이름 과목</td>
			<% for(int i=0; i<title.length; i++) { %>
				<td><%=title[i] %></td>
			<% } %>
			<td>총점</td>
			<td>평균</td>
			<td>등급</td>
			<td>등수</td>
		</tr>
		
		<!-- 입력한 순서대로 출력 -->
<%-- 	<% for(Exam2 student : Students) { %>
		<tr>
            <td><%=student.getName() %></td>
            <% for (int j=0; j<title.length; j++) { %>
                <td><%= student.getJum()[j] %></td>
            <% } %>
			<%=student.toString() %>
	<% } %> --%>
	
		<!-- 등수기준으로 출력 -->
		<%	
		for(int i=0; i<pname.length; i++) {
			for(int k=0; k<pname.length; k++) {
				// 학생들 중 i에 따라 1등부터 pname.length+1등인 인덱스를 갖고 있는 k인덱스를 찾아내서 출력
				if(Students[k].getRank()==i+1) { 
		%>			
					<tr>
						<td><%=Students[k].getName() %></td>
						<%	for(int j=0; j<title.length; j++) { %>
			                <td><%= Students[k].getJum()[j] %></td>
		        	    <% } %>
						<%=Students[k].toString() %>
					</tr>
		<% 
				}
			}
		} 
		%>
	</table>
</body>
</html>