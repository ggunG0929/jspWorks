package basic_p;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//서블릿 라이프사이클

@WebServlet("/basic/First")
public class First extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public First() {
        super();
        System.out.println("First 생성자 실행");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 실행");
		response.getWriter()
		.append("Served at: ")
		.append(request.getContextPath())
		.append("First지롱");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {	// init override
		super.init();
		System.out.println("init 실행");
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroy 실행");
	}

}