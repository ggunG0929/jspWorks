package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
	// 인터페이스이기 때문에 execute는 다른 곳들에서 꼭 재정의해서 써야함
	void execute(HttpServletRequest request, HttpServletResponse response);
}
