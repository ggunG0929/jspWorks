package ser2_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import control.BoardService;
import model_p.GalleryDAO;
import model_p.GalleryDTO;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class GWriteReg implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 실제 path를 부여
		// 학원
//		String path = "C:\\Users\\Administrator\\git\\jspWorks\\mvcProj\\src\\main\\webapp\\up";
		// 놋북
		String path = "C:\\Users\\Administrator\\git\\jspWorks\\mvcProj\\src\\main\\webapp\\up";
		
		try {
			// MultipartRequest: cos.jar를 통해 쓸 수 있는 클래스
			// 클라이언트에서 전송된 멀티파트요청(파일 업로드 또는 폼 데이터에 파일과 일반 텍스트 데이터가 혼합된 요청)을 처리
			MultipartRequest mr = new MultipartRequest(
						request, 
						path,
						10*1024*1024,
						"utf-8",
						// 같은 이름의 파일이 있다면 뒤에 숫자를 붙여서 저장
						new DefaultFileRenamePolicy()
					);
			
			// 게시글 모델 생성
			GalleryDTO dto = new GalleryDTO();
			// 제목 작성자 비번 내용 파일 필드에서 정보를 가져와서 세팅
			dto.setTitle(mr.getParameter("title"));
			dto.setPname(mr.getParameter("pname"));
			dto.setPw(mr.getParameter("pw"));
			dto.setDescription(mr.getParameter("description"));
			// 시스템에 저장된 파일이름을 가져옴
			dto.setUpfile(mr.getFilesystemName("upfile"));
			
			// 게시글 모델을 boardDAO의 write 메서드로
			new GalleryDAO().write(dto);
			
			// 리다이렉트 하기위해 alert로
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg", "작성되었습니다.");
			request.setAttribute("goUrl", "GDetail?id="+dto.getId());
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
