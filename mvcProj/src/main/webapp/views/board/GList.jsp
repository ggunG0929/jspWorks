<%@page import="model_p.PageData2"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model_p.GalleryDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	img{width: 180px;
	}
</style>
<table border="">
	<!-- 같은 이름의 클래스에서 넘겨받은 메인데이터 출력 -->
	<tr align="center">
	<%
	PageData2 pd = (PageData2)request.getAttribute("pd");
	int i=pd.start;
	for(GalleryDTO dto : (ArrayList<GalleryDTO>)request.getAttribute("mainData")) {
		i++; 
	%>
		<td>
			<a href="GDetail?id=<%=dto.getId() %>">
				<img src="/mvcProj/up/<%=dto.getUpfile()%>" alt="">
				<%=dto.getTitle() %>
			</a>
		</td>
	<% } %>
	</tr>
	<tr>
		<td colspan="5" align="center">
		<% if(pd.pageStart>1) { %>
			<a href="?page=<%=pd.pageStart-1 %>">[이전]</a>
		<% } for(int p=pd.pageStart; p<=pd.pageEnd; p++) {
			if(pd.page == p) { %>
				[<%=p %>]
		<% }else{ %>
				<a href="?page=<%=p %>"><%=p %></a>
		<% }} if(pd.pageEnd < pd.pageTotal) { %>
			<a href="?page=<%=pd.pageEnd+1 %>">[다음]</a>
		<% } %>
		</td>
	</tr>
	<tr>
		<td colspan="5" align="right"><a href="GWriteForm?page=<%=pd.page %>">글쓰기</a></td>
	</tr>
</table>