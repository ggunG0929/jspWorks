package member;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Member implements Serializable {	// member add default versionID	//	serializable import

	private static final long serialVersionUID = 3L;
	
	// 필드
	private String memberId;
	private String passwd;
	private String name;
	private String gender;
	private Timestamp joinDate;		// date->timestamp import java.util
	
	// 생성자
	public Member() {}
	
	// get, set 메서드(우클 src)
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	
}
