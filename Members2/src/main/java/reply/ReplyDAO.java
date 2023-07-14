package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.JDBCUtil;

// 댓글 삽입, 조회, 수정, 삭제를 관리할 클래스
public class ReplyDAO {
	private Connection conn = null;			// import
	private PreparedStatement pstmt = null;	// import
	private ResultSet rs = null;			// import
	
	// 댓글 목록 조회
	public ArrayList<Reply> getReplyList(int bnum) {	// 매개변수-게시글번호
		ArrayList<Reply> replyList = new ArrayList<>();
		
		conn = JDBCUtil.getConnection();
		String sql = "select * from t_reply where bnum = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setInt(1, bnum);	// ?자리에 bnum을 줌(bnum이 같은 댓글들 정보를 가져옴)
			rs = pstmt.executeQuery();
			while(rs.next()) {	// 순회하면서 지정해줌
				Reply reply = new Reply();
				reply.setRno(rs.getInt("rno"));
				reply.setBnum(rs.getInt("bnum"));
				reply.setRcontent(rs.getString("rcontent"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setRdate(rs.getTimestamp("rdate"));
				reply.setRupdate(rs.getTimestamp("rupdate"));
				replyList.add(reply);	// 리스트에 댓글을 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return replyList;
	}
	
	// 댓글 등록
	public void addReply(Reply reply) {
		conn = JDBCUtil.getConnection();
		String sql = "insert into t_reply(bnum, rcontent, replyer) values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setInt(1, reply.getBnum());
			pstmt.setString(2, reply.getRcontent());
			pstmt.setString(3, reply.getReplyer());
			pstmt.executeUpdate();	// sql문을 실행(처리)
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 댓글 삭제
	public void deleteReply(int rno) {
		conn = JDBCUtil.getConnection();
		String sql = "delete from t_reply where rno = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();	// 삭제 처리
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}

	// 특정 댓글 상세보기
	public Reply getReply(int rno) {
		Reply reply = new Reply();

		conn = JDBCUtil.getConnection();
		String sql = "select * from t_reply where rno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply.setRno(rs.getInt("rno"));
				reply.setBnum(rs.getInt("bnum"));
				reply.setRcontent(rs.getString("rcontent"));
				reply.setReplyer(rs.getString("replyer"));
				reply.setRupdate(rs.getTimestamp("rupdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return reply;
	}
	
	// 댓글 수정
	public void updateReply(Reply reply) {
		Timestamp now = new Timestamp(System.currentTimeMillis()); 

		conn = JDBCUtil.getConnection();
		String sql = "update t_reply set rcontent = ?, rupdate = ? where rno = ? ";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, reply.getRcontent());
			pstmt.setTimestamp(2, now);
			pstmt.setInt(3, reply.getRno());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
}
