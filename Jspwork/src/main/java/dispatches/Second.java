package dispatches;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/disp/second")
public class Second extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨텐츠 유형 설정
		response.setContentType("text/html; charset=utf-8");
		
		// 출력 스트림 객체 생성
		PrintWriter out = response.getWriter();		// printwriter create
		out.println("<title>제목테스트</title>");	// 임의로 테스트해봄
		out.println("<h3>dispatch를 이용한 포워딩 실습입니다.</h3>");
		
		// second?name=kang
		String name = request.getParameter("name");
		out.println("<p>이름: " + name + "</p>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
	}

}
