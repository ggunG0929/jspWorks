<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<%
	/* pageContext - 동일 페이지에서 저장 기능 */
	pageContext.setAttribute("LF", "\n");	// 엔터(\n)를 치면 아스키코드 LF(line feed)
	pageContext.setAttribute("BR", "<br>");	// 줄바꿈(<br>)을 치면 아스키코드 BR
%>
<body>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="board_detail">
			<h2>게시글 상세 보기</h2>
			<table>
				<tr>
					<td><input type="text" name="title" value="${board.title }" readonly="readonly"></td>
				</tr>
				<tr>
					<td><textarea rows="8" cols="100" readonly="readonly">${board.content }</textarea></td>
				</tr>
				<tr>
					<td>
						<c:out value="글쓴이: ${board.memberId }"/>
						<c:choose>
							<c:when test="${not empty board.modifyDate }">
								(수정일: <fmt:formatDate value="${board.modifyDate }" pattern = "yyyy-MM-dd HH:mm:ss"/>)
							</c:when>
							<c:otherwise>
								(작성일: <fmt:formatDate value="${board.regDate }" pattern = "yyyy-MM-dd HH:mm:ss"/>)
							</c:otherwise>
						</c:choose>
						<c:out value="조회: ${board.hit }"/>
					</td>
				</tr>
				<tr>
					<td>
						<c:if test="${board.memberId == sessionId }">			   
							<a href="/deleteBoard.do?bnum=${board.bnum }" onclick="return confirm('정말로 삭제하시겠습니까?')"><button type="button">삭제</button></a>
							<a href="/updateBoard.do?bnum=${board.bnum }"><button type="button">수정</button></a>
						</c:if>
						<a href="/boardList.do"><button type="button">목록</button></a>
					</td>
				</tr>
			</table>
			<!-- 댓글 영역 -->
			<h3><i class="fa-solid fa-pen-to-square"></i> 댓글 ${replyList.size() }</h3>
			<c:forEach items="${replyList }" var="reply">
			<div class=reply>
				<!-- 댓글 내용 줄바꿈 -->
				<p><c:out value="${fn:replace(reply.rcontent, LF, BR) }" escapeXml="false" /></p>
				<p>
					작성자: ${reply.replyer } 
					<c:choose>
						<c:when test="${not empty reply.rupdate }">
							(수정일: <fmt:formatDate value="${reply.rupdate }" pattern = "yyyy-MM-dd HH:mm:ss"/>)
						</c:when>
						<c:otherwise>
							(작성일: <fmt:formatDate value="${reply.rdate }" pattern = "yyyy-MM-dd HH:mm:ss"/>)
						</c:otherwise>
					</c:choose>
					<c:if test="${reply.replyer == sessionId }">			   
						<a href="/deleteReply.do?bnum=${board.bnum }&rno=${reply.rno }" onclick="return confirm('정말로 삭제하시겠습니까?')"><button type="button">삭제</button></a>
						<a href="/replyUpdateForm.do?bnum=${board.bnum }&rno=${reply.rno }"><button type="button">수정</button></a>
					</c:if>
				</p>
			</div>
			</c:forEach>
			<!-- 댓글 등록 -->
			<c:if test = "${not empty sessionId }">
				<form action="/addReply.do" method="post" id="replyForm">
					<p>${sessionId }</p>
					<p><input type="hidden" name="bnum" value="${board.bnum }"></p>
					<p><input type="hidden" name="replyer" value="${sessionId }"></p>
					<p>
						<textarea name="rcontent" rows="3" cols="76" placeholder="댓글을 남겨주세요" required></textarea>
					</p>
					<button type="submit">등록</button>
				</form>
			</c:if>
			<c:if test="${empty sessionId }">
				<div class="replyLogin">
					<a href="/loginForm.do"><i class="fa-solid fa-user"></i> 로그인한 사용자만 댓글 등록이 가능합니다.</a>
				</div>
			</c:if>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>