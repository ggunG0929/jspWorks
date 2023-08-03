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
//		System.out.println(request.getRequestURI());	// /mvcProj/board/BList
//		System.out.println(request.getContextPath()+"/board/");	// /mvcProj/board/
		// 나머지 = URI를 가져와서.잘라낸다((contextpath+/board/)의 길이만큼을):board/ 다음을 가져옴
		String serviceStr = request.getRequestURI().substring(
				(request.getContextPath()+"/board/").length()
				);
		System.out.println(serviceStr);
		
		try {
			BoardService service = (BoardService)Class.forName("ser_p."+serviceStr).newInstance();	// surround with try catch
			service.execute(request, response);
			
			// 메인페이지라는 필드로 uri 중 board/ 다음을 지정해서 넘겨줌 
			request.setAttribute("mainPage", serviceStr);
			
			// 템플릿에서 정보를 request해서 forward 한다
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/template.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
