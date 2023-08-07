<%@page import="model_p.PageData2"%>
<%@page import="model_p.GalleryDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData2 pd = (PageData2)request.getAttribute("pd");
	// 같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	GalleryDTO dto = (GalleryDTO)request.getAttribute("mainData");
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
	<tr>
		<td>파일</td>
		<td>
			<img src="/mvcProj/up/<%=dto.getUpfile()%>" alt="">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><%=dto.getDescriptionBr() %></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<a href="GList?page=<%=pd.page %>">목록으로</a>
			<a href="GDeleteForm?id=<%=dto.getId() %>&page=<%=pd.page %>">삭제</a>
			<a href="GModifyForm?id=<%=dto.getId() %>&page=<%=pd.page %>">수정</a>
		</td>
	</tr>
</table>