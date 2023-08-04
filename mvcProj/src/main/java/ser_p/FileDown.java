package ser_p;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class FileDown implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String fName = request.getParameter("fName");
//		System.out.println("FileDown.execute() 실행 "+fName);
		String path = request.getRealPath("up");
		// 가상화된 주소가 떠버림 C:\\green_project\\jspWorks\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvcProj\\up
//		System.out.println(path);
		// 실제 path를 부여
		path = "C:\\Users\\Administrator\\git\\jspWorks\\mvcProj\\src\\main\\webapp\\up";
//		System.out.println(path);
		
		try {
			// input으로 읽고
			FileInputStream fis = new FileInputStream(path+"\\"+fName);
			String encFName = URLEncoder.encode(fName,"utf-8");
	//		System.out.println(fName+"->"+encFName);
			
			// 파일저장이 뜨지만 파일에 내용이 없어 용량이 0kb
			response.setHeader("Content-Disposition", "attachment;filename="+encFName);
			
			// output으로 출력, data를 클라이언트에게 전달
			ServletOutputStream sos = response.getOutputStream();
			
			byte [] buf = new byte[1024];	// 한번에 1바이트씩 전송 (제일 정보량이 지나갈 수 있는 통로가 작을 때를 기준으로 정해준 것)
			
	//		int cnt = 0;	// 정보가 전송된 횟수
			while(fis.available()>0) {	// 읽어올 정보가 남아있다면
				int len = fis.read(buf);	// 배열크기(바이트단위) = 읽어서 buf배열에 넣음
				sos.write(buf, 0, len);		// buf의 0부터 len만큼 보냄
	//			cnt++;
	//			System.out.println(cnt+":"+len);
			}
			sos.close();
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
