<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	String id = request.getParameter("id");
%>
<form action="BDeleteReg" method="post">
<!-- 아이디 정보를 숨겨서 보냄 -->
<input type="hidden" name="id" value="<%=id%>" />
	<table border="">
		<tr>
			<td width="200px">암호</td>
			<td width="700px"><input type="text" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="삭제" /><a href="BDetail?id=<%=id %>">뒤로</a>
			</td>
		</tr>
	</table>
</form>