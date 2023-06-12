package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	
	static String driverClass = "oracle.jdbc.OracleDriver";		// 드라이버 이름
//	static String url = "jdbc:oracle:thin:@localhost:1521:xe";	// db 경로(위치)	// 학원
	static String url = "jdbc:oracle:thin:@192.168.219.111:1521:xe";	// 집
	static String username = "c##mydb";		// user 이름
	static String password = "mydb";		// 비밀번호
	
	// DB 연결 메서드
	public static Connection getConnection() {	// import
		try {
			Class.forName(driverClass);			// surround with try catch
			return DriverManager.getConnection(url, username, password);	// add catch clause
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// DB 연결 종료 메서드
	public static void close(Connection conn, PreparedStatement pstmt) {	// import
		if(pstmt != null) {
			try {
				pstmt.close();		// surround with try catch
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
		
		if(conn != null) {
			try {
				conn.close();	// surround with try catch
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	// DB 연결 종료 메서드(ResultSet이 있는 경우
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {	// import
		if(rs != null) {
			try {
				rs.close();			// surround with try catch
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();		// surround with try catch
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
		
		if(conn != null) {
			try {
				conn.close();	// surround with try catch
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}

}
