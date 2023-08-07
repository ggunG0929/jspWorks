package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model_p.PageData2;

// 서블릿(경로 /gallery/ 뒤에 뭐가 붙던)
@WebServlet("/gallery/*")
public class GalleryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GalleryController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String serviceStr = request.getRequestURI().substring(
				(request.getContextPath()+"/gallery/").length()
				);

		try {
			// 한글 깨지지 않게
			request.setCharacterEncoding("UTF-8");
			
			request.setAttribute("mainPage", serviceStr);
			
			request.setAttribute("pd", new PageData2(request));
			
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
