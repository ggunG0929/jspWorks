package ser_p;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;
import model_p.BoardDAO;
import model_p.BoardDTO;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class BDeleteReg implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 게시글 모델 생성
		BoardDTO dto = new BoardDTO();
		// id와 pw 세팅
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setPw(request.getParameter("pw"));
		
		
		// id, pw 검사 및 파일정보 가져오기
		// id, pw 검사 실패값으로 초기화
		String msg = "비밀번호가 일치하지 않습니다.";
		String goUrl = "BDeleteForm?id="+dto.getId();
		
		// 게시글 모델 dto에 idPwChk메서드를 호출해서 delDto로 만듦
		BoardDTO delDto = new BoardDAO().idPwChk(dto);
		// 실제 path를 부여
		String path = "C:\\Users\\Administrator\\git\\jspWorks\\mvcProj\\src\\main\\webapp\\up";
		if(delDto!=null) {	// id, pw가 일치한다면(검색한 결과가 있다면)
			if(!delDto.getUpfile().equals("")) {	// 파일이 존재한다면
				// path에서 파일을 삭제
				new File(path+"\\"+delDto.getUpfile()).delete();
			}
			// 일치하는 결과를 db에서 삭제
			new BoardDAO().delete(dto);
			// id, pw 검사 성공값 설정
			msg = "삭제되었습니다.";
			goUrl = "BList";
		}
		
		// 리다이렉트 하기위해 alert로
		request.setAttribute("mainPage", "alert");
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
	}
}
