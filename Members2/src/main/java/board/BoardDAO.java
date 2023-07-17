package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.JDBCUtil;

public class BoardDAO {
	// 필드	// import java.sql
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 게시글 목록(페이징X)
//	public ArrayList<Board> getBoardList() {	// import java.util
//		ArrayList<Board> boardList = new ArrayList<>();
//		conn = JDBCUtil.getConnection();
//		String sql = "SELECT * FROM t_board ORDER BY regdate desc";
//		try {
//			pstmt = conn.prepareStatement(sql);	// surround with try catch
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				Board board = new Board();
//				board.setBnum(rs.getInt("bnum"));
//				board.setTitle(rs.getString("title"));
//				board.setContent(rs.getString("content"));
//				board.setRegDate(rs.getTimestamp("regdate"));
//				board.setModifyDate(rs.getTimestamp("modifydate"));
//				board.setHit(rs.getInt("hit"));
//				board.setMemberId(rs.getString("memberid"));
//				boardList.add(board); // 개별 Board 객체를 추가
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(conn, pstmt, rs);
//		}
//		return boardList;
//	}
	
//	// 게시글 목록(페이지처리)
//	public ArrayList<Board> getBoardList(int startRow, int pageSize) {
//		ArrayList<Board> boardList = new ArrayList<>();
//		try {
//			conn = JDBCUtil.getConnection();
//			String sql = "SELECT * FROM t_board ORDER BY bnum DESC limit ?, ?";
//			pstmt = conn.prepareStatement(sql);
////			pstmt.setInt(1, (page-1)*pageSize+1);	// 시작행
//			pstmt.setInt(1, startRow-1);	// 시작행
////			pstmt.setInt(2, page*pageSize);			// 마지막행(페이지당 게시글수)
//			pstmt.setInt(2, pageSize);		// 마지막행(페이지당 게시글수)
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				Board board = new Board();
//				board.setBnum(rs.getInt("bnum"));
//				board.setTitle(rs.getString("title"));
//				board.setContent(rs.getString("content"));
//				board.setRegDate(rs.getTimestamp("regdate"));
//				board.setModifyDate(rs.getTimestamp("modifydate"));
//				board.setHit(rs.getInt("hit"));
//				board.setMemberId(rs.getString("memberid"));
//				boardList.add(board); // 개별 Board 객체를 추가
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(conn, pstmt, rs);
//		}
//		return boardList;
//	}
//	
//	// 게시글 목록(검색처리)
//	public ArrayList<Board> getBoardList(String field, String kw) {
//		ArrayList<Board> boardList = new ArrayList<>();
//		try {
//			conn = JDBCUtil.getConnection();
//			String sql = "SELECT * FROM t_board where " + field + " like ? order by bnum";	// 띄어쓰기 주의
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%"+kw+"%");		// 검색어가 포함된 단어
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				Board board = new Board();
//				board.setBnum(rs.getInt("bnum"));
//				board.setTitle(rs.getString("title"));
//				board.setContent(rs.getString("content"));
//				board.setRegDate(rs.getTimestamp("regdate"));
//				board.setModifyDate(rs.getTimestamp("modifydate"));
//				board.setHit(rs.getInt("hit"));
//				board.setMemberId(rs.getString("memberid"));
//				boardList.add(board); // 개별 Board 객체를 추가
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(conn, pstmt, rs);
//		}
//		return boardList;
//	}
	
	// 게시글 목록(검색 및 페이지 처리)
	public ArrayList<Board> getBoardList(String field, String kw, int startRow, int pageSize) {
		ArrayList<Board> boardList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM t_board where " + field + " like ? order by bnum DESC limit ?, ?";	// 띄어쓰기 주의(field쪽)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kw+"%");		// 검색어가 포함된 단어
			pstmt.setInt(2, startRow-1);	// 시작행
			pstmt.setInt(3, pageSize);		// 마지막행(페이지당 게시글수)
			rs = pstmt.executeQuery();
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
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	// 최신글목록
	public ArrayList<Board> getBoardList() {
		ArrayList<Board> recentList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			// 3개(내 방식)
			String sql = "SELECT * FROM t_board ORDER BY bnum DESC limit 0,3";
//			// 선생님 방식
//			String sql = "SELECT * FROM t_board ORDER BY bnum";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board recent = new Board();
				recent.setBnum(rs.getInt("bnum"));
				recent.setTitle(rs.getString("title"));
				recent.setContent(rs.getString("content"));				// 사실상 불필요
				recent.setRegDate(rs.getTimestamp("regdate"));
				recent.setModifyDate(rs.getTimestamp("modifydate"));	// 사실상 불필요
				recent.setHit(rs.getInt("hit"));						// 사실상 불필요
				recent.setMemberId(rs.getString("memberid"));
				recentList.add(recent); // 개별 Board 객체를 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return recentList;
	}
	
	// 게시글 총 개수
	public int getBoardCount() {
		int total = 0;
		conn = JDBCUtil.getConnection();	// import
		String sql = "SELECT COUNT(*) AS total FROM t_board";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			rs = pstmt.executeQuery();
			if(rs.next())
				total = rs.getInt("total");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return total;
	}
	
	// 게시글 쓰기
	public void addBoard(Board board) {
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO t_board(title, content, memberid, fileupload) VALUES (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getMemberId());
			pstmt.setString(4, board.getFileUpload());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 게시글 상세 보기
	public Board getBoard(int bnum) {
		Board board = new Board();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM t_board WHERE bnum = ?";
		try {
			pstmt = conn.prepareStatement(sql);	// surround with try catch
			pstmt.setInt(1, bnum);	// 글 번호 바인딩 시킴
			rs = pstmt.executeQuery();
			if(rs.next()) {		// if - 데이터가 있으면 db에서 칼럼을 가져옴
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("regdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setMemberId(rs.getString("memberid"));
				
				// 조회수 1 증가(db수정)
				int hit = rs.getInt("hit") + 1;
				sql = "UPDATE t_board SET hit = ? WHERE bnum = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, hit);
				pstmt.setInt(2, bnum);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return board;
	}
	
	// 게시글 삭제
	public void deleteBoard(int bnum) {
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM t_board WHERE bnum = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setInt(1, bnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 게시글 수정
	public void updateBoard(Board board) {
		// 현재 시간 객체 생성
		Timestamp now = new Timestamp(System.currentTimeMillis());	// import java.sql
		
		
		conn = JDBCUtil.getConnection();
		String sql = "UPDATE t_board SET title=?, content=?, modifydate=? WHERE bnum=?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setTimestamp(3, now);
			pstmt.setInt(4, board.getBnum());
			pstmt.executeUpdate();	// db에 저장
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}

}
