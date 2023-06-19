package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCUtil;

public class MemberDAO {
	
	// 필드
	private Connection conn = null;			// import
	private PreparedStatement pstmt = null;	// import
	private ResultSet rs = null;			// import
	
	// 회원 가입
	public void addMember(Member member) {
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO t_member (memberid, passwd, name, gender) VALUES (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 회원목록
	public ArrayList<Member> getMemberList(){	// import
		ArrayList<Member> memberList = new ArrayList<>();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM t_member ORDER BY joindate DESC";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();	// db에서 꺼내옴
				member.setMemberId(rs.getString("memberid"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setJoinDate(rs.getDate("joindate"));
				memberList.add(member);			// 리스트에 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return memberList;
	}
	
	// 회원 상세 보기(정보)
	public Member getMember(String memberId) {
		Member member = new Member();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM t_member WHERE memberid = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setMemberId(rs.getString("memberid"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setJoinDate(rs.getDate("joindate"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return member;
	}
	
	// 로그인 체크
	public boolean checkLogin(Member member) {
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM t_member WHERE memberid = ? AND passwd = ?";
		try {
			pstmt = conn.prepareStatement(sql);	// try catch
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPasswd());
			rs = pstmt.executeQuery();
			if(rs.next()) {
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return false;
	}
	
	// 회원 삭제
	public void deleteMember(String memberId) {
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM t_member WHERE memberId = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// ID 중복 체크
//	public boolean duplicatedID(String memberId) {
	public int duplicatedID(String memberId) {
//		boolean result = false;
		int result = 0;
		conn = JDBCUtil.getConnection();
//		String sql = "SELECT DECODE(COUNT(*), 1, 'true', 'false') AS result FROM t_member WHERE memberid = ?";
		String sql = "SELECT COUNT(*) AS result FROM t_member WHERE memberid = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				result = rs.getBoolean("result");	// 칼럼이 result인 값을 꺼내옴
				result = rs.getInt("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return result;
	}

}
