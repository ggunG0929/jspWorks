<%@page import="model_p.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	BoardDTO dto = (BoardDTO)request.getAttribute("mainData");
%>
<!-- 파일이 존재하므로 enctype="multipart/form-data" -->
<form action="BModifyReg" method="post" enctype="multipart/form-data">
	<table border="">
		<tr>
		<!-- 수정 불가 -->
			<td width="200px">id</td>
			<td width="700px">
				<input type="text" name="id" readonly="readonly" value="<%=dto.getId() %>"/>
			</td>
		</tr>
		<tr>
			<td width="100px">제목</td>
			<td width="800px"><input type="text" name="title" value="<%=dto.getTitle() %>"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="pname" value="<%=dto.getPname() %>"></td>
		</tr>
		<tr>
		<!-- 수정 불가 -->
			<td >작성일</td>
			<td><%=dto.getReg_dateStr() %></td>
		</tr>
		<tr>
		<!-- 수정 불가 -->
			<td >조회수</td>
			<td><%=dto.getCnt() %></td>
		</tr>
		<tr>
		<!-- 수정 불가 -->
		<!-- 암호로 수정자격 확인 -->
			<td>암호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td>파일</td>
			<td>
			<!-- 작성시 파일을 첨부했다면 -->
			<% if(dto.getUpfile().equals("")) { %>
				<!-- 새로 파일첨부 가능 -->
				<input type="file" name="upfile">
			<!-- 작성시 파일을 첨부하지 않았다면 -->
			<% }else{ %>
				<!-- 파일삭제 가능 -->
				<%=dto.getUpfile() %><input type="button" value="파일삭제" />
			<% } %>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" id="" 
				cols="30" rows="10"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정하기">
				<input type="reset" value="초기화">
				<a href="BDetail?id=<%=dto.getId() %>">뒤로</a>
			</td>
		</tr>
	</table>
</form>
