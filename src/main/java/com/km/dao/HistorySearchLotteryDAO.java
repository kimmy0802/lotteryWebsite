package com.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.km.model.HistorySearch;

// TODO: Auto-generated Javadoc
/**
 * Class chứa các phương thước làm việc với bảng HISTORY_SEARCH_LOTTERY trong database.
 */
public class HistorySearchLotteryDAO {
	
	/** The conn. */
	private Connection conn;

	/**
	 * Instantiates a new history search lottery DAO.
	 */
	public HistorySearchLotteryDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new history search lottery DAO.
	 *
	 * @param conn the conn
	 */
	public HistorySearchLotteryDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Creates new history search.
	 *
	 * @param historySearch the history search
	 */
	public void create(HistorySearch historySearch) {
		String sql = "insert into HISTORY_SEARCH_LOTTERY(username,province_id,lottery_number,lottery_date,search_time,result_search) values(?,?,?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,historySearch.getUsername());
			stmt.setInt(2, historySearch.getProvince_id());
			stmt.setString(3, historySearch.getLottery_number());
			stmt.setString(4, historySearch.getLottery_date());
			stmt.setString(5, historySearch.getSearch_time());
			stmt.setString(6, historySearch.getResultSearch());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the history search.
	 *
	 * @param username the username
	 * @param page the page
	 * @return the history search
	 */
	public List<HistorySearch> getHistorySearch(String username, int page) {
		page=page*10-10;
		List<HistorySearch> results = new ArrayList<HistorySearch>();
		String sql = "select acces_id, username, history_search_lottery.province_id as province_id, lottery_number,  history_search_lottery.lottery_date as lottery_date, search_time, result_search from HISTORY_SEARCH_LOTTERY "
				+ " join lotterywebsite.lotteries_detail "
				+ " on history_search_lottery.province_id=lotteries_detail.province_id and history_search_lottery.lottery_date=lotteries_detail.lottery_date "
				+ "  where username=? and deleted=0 order by search_time desc limit ?,10;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setInt(2, page);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Date d = (Date) rs.getDate("lottery_date");
				String t=""+rs.getDate("search_time")+" "+rs.getTime("search_time");
				HistorySearch h = new HistorySearch(rs.getString("username"), rs.getInt("province_id"),
						rs.getString("lottery_number"), d + "",t,rs.getString("result_search"));
				if (h.getLottery_number() != null) {
					results.add(h);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;

	}

	/**
	 * Gets the history search.
	 *
	 * @param page the page
	 * @return the history search
	 */
	public List<HistorySearch> getHistorySearch(int page) {
		page=page*10-10;
		List<HistorySearch> results = new ArrayList<HistorySearch>();
		String sql = "select * from HISTORY_SEARCH_LOTTERY order by search_time desc limit ?,10;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, page);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Date d = (Date) rs.getDate("lottery_date");
				String t=""+rs.getDate("search_time")+" "+rs.getTime("search_time");
				HistorySearch h = new HistorySearch(rs.getString("username"), rs.getInt("province_id"),
						rs.getString("lottery_number"), d + "",t,rs.getString("result_search"));
				if (h.getLottery_number() != null) {
					results.add(h);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;

	}
	
	/**
	 * Gets the pages.
	 *
	 * @param username the username
	 * @return the pages
	 */
	public int getPages(String username) {
		int result=0;
		String sql =  "select count(*) as c  from HISTORY_SEARCH_LOTTERY "
				+ " join lotterywebsite.lotteries_detail "
				+ " on history_search_lottery.province_id=lotteries_detail.province_id and history_search_lottery.lottery_date=lotteries_detail.lottery_date "
				+ "  where username=? and deleted=0;";

				;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				 result=rs.getInt("c");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result=result % 10 == 0 ? result / 10 : result / 10 + 1;
		return result;
	}
	
	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public int getPages() {
		int result=0;
		String sql = "select count(*) as c from HISTORY_SEARCH_LOTTERY "
					+ " join lotterywebsite.lotteries_detail "
					+ " on history_search_lottery.province_id=lotteries_detail.province_id and history_search_lottery.lottery_date=lotteries_detail.lottery_date "
					+ "  where deleted=0;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result=rs.getInt("c");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result=result % 10 == 0 ? result / 10 : result / 10 + 1;
		return result;
	}

}
