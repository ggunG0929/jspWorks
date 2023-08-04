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
		// 내림차순으로
		sql = "select * from board order by id desc";
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
	
	// 글 상세보기(글 아이디)
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

	// 조회수 올리기(글 아이디)
	public void addCount(int id) {
		sql = "update board set cnt = cnt+1 where id = ?";
		BoardDTO dto = null;
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// 글 작성하기(게시글모델)
	public void write(BoardDTO dto) {
		// 작성하자마자 글이 뜨도록 했으므로 조회수 초기값을 -1로 해서 확인하는 순간 조회수가 0이 되도록
		sql = "insert into board (title, pname, pw, upfile, content, seq, lev, gid, cnt, reg_date) "
				+ "values (?, ?, ?, ?, ?, 0, 0, 0, -1, sysdate())";
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getPw());
			ptmt.setString(4, dto.getUpfile());
			ptmt.setString(5, dto.getContent());
			ptmt.executeUpdate();
			
			// 내가 쓴 글을 확인하기 위해 내가 쓴 글 id를 받아와야함
			ptmt.close();
			
			sql = "select max(id) from board";
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			dto.setId(rs.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// 글 삭제하기(게시글모델)
	public int delete(BoardDTO dto) {
		sql = "delete from board where id = ? and pw = ?";
		int res = 0;
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			res = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// 1이면 성공, 0이면 실패
		return res;
	}
	
	// 글 삭제시 비번체크(게시글모델)
	public BoardDTO idPwChk(BoardDTO dto) {
		sql = "select * from board where id = ? and pw = ?";
		BoardDTO res = null;
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			rs = ptmt.executeQuery();
			// ResultSet에 레코드가 있다면
			if(rs.next()) {
				// dto-게시물. 게시물의 필드들을 database의 값을 가져와 세팅
				res = new BoardDTO();
				res.setId(rs.getInt("id"));
				res.setUpfile(rs.getString("upfile"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// id로 검색한 게시물 반환
		return res;
	}

	// 글 수정하기(게시글모델)
	public int modify(BoardDTO dto) {
		int res = 0;
		sql = "update board set title= ? , pname = ?, upfile = ?, content = ? where id = ? and pw = ?";
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getUpfile());
			ptmt.setString(4, dto.getContent());
			ptmt.setInt(5, dto.getId());
			ptmt.setString(6, dto.getPw());
			res = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return res;
	}
	
	// 데이터베이스 접속 종료
	public void close() {
		// 뭔가 담겨있다면 끝을 내줘라
		if(rs!=null) try {rs.close();} catch (Exception e) {}
		if(ptmt!=null) try {ptmt.close();} catch (Exception e) {}
		if(con!=null) try {con.close();} catch (Exception e) {}
	}

}
