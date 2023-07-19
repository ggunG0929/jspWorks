package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {

	static String driverClass = "com.mysql.cj.jdbc.Driver";		// 드라이버 이름(mySQL)
	static String url = "jdbc:mysql://localhost:3306/javaweb";		// db 경로
	static String username = "jweb";		// user 이름
	static String password = "jweb";		// 비밀번호

	public static void main(String[] args) {
		// 연결 객체 선언
		Connection conn = null;		// import connection
		
		try {
			Class.forName(driverClass);		// surround with try catch
			conn = DriverManager.getConnection(url, username, password);	// add catch clause to surrounding try
			System.out.println("연결 객체 생성: " + conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();	// surround with try catch
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
