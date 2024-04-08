package com.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.km.model.Province;

// TODO: Auto-generated Javadoc
/**
 * Class chứa các phương thước làm việc với bảng provinces,dateopen trong
 * database.
 */
public class ProvincesDAO {

	/** The conn. */
	private Connection conn;
	
	/** The is error. */
	private boolean isError;

	/**
	 * Instantiates a new provinces DAO.
	 */
	public ProvincesDAO() {
		this.conn = null;
	}

	/**
	 * Instantiates a new provinces DAO.
	 *
	 * @param conn the conn
	 */
	public ProvincesDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Gets the province ID.
	 *
	 * @param province the province
	 * @return the province ID
	 * @throws SQLException the SQL exception
	 */
	public int getProvinceID(String province) throws SQLException {
		String sql = "select * from provinces where province=? and isDeleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, province);
		ResultSet rs = stmt.executeQuery();
		int result = -1;
		if (rs.next()) {
			result = rs.getInt("province_id");
		}
		return result;
	}

	/**
	 * Gets the region.
	 *
	 * @param provinceID the province ID
	 * @return the region
	 * @throws SQLException the SQL exception
	 */
	public String getRegion(int provinceID) throws SQLException {
		String sql = "select * from provinces where province_id=? and isDeleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, provinceID);
		ResultSet rs = stmt.executeQuery();
		String result = "";
		if (rs.next()) {
			result = rs.getString("region");
		}
		return result;
	}

	/**
	 * Gets the regions.
	 *
	 * @return the regions
	 * @throws SQLException the SQL exception
	 */
	public List<String> getRegions() throws SQLException {
		String sql = "select region as r from provinces where isDeleted=0 group by region;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<String> result = new ArrayList<String>();
		while (rs.next()) {
			result.add(rs.getString("r"));
		}
		return result;
	}

	/**
	 * Gets the province.
	 *
	 * @param province_id the province id
	 * @return the province
	 * @throws SQLException the SQL exception
	 */
	public String getProvince(int province_id) throws SQLException {
		String sql = "select * from provinces where province_id=? and isDeleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, province_id);
		ResultSet rs = stmt.executeQuery();
		String result = null;

		if (rs.next()) {
			result = rs.getString("province");
		}

		return result;
	}

