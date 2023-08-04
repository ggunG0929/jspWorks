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
		// 실제 path를 부여
		String path = "C:\\Users\\Administrator\\git\\jspWorks\\mvcProj\\src\\main\\webapp\\up";
		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setPw(request.getParameter("pw"));
		
		String msg = "비밀번호가 일치하지 않습니다.";
		String goUrl = "BDeleteForm?id="+dto.getId();
		
		// id/pw 검사 및 파일정보 가져오기
		BoardDTO delDto = new BoardDAO().idPwChk(dto);
		
		if(delDto!=null) {	// id, pw가 일치한다면(id pw로 검색한 결과가 있다면)
			if(!delDto.getUpfile().equals("")) {	// 파일이 존재한다면
				// 나는 왜 파일 삭제가 안되지?? - dao에서 res로 받아야 할 것을 dto에서 수정을 안함
				new File(path+"\\"+delDto.getUpfile()).delete();
			}
			new BoardDAO().delete(dto);
			msg = "삭제되었습니다.";
			goUrl = "BList";
		}
		
		// 리다이렉트
		request.setAttribute("mainPage", "alert");
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
	}
}
