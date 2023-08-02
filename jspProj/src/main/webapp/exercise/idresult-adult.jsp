<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
</head>
<body>
<%
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	int nation = Integer.parseInt(request.getParameter("nation"));
	
	// 정보 전달을 위해 인코딩
	String ename = URLEncoder.encode(name, "UTF-8");
	String ebirth = URLEncoder.encode(birth, "UTF-8");

	LocalDate now = LocalDate.now();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMdd");
	String today = now.format(format);
	//System.out.println(today);
	
	int ibirth = Integer.parseInt(birth);
	int itoday = Integer.parseInt(today);
	
	// yyMMdd
	// 012345
	String y = birth.substring(0, 2);
	String m = birth.substring(2, 4);
	String d = birth.substring(4, 6);

	// java.lang.IllegalArgumentException: 유니코드 문자는 허용 범위 바깥에 있으므로 인코딩될 수 없습니다.
	if(nation >=5 && nation < 9) {
		response.sendRedirect("idresult-notkor.jsp?name="+ename+"&birth="+ebirth);	// HTTP 응답 헤더 [Location](이)가 유효하지 않은 값이므로 응답에서 제거되었습니다.
	}else{
		if((nation==3 || nation==4) && itoday-190000<ibirth) {
			response.sendRedirect("idresult-nota.jsp?name="+ename+"&birth="+ebirth);
		}
	}
%>
<h1>일반회원</h1>
<form action="?">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" value="<%=name %>" /></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				 <input type="text" value="<%=y %>" />년
				 <input type="text" value="<%=m %>" />월
				 <input type="text" value="<%=d %>" />일
			 </td>
		</tr>
	</table>
</form>
</body>
</html>


 
<%-- 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinReg.jsp</title>
</head>
<body>
<h1>joinReg.jsp</h1>
<%
	String jumin = request.getParameter("jumin");
	String pname = request.getParameter("pname");
	
	// charAt(7) : 7번 인덱스자리의 문자열을 추출(현재는 주민등록번호 7번째, 생년월일 - 다음의 한자리 숫자)
	// charAt으로 추출시 char형 - 0의 char형이 48이므로('0'=48) 이만큼을 빼주어야 연산 가능
	int sv = (jumin.charAt(7)-'0')-1;

	// 1,2 / 3,4 / 5,6 / 7,8 을 묶어주기 위해 -1을 하고(sv) 2로 나눠줌 -> sv/2 = 2, 3은 외국인
	/*
	*100년출생	-1	/2
	19		1	0	0
	19		2	1	0
	20		3	2	1
	20		4	3	1
	19		5	4	2
	19		6	5	2
	20		7	6	3
	20		8	7	3

	// 1,2 / 3,4 / 5,6 / 7,8 을 묶어주기 위해 -1을 하고(sv)
	// 1,5 / 2,6 / 3,7 / 4,8 을 묶어주기 위해 %4
	// 1,2 / 3,4 을 묶어주기 위해 /2 -> (sv%4)/2 = 0은 1900년대 출생, 1은 2000년대 출생
	*100년출생	-1	%4	/2	+19
	19		1	0	0	0	19
	19		2	1	1	0	19
	20		3	2	2	1	20
	20		4	3	3	1	20
	----------------------------------------
	19		5	4	0
	19		6	5	1
	20		7	6	2
	20		8	7	3
	*/
	
	// 출생년도
	// 주민등록번호 앞자리 앞에 붙일 출생년도
	int pre = sv %4	/2	+19;
	// birth[0]: 년, birth[1]: 월, birth[2]: 일로 3개
	String [] birth = new String[3];
	for(int i = 0; i<birth.length;i++){
		birth[i] = jumin.substring(i*2,i*2+2);	// 0,2 2,4 4,6
	}
	// 출생년도 4자리
	birth[0]=pre+birth[0];
	
	// 성인, 미성년자 연산
	// today = date형 오늘
	Date today = new Date();
	// today = date형 19년전 오늘
	today.setYear(today.getYear()-19);
	// birth 배열을 - 로 묶어줌: yyyy-MM-dd
	String birthStr = String.join("-", birth);
	// 19년전오늘.이전인지(birth를 date로 형변환) - true: 성인(sv=2: 주민번호 뒷첫자리 3) false: 미성년자(sv=0: 주민번호 뒷첫자리 1)
	if( today.before(new SimpleDateFormat("yyyy-MM-dd").parse(birthStr))){
		out.println("생일안지남<br/>");
		sv = 2;
	}else{
		sv = 0;
	}
	
	// 성인, 미성년자, 외국인 연산
	// arr = [adult, child, foreign, foreign]
	String [] arr = "adult,child,foreign,foreign".split(",");
	// goUrl = 0: 성인 1: 미성년자
	String goUrl = arr[sv/2];
	
	// pname을 url 주소로 전달하기 위해 인코딩
	String ppname = URLEncoder.encode(pname,"UTF-8");
	//response.sendRedirect(goUrl+".jsp?ppname="+ppname+"&year="+1111);
