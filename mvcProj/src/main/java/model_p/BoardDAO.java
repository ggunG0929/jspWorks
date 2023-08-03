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
// DAO(Data Access Object): 디자인 패턴 및 데이터 전달 객체에 대한 약어 
// =  CRUD기능을 수행, 서비스 계층과 데이터베이스 계층 사이에서 중간 계층
public class BoardDAO {
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	// mvc322 database를 찾아서 연결
	public BoardDAO() {
		try {
			// javax.naming.InitialContext 클래스: 네이밍 시스템에서 객체를 찾기 위한 기본 컨텍스트(Initial Context)를 생성
			Context init = new InitialContext();
			// 연결관리 커넥션제공 객체 ds = InitialContext 객체의. lookup()메서드를 이용하여 이름으로 등록된 DataSource 객체를 찾음("java:/comp/env/" 네임스페이스에 등록된 "mvc322"라는 이름의 database)
			DataSource ds = (DataSource)init.lookup("java:/comp/env/mvc322");
			// DataSource 객체로부터 데이터베이스 커넥션을 얻어옴
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 글목록
	public ArrayList<BoardDTO> list() {
		sql = "select * from board";
		// 배열리스트 생성
		ArrayList<BoardDTO> res = new ArrayList<>();
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 데이터베이스에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 더 이상의 레코드가 존재하는 동안에 계속해서 반복하는 반복문다음 레코드로 이동, 다음 레코드가 존재하면 true를 반환
			while(rs.next()) {
				// dto-게시물. 게시물의 필드들을 database의 값을 가져와 세팅
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
				// res목록에 게시물 추가시킴
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 연결종료
			close();
		}
		// 게시글 목록 반환
		return res;
	}
	
	// 글 상세보기(글아이디)
	public BoardDTO detail(int id) {
		sql = "select * from board where id = ?";
		BoardDTO dto = null;
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			// ResultSet에 레코드가 있다면
			if(rs.next()) {
				// dto-게시물. 게시물의 필드들을 database의 값을 가져와 세팅
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
		// id로 검색한 게시물 반환
		return dto;
	}
	
	public void close() {
		// 뭔가 담겨있다면 끝을 내줘라
		if(rs!=null) try {rs.close();} catch (Exception e) {}
		if(ptmt!=null) try {ptmt.close();} catch (Exception e) {}
		if(con!=null) try {con.close();} catch (Exception e) {}
	}

}
