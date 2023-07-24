package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class MemberDAO {
	Connection conn = null;		// import
	PreparedStatement pstmt = null;		// import
	ResultSet rs = null;	// import
	
	// 회원 목록 보기
	public List<Member> getMemberList(){	// import
		List<Member> memberList = new ArrayList<>();	// import
		conn = JDBCUtil.getConnection();
		String sql = "select * from member order by regDate desc";
		try {
			pstmt = conn.prepareStatement(sql);		// surround try catch
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setMid(rs.getString("mid"));
				member.setPasswd(rs.getString("passwd"));
				member.setMname(rs.getString("mname"));
				member.setGender(rs.getString("gender"));
				member.setBirth(rs.getString("birth"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setRegDate(rs.getTimestamp("regDate"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return memberList;
	}
	
	// 회원 가입 (return값 없는 void)
	public void addMember(Member member) {
		conn = JDBCUtil.getConnection();
		String sql = "insert into member(mid, passwd, mname, gender, birth, email, phone, address)\r\n"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getMname());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getBirth());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}

	// 회원 정보
	public Member getMember(String mid) {
		Member member = new Member();
		conn = JDBCUtil.getConnection();
		String sql = "select * from member where mid = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround try catch
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setMid(rs.getString("mid"));
				member.setPasswd(rs.getString("passwd"));
				member.setMname(rs.getString("mname"));
				member.setGender(rs.getString("gender"));
				member.setBirth(rs.getString("birth"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setRegDate(rs.getTimestamp("regDate"));
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
		String sql = "select * from member where mid = ? and passwd = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround try catch
			pstmt.setString(1, member.getMid());
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
	
	// ID 중복 체크
	public int duplicatedID(String mid) {
		int result = 0;
		conn = JDBCUtil.getConnection();
		String sql = "SELECT COUNT(*) AS result FROM member WHERE mid = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
