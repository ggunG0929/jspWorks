package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.Board;
import board.BoardDAO;
import member.Member;
import member.MemberDAO;

@WebServlet("*.do")		// 경로를 .do로 끝나도록 설정
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 4L;
	
	// MemberDAO 객체 선언
	MemberDAO memberDAO;	// import
	BoardDAO boardDAO;		// import
	
	public void init(ServletConfig config) throws ServletException {
		// 객체 생성
		memberDAO = new MemberDAO();
		boardDAO = new BoardDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// command 패턴으로 url 설정하기
		String uri = request.getRequestURI();
		System.out.println(uri);
		String command = uri.substring(uri.lastIndexOf('/'));
		System.out.println(uri.lastIndexOf('/'));
		System.out.println("command: " + command);
		
		String nextPage = null;
		
		// 출력스트림 객체
		PrintWriter out = response.getWriter();		// import java.io
		
		// 세션 객체 생성
		HttpSession session = request.getSession();		// import javax.servlet
	
		if(command.equals("/memberList.do")) {			// 회원 목록 조회
			ArrayList<Member> memberList = memberDAO.getMemberList();	// import arraylist		// import member
			// 모델 생성 및 보내기
			request.setAttribute("memberList", memberList);
			nextPage = "/member/memberList.jsp";
			
		}else if(command.equals("/memberForm.do")) {	// 회원 가입
			nextPage = "/member/memberForm.jsp";
		
		}else if(command.equals("/addMember.do")) {		// 회원 등록
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
			nextPage = "/index.jsp";	// 회원 등록 후 메인으로
			
		}else if(command.equals("/memberView.do")) {	// 회원 정보 요청
			// memberId 받기
			String memberId = request.getParameter("memberId");
			Member member = memberDAO.getMember(memberId);
			
			request.setAttribute("member",  member);	// member 모델 생성
			
			nextPage = "/member/memberView.jsp";
			
		}else if(command.equals("/loginForm.do")) {		// 로그인페이지요청
			nextPage = "/member/loginForm.jsp";

		}else if(command.equals("/loginProcess.do")) {	// 로그인처리
			String memberId = request.getParameter("memberId");
			String passwd = request.getParameter("passwd");

			Member memberlogin = new Member();
			memberlogin.setMemberId(memberId);
			memberlogin.setPasswd(passwd);

			// 로그인 체크 처리
			boolean result = memberDAO.checkLogin(memberlogin);
			if(result) {
				// 세션발급
				session.setAttribute("sessionId", memberId);
				nextPage = "index.jsp";		// 로그인 완료 후 메인으로
			}else{
				// 2가지 방법: alert(), errorMsg
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 확인해주세요')");
				out.print("history.go(-1)");	// 이전 페이지로 이동
				out.println("</script>");
			}
		}else if(command.equals("/logout.do")) {		// 로그아웃 요청
			// 세션 모두 삭제
			session.invalidate();
			nextPage="/index.jsp";
		}else if(command.equals("/deleteMember.do")) {	// 회원 삭제 요청
			String memberId = request.getParameter("memberId");
			memberDAO.deleteMember(memberId);			// 회원 삭제 처리
			nextPage="/memberList.do";
		}
		
		// 게시판 관리
		if(command.equals("/boardList.do")) {			// 게시글 목록
			ArrayList<Board> boardList = boardDAO.getBoardList();
			// 모델 생성
			request.setAttribute("boardList", boardList);
			nextPage = "/board/boardList.jsp";
		}else if(command.equals("/boardForm.do")) {		// 글 쓰기
			nextPage = "/board/boardForm.jsp";			
		}else if(command.equals("/addBoard.do")) {		// 글 올리기
			// 글쓰기 폼에 입력된 데이터 받아오기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// memberId 세션을 가져오기
			String memberId = (String)session.getAttribute("sessionId");
			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			board.setMemberId(memberId);
			
			// 글쓰기 처리 메서드 호출
			boardDAO.addBoard(board);
//			nextPage = "/boardList.do";		// 새로고침 시에도 이전에 올린 글이 또 올라가는 오류 생김->포워딩에 이프문 추가해서 처리
		
		}else if(command.equals("/boardView.do")) {		// 글 상세보기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			Board board = boardDAO.getBoard(bnum);
			// 모델 생성
			request.setAttribute("board", board);
			nextPage = "/board/boardView.jsp";
			
		}else if(command.equals("/deleteBoard.do")) {	// 글 삭제
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			boardDAO.deleteBoard(bnum);
			nextPage = "/boardList.do";		// 삭제 후 게시글 목록으로 이동	// jsp로 하면 글이 없는 목록이 뜸
		}else if(command.equals("/updateBoard.do")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			Board board = boardDAO.getBoard(bnum);	// 게시글 상세보기
			request.setAttribute("board", board);
			nextPage = "/board/updateBoard.jsp";
		}else if(command.equals("/updateProcess.do")) {
			// 수정 폼에서 입력 내용 받기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			Board updateBoard = new Board();
			updateBoard.setTitle(title);
			updateBoard.setContent(content);
			updateBoard.setBnum(bnum);
			
			boardDAO.updateBoard(updateBoard);	// 수정 처리
			nextPage = "/boardList.do";
		} else if(command.equals("/memberEvent.do")) { 
			nextPage = "/member/memberEvent.jsp";
		}
		
		// 포워딩 - 새로고침 자동 저장 오류 해결
		if(command.equals("/addBoard.do")) {	// 글올리기 요청시
			response.sendRedirect("/boardList.do");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	// import	// nextPage로 고쳐주어야 command에 따라 넘어갈 페이지가 바뀜
			dispatcher.forward(request, response);
		}
	}
}
