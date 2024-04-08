package com.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.km.model.Prizes;

// TODO: Auto-generated Javadoc
/**
 * Class chứa các phương thước làm việc với bảng PRIZES trong database.
 */
public class PrizesDAO {

	/** The conn. */
	private Connection conn;

	/**
	 * Instantiates a new prizes DAO.
	 */
	public PrizesDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new prizes DAO.
	 *
	 * @param conn the conn
	 */
	public PrizesDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	
	/**
	 * Gets the cost of prizes.
	 *
	 * @return the cost of prizes
	 */
	public List<Prizes> getCostOfPrizes() {
		List<Prizes> results=new ArrayList<>();
		String sql="select * from PRIZES;";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Prizes p=new Prizes(rs.getString("region"), rs.getString("special_prize"), rs.getString("first_prize"), rs.getString("second_prize"), rs.getString("third_prize"), rs.getString("fourth_prize"), rs.getString("fifth_prize"),  rs.getString("sixth_prize"),  rs.getString("seventh_prize"), rs.getString("eighth_prize"));
				results.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	/**
	 * Gets the cost of prizes.
	 *
	 * @param region the region
	 * @return the cost of prizes
	 */
	public Prizes getCostOfPrizes(String region) {
		Prizes result=new Prizes();
		String sql="select * from PRIZES where region=?;";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, region);
			ResultSet rs=stmt.executeQuery();
			if (rs.next()) {
				result =new Prizes(rs.getString("region"), rs.getString("special_prize"), rs.getString("first_prize"), rs.getString("second_prize"), rs.getString("third_prize"), rs.getString("fourth_prize"), rs.getString("fifth_prize"),  rs.getString("sixth_prize"),  rs.getString("seventh_prize"), rs.getString("eighth_prize"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Gets the cost of prize.
	 *
	 * @param region the region
	 * @param prize the prize
	 * @return the cost of prize
	 */
	public String getCostOfPrize(String region,String prize) {
		if(prize.length()==0) {
			return "";
		}
		String result=new String();
		String sql="select * from PRIZES where region=?;";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, region);
			ResultSet rs=stmt.executeQuery();
			if (rs.next()) {
				result =rs.getString(prize);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Gets the conn.
	 *
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * Sets the conn.
	 *
	 * @param conn the new conn
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	


}
