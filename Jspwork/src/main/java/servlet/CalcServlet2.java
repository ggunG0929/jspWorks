package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator/calc2")
public class CalcServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 우클>source>override implement methods>service(노란색)
	// doPost내용 가져오고 doGet과 doPost에서 service호출.(method 방식에 상관없음)
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String op = request.getParameter("op");
		int result = 0;

		switch (op) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "x":
			result = num1 * num2;
			break;
		case "/":
			result = num1 / num2;
			break;
		default:
			System.out.println("올바른 연산자가 아닙니다.");
		}

		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter(); // import printwriter

		out.append("<link rel=\"stylesheet\" href=\"../resources/css/style.css\">")
			.append("<div id=\"container\">").append("<h2>계산기</h2><hr>")
			.append("<p>계산 결과: " + result + "</p></div>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response); // 호출
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response); // 호출
	}

}
