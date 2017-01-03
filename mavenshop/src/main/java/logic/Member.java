package logic;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.NotNull;

public class Member {
	@Size(min = 2, max = 20, message = "ID는 2자이상, 20자 이하로 입력하세요.")
	private String id;
	@Size(min = 2, max = 20, message = "비밀번호는 2자이상, 20자 이하로 입력하세요.")
	private String pass;
	@NotEmpty
	private String name;
	private Date birthDay;
	private String gender;
	private String email;
	private String pictureUrl;
	private String postCode;
	private String address;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pass=" + pass + ", name=" + name + ", birthDay=" + birthDay + ", gender="
				+ gender + ", email=" + email + ", pictureUrl=" + pictureUrl + ", postCode=" + postCode + ", address="
				+ address + "]";
	}

}