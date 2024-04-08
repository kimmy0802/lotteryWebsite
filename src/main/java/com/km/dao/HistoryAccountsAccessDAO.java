package com.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.km.model.HistoryAccess;

// TODO: Auto-generated Javadoc
/**
 * Class chứa các phương thước làm việc với bảng HISTORY_ACCOUNTS_ACCESS trong database.
 */
public class HistoryAccountsAccessDAO {
	
	/** The conn. */
	private Connection conn;

	/**
	 * Instantiates a new history accounts access DAO.
	 */
	public HistoryAccountsAccessDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new history accounts access DAO.
	 *
	 * @param conn the conn
	 */
	public HistoryAccountsAccessDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Update history access when user search lottery.
	 *
	 * @param username the username
	 * @param access_date the access date
	 */
	public void updateHistoryAccess(String username, String access_date) {
		String sql = "insert into HISTORY_ACCOUNTS_ACCESS(username,access_date) values(?,?);";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, access_date);
			
			//System.out.println(stmt.toString());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("update history access fail");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the history access by access date.
	 *
	 * @param access_date the access date
	 * @param page the page
	 * @return the history access
	 */
	public List<HistoryAccess> getHistoryAccess(String access_date, int page) {
		page = page * 10 - 10;
		List<HistoryAccess> results = new ArrayList<HistoryAccess>();
		String sql = "select * from HISTORY_ACCOUNTS_ACCESS where access_date=? limit ?,10;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, access_date);
			stmt.setInt(2, page);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Date d = (Date) rs.getDate("access_date");
				HistoryAccess h = new HistoryAccess(rs.getString("username"), d + "");
				results.add(h);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;

	}

	/**
	 * Gets the history access.
	 *
	 * @param page the page
	 * @return the history access
	 */
	public List<HistoryAccess> getHistoryAccess(int page) {
		page = page * 10 - 10;
		List<HistoryAccess> results = new ArrayList<HistoryAccess>();
		String sql = "select * from HISTORY_ACCOUNTS_ACCESS order by access_date desc limit ?,10;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, page);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Date d = (Date) rs.getDate("access_date");
				HistoryAccess h = new HistoryAccess(rs.getString("username"), d + "");
				results.add(h);
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
	 * @param access_date the access date
	 * @return the pages
	 */
	public int getPages(String access_date) {
		int result = 0;
		String sql = "select count(*) as c from HISTORY_ACCOUNTS_ACCESS where access_date=?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, access_date);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("c");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = result % 10 == 0 ? result / 10 : result / 10 + 1;
		return result;
	}

	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public int getPages() {
		int result = 0;
		String sql = "select count(*) as c from HISTORY_ACCOUNTS_ACCESS;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("c");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = result % 10 == 0 ? result / 10 : result / 10 + 1;
		return result;
	}

}
