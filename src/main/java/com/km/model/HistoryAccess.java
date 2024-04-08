/*
 * 
 */
package com.km.model;


// TODO: Auto-generated Javadoc
/**
 * The Class HistoryAccess.
 */
public class HistoryAccess {

	/** The date access. */
	private String username,date_access;
	
	/**
	 * Instantiates a new history access.
	 */
	public HistoryAccess() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new history access.
	 *
	 * @param username the username
	 * @param date_access the date access
	 */
	public HistoryAccess(String username, String date_access) {
		super();
		this.username = username;
		this.date_access = date_access;
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
	 * Gets the date access.
	 *
	 * @return the date access
	 */
	public String getDate_access() {
		return date_access;
	}
	
	/**
	 * Sets the date access.
	 *
	 * @param date_access the new date access
	 */
	public void setDate_access(String date_access) {
		this.date_access = date_access;
	}


}
