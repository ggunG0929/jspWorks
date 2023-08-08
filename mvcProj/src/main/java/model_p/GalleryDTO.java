package model_p;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

// 모델링
// DTO(Data Transfer Object):
// 일반적으로 필드와 필드에 접근하기 위한 Getter와 Setter 메서드로 구성
// 데이터의 전송과 변환을 담당, 데이터의 저장이나 로직 처리를 하지 않음
public class GalleryDTO {
	
	String title, pname, pw, upfile, description;
	Date reg_date;
	int id, cnt;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) hh:mm");
	
	// 우클>src>getter,setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	/*
	 * public String getUpfile() { return upfile; }
	 */
	public String getUpfile() {
		// 파일이 없거나 공백이거나 null이라는 문자라면 공백으로 통일시킴
		if(upfile==null
				|| upfile.trim().equals("")
				|| upfile.trim().equals("null")) {
			upfile = "";
		}
		return upfile;
	}
	
	// 파일이 이미지일 때는 true를 반환
	public boolean isImg() {
		// Pattern.matches("찾을 글자", "원본 글")
		// ".*[.](jpg|bmp|png|gif)" : [.] 기호 .을 의미 그 앞에는 .(모든것)*(몇글자)[.](jpg나 bmp나 무엇이든)
		boolean res = Pattern.matches(".*[.](jpg|bmp|png|gif)", getUpfile().toLowerCase());
		return res;
	}
	
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	
	public String getDescription() {
		return description;
	}
	// 게시글 내용(content)에 엔터키 적용
	public String getDescriptionBr() {
		return description.replaceAll("\n", "<br>");
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getReg_date() {
		return reg_date;
	}
	// 작성일(reg_date)에 포맷팅 적용
	public String getReg_dateStr() {
		return sdf.format(reg_date);
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	// 우클>src>generate toString
	@Override
	public String toString() {
		return "GalleryDTO [title=" + title + ", pname=" + pname + ", pw=" + pw + ", upfile=" + upfile + ", description="
				+ description + ", id=" + id + ", cnt=" + cnt + "]";
	}

}