<%@page import="model_p.PageData"%>
<%@page import="model_p.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	//같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	BoardDTO dto = (BoardDTO)request.getAttribute("mainData");
%>
<form action="BReplyReg" method="post">
	<input type="hidden" name="page" value="<%=pd.page %>" />
	<input type="hidden" name="gid" value="<%=dto.getGid() %>" />
	<input type="hidden" name="seq" value="<%=dto.getSeq() %>" />
	<input type="hidden" name="lev" value="<%=dto.getLev() %>" />
	<table border="">
		<tr>
			<td width="100px">제목</td>
			<td width="800px"><input type="text" name="title" value="[Re]<%=dto.getTitle() %>"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="pname"></td>
		</tr>
		<tr>
		<!-- 수정 불가 -->
		<!-- 암호로 수정자격 확인 -->
			<td>암호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" id="" 
				cols="30" rows="10">[Re]<%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="답변하기">
				<input type="reset" value="초기화">
				<a href="BDetail?id=<%=dto.getId() %>&page=<%=pd.page%>">뒤로</a>
			</td>
		</tr>
	</table>
</form>
