/*
 * 
 */
package com.km.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Province.
 */
public class Province {
	
	/** The province id. */
	private int province_id;
	
	/** The region. */
	private String province, region,dateOpen;
	
	/**
	 * Instantiates a new province.
	 */
	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new province.
	 *
	 * @param province_id the province id
	 * @param province the province
	 * @param region the region
	 */
	public Province(int province_id, String province, String region) {
		super();
		this.province_id = province_id;
		this.province = province;
		this.region = region;
	}
	
	/**
	 * Instantiates a new province.
	 *
	 * @param province_id the province id
	 * @param province the province
	 * @param region the region
	 * @param dateOpen the date open
	 */
	public Province(int province_id, String province, String region,String dateOpen) {
		super();
		this.province_id = province_id;
		this.province = province;
		this.region = region;
		this.dateOpen=dateOpen;
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
	 * Gets the date open.
	 *
	 * @return the date open
	 */
	public String getDateOpen() {
		return dateOpen;
	}

	/**
	 * Sets the date open.
	 *
	 * @param dateOpen the new date open
	 */
	public void setDateOpen(String dateOpen) {
		this.dateOpen = dateOpen;
	}
	
	

}
