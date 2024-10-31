package dataTestObject;

public class User {
	private String emailId;
	private String password;

	/**
	 * @param emailId
	 * @param password
	 */
	public User(String emailId, String password) {

		this.emailId = emailId;
		this.password = password;
	}

	// use getter and setter method
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
