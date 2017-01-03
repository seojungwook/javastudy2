package logic;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
/*
 * 유효성 검증에 사용되는 어노테이션정리
 * 
 *  @Future: 미래 날짜 만 가능
 *  @Past:과거 날짜만가능
 *  @Max: 최대값을 지정 해준다 .
 *  @Min: 최소값을 지정해줄떄 .
 *  @NotEmpty: 공백 null 안됨 
 *  @NotNull : null이면 안됨 
 *  @Null : 반드시 null이어야함 
 *  @Size(min= ,max= ) :글자의 크기가 Min~Max사이만 가능
 */

public class User {
	@Size(min = 3, max = 15, message = "아이디는 3자이상 15자리이하로 입력하세요.")
	private String userId;
	@Size(min = 5, max = 15, message = "비밀번호는 5자이상 15자리이하로 입력하세요.")
	private String password;
	//@Notempty
	private String userName;
	private String postCode;
	private String address;
	private String email;
	private String job;
	@Past(message = "생년월일은 과거날짜만 가능합니다 확인하세요.")//과거 날짜만 가능한경우 
	private Date birthDay;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", postCode=" + postCode
				+ ", address=" + address + ", email=" + email + ", job=" + job + ", birthDay=" + birthDay + "]";
	}

}
