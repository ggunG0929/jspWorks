<%@page import="model_p.PageData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model_p.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="">
	<tr align="center">
		<td width="50px">번호</td>
		<td width="500px">제목</td>
		<td width="100px">작성자</td>
		<td width="200px">작성일</td>
		<td width="50px">조회수</td>
	</tr>
	<%
	PageData pd = (PageData)request.getAttribute("pd");
	int i=pd.start;
	// 같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	for(BoardDTO dto : (ArrayList<BoardDTO>)request.getAttribute("mainData")) {
		i++; 
	%>
	<tr align="center">
		<td><%=i %></td>
		<td align="left">
		<!-- lev값이 있으면 그만큼 공백을 붙이고 ㅂ한자6 표시를 앞에 붙여줌 -->
		<% for(int n=0; n<dto.getLev(); n++) { %>
			&nbsp;&nbsp;
		<%	} if(dto.getLev()>0) { %>
			└
		<% } %>
			<a href="BDetail?id=<%=dto.getId() %>&page=<%=pd.page %>"><%=dto.getTitle() %></a>
		</td>
		<td><%=dto.getPname() %></td>
		<td><%=dto.getReg_dateStr() %></td>
		<td><%=dto.getCnt() %></td>
	</tr>
	<% } %>
	<tr>
		<td colspan="5" align="center">
		<!-- 현재 페이지 범위의 시작번호가 1이 아니면 이전글자가 나오게 함 -->
		<% if(pd.pageStart>1) { %>
			<a href="?page=<%=pd.pageStart-1 %>">[이전]</a>
		<!-- 페이지 범위의 시작부터 끝까지 돌면서 해당페이지면 [p] -->
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
		<td colspan="5" align="right"><a href="BWriteForm?page=<%=pd.page %>">글쓰기</a></td>
	</tr>
</table>