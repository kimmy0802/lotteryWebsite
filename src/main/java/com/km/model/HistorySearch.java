/*
 * 
 */
package com.km.model;

// TODO: Auto-generated Javadoc
/**
 * The Class HistorySearch.
 */
public class HistorySearch {
	
	/** The result search. */
	private String username,lottery_number,lottery_date,search_time,resultSearch;
	
	/** The province id. */
	private int province_id;
	
	/**
	 * Instantiates a new history search.
	 */
	public HistorySearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new history search.
	 *
	 * @param username the username
	 * @param province_id the province id
	 * @param lottery_number the lottery number
	 * @param lottery_date the lottery date
	 * @param search_time the search time
	 * @param resultSearch the result search
	 */
	public HistorySearch(String username, int province_id, String lottery_number, String lottery_date, String search_time,String resultSearch) {
		super();
		this.username = username;
		this.province_id = province_id;
		this.lottery_number = lottery_number;
		this.lottery_date = lottery_date;
		this.search_time=search_time;
		this.resultSearch=resultSearch;
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
	 * Gets the province id.
	 *
	 * @return the province id
	 */
	public int getProvince_id() {
		return province_id;
	}

	/**
	 * Sets the province id.
	 *
	 * @param province_id the new province id
	 */
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}

	/**
	 * Gets the lottery number.
	 *
	 * @return the lottery number
	 */
	public String getLottery_number() {
		return lottery_number;
	}

	/**
	 * Sets the lottery number.
	 *
	 * @param lottery_number the new lottery number
	 */
	public void setLottery_number(String lottery_number) {
		this.lottery_number = lottery_number;
	}

	/**
	 * Gets the lottery date.
	 *
	 * @return the lottery date
	 */
	public String getLottery_date() {
		return lottery_date;
	}

	/**
	 * Sets the lottery date.
	 *
	 * @param lottery_date the new lottery date
	 */
	public void setLottery_date(String lottery_date) {
		this.lottery_date = lottery_date;
	}

	/**
	 * Gets the search time.
	 *
	 * @return the search time
	 */
	public String getSearch_time() {
		return search_time;
	}

	/**
	 * Sets the search time.
	 *
	 * @param search_time the new search time
	 */
	public void setSearch_time(String search_time) {
		this.search_time = search_time;
	}

	/**
	 * Gets the result search.
	 *
	 * @return the result search
	 */
	public String getResultSearch() {
		return resultSearch;
	}

	/**
	 * Sets the result search.
	 *
	 * @param resultSearch the new result search
	 */
	public void setResultSearch(String resultSearch) {
		this.resultSearch = resultSearch;
	}

}
