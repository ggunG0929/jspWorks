<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	// msg를 alert창으로 띄우고
	alert("<%=request.getAttribute("msg") %>")
	// redirect로 goUrl로 넘어감
	location.href = "<%=request.getAttribute("goUrl") %>"
</script>