package ser_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;
import model_p.BoardDAO;
import model_p.BoardDTO;
import model_p.PageData;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class BReplyReg implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			// 게시글 모델 생성
			BoardDTO dto = new BoardDTO();
			
			PageData pd = (PageData)request.getAttribute("pd");
			
			// 글아이디 제목 작성자 비번 내용 파일 필드에서 정보를 가져와서 세팅
			dto.setTitle(request.getParameter("title"));
			dto.setPname(request.getParameter("pname"));
			dto.setPw(request.getParameter("pw"));
			dto.setContent(request.getParameter("content"));
			dto.setGid(Integer.parseInt(request.getParameter("gid")));
			dto.setLev(Integer.parseInt(request.getParameter("lev")));
			dto.setSeq(Integer.parseInt(request.getParameter("seq")));
					
			new BoardDAO().reply(dto);
			
			// 리다이렉트 하기위해 alert로
			request.setAttribute("mainPage", "alert");
			request.setAttribute("msg", "작성되었습니다.");
			request.setAttribute("goUrl", "BDetail?id="+dto.getId()+"&page="+pd.page);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
