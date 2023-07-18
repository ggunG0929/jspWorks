<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="boardlist">
			<h2>게시판 목록</h2>
			<!-- 검색 폼 -->
			<form action="" method="get" class="search_form">
				<!-- 임의로 추가 -->
				<c:if test="${kw != ''}">
					검색결과: ${boardList.size() }건
				</c:if>
				<select name="field" class="sel_field">
					<option ${(field == "title")? "selected":""} value="title">제목</option>
					<option ${(field == "memberId")? "selected":""} value="memberId">작성자</option>
				</select>
				<input type="text" name="kw" class="in_kw" value="${kw }">
				<button type="submit">검색</button>
			</form>
			<table id="brd_table">
				<thead>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>글쓴이</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${boardList }">
					<tr>
						<td><c:out value="${board.bnum }"></c:out></td>
						<td><a href="/boardView.do?bnum=${board.bnum }"><c:out value="${board.title }"/></a></td>
						<td><fmt:formatDate value="${board.regDate }" pattern = "yyyy-MM-dd HH:mm:ss"/></td>
						<td><c:out value="${board.hit }"></c:out></td>
						<td><c:out value="${board.memberId }"></c:out></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 페이지 처리 영역 -->
			<div class="pagination">
				<!-- 이전 페이지 -->
				<c:if test="${startPage > 1}">
					<a href="/boardList.do?pageNum=${startPage-1}">이전</a><!-- 오류해결: boardlist -> boardList -->
				</c:if>
				<c:if test="${startPage == 1}">
					<a href="">이전</a>
				</c:if>
				<!-- 페이지 리스트 -->
				<c:forEach var="i" begin="1" end="${endPage}">
					<c:if test="${currentPage == i}">
						<a href="/boardList.do?pageNum=${i}">
							<span class="page">${i}</span>
						</a>
					</c:if>
					<c:if test="${currentPage != i}">
						<a href="/boardList.do?pageNum=${i}">${i}</a>
					</c:if>
				</c:forEach>
				<!-- 다음 페이지 -->
				<c:if test="${endPage > startPage}">
					<a href="/boardList.do?pageNum=${startPage+1}">다음</a>
				</c:if>
				<c:if test="${endPage == startPage}">
					<a href="">다음</a>
				</c:if>
			</div>
			<!-- 글쓰기 버튼 -->
			<div class="btnWrite">
				<a href="/boardForm.do">
					<button type="button">글쓰기</button>
				</a>
			</div>
		</section>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>