%>
</body>
</html> --%>

<%-- 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinReg.jsp</title>
</head>
<body>
<h1>joinReg.jsp</h1>
<%
	String jumin = request.getParameter("jumin");
	String pname = request.getParameter("pname");
	
	int sv = jumin.charAt(7)-'0'-1;
	
	
	
	
	
	
	/*
			-1	/2
	19	1	0	0
	19	2	1	0
	20	3	2	1
	20	4	3	1
	19	5	4	2
	19	6	5	2
	20	7	6	3
	20	8	7	3
	
	
			-1	%4	/2	+19
	19	1	0	0	0	19
	19	2	1	1	0	19
	20	3	2	2	1	20
	20	4	3	3	1	20
	----------------------------------------
	19	5	4	0
	19	6	5	1
	20	7	6	2
	20	8	7	3
	
	
	*/
	
	int pre = sv %4	/2	+19;
	String [] birth = new String[3];
	for(int i = 0; i<birth.length;i++){
		birth[i] = jumin.substring(i*2,i*2+2);
	}
	
	out.println(sv*4+"<br/>");
	out.println(pre+"<br/>");
	birth[0]=pre+birth[0];
	
	Date today = new Date();
	today.setYear(today.getYear()-19);
	
	String birthStr = String.join("-", birth);
	
	
	if( today.before(new SimpleDateFormat("yyyy-MM-dd").parse(birthStr))){
		out.println("생일안지남<br/>");
		sv = 2;
	}else{
		sv = 0;
	}
	out.println(today+"<br/>");
	
	out.println(birthStr);
	out.println(pname+"<br/>");
	
	
	String [] arr = "adult,child,foreign,foreign".split(",");
	String goUrl = arr[sv/2];
	out.println(goUrl);
	String ppname = URLEncoder.encode(pname,"UTF-8");
	//response.sendRedirect(goUrl+".jsp?ppname="+ppname+"&year="+1111);
%>
</body>
</html>
 --%>
 
 <%--
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinForm</title>
</head>
<body>
<h1>joinForm</h1>
<form action="joinReg.jsp">
	<table border="">
		<tr>
			<td>주민번호</td>
			<td><input type="text" name="jumin"  value="040802-1234567" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="pname" value="정우성" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="가입" /></td>
		</tr>
	</table>
</form>
</body>
</html>
  --%>
  
<%--
  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성인의 세계</title>
</head>
<body>
<h1>후끈 달아오르는 성인의 세계</h1>
<h2>성인 1 : 공자</h2>
<h2>성인 2 : 석가</h2>
<h2>성인 3 : 맹자</h2>
<form action="?">
	<table border="">
		<tr>
			<td>생년월일</td>
			<td>
			<input type="text" name="year"  value="<%=request.getParameter("year") %>" />-
			<input type="text" name="month"  value="" />-
			<input type="text" name="day"  value="" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="pname" value="<%=request.getParameter("ppname") %>" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="가입" /></td>
		</tr>
	</table>
</form>
</body>
</html>
--%>

