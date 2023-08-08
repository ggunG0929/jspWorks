<%@page import="model_p.PageData2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData2 pd = (PageData2)request.getAttribute("pd");
%>
<form action="GWriteReg" method="post" enctype="multipart/form-data">
	<table border="">
		<tr>
			<td width="100px">제목</td>
			<td width="800px"><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="pname"></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td>파일</td>
			<td><input type="file" name="upfile"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="description" id="" cols="30" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글쓰기">
				<input type="reset" value="초기화">
				<a href="GList?page=<%=pd.page %>">목록으로</a>
			</td>
		</tr>
	</table>
</form>
