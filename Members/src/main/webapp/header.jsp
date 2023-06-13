<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<header>
	<div id="logo">
		<h1>
			<a href="index.jsp">Gangnam</a>
		</h1>
	</div>
	<nav>
<!-- 		<ul id="topMenu">
			<li><a href="">로그인</a></li>
			<li><a href="/memberForm.do">회원가입</a></li>
			<li><a href="">게시판</a></li>
			<li><a href="/memberList.do">회원목록</a></li>
		</ul> -->
		<ul id="topMenu">
			<c:choose>
				<c:when test="${empty sessionId }">
					<li><a href="/loginForm.do">로그인</a></li>
					<li><a href="/memberForm.do">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/logout.do"><c:out value="(${sessionId })"/>님 로그아웃</a></li>
					<li><a href="/memberView.do?memberId=${sessionId }">내정보</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="/boardList.do">게시판</a></li>
			<li><a href="/memberList.do">회원목록</a></li>
		</ul>
	</nav>
</header>