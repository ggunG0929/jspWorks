package filter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFileFilter implements Filter {	// add unimplement method	// import
	
	PrintWriter writer;
	
	// 우클>source>override
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 로그파일 설정
		String filename = filterConfig.getInitParameter("filename");
		if(filename == null) {
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다.");	// 강제로 예외 발생
		}
		try {
			writer = new PrintWriter(new FileWriter(filename, true), true);	// surround try catch
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 콘솔에 기록할 내용
		String clientAddr = request.getRemoteAddr();	// IP주소(ex. 0:0:0:0:0:0:0:1 )
		writer.printf("클라이언트 IP주소: %s %n", clientAddr);
		
		long start = System.currentTimeMillis();
		writer.println("접근한 URL 경로: " + getURLPath(request));	// create method
		writer.println("요청 처리 시작 시각: " + getCurrentTime());
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		writer.println("요청 처리 종료 시각: " + getCurrentTime());

		writer.println("요청 처리 소요 시간: " + (end-start) + "ms");
		writer.println("====================================");
}
	
	@Override
	public void destroy() {
		writer.close();
	}
	
	private String getURLPath(ServletRequest request) {
		HttpServletRequest req;		// servletRequest를 상속받은 인터페이스
		String currentPath = "";
		String queryString = "";
		if(request instanceof HttpServletRequest) {
			req = (HttpServletRequest) request;		// add cast to(다운캐스팅(형변환))
			currentPath = req.getRequestURI();		// uri
			queryString = req.getQueryString();		// ? -> 쿼리스트링
			queryString = (queryString == null)? "":"?" + queryString;
		}
		return currentPath + queryString;
	}
	
	// 현재 날짜와 시간을 가져오는 메서드
	private String getCurrentTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	// ex. 2023-07-17 16:58:29
		return now.format(datetime);
	}

}
