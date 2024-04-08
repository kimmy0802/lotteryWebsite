/*
 * 
 */
package com.km.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Lottery.
 */
public class Lottery {
	
	/** The eighth prize. */
	private String  special_prize,first_prize,second_prize,third_prize,fourth_prize,fifth_prize,sixth_prize,seventh_prize,eighth_prize;
	
	/** The lottery date. */
	private String lottery_date;
	
	/** The deleted. */
	private int lottery_id,province_id,deleted;//deleted 0: has, 1: deleted
	
	/**
	 * Instantiates a new lottery.
	 */
	public Lottery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new lottery.
	 *
	 * @param special_prize the special prize
	 * @param first_prize the first prize
	 * @param second_prize the second prize
	 * @param third_prize the third prize
	 * @param fourth_prize the fourth prize
	 * @param fifth_prize the fifth prize
	 * @param sixth_prize the sixth prize
	 * @param seventh_prize the seventh prize
	 * @param eighth_prize the eighth prize
	 * @param lottery_date the lottery date
	 * @param lottery_id the lottery id
	 * @param province_id the province id
	 * @param deleted the deleted
	 */
	public Lottery(String special_prize, String first_prize, String second_prize, String third_prize,
			String fourth_prize, String fifth_prize, String sixth_prize, String seventh_prize, String eighth_prize,
			String lottery_date, int lottery_id, int province_id, int deleted) {
		super();
		this.special_prize = special_prize;
		this.first_prize = first_prize;
		this.second_prize = second_prize;
		this.third_prize = third_prize;
		this.fourth_prize = fourth_prize;
		this.fifth_prize = fifth_prize;
		this.sixth_prize = sixth_prize;
		this.seventh_prize = seventh_prize;
		this.eighth_prize = eighth_prize;
		this.lottery_date = lottery_date;
		this.lottery_id = lottery_id;
		this.province_id = province_id;
		this.deleted = deleted;
	}

	/**
	 * Gets the special prize.
	 *
	 * @return the special prize
	 */
	public String getSpecial_prize() {
		return special_prize;
	}

	/**
	 * Sets the special prize.
	 *
	 * @param special_prize the new special prize
	 */
	public void setSpecial_prize(String special_prize) {
		this.special_prize = special_prize;
	}

	/**
	 * Gets the first prize.
	 *
	 * @return the first prize
	 */
	public String getFirst_prize() {
		return first_prize;
	}

	/**
	 * Sets the first prize.
	 *
	 * @param first_prize the new first prize
	 */
	public void setFirst_prize(String first_prize) {
		this.first_prize = first_prize;
	}

	/**
	 * Gets the second prize.
	 *
	 * @return the second prize
	 */
	public String getSecond_prize() {
		return second_prize;
	}

	/**
	 * Sets the second prize.
	 *
	 * @param second_prize the new second prize
	 */
	public void setSecond_prize(String second_prize) {
		this.second_prize = second_prize;
	}

	/**
	 * Gets the third prize.
	 *
	 * @return the third prize
	 */
	public String getThird_prize() {
		return third_prize;
	}

	/**
	 * Sets the third prize.
	 *
	 * @param third_prize the new third prize
	 */
	public void setThird_prize(String third_prize) {
		this.third_prize = third_prize;
	}

	/**
	 * Gets the fourth prize.
	 *
	 * @return the fourth prize
	 */
	public String getFourth_prize() {
		return fourth_prize;
	}

	/**
	 * Sets the fourth prize.
	 *
	 * @param fourth_prize the new fourth prize
	 */
	public void setFourth_prize(String fourth_prize) {
		this.fourth_prize = fourth_prize;
	}

	/**
	 * Gets the fifth prize.
	 *
	 * @return the fifth prize
	 */
	public String getFifth_prize() {
		return fifth_prize;
	}

	/**
	 * Sets the fifth prize.
	 *
	 * @param fifth_prize the new fifth prize
	 */
	public void setFifth_prize(String fifth_prize) {
		this.fifth_prize = fifth_prize;
	}

	/**
	 * Gets the sixth prize.
	 *
	 * @return the sixth prize
	 */
	public String getSixth_prize() {
		return sixth_prize;
	}

	/**
	 * Sets the sixth prize.
	 *
	 * @param sixth_prize the new sixth prize
	 */
	public void setSixth_prize(String sixth_prize) {
		this.sixth_prize = sixth_prize;
	}

	/**
	 * Gets the seventh prize.
	 *
	 * @return the seventh prize
	 */
	public String getSeventh_prize() {
		return seventh_prize;
	}

	/**
	 * Sets the seventh prize.
	 *
	 * @param seventh_prize the new seventh prize
	 */
	public void setSeventh_prize(String seventh_prize) {
		this.seventh_prize = seventh_prize;
	}

	/**
	 * Gets the eighth prize.
	 *
	 * @return the eighth prize
	 */
	public String getEighth_prize() {
		return eighth_prize;
	}

	/**
	 * Sets the eighth prize.
	 *
	 * @param eighth_prize the new eighth prize
	 */
	public void setEighth_prize(String eighth_prize) {
		this.eighth_prize = eighth_prize;
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
	 * Gets the lottery id.
	 *
	 * @return the lottery id
	 */
	public int getLottery_id() {
		return lottery_id;
	}

	/**
	 * Sets the lottery id.
	 *
	 * @param lottery_id the new lottery id
	 */
	public void setLottery_id(int lottery_id) {
		this.lottery_id = lottery_id;
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
	
	
	

	
	
	
	

}
