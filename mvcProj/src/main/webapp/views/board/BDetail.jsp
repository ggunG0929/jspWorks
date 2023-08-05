<%@page import="model_p.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	BoardDTO dto = (BoardDTO)request.getAttribute("mainData");
%>
<table border="">
	<tr>
		<td width="200px">id</td>
		<td width="700px"><%=dto.getId() %></td>
	</tr>
	<tr>	
		<td>제목</td>
		<td><%=dto.getTitle() %></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><%=dto.getPname() %></td>
	</tr>
	<tr>
		<td>작성일</td>
		<td><%=dto.getReg_dateStr() %></td>
	</tr>
	<tr>
		<td>조회수</td>
		<td><%=dto.getCnt() %></td>
	</tr>
	<!-- 파일이 있을 때만 -->
	<% if(!dto.getUpfile().equals("")) { %>
	<tr>
		<td>파일</td>
		<td>
			<!-- 이미지 파일이라면 -->
			<% if(dto.isImg()) { %>
				<!-- 이미지를 보여줌 -->
				<img src="/mvcProj/up/<%=dto.getUpfile()%>" alt="">
			<!-- 그 외의 파일이라면 -->
			<% }else{ %>
				<!-- 파일 다운로드 -->
				<!-- 보드컨트롤러에서는 forward를 바로 시켜 404오류가 뜨므로 컨트롤러를 새로 연결해줘야함 -->
				<a href="/mvcProj/noneJsp/FileDown?fName=<%=dto.getUpfile() %>"><%=dto.getUpfile() %></a>
			<% } %>
		</td>
	</tr>
	<% } %>
	<tr>
		<td>내용</td>
		<td><%=dto.getContentBr() %></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<a href="BList">목록으로</a>
			<a href="BDeleteForm?id=<%=dto.getId() %>">삭제</a>
			<a href="BModifyForm?id=<%=dto.getId() %>">수정</a>
			<a href="BReplyForm?id=<%=dto.getId() %>">답변</a>
		</td>
	</tr>
</table>