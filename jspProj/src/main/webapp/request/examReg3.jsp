<%@page import="oops_p.Exam3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>examReg-자바연결</title>
</head>
<body>
<h1>examReg-자바연결(이중배열X)</h1>
<%
	// name 필드
    String[] pname = request.getParameterValues("pname");
    // jum 필드
	String[] title = "kor,eng,mat".split(",");
	// 빈 모델 생성
    Exam3[] Students = new Exam3[pname.length];

	// 초기화를 막기 위해 전역변수로
    int kor, eng, mat;
	// jum 필드를 int로 채워주기
    for (int i=0; i<pname.length; i++) {
        kor = Integer.parseInt(request.getParameterValues(title[0])[i]);
        eng = Integer.parseInt(request.getParameterValues(title[1])[i]);
        mat = Integer.parseInt(request.getParameterValues(title[2])[i]);
        Students[i] = new Exam3(pname[i], kor, eng, mat);
    }
    // rankCalc 메서드 호출
    for (Exam3 student : Students) {
        student.rankCalc(Students);
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
        <td>등급</td>
    </tr>
    <% for (int i = 0; i < pname.length; i++) { %>
        <tr>
            <td><%= pname[i] %></td>
            <% int[] jum = Students[i].getJum(); %>
            <% for (int j = 0; j < jum.length; j++) { %>
                <td><%= jum[j] %></td>
            <% } %>
            <td><%= Students[i].getTot() %></td>
            <td><%= Students[i].getAvg() %></td>
            <td><%= Students[i].getRank() %></td>
            <td><%= Students[i].getGrade() %></td>
        </tr>
    <% } %>
</table>
</body>
</html>