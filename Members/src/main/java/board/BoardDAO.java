package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCUtil;

public class BoardDAO {
	// 필드	// import java.sql
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	// 게시글 목록
	public ArrayList<Board> getBoardList() {	// import java.util
		ArrayList<Board> boardList = new ArrayList<>();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM t_board ORDER BY regdate desc";
		try {
			psmt = conn.prepareStatement(sql);	// surround with try catch
			rs = psmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("regdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setMemberId(rs.getString("memberid"));
				boardList.add(board); // 개별 Board 객체를 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

}
