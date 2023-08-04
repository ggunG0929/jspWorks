package ser_p;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import control.BoardService;
import model_p.BoardDAO;
import model_p.BoardDTO;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class BModifyReg implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 실제 path를 부여
		String path = "C:\\Users\\Administrator\\git\\jspWorks\\mvcProj\\src\\main\\webapp\\up";
		
		try {
			MultipartRequest mr = new MultipartRequest(
						request, 
						path,
						10*1024*1024,
						"utf-8",
						// 같은 이름의 파일이 있다면 뒤에 숫자를 붙여서 저장
						new DefaultFileRenamePolicy()
					);
			
			BoardDTO dto = new BoardDTO();
			dto.setId(Integer.parseInt(mr.getParameter("id")));
			dto.setTitle(mr.getParameter("title"));
			dto.setPname(mr.getParameter("pname"));
			dto.setPw(mr.getParameter("pw"));
			dto.setContent(mr.getParameter("content"));
			// 시스템에 저장된 파일이름을 가져옴
			dto.setUpfile(mr.getFilesystemName("upfile"));
			
			String msg = "비밀번호가 일치하지 않습니다.";
			String goUrl = "BModifyForm?id="+dto.getId();
			
			if(new BoardDAO().modify(dto)>0) {	// id, pw가 일치한다면
				msg = "수정되었습니다.";
				goUrl = "BDetail?id="+dto.getId();
			}else{	// 이미 인스턴스를 만들면서 이미지가 업로드 되기 때문에 id pw가 불일치하면 지워줘야 함
//				if(!dto.getUpfile().equals("")) {
				if(mr.getFilesystemName("upfile")!=null) {
					new File(path+"//"+mr.getFilesystemName("upfile")).delete();
				}
			}
			
			// 리다이렉트
			request.setAttribute("mainPage", "alert");
			request.setAttribute("msg", msg);
			request.setAttribute("goUrl", goUrl);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
