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
			// 연결관리 커넥션제공 객체 ds = InitialContext 객체의. lookup()메서드를 이용하여 이름으로 등록된 DataSource 객체를 찾음
			// ("java(자바):/comp(컴파일)/env(환경)/mvc322(이름)")
			// 네임스페이스(META-INF>context.xml>resource name)에 등록된 "mvc322"라는 이름의 database)
			DataSource ds = (DataSource)init.lookup("java:/comp/env/mvc322");
			// DataSource 객체로부터 db 커넥션을 얻어옴
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글목록, 게시판
	public ArrayList<BoardDTO> list() {
		// 내림차순으로
		sql = "select * from board order by id desc";
		// 배열리스트 생성
		ArrayList<BoardDTO> res = new ArrayList<>();
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 더 이상의 레코드가 존재하는 동안에 계속해서 반복하는 반복문다음 레코드로 이동, 다음 레코드가 존재하면 true를 반환
			while(rs.next()) {
				// 게시글 모델 생성
				BoardDTO dto = new BoardDTO();
				// 게시글의 필드들을 db값을 가져와 세팅
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
				// res목록에 게시글 추가시킴
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 연결종료
			close();
		}
		// 게시글목록 반환
		return res;
	}
	
	// 게시글 상세보기(글 아이디)
	public BoardDTO detail(int id) {
		sql = "select * from board where id = ?";
		// 게시글 모델 null로(리턴하기 위해 if문 밖에서 생성)
		BoardDTO dto = null;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setInt(1, id);
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 레코드가 있다면
			if(rs.next()) {
				// 게시글 모델 생성
				dto = new BoardDTO();
				// 게시글의 필드들을 db값을 가져와 세팅
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
		// id로 검색된 db값으로 세팅된 게시글 반환
		return dto;
	}

	// 조회수 올리기(글 아이디)
	public void addCount(int id) {
		// db의 cnt(조회수)를 1 올려라 id가 ?인 게시글의
		sql = "update board set cnt = cnt+1 where id = ?";
		// 게시글 모델 null로
		BoardDTO dto = null;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setInt(1, id);
			// db 업데이트
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// 글 작성하기(게시글 모델)
	public void write(BoardDTO dto) {
		// 작성하자마자 글이 뜨도록 했으므로 조회수 초기값을 -1로 해서 확인하는 순간 조회수가 0이 되도록
		sql = "insert into board (title, pname, pw, upfile, content, seq, lev, gid, cnt, reg_date) "
				+ "values (?, ?, ?, ?, ?, 0, 0, 0, -1, sysdate())";
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getPw());
			ptmt.setString(4, dto.getUpfile());
			ptmt.setString(5, dto.getContent());
			// db에서 쿼리실행하고 결과로 영향받은 행의 수를 반환
			ptmt.executeUpdate();
			
			// 내가 쓴 글을 확인하기 위해 내가 쓴 글 id를 받아와야 함
			// 일단 종료
			ptmt.close();
			
			// db의 id 중 제일 큰 값을 가져옴
			sql = "select max(id) from board";
			
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 해당 레코드로 이동
			rs.next();
			// 게시글 모델에 rs의 첫번째 값(id)을 세팅함
			dto.setId(rs.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// 글 삭제하기(게시글 모델)
	public int delete(BoardDTO dto) {
		sql = "delete from board where id = ? and pw = ?";
		// 결과값을 0으로 선언
		int res = 0;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			// db에서 쿼리실행하고 결과로 영향받은 행의 수를 반환
			res = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// 결과값(영향받은 행의 수가 1개면 성공, 0이면 실패)
		return res;
	}
	
	// 글 삭제시 비번체크(게시글 모델)
	public BoardDTO idPwChk(BoardDTO dto) {
		sql = "select * from board where id = ? and pw = ?";
		// 게시글 모델 null로(리턴하기 위해 if문 밖에서 생성)
		BoardDTO res = null;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 레코드가 있다면
			if(rs.next()) {
				// 게시글 모델 생성
				res = new BoardDTO();
				// 게시글의 필드들을 db값을 가져와 세팅
				res.setId(rs.getInt("id"));
				res.setUpfile(rs.getString("upfile"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// id와 pw로 검색된 db값으로 id와 파일정보가 세팅된 게시글 반환
		return res;
	}

	// 글 수정하기(게시글 모델)
	public int modify(BoardDTO dto) {
		int res = 0;
		sql = "update board set title= ? , pname = ?, upfile = ?, content = ? where id = ? and pw = ?";
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getUpfile());
			ptmt.setString(4, dto.getContent());
			ptmt.setInt(5, dto.getId());
			ptmt.setString(6, dto.getPw());
			// db에서 쿼리실행하고 결과로 영향받은 행의 수를 반환
			res = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// 결과값(영향받은 행의 수가 1개면 성공, 0이면 실패)
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
