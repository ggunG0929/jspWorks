package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.Member;
import member.MemberDAO;

@WebServlet("*.do")		// 경로를 .do로 끝나도록 설정
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 4L;
	
	// MemberDAO 객체 선언
	MemberDAO memberDAO;	// import

	public void init(ServletConfig config) throws ServletException {
		// 객체 생성
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// command 패턴으로 url 설정하기
		String uri = request.getRequestURI();
		System.out.println(uri);
		String command = uri.substring(uri.lastIndexOf('/'));
		System.out.println(uri.lastIndexOf('/'));
		System.out.println("command: " + command);
		
		String nextPage = null;
	
		// 회원 목록 조회
		if(command.equals("/memberList.do")) {
			ArrayList<Member> memberList = memberDAO.getMemberList();	// import arraylist		// import member
			// 모델 생성 및 보내기
			request.setAttribute("memberList", memberList);
			nextPage = "memberList.jsp";
			
		}else if(command.equals("/memberForm.do")) {
			nextPage = "memberForm.jsp";
		
		}else if(command.equals("/addMember.do")) {
			// 폼에 입력된 데이터 받기
			String memberId = request.getParameter("memberId");
			String passwd = request.getParameter("passwd1");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			Member newMember = new Member();	// 회원 객체 생성
			newMember.setMemberId(memberId);
			newMember.setPasswd(passwd);
			newMember.setName(name);
			newMember.setGender(gender);
			
			memberDAO.addMember(newMember); 	// 회원 매개로 db에 저장
			nextPage = "index.jsp";
		}
		
		// 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	// import	// nextPage로 고쳐주어야 command에 따라 넘어갈 페이지가 바뀜
		dispatcher.forward(request, response);
		
	}
}
