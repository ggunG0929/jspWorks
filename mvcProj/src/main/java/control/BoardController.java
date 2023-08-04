package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿(경로 /board/ 뒤에 뭐가 붙던)
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이전 선생님의경우
		// String uri = request.getRequestURI();
		// String command = uri.substring(uri.lastIndexOf('/'));
//		System.out.println(request.getRequestURI());	// /mvcProj/board/BList		// request.getRequestURI()메서드: 클라이언트가 요청한 페이지의 URI를 가져옴
//		System.out.println(request.getContextPath()+"/board/");	// /mvcProj/board/	// request.getParameter()메서드: 클라이언트가 요청한 URL에 포함된 파라미터를 가져옴
		// 나머지 = URI를 가져와서.잘라낸다((contextpath+/board/)의 길이만큼을):board/ 다음을 가져옴
		String serviceStr = request.getRequestURI().substring(	// .substring(첫인덱스, 마지막인덱스)메서드: .앞의 범위에서 첫인덱스~마지막인덱스-1까지를 추출함. 마지막인덱스 생략시 끝까지 추출
				(request.getContextPath()+"/board/").length()
				);
		//System.out.println(serviceStr);
		try {
			// 한글 깨지지 않게
			request.setCharacterEncoding("UTF-8");
			
			// 메인페이지라는 필드로 uri 중 board/ 다음을 지정해서 넘겨줌
			// redirect가 필요해지면서 보드서비스 보다 위로 자리를 옮겨줌
			request.setAttribute("mainPage", serviceStr);
			
			// 보드서비스타입의 서비스 객체(인스턴스)를 생성 = (보드서비스타입으로 형변환)ser_p패키지의 서비스str이름인 클래스 객체를 가져오고. 기본 생성자를 호출
			BoardService service = (BoardService)Class.forName("ser_p."+serviceStr).newInstance();	// surround with try catch
			// execute: 실행하다 수행하다	// 보드서비스를 통해 execute메서드 호출, 각 클래스에서 재정의 된 execute가 실행됨
			service.execute(request, response);
			
			// RequestDispatcher인터페이스 - 두가지 메서드를 제공함 include와 forward
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/template.jsp");
			// dispatcher의 위치로.forward-처리를 위임하면서 페이지이동 template.jsp를 출력(요청과 응답정보를 함께)
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
