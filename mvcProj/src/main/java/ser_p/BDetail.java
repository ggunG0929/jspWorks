package ser_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;
import model_p.BoardDAO;

// 보드서비스 인터페이스 상속 - execute 재정의 의무
public class BDetail implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 헤더에 띄울 mainTitle 정보 보내기
		request.setAttribute("mainTitle","게시판 상세");

		// sql문에 넣기 위해 숫자형으로 형변환
		int id = Integer.parseInt(request.getParameter("id"));
		// 글 상세보기 외에 수정시에는 조회수를 늘리지 않으려고 내용을 가져오기 전에 추가하려고 함 그래서 detail클래스가 아니라 dao에서 처리
		new BoardDAO().addCount(id);
		// 클라이언트의 요청결과.발생한 데이터나 결과를 세팅함(메인데이터라는 이름으로, BoardDAO객체생성-detail(id)메서드호출)
		request.setAttribute("mainData", new BoardDAO().detail(id));
	}

}
