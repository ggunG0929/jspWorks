<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
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
	// 이름
	String name = request.getParameter("name");
	// 주민번호 앞6자리
	String birth = request.getParameter("birth");
	// 주민번호 뒤1자리
	int nation = Integer.parseInt(request.getParameter("nation"));
	
	// birth의 yy를 yyyy로
	if((nation-1)%4/2 == 0) {
		birth = "19" + birth;
	}else {
		birth = "20" + birth;
	}

	// 성별
	String gender;
	if(nation%2==0) {
		gender = "여";
	}else {
		gender = "남";
	}

	// 오늘
	LocalDate now = LocalDate.now();
	// 포맷팅
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	// 포맷팅 형식인 오늘
	String today = now.format(formatter);
	//System.out.println(today);
	
	// 연산을 위해 인수형으로
	int itoday = Integer.parseInt(today);
	int ibirth = Integer.parseInt(birth);
	
	// HTTP 응답 헤더 [Location](이)가 유효하지 않은 값이므로 응답에서 제거되었습니다.
	// java.lang.IllegalArgumentException: 유니코드 문자는 허용 범위 바깥에 있으므로 인코딩될 수 없습니다.
	// 정보 전달을 위해 인코딩
	// 배열은 인코딩이 안됨
	String ename = URLEncoder.encode(name, "UTF-8");
	String ebirth = URLEncoder.encode(birth, "UTF-8");
	String egender = URLEncoder.encode(gender, "UTF-8");
	
	// 외국인인 경우
//	if(nation >=5 && nation < 9) {
	if((nation-1)/4 ==1) {
		response.sendRedirect("idres-f.jsp?ename="+ename+"&ebirth="+ebirth+"&egender="+egender);
	// 미성년자인 경우
	}else{
//		if((nation==3 || nation==4) && itoday-190000<ibirth) {
//		if(((nation-1)/2 == 1) && itoday-190000<ibirth) {
		if(itoday-190000<ibirth) {	// yyyy형태로 만들었기 때문에 1900년대와 2000년대로 연산가능
			response.sendRedirect("idres-k.jsp?ename="+ename+"&ebirth="+ebirth+"&egender="+egender);
		}
	}
%>
<h1>일반 회원가입폼</h1>
<form action="?">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" placeholder="입력해주세요" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" placeholder="입력해주세요" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" value="<%=name %>" /></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				 <input type="text" value="<%=birth.substring(0, 4) %>" style="width: 40px" />년
				 <input type="text" value="<%=birth.substring(4, 6) %>" style="width: 20px" />월
				 <input type="text" value="<%=birth.substring(6, 8) %>" style="width: 20px" />일
			 </td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				 <input type="radio"<% String fchk = gender.equals("여") ? "checked" : "disabled"; %><%=fchk %>/>여
				 <input type="radio"<% String mchk = gender.equals("남") ? "checked" : "disabled"; %><%=mchk %>/>남
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="등록"></td>
		</tr>
	</table>
</form>
</body>
</html>


 
<%-- 
<%
	// 선생님 방식
	
	// 주민등록번호 전체를 입력받아서 가지고 옴
	String jumin = request.getParameter("jumin");
	String pname = request.getParameter("pname");
	
	// charAt(7) : 7번 인덱스자리의 문자열을 추출(현재는 주민등록번호 7번째, 생년월일 - 다음의 한자리 숫자)
	// charAt으로 추출시 char형 - 0의 char형이 48이므로('0'=48) 이만큼을 빼주어야 숫자 그대로로 연산 가능
	int sv = (jumin.charAt(7)-'0')-1;
	
	// 1,2 / 3,4 / 5,6 / 7,8 을 묶어주기 위해 -1을 하고(sv) 2로 나눠줌 -> sv/2 = 0,1,(4)는 내국인, 2,3은 외국인
	/*
	*100년출생		-1	/2
	19	남	1	0	0
	19	여	2	1	0
	20	남	3	2	1
	20	여	4	3	1
	19	남	5	4	2
	19	여	6	5	2
	20	남	7	6	3
	20	여	8	7	3
	18	남	9	8	4
	18	여	0	9	4

	// 1,2 / 3,4 / 5,6 / 7,8 을 묶어주기 위해 -1을 하고(sv)
	// 1,5 / 2,6 / 3,7 / 4,8 을 묶어주기 위해 %4
	// 1,2 / 3,4 을 묶어주기 위해 /2 -> (sv%4)/2 = 0은 1900년대 출생, 1은 2000년대 출생
	*100년출생		-1	%4	/2	+19
	19	남	1	0	0	0	19
	19	여	2	1	1	0	19
	20	남	3	2	2	1	20
	20	여	4	3	3	1	20
	----------------------------------------
	19	남	5	4	0
	19	여	6	5	1
	20	남	7	6	2
	20	여	8	7	3
	----------------------------------------
	18	남	9	8	0	생존자가 없어 논외
	18	여	0	9	1	생존자가 없어 논외
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
	// 19년전오늘.이전인지(생일보다도birth를 date로 형변환) - true: 미성년자(sv=2: 주민번호 뒷첫자리 3) false: 성인(sv=0: 주민번호 뒷첫자리 1)
	if(today.before(new SimpleDateFormat("yyyy-MM-dd").parse(birthStr))){
		sv = 2;
	}else{
		sv = 0;
	}
	
	// 오류: 외국인의 경우가 없어지고 성인과 미성년자로만 나옴
	// 성인, 미성년자, 외국인 연산
	// arr = [adult, child, foreign, foreign]
	String [] arr = "adult,child,foreign,foreign".split(",");
	// goUrl = 0: 성인(sv=0,1) 1: 미성년자(sv=2,3) 2,3: 외국인(sv=4,5,6,7)
	String goUrl = arr[sv/2];

	// pname을 url 주소로 전달하기 위해 인코딩
	String ppname = URLEncoder.encode(pname,"UTF-8");
	//response.sendRedirect(goUrl+".jsp?ppname="+ppname+"&year="+1111);
	
	
	
	
	
%>
--%>
  
<%--
<form action="?">
	<table border="">
		<tr>
			<td>생년월일</td>
			<td>
			<input type="text" name="year"  value="<%=request.getParameter("year") %>" />-
			<input type="text" name="month"  value="<%=request.getParameter("month") %>" />-
			<input type="text" name="day"  value="<%=request.getParameter("day") %>" /></td>
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
--%>

