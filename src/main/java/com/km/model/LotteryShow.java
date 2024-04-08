/*
 * 
 */
package com.km.model;

// TODO: Auto-generated Javadoc
/**
 * The Class LotteryShow.
 */
public class LotteryShow {
	
	/** The day of week. */
	private String region, province,dayOfWeek;
	
	/** The lottery. */
	private Lottery lottery;
	
	/** The date. */
	private String date;

	/**
	 * Instantiates a new lottery show.
	 */
	public LotteryShow() {
		super();
		// TODO Auto-generated constructor stub
	}

/**
 * Instantiates a new lottery show.
 *
 * @param region the region
 * @param province the province
 * @param date the date
 * @param lottery the lottery
 * @param dayOfWeek the day of week
 */
/*
	public LotteryShow(String region, String province, String date, Lottery lottery) {
		super();
		this.region = region;
		this.province = province;
		this.date = date;
		this.lottery = lottery;
	}
*/
	public LotteryShow(String region, String province,  String date,Lottery lottery, String dayOfWeek) {
		super();
		this.region = region;
		this.province = province;
		this.dayOfWeek = dayOfWeek;
		this.lottery = lottery;
		this.date = date;
	}


	/**
	 * Gets the day of week.
	 *
	 * @return the day of week
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Sets the day of week.
	 *
	 * @param dayOfWeek the new day of week
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Gets the province.
	 *
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the province.
	 *
	 * @param province the new province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the lottery.
	 *
	 * @return the lottery
	 */
	public Lottery getLottery() {
		return lottery;
	}

	/**
	 * Sets the lottery.
	 *
	 * @param lottery the new lottery
	 */
	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

}
