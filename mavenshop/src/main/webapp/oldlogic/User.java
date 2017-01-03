package logic;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
/*
 * ��ȿ�� ������ ���Ǵ� ������̼�����
 * 
 *  @Future: �̷� ��¥ �� ����
 *  @Past:���� ��¥������
 *  @Max: �ִ밪�� ���� ���ش� .
 *  @Min: �ּҰ��� �������ً� .
 *  @NotEmpty: ���� null �ȵ� 
 *  @NotNull : null�̸� �ȵ� 
 *  @Null : �ݵ�� null�̾���� 
 *  @Size(min= ,max= ) :������ ũ�Ⱑ Min~Max���̸� ����
 */

public class User {
	@Size(min = 3, max = 15, message = "���̵�� 3���̻� 15�ڸ����Ϸ� �Է��ϼ���.")
	private String userId;
	@Size(min = 5, max = 15, message = "��й�ȣ�� 5���̻� 15�ڸ����Ϸ� �Է��ϼ���.")
	private String password;
	//@Notempty
	private String userName;
	private String postCode;
	private String address;
	private String email;
	private String job;
	@Past(message = "��������� ���ų�¥�� �����մϴ� Ȯ���ϼ���.")//���� ��¥�� �����Ѱ�� 
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
