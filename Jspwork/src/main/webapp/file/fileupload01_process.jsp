<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 한글 인코딩
	request.setCharacterEncoding("utf-8");

	// 업로드된 파일의 폴더 경로('\\' or '/' 가능)
	String realFolder = "C:/Users/Administrator/git/jspWorks/Jspwork/src/main/webapp/upload";	// 폴더위치 복붙하고 역슬래시를 슬래시로 바꿔줌
	
	// MultipartRequest 객체 생성(5*1024*1024: 5메가)
	MultipartRequest multi = new MultipartRequest(request, realFolder, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());

	// 요청 파라미터 name 속성 처리(Enumeration 클래스 사용)
	Enumeration<String> params = multi.getParameterNames();		// enumeration import java.util
	while(params.hasMoreElements()) {
		String name = params.nextElement();			// 다음 name 속성이 있으면 가져와서 할당
		String value = multi.getParameter(name);	// name 속성을 매개로 값을 할당
		out.println(name + ": " + value + "<br>");
	}
	
	// 요청 파라미터 filename 속성 처리(Enumeration 클래스 사용)
	Enumeration<String> files = multi.getFileNames();
	while(files.hasMoreElements()) {
		String name = files.nextElement();
		String fileName = multi.getFilesystemName(name);		// 서버에 업로드된 파일 이름
		String originFile = multi.getOriginalFileName(name);	// 원본 파일 이름
		
		out.println("요청 파라미터 이름: " + name + "<br>");
		out.println("저장(업로드) 파일 이름: " + fileName + "<br>");		// 중복시 자동으로 번호를 붙여 저장함
		out.println("실제 원본 이름: " + originFile + "<br>");
%>
<p>이미지 보기</p>
<img src="../upload/<%=fileName %>">
<%	} %>