package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.Board;
import board.BoardDAO;
import member.Member;
import member.MemberDAO;
import reply.Reply;
import reply.ReplyDAO;

@WebServlet("*.do")		// 경로를 .do로 끝나도록 설정
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 4L;
	
	// MemberDAO 객체 선언
	MemberDAO memberDAO;	// import
	BoardDAO boardDAO;		// import
	ReplyDAO replyDAO;
	
	public void init(ServletConfig config) throws ServletException {
		// 객체 생성
		memberDAO = new MemberDAO();	// 회원 관리 객체 생성
		boardDAO = new BoardDAO();		// 게시글 관리 객체 생성
		replyDAO = new ReplyDAO();		// 댓글 관리 객체 생성
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
		
        if(command.equals("/index.do")) {
        	// 게시글 가져오기
            ArrayList<Board> recentList = boardDAO.getBoardList();
            // 선생님방식(내 방식에서는 boardDAO에서 sql명령어로 처리했기에 생략가능)
//            int size = recentList.size();
//            Board[] recentList = {recentList.get(size-1), recentList.get(size-2), recentList.get(size-3)};
            // 모델 생성
        	request.setAttribute("recentList", recentList);
        	// main 페이지로 포워딩
            nextPage="/main.jsp";
        }else if(command.equals("/memberList.do")) {	// 회원 목록 조회
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
			// 가입 후 자동로그인(세션발급)
			session.setAttribute("sessionId", memberId);
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
			// 페이지 처리
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) {	// pageNum이 없으면 1페이지로
				pageNum = "1";
			}
			// 각 페이지의 첫 행: 1page->1~, 2page->11~, 3page->21~
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int startRow = (currentPage-1)*pageSize + 1;
			
			// 시작 페이지(int형이므로 몫만 나옴 소수점 안나옴)
			int startPage = startRow / pageSize + 1;
			
			// 종료(끝) 페이지
			int total = boardDAO.getBoardCount();	// 총 행수가 나누어 떨어지지 않으면 페이지 수에 1을 더함
			int endPage = (total / pageSize);		// 총행수 / 페이지당 행의 수
			endPage = (total % pageSize == 0) ? endPage : endPage+1;
			
			// 게시글 목록보기 함수 호출
			ArrayList<Board> boardList = boardDAO.getBoardList(startRow, pageSize);
			
			// 모델 생성
			request.setAttribute("boardList", boardList);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			nextPage = "/board/boardList.jsp";
			
		}else if(command.equals("/boardForm.do")) {		// 글 쓰기
			nextPage = "/board/boardForm.jsp";			
		}else if(command.equals("/addBoard.do")) {		// 글 올리기
			
			String realFolder = "C:\\Users\\Administrator\\git\\jspWorks\\Members\\src\\main\\webapp\\upload";	// 깃 위치로 변경함
			MultipartRequest multi = new MultipartRequest(request, realFolder, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			// 글쓰기 폼에 입력된 데이터 받아오기(request는 사용하지 않고 multi를 사용함)
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			
			// memberId 세션을 가져오기
			String memberId = (String)session.getAttribute("sessionId");
			
			// fileName 속성 가져오기
			Enumeration<String> files = multi.getFileNames();
			String name = "";
			String fileName = "";
			if(files.hasMoreElements()) {
				name = (String)files.nextElement();
				fileName = multi.getFilesystemName(name);	// 서버에 저장될 파일이름
			}
			
			Board newBoard = new Board();
			newBoard.setTitle(title);
			newBoard.setContent(content);
			newBoard.setMemberId(memberId);
			newBoard.setFileUpload(fileName);
			
			// 글쓰기 처리 메서드 호출
			boardDAO.addBoard(newBoard);	//db에 저장
//			nextPage = "/boardList.do";		// 새로고침 시에도 이전에 올린 글이 또 올라가는 오류 생김->포워딩에 이프문 추가해서 처리
		
		}else if(command.equals("/boardView.do")) {		// 글 상세보기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			Board board = boardDAO.getBoard(bnum);
			
			ArrayList<Reply> replyList = replyDAO.getReplyList(bnum);	// 댓글 가져오기	// import
			
			// 모델 생성
			request.setAttribute("board", board);
			request.setAttribute("replyList", replyList);
			nextPage = "/board/boardView.jsp";
			
		}else if(command.equals("/deleteBoard.do")) {	// 글 삭제
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			boardDAO.deleteBoard(bnum);
			nextPage = "/boardList.do";		// 삭제 후 게시글 목록으로 이동	// jsp로 하면 글이 없는 목록이 뜸
		}else if(command.equals("/updateBoard.do")) {	// 글 수정하러 가기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			Board board = boardDAO.getBoard(bnum);	// 게시글 상세보기
			request.setAttribute("board", board);
			nextPage = "/board/updateBoard.jsp";
		}else if(command.equals("/updateProcess.do")) {	// 글 수정과정
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
		}else if(command.equals("/addReply.do")) {	// 댓글 등록 과정
			// 댓글 폼에 입력된 데이터 가져옴
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			String rcontent = request.getParameter("rcontent");
			String replyer = request.getParameter("replyer");
			
			Reply newReply = new Reply();
			newReply.setBnum(bnum);
			newReply.setRcontent(rcontent);
			newReply.setReplyer(replyer);
			
			replyDAO.addReply(newReply);	// 댓글 등록 처리
		}  
		
		// 포워딩 - 새로고침 자동 저장 오류 해결
		if(command.equals("/addBoard.do")) {	// 글올리기 요청시
			response.sendRedirect("/boardList.do");
		}else if(command.equals("/addReply.do")) {	// 댓글 등록시
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			response.sendRedirect("/boardView.do?bnum=" + bnum);
		}else{
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	// import	// nextPage로 고쳐주어야 command에 따라 넘어갈 페이지가 바뀜
            dispatcher.forward(request, response);
        }
	}
}