	/**
	 * Gets the provinces name with region.
	 *
	 * @param region the region
	 * @return the provinces name
	 * @throws SQLException the SQL exception
	 */
	public List<String> getProvincesName(String region) throws SQLException {
		List<String> results = new ArrayList<>();
		String sql = "select * from provinces where isDeleted=0;";
		if (!region.equals("Tất cả")) {
			sql = "select * from provinces where region=\"" + region + "\" and isDeleted=0;;";
		}
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			results.add(rs.getString("province"));
		}
		return results;
	}

	/**
	 * Gets the provinces.
	 *
	 * @param region     the region
	 * @param dateOfWeek the date of week
	 * @return the provinces
	 * @throws SQLException the SQL exception
	 */
	public List<Province> getProvinces(String region, String dateOfWeek) throws SQLException {
		List<Province> results = new ArrayList<>();
		if (region.equals("Tất cả"))
			region = "";
		region = "%" + region + "%";

		String sql = "SELECT provinces.province_id as province_id,provinces.region as region, province, dateOfWeek FROM provinces "
				+ " join dateopen " + " on provinces.province_id=dateopen.province_id "
				+ " where provinces.region like ? and dateOfWeek = ? and isDeleted=0 and dateOpenDeleted=0;";
		if (dateOfWeek.equals("Tất cả")) {
			sql = "SELECT provinces.province_id as province_id,provinces.region as region, province, dateOfWeek FROM provinces "
					+ " join dateopen " + " on provinces.province_id=dateopen.province_id "
					+ " where provinces.region like ? and dateOfWeek like ? and isDeleted=0 and dateOpenDeleted=0;";
			dateOfWeek = "%%";
		}

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, region);
		stmt.setString(2, dateOfWeek);
		//System.out.println(stmt.toString());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Province province = new Province(rs.getInt("province_id"), rs.getString("province"), rs.getString("region"),
					rs.getString("dateOfWeek"));
			results.add(province);
		}
		return results;
	}

	/**
	 * Gets the provinces.
	 *
	 * @param provinceID the province ID
	 * @return the provinces
	 * @throws SQLException the SQL exception
	 */
	public Province getProvinces(int provinceID) throws SQLException {
		String sql = "select * from provinces where province_id=? and  isDeleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, provinceID);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			Province p = new Province(rs.getInt("province_id"), rs.getString("province"), rs.getString("region"));
			return p;
		}

		return null;
	}

	/**
	 * Gets the provinces.
	 *
	 * @return the provinces
	 * @throws SQLException the SQL exception
	 */
	public List<Province> getProvinces() throws SQLException {
		List<Province> results = new ArrayList<>();
		String sql = "select * from provinces where isDeleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Province p = new Province(rs.getInt("province_id"), rs.getString("province"), rs.getString("region"));
			results.add(p);
		}

		
		return results;
	}

	/**
	 * Gets the provinces.
	 *
	 * @param region the region
	 * @return the provinces
	 * @throws SQLException the SQL exception
	 */
	public List<Province> getProvinces(String region) throws SQLException {

		List<Province> results = new ArrayList<>();
		String sql = "select * from provinces where region=? and isDeleted=0";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, region);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Province p = new Province(rs.getInt("province_id"), rs.getString("province"), rs.getString("region"));
			results.add(p);
		}

		return results;
	}
	
	/**
	 * Checks if is date open.
	 *
	 * @param provinceID the province ID
	 * @param dateOfWeek the date of week
	 * @return true, if is date open
	 * @throws SQLException the SQL exception
	 */
	public boolean isDateOpen(int provinceID,String dateOfWeek) throws SQLException {		

		String sql = "select * from dateopen where  province_id=? and dateOpenDeleted=0 and dateOfWeek=?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, provinceID);
		stmt.setString(2, dateOfWeek);
		
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Delete province.
	 *
	 * @param provinceid the provinceid
	 */
	public void deleteProvince(int provinceid) {
		String sql = "update provinces set isDeleted=1 where province_id=? ";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, provinceid);

			stm.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}
	}

	/**
	 * Delete province.
	 *
	 * @param provincesList the provinces list
	 */
	public void deleteProvince(List<Integer> provincesList) {
		isError = false;
		for (int i : provincesList) {
			deleteProvince(i);
			if (isError)
				break;

		}
		if (isError) {
			for (int i : provincesList) {
				try {
					undeletedProvince(i);
				} catch (SQLException e) {
					isError = true;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Undeleted province.
	 *
	 * @param proviceID the provice ID
	 * @throws SQLException the SQL exception
	 */
	public void undeletedProvince(int proviceID) throws SQLException {
		String sql = "update provinces set isDeleted=0 where province_id=? ";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, proviceID);
		stm.executeUpdate();
	}

	/**
	 * Checks if is province name.
	 *
	 * @param province_name the province name
	 * @return true, if is province name
	 * @throws SQLException the SQL exception
	 */
	public boolean isProvinceName(String province_name) throws SQLException {
		String sql = "select * from provinces where province=? and isDeleted=0";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, province_name);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Creates the province.
	 *
	 * @param province the province
	 * @param region the region
	 */
	public void createProvince(String province, String region) {
		String sql = "insert into provinces(province,region) values(?,?);";
		PreparedStatement stm2;
		try {
			stm2 = conn.prepareStatement(sql);
			stm2.setString(1, province);
			stm2.setString(2, region);
			stm2.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isError = true;
		}
	}

	/**
	 * Update province.
	 *
	 * @param provinceID the province ID
	 * @param province the province
	 * @param region the region
	 */
	public void updateProvince(int provinceID, String province, String region) {
		String sql = "update provinces set province=?,region=? where province_id=?";
		PreparedStatement stm2;
		try {
			stm2 = conn.prepareStatement(sql);
			stm2.setString(1, province);
			stm2.setString(2, region);
			stm2.setInt(3, provinceID);
			stm2.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isError = true;
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
