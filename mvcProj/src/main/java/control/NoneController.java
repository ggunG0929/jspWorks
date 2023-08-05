package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿(경로 /noneJsp/ 뒤에 뭐가 붙던 - 파일다운로드 클릭시)
@WebServlet("/noneJsp/*")
public class NoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoneController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serviceStr = request.getRequestURI().substring(
				(request.getContextPath()+"/noneJsp/").length()
				);
		
		try {
			BoardService service = (BoardService)Class.forName("ser_p."+serviceStr).newInstance();
			service.execute(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
