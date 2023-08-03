package model_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// sql과 연동
public class BoardDAO {
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			// 모르겠당
			DataSource ds = (DataSource)init.lookup("java:/comp/env/mvc322");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BoardDTO> list() {
		sql = "select * from board";
		ArrayList<BoardDTO> res = new ArrayList<>();
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLev(rs.getInt("lev"));
				dto.setPname(rs.getString("pname"));
				dto.setPw(rs.getString("pw"));
				dto.setTitle(rs.getString("title"));
				dto.setUpfile(rs.getString("upfile"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getTimestamp("reg_date"));

				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return res;
	}
	
	public BoardDTO detail(int id) {
		sql = "select * from board where id = ?";
		BoardDTO dto = null;
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLev(rs.getInt("lev"));
				dto.setPname(rs.getString("pname"));
				dto.setPw(rs.getString("pw"));
				dto.setTitle(rs.getString("title"));
				dto.setUpfile(rs.getString("upfile"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	
	public void close() {
		// 뭔가 담겨있다면 끝을 내줘라
		if(rs!=null) try {rs.close();} catch (Exception e) {}
		if(ptmt!=null) try {ptmt.close();} catch (Exception e) {}
		if(con!=null) try {con.close();} catch (Exception e) {}
	}

}
