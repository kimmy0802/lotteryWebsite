package com.km.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
public class Account {
	
	/** The created by. */
	private String username,password,fullname,phonenumber,email,part, created_date,created_by;
	
	/** The activate. */
	private int activate;//0:is activating; 1: is not activating ; 
	
	/** The deleted. */
	private int deleted;// 0: has; 1:deleted
	
	/** The locked. */
	private int locked;// 0: unlock; 1:locked
	
	/** The login fail. */
	private int loginFail;
	
	/**
	 * Instantiates a new account.
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new account.
	 *
	 * @param username the username
	 * @param password the password
	 * @param fullname the fullname
	 * @param phonenumber the phonenumber
	 * @param email the email
	 * @param part the part
	 * @param created_date the created date
	 * @param created_by the created by
	 * @param activate the activate
	 * @param deleted the deleted
	 * @param locked the locked
	 * @param loginFail the login fail
	 */
	public Account(String username, String password, String fullname, String phonenumber, String email, String part,
			String created_date, String created_by, int activate, int deleted,int locked,int loginFail) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.part = part;
		this.created_date = created_date;
		this.created_by = created_by;
		this.activate = activate;
		this.deleted = deleted;
		this.locked=locked;
		this.loginFail=loginFail;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the fullname.
	 *
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}
	
	/**
	 * Sets the fullname.
	 *
	 * @param fullname the new fullname
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	/**
	 * Gets the phonenumber.
	 *
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	
	/**
	 * Sets the phonenumber.
	 *
	 * @param phonenumber the new phonenumber
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the part.
	 *
	 * @return the part
	 */
	public String getPart() {
		return part;
	}
	
	/**
	 * Sets the part.
	 *
	 * @param part the new part
	 */
	public void setPart(String part) {
		this.part = part;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public String getCreated_date() {
		return created_date;
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param created_date the new created date
	 */
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	
	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreated_by() {
		return created_by;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param created_by the new created by
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	/**
	 * Gets the activate.
	 *
	 * @return the activate
	 */
	public int getActivate() {
		return activate;
	}
	
	/**
	 * Sets the activate.
	 *
	 * @param activate the new activate
	 */
	public void setActivate(int activate) {
		this.activate = activate;
	}
	
	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public int getDeleted() {
		return deleted;
	}
	
	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * Gets the locked.
	 *
	 * @return the locked
	 */
	public int getLocked() {
		return locked;
	}
	
	/**
	 * Sets the locked.
	 *
	 * @param locked the new locked
	 */
	public void setLocked(int locked) {
		this.locked = locked;
	}
	
	/**
	 * Gets the login fail.
	 *
	 * @return the login fail
	 */
	public int getLoginFail() {
		return loginFail;
	}
	
	/**
	 * Sets the login fail.
	 *
	 * @param loginFail the new login fail
	 */
	public void setLoginFail(int loginFail) {
		this.loginFail = loginFail;
	}	
	
}
