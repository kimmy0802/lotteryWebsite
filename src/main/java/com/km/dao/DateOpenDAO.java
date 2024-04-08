package com.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DateOpenDAO.
 */
public class DateOpenDAO {


	/** The conn. */
	private Connection conn;
	
	/** The is error. */
	private boolean isError;

	/**
	 * Instantiates a new DateOpenDAO.
	 */
	public DateOpenDAO() {
		this.conn = null;
	}

	/**
	 * Instantiates a new DateOpenDAO.
	 *
	 * @param conn the conn
	 */
	public DateOpenDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Insert date open.
	 *
	 * @param dateOpen the date open
	 * @param provinceID the province ID
	 * @param region the region
	 */
	public void insertDateOpen(List<String> dateOpen,int provinceID,String region ) {
		for(String dateOfWeek:dateOpen) {
			String sql = "insert into dateopen(dateOfWeek,province_id,region) values(?,?,?);";
			PreparedStatement stm2;		
			try {
				stm2 = conn.prepareStatement(sql);
				stm2.setString(1, dateOfWeek);
				stm2.setInt(2, provinceID);
				stm2.setString(3, region);
				stm2.executeUpdate();
				isError=false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isError=true;
			}
		}
	}
	
	/**
	 * Delete date open.
	 *
	 * @param provinceID the province ID
	 */
	public void deleteDateOpen(int provinceID) {
		String sql = "update dateopen set dateOpenDeleted=1 where province_id=?";
		PreparedStatement stm;		
		try {
			stm = conn.prepareStatement(sql);			
			stm.setInt(1, provinceID);
			stm.executeUpdate();
			isError=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isError=true;
		}
		
	}
	
	/**
	 * Delete date open.
	 *
	 * @param dateOfWeek the date of week
	 * @param provinceID the province ID
	 */
	public void deleteDateOpen(String dateOfWeek,int provinceID) {
		String sql = "update dateopen set dateOpenDeleted=1 where province_id=? and dateOfWeek=?;";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, provinceID);
			stm.setString(2,dateOfWeek);
			stm.executeUpdate();
			isError=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isError=true;
		}		
		
	}

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * Sets the error.
	 *
	 * @param isError the new error
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}


}
