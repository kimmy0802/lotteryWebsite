package com.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.km.model.CheckInput;
import com.km.model.Lottery;
import com.km.model.LotteryShow;

// TODO: Auto-generated Javadoc
/**
 * Class chứa các phương thước làm việc với bảng lotteries_detail trong database.
 */
public class LotteryDAO {

	/** The checkaction. */
	boolean checkaction;
	
	/** The conn. */
	private Connection conn;
	
	/** The is error. */
	private boolean isError = false;

	/**
	 * Checks if is checkaction.
	 *
	 * @return true, if is checkaction
	 */
	public boolean isCheckaction() {
		return checkaction;
	}

	/**
	 * Sets the checkaction.
	 *
	 * @param checkaction the new checkaction
	 */
	public void setCheckaction(boolean checkaction) {
		this.checkaction = checkaction;
	}

	/**
	 * Instantiates a new lottery DAO.
	 */
	public LotteryDAO() {
		this.conn = null;
	}

	/**
	 * Instantiates a new lottery DAO.
	 *
	 * @param conn the conn
	 */
	public LotteryDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Update lottery.
	 *
	 * @param province_id the province id
	 * @param lottery_date the lottery date
	 * @param special_prize the special prize
	 * @param first_prize the first prize
	 * @param second_prize the second prize
	 * @param third_prize the third prize
	 * @param fourth_prize the fourth prize
	 * @param fifth_prize the fifth prize
	 * @param sixth_prize the sixth prize
	 * @param seventh_prize the seventh prize
	 * @param eighth_prize the eighth prize
	 * @throws SQLException the SQL exception
	 */
	public void updateLottery(int province_id, String lottery_date, String special_prize, String first_prize,
			String second_prize, String third_prize, String fourth_prize, String fifth_prize, String sixth_prize,
			String seventh_prize, String eighth_prize) throws SQLException {

		if (!isLottery(province_id, lottery_date)) {
			isError = true;
			return;
		}
		lottery_date = new CheckInput().formDateToYMD(lottery_date);
		String sql = "update lotteries_detail set special_prize=?, first_prize=?, second_prize=?,"
				+ " third_prize=?, fourth_prize=?, fifth_prize=?, sixth_prize=?, seventh_prize=?,"
				+ " eighth_prize=? where province_id=? and lottery_date=?  and deleted=0";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, special_prize);
			stm.setString(2, first_prize);
			stm.setString(3, second_prize);
			stm.setString(4, third_prize);
			stm.setString(5, fourth_prize);
			stm.setString(6, fifth_prize);
			stm.setString(7, sixth_prize);
			stm.setString(8, seventh_prize);
			stm.setString(9, eighth_prize);
			stm.setInt(10, province_id);
			stm.setString(11, lottery_date);
			stm.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}

	}

	/**
	 * Update lottery.
	 *
	 * @param lottery the lottery
	 * @throws SQLException the SQL exception
	 */
	public void updateLottery(Lottery lottery) throws SQLException {
		if (lottery == null) {
			isError = true;
			return;
		}

		if (!isLottery(lottery.getProvince_id(), lottery.getLottery_date())) {
			createLottery(lottery);
			return;
		}
		String sql = "update lotteries_detail set special_prize=?, first_prize=?, second_prize=?,"
				+ " third_prize=?, fourth_prize=?, fifth_prize=?, sixth_prize=?, seventh_prize=?,"
				+ " eighth_prize=? where province_id=? and lottery_date=?  and deleted=0";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, lottery.getSpecial_prize());
			stm.setString(2, lottery.getFirst_prize());
			stm.setString(3, lottery.getSecond_prize());
			stm.setString(4, lottery.getThird_prize());
			stm.setString(5, lottery.getFourth_prize());
			stm.setString(6, lottery.getFifth_prize());
			stm.setString(7, lottery.getSixth_prize());
			stm.setString(8, lottery.getSeventh_prize());
			stm.setString(9, lottery.getEighth_prize());
			stm.setInt(10, lottery.getProvince_id());
			stm.setString(11, new CheckInput().formDateToYMD(lottery.getLottery_date()));
			stm.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}

	}

	/**
	 * Creates the lottery.
	 *
	 * @param lottery the lottery
	 */
	// create a lottery
	public void createLottery(Lottery lottery) {
		if (lottery == null) {
			isError = true;
			return;
		}
		String sql = "insert into lotteries_detail( province_id, lottery_date, special_prize, first_prize, second_prize, third_prize,"
				+ " fourth_prize, fifth_prize, sixth_prize, seventh_prize, eighth_prize,deleted) values(?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, lottery.getProvince_id());
			stm.setString(2, new CheckInput().formDateToYMD(lottery.getLottery_date()));
			stm.setString(3, lottery.getSpecial_prize());
			stm.setString(4, lottery.getFirst_prize());
			stm.setString(5, lottery.getSecond_prize());
			stm.setString(6, lottery.getThird_prize());
			stm.setString(7, lottery.getFourth_prize());
			stm.setString(8, lottery.getFifth_prize());
			stm.setString(9, lottery.getSixth_prize());
			stm.setString(10, lottery.getSeventh_prize());
			stm.setString(11, lottery.getEighth_prize());
			stm.setInt(12, 0);
			//System.out.println(stm.toString().substring(43));
			stm.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}

	}

	/**
	 * Delete lottery  by LotteryID.
	 *
	 * @param lottery_id the lottery id
	 */
	public void deleteLottery(int lottery_id) {
		String sql = "update lotteries_detail set deleted=1 where lottery_id=?;";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, lottery_id);
			stmt.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}
	}

	/**
	 * Delete lottery by LotteryID List.
	 *
	 * @param lotteryList the lottery list
	 */
	public void deleteLottery(List<Integer> lotteryList) {
		String s = "select * from lotteries_detail WHERE lottery_id = ? and deleted=0";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(s);
			for (int i : lotteryList) {
				stm.setInt(1, i);
				ResultSet rs = stm.executeQuery();
				if (!rs.next()) {
					isError = true;
					return;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "update lotteries_detail set deleted=1 where lottery_id=?;";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			for (int i : lotteryList) {
				stmt.setInt(1, i);
				stmt.executeUpdate();
			}
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}

	}

	/**
	 * Gets the lottery by lotteryID.
	 *
	 * @param lotteryID the lottery ID
	 * @return the lottery
	 * @throws SQLException the SQL exception
	 */
	public LotteryShow getLottery(int lotteryID) throws SQLException {
		LotteryShow result = new LotteryShow();
		String sql = "select lottery_id, special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize, sixth_prize,"
				+ " seventh_prize, eighth_prize,lotteries_detail.province_id as province_id, lottery_date, deleted,province, region "
				+ "from lotteries_detail join provinces on lotteries_detail.province_id=provinces.province_id "
				+ " where lottery_id=?  and isDeleted=0 and deleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, lotteryID);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String d = ""+rs.getDate("lottery_date");
			d=new CheckInput().formDateToDMY(d);
			Lottery l = new Lottery();
			l.setDeleted(rs.getInt("deleted"));
			l.setSpecial_prize(rs.getString("special_prize"));
			l.setFirst_prize(rs.getString("first_prize"));
			l.setSecond_prize(rs.getString("second_prize"));
			l.setThird_prize(rs.getString("third_prize"));
			l.setFourth_prize(rs.getString("fourth_prize"));
			l.setFifth_prize(rs.getString("fifth_prize"));
			l.setSixth_prize(rs.getString("sixth_prize"));
			l.setSeventh_prize(rs.getString("seventh_prize"));
			l.setEighth_prize(rs.getString("eighth_prize"));
			l.setLottery_id(rs.getInt("lottery_id"));
			l.setLottery_date(d);
			l.setProvince_id(rs.getInt("province_id"));
			result = new LotteryShow(rs.getString("region"), rs.getString("province"),d, l,
					new CheckInput().getDayOfWeek(d));
		}

		return result;
	}

	/**
	 * Gets the lottery by province name and date.
	 *
	 * @param date the date
	 * @param province the province
	 * @return the lottery
	 * @throws SQLException the SQL exception
	 */
	public LotteryShow getLottery(String date, String province) throws SQLException {
		LotteryShow result = new LotteryShow();
		String sql = "select lottery_id, special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize, sixth_prize,"
				+ " seventh_prize, eighth_prize,lotteries_detail.province_id as province_id, lottery_date, deleted,province, region "
				+ "from lotteries_detail join provinces on lotteries_detail.province_id=provinces.province_id "
				+ " where lottery_date=? and province=?  and isDeleted=0 and deleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, new CheckInput().formDateToYMD(date));
		stmt.setString(2, province);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String d = ""+ rs.getDate("lottery_date");
			d=new CheckInput().formDateToYMD(d);
			Lottery l = new Lottery();
			l.setDeleted(rs.getInt("deleted"));
			l.setSpecial_prize(rs.getString("special_prize"));
			l.setFirst_prize(rs.getString("first_prize"));
			l.setSecond_prize(rs.getString("second_prize"));
			l.setThird_prize(rs.getString("third_prize"));
			l.setFourth_prize(rs.getString("fourth_prize"));
			l.setFifth_prize(rs.getString("fifth_prize"));
			l.setSixth_prize(rs.getString("sixth_prize"));
			l.setSeventh_prize(rs.getString("seventh_prize"));
			l.setEighth_prize(rs.getString("eighth_prize"));
			l.setLottery_id(rs.getInt("lottery_id"));
			l.setLottery_date(d);
			l.setProvince_id(rs.getInt("province_id"));
			result = new LotteryShow(rs.getString("region"), rs.getString("province"), d, l,
					new CheckInput().getDayOfWeek(d));
		}

		return result;
	}

	/**
	 * Gets the lottery by province id and date .
	 *
	 * @param date the date
	 * @param provinceID the province ID
	 * @return the lottery
	 * @throws SQLException the SQL exception
	 */
	public LotteryShow getLottery(String date, int provinceID) throws SQLException {
		LotteryShow result = new LotteryShow();
		String sql = "select lottery_id, special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize, sixth_prize,"
				+ " seventh_prize, eighth_prize,lotteries_detail.province_id as province_id, lottery_date, deleted,province, region "
				+ "from lotteries_detail join provinces on lotteries_detail.province_id=provinces.province_id "
				+ " where lottery_date=? and lotteries_detail.province_id=?  and isDeleted=0 and deleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		date=new CheckInput().formDateToYMD(date);
		stmt.setString(1, date);
		stmt.setInt(2, provinceID);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String d = ""+ rs.getDate("lottery_date");
			d=new CheckInput().formDateToDMY(d);
			Lottery l = new Lottery();
			l.setDeleted(rs.getInt("deleted"));
			l.setSpecial_prize(rs.getString("special_prize"));
			l.setFirst_prize(rs.getString("first_prize"));
			l.setSecond_prize(rs.getString("second_prize"));
			l.setThird_prize(rs.getString("third_prize"));
			l.setFourth_prize(rs.getString("fourth_prize"));
			l.setFifth_prize(rs.getString("fifth_prize"));
			l.setSixth_prize(rs.getString("sixth_prize"));
			l.setSeventh_prize(rs.getString("seventh_prize"));
			l.setEighth_prize(rs.getString("eighth_prize"));
			l.setLottery_id(rs.getInt("lottery_id"));
			l.setLottery_date(d);
			l.setProvince_id(rs.getInt("province_id"));
			result = new LotteryShow(rs.getString("region"), rs.getString("province"), d, l,
					new CheckInput().getDayOfWeek(d));
		}
		return result;
	}

	/**
	 * Gets the lotteries return: List of lotteryShow : condition region!="tat ca" .
	 *
	 * @param search the search
	 * @param region the region
	 * @param colOrder the col order
	 * @param order the order
	 * @param page the page
	 * @param sum the sum
	 * @return the lotteries
	 * @throws SQLException the SQL exception
	 */
	public List<LotteryShow> getLotteries(String search, String region, String colOrder, String order, int page,
			int sum) throws SQLException {
		List<LotteryShow> results = new ArrayList<>();
		page = page * sum - sum;
		String sql = "select lottery_date, deleted, provinces.province_id, province, region, lottery_id, special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize, sixth_prize, seventh_prize, eighth_prize from lotteries_detail\r\n"
				+ "join provinces on lotteries_detail.province_id=provinces.province_id "
				+ "where (special_prize like '%" + search + "%' or  first_prize like '%" + search
				+ "%' or second_prize like '%" + search + "%' or third_prize like '%" + search
				+ "%' or fourth_prize like '%" + search + "%' or fifth_prize like '%" + search
				+ "%' or sixth_prize like '%" + search + "%' or seventh_prize like '%" + search
				+ "%' or eighth_prize like '%" + search + "%' or lottery_date like '%" + search + "%' or region like '%"
				+ search + "%' or province like '%" + search + "%' ) and isDeleted=0 and deleted=0 and provinces.region like '%"
				+ region + "%' order by " + colOrder + " " + order + " limit " + page + "," + sum + ";";

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String d = ""+ rs.getDate("lottery_date");
			d=new CheckInput().formDateToDMY(d);
			Lottery l = new Lottery();
			l.setLottery_id(rs.getInt("lottery_id"));
			l.setLottery_date(d);
			l.setProvince_id(rs.getInt("province_id"));
			l.setSpecial_prize(rs.getString("special_prize"));
			l.setFirst_prize(rs.getString("first_prize"));
			l.setSecond_prize(rs.getString("second_prize"));
			l.setThird_prize(rs.getString("third_prize"));
			l.setFourth_prize(rs.getString("fourth_prize"));
			l.setFifth_prize(rs.getString("fifth_prize"));
			l.setSixth_prize(rs.getString("sixth_prize"));
			l.setSeventh_prize(rs.getString("seventh_prize"));
			l.setEighth_prize(rs.getString("eighth_prize"));
			l.setDeleted(rs.getInt("deleted"));
			LotteryShow lS = new LotteryShow(rs.getString("region"), rs.getString("province"), d, l,
					new CheckInput().getDayOfWeek(d));
			results.add(lS);
		}
		return results;
	}

	/**
	 * Gets the lotteries list.
	 *
	 * @param search the search
	 * @param region the region
	 * @param colOrder the colorder
	 * @param order the order
	 * @param page the page
	 * @param sum the sum
	 * @return the lotteries list
	 * @throws SQLException the SQL exception
	 */
	public List<LotteryShow> getLotteriesList(String search, String region, String colOrder, String order, int page,
			int sum) throws SQLException {
		List<LotteryShow> results = new ArrayList<>();
		String sql = "select lottery_date, region,province,provinces.province_id as province_id,lottery_id, special_prize, first_prize, second_prize, third_prize,"
				+ " fourth_prize, fifth_prize, sixth_prize, seventh_prize, eighth_prize, deleted from lotteries_detail "
				+ "	join provinces on lotteries_detail.province_id=provinces.province_id "
				+ "where (special_prize like '%" + search + "%' or  first_prize like '%" + search
				+ "%' or second_prize like '%" + search + "%' or third_prize like '%" + search
				+ "%' or fourth_prize like '%" + search + "%' or fifth_prize like '%" + search
				+ "%' or sixth_prize like '%" + search + "%' or seventh_prize like '%" + search
				+ "%' or eighth_prize like '%" + search + "%' or lottery_date like '%" + search + "%' or region like '%"
				+ search + "%' or province like '%" + search + "%' ) and isDeleted=0 and deleted=0 and provinces.region like '%"
				+ region + "%' order by province " + order + " limit " + (page * 3 - 3) + ",3;";

		if (colOrder != null && colOrder.equals("lottery_date")) {
			sql = "select lottery.lottery_date as lottery_date, region, province, province_id, lottery_id, special_prize, first_prize, second_prize, third_prize, "
					+ " fourth_prize, fifth_prize, sixth_prize, seventh_prize, eighth_prize, deleted from( select lottery_date, region,province,provinces.province_id as province_id,"
					+ " lottery_id, special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize,"
					+ " sixth_prize, seventh_prize, eighth_prize, deleted from lotteries_detail "
					+ " join provinces on lotteries_detail.province_id=provinces.province_id "
					+ " where (special_prize like '%" + search + "%' or  first_prize like '%" + search
					+ "%' or second_prize like '%" + search + "%' or third_prize like '%" + search
					+ "%' or fourth_prize like '%" + search + "%' or fifth_prize like '%" + search
					+ "%' or sixth_prize like '%" + search + "%' or seventh_prize like '%" + search
					+ "%' or eighth_prize like '%" + search + "%' or lottery_date like '%" + search
					+ "%' or region like '%" + search + "%' or province like '%" + search
					+ "%' ) and isDeleted=0 and deleted=0 and provinces.region like '%" + region + "%') as lottery"
					+ "	join (select lottery_date,count(lottery_date) from ("
					+ " select lottery_date from lotteries_detail "
					+ " join provinces on lotteries_detail.province_id=provinces.province_id "
					+ " where (special_prize like '%" + search + "%' or  first_prize like '%" + search
					+ "%' or second_prize like '%" + search + "%' or third_prize like '%" + search
					+ "%' or fourth_prize like '%" + search + "%' or fifth_prize like '%" + search
					+ "%' or sixth_prize like '%" + search + "%' or seventh_prize like '%" + search
					+ "%' or eighth_prize like '%" + search + "%' or lottery_date like '%" + search
					+ "%' or region like '%" + search + "%' or province like '%" + search
					+ "%' ) and isDeleted=0 and deleted=0 and provinces.region like '%" + region + "%') as d "
					+ " group by lottery_date order by lottery_date " + order + " limit " + (page - 1)
					+ ",1) as dateList on lottery.lottery_date=dateList.lottery_date	order by province asc;";
		}

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String d = ""+ rs.getDate("lottery_date");
			d=new CheckInput().formDateToDMY(d);
			Lottery l = new Lottery();
			l.setLottery_id(rs.getInt("lottery_id"));
			l.setLottery_date(d);
			l.setProvince_id(rs.getInt("province_id"));
			l.setSpecial_prize(rs.getString("special_prize"));
			l.setFirst_prize(rs.getString("first_prize"));
			l.setSecond_prize(rs.getString("second_prize"));
			l.setThird_prize(rs.getString("third_prize"));
			l.setFourth_prize(rs.getString("fourth_prize"));
			l.setFifth_prize(rs.getString("fifth_prize"));
			l.setSixth_prize(rs.getString("sixth_prize"));
			l.setSeventh_prize(rs.getString("seventh_prize"));
			l.setEighth_prize(rs.getString("eighth_prize"));
			l.setDeleted(rs.getInt("deleted"));
			LotteryShow lS = new LotteryShow(rs.getString("region"), rs.getString("province"),d, l,
					new CheckInput().getDayOfWeek(d));
			results.add(lS);
		}
		return results;
	}

	/**
	 * Gets the lottery of province.
	 *
	 * @param province the province
	 * @param region the region
	 * @param colOrder the col order
	 * @param order the order
	 * @param page the page
	 * @param sum the sum
	 * @return the lottery of province
	 * @throws SQLException the SQL exception
	 */
	public List<LotteryShow> getLotteryOfProvince(String province, String region, String colOrder, String order,
			int page, int sum) throws SQLException {
		if (province != null && province.equals("Tất cả"))
			province = "";
		province = "%" + province + "%";

		List<LotteryShow> results = new ArrayList<>();
		page = page * sum - sum;

		String sql = "SELECT lottery_id, special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize,"
				+ " sixth_prize, seventh_prize, eighth_prize, lottery_date, deleted,"
				+ " provinces.province_id as province_id,provinces.province as  province,provinces.region as region FROM lotteries_detail  "
				+ " join provinces on lotteries_detail.province_id=provinces.province_id\r\n"
				+ " where province like ?  and provinces.region = ?  and isDeleted=0 and deleted=0 " + " order by " + colOrder + " "
				+ order + " limit ?,?;";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, province);
		stmt.setString(2, region);
		stmt.setInt(3, page);
		stmt.setInt(4, sum);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String d = ""+ rs.getDate("lottery_date");
			d=new CheckInput().formDateToDMY(d);
			Lottery l = new Lottery();
			l.setLottery_id(rs.getInt("lottery_id"));
			l.setLottery_date(d);
			l.setProvince_id(rs.getInt("province_id"));
			l.setSpecial_prize(rs.getString("special_prize"));
			l.setFirst_prize(rs.getString("first_prize"));
			l.setSecond_prize(rs.getString("second_prize"));
			l.setThird_prize(rs.getString("third_prize"));
			l.setFourth_prize(rs.getString("fourth_prize"));
			l.setFifth_prize(rs.getString("fifth_prize"));
			l.setSixth_prize(rs.getString("sixth_prize"));
			l.setSeventh_prize(rs.getString("seventh_prize"));
			l.setEighth_prize(rs.getString("eighth_prize"));
			l.setDeleted(rs.getInt("deleted"));
			LotteryShow lS = new LotteryShow(rs.getString("region"), rs.getString("province"), d, l,
					new CheckInput().getDayOfWeek(d));
			results.add(lS);
		}
		return results;
	}

	/**
	 * Checks if is lottery on date with provinceID.
	 *
	 * @param provinceID the province ID
	 * @param date the date
	 * @return true, if is lottery
	 * @throws SQLException the SQL exception
	 */
	public boolean isLottery(int provinceID, String date) throws SQLException {
		String sql = "select * from lotteries_detail where lottery_date=? and province_id=? and deleted=0";
		PreparedStatement stmt = conn.prepareStatement(sql);
		date=new CheckInput().formDateToYMD(date);
		stmt.setString(1, date);
		stmt.setInt(2, provinceID);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the lotteries at prize.
	 *
	 * @param provinceID the province ID
	 * @param date the date
	 * @param prize the prize
	 * @return the lotteries at prize
	 * @throws SQLException the SQL exception
	 */
	public String getLotteriesAtPrize(int provinceID, String date, String prize) throws SQLException {
		String results = new String();
		String sql = "select lotteries_detail.lottery_id as lottery_id,lotteries_detail.lottery_date as lottery_date,lotteries_detail.province_id as province_id,lotteries_detail.special_prize as special_prize,lotteries_detail.first_prize as first_prize,\r\n"
				+ "lotteries_detail.second_prize as second_prize, lotteries_detail.third_prize as third_prize, lotteries_detail.fourth_prize as fourth_prize,\r\n"
				+ "lotteries_detail.fifth_prize as fifth_prize, lotteries_detail.sixth_prize as sixth_prize, lotteries_detail.seventh_prize as seventh_prize,\r\n"
				+ "lotteries_detail.eighth_prize as eighth_prize,lotteries_detail.deleted as deleted  from lotteries_detail join provinces on lotteries_detail.province_id=provinces.province_id\r\n"
				+ "where lotteries_detail.lottery_date=? and provinces.province_id=?  and isDeleted=0 and deleted=0";

		PreparedStatement stmt = conn.prepareStatement(sql);
		date=new CheckInput().formDateToYMD(date);
		stmt.setString(1, date);
		stmt.setInt(2, provinceID);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			results = rs.getString(prize);
		}
		if (results == null)
			results = "";
		results = results.replaceAll("null", "");
		results = results.trim();
		return results;
	}

	/**
	 * Gets the lottery of province pages.
	 *
	 * @param province the province
	 * @param region the region
	 * @param sum the sum
	 * @return the lottery of province pages
	 * @throws SQLException the SQL exception
	 */
	public int getLotteryOfProvincePages(String province, String region, int sum) throws SQLException {
		if (province != null && province.equals("Tất cả"))
			province = "";
		int result = 0;
		province = "%" + province + "%";

		String sql = "select count(*) as c from lotteries_detail join provinces on provinces.province_id=lotteries_detail.province_id "
				+ " where provinces.province like ?  and isDeleted=0 and deleted=0 and provinces.region =?;";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, province);
		stmt.setString(2, region);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			result = rs.getInt("c");
		}
		result = result % sum == 0 ? result / sum : result / sum + 1;
		return result;
	}

	/**
	 * Gets the lotteries pages.
	 *
	 * @param search the search
	 * @param region the region
	 * @param colOrder the col order
	 * @param sum the sum
	 * @return the lotteries pages
	 * @throws SQLException the SQL exception
	 */
	public int getLotteriesPages(String search, String region, String colOrder, int sum) throws SQLException {
		if (region != null && region.equals("Tất cả"))
			region = "";
		int result = 0;
		String sql = "select count(*) as c from (select lottery.lottery_date , count(*)"
				+ " from( select lottery_date, region,province,provinces.province_id as province_id,lottery_id,"
				+ " special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize, sixth_prize,"
				+ " seventh_prize, eighth_prize, deleted from lotteries_detail "
				+ " join provinces on lotteries_detail.province_id=provinces.province_id"
				+ " where (special_prize like '%" + search + "%' or  first_prize like '%" + search
				+ "%' or second_prize like '%" + search + "%' or third_prize like '%" + search
				+ "%' or fourth_prize like '%" + search + "%' or fifth_prize like '%" + search
				+ "%' or sixth_prize like '%" + search + "%' or seventh_prize like '%" + search
				+ "%' or eighth_prize like '%" + search + "%' or lottery_date like '%" + search + "%' or region like '%"
				+ search + "%' or province like '%" + search + "%' )  and isDeleted=0 and deleted=0 and provinces.region like '%"
				+ region + "%') as lottery "
				+ " join (select lottery_date,count(lottery_date) from lotteries_detail group by lottery_date order by lottery_date desc ) as dateList"
				+ "	on lottery.lottery_date=dateList.lottery_date group by dateList.lottery_date) as d;";

		if (colOrder != null && colOrder.equals("province")) {
			sql = "select count(*) as c from lotteries_detail join provinces on provinces.province_id=lotteries_detail.province_id "
					+ " where (special_prize like '%" + search + "%' or  first_prize like '%" + search
					+ "%' or second_prize like '%" + search + "%' or third_prize like '%" + search
					+ "%' or fourth_prize like '%" + search + "%' or fifth_prize like '%" + search
					+ "%' or sixth_prize like '%" + search + "%' or seventh_prize like '%" + search
					+ "%' or eighth_prize like '%" + search + "%' or lottery_date like '%" + search
					+ "%' or region like '%" + search + "%' or province like '%" + search
					+ "%' )  and isDeleted=0 and deleted=0 and provinces.region like '%" + region + "%';";
		}

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			result = rs.getInt("c");
		}
		if (colOrder != null && colOrder.equals("province")) {
			result = result % sum == 0 ? result / sum : result / sum + 1;
		}

		return result;
	}

	
	/**
	 * Gets the lottery of date.
	 *
	 * @param date the date
	 * @param region the region
	 * @param province the province
	 * @return the lottery of date
	 * @throws SQLException the SQL exception
	 */
	public List<LotteryShow> getLotteryOfDate(String date, String region, String province) throws SQLException{
		List<LotteryShow> results=new ArrayList<>() ;
		if(province.equals("Tất cả")) province="";
		province = "%" + province + "%";
		date=new CheckInput().formDateToYMD(date);

		String sql = "SELECT lottery_id, special_prize, first_prize, second_prize, third_prize, fourth_prize, fifth_prize,"
				+ " sixth_prize, seventh_prize, eighth_prize, lottery_date, deleted,"
				+ " provinces.province_id as province_id,provinces.province as  province,provinces.region as region FROM lotteries_detail  "
				+ " join provinces on lotteries_detail.province_id=provinces.province_id\r\n"
				+ " where lottery_date=? and province like ?  and provinces.region = ?  and isDeleted=0 and deleted=0 ";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, date);
		stmt.setString(2, province);
		stmt.setString(3, region);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			String d = ""+ rs.getDate("lottery_date");
			d=new CheckInput().formDateToDMY(d);
			Lottery l = new Lottery();
			l.setLottery_id(rs.getInt("lottery_id"));
			l.setLottery_date(d);
			l.setProvince_id(rs.getInt("province_id"));
			l.setSpecial_prize(rs.getString("special_prize"));
			l.setFirst_prize(rs.getString("first_prize"));
			l.setSecond_prize(rs.getString("second_prize"));
			l.setThird_prize(rs.getString("third_prize"));
			l.setFourth_prize(rs.getString("fourth_prize"));
			l.setFifth_prize(rs.getString("fifth_prize"));
			l.setSixth_prize(rs.getString("sixth_prize"));
			l.setSeventh_prize(rs.getString("seventh_prize"));
			l.setEighth_prize(rs.getString("eighth_prize"));
			l.setDeleted(rs.getInt("deleted"));
			results.add(new LotteryShow(rs.getString("region"), rs.getString("province"), d, l,
					new CheckInput().getDayOfWeek(d)));
		}
		
		return results;
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
