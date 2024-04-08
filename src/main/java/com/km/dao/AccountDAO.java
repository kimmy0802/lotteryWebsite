package com.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.km.model.Account;

// TODO: Auto-generated Javadoc
/**
 * Class chứa các phương thước làm việc với bảng Accounts trong database.
 */
public class AccountDAO {

	/** The conn. */
	private Connection conn;

	/** The is error. */
	private boolean isError = false; // true/1: is error; false/0: correct

	/**
	 * Instantiates a new account DAO.
	 */
	public AccountDAO() {
		this.conn = null;
	}

	/**
	 * Instantiates a new account DAO.
	 *
	 * @param conn the conn
	 */
	public AccountDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Phương thức Lấy số lần nhập sai mật khẩu liên tiếp *.
	 *
	 * @param username the username
	 * @return the login fail
	 * @throws SQLException the SQL exception
	 */
	public int getLoginFail(String username) throws SQLException {
		int result = 6;
		String sql = "select * from accounts where username=? and deleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			result = rs.getInt("loginFail");
		}
		return result;
	}

	/**
	 * phương thức đặt số lần nhập sai mật khẩu liên tiếp.
	 *
	 * @param username  the username
	 * @param loginFail the login fail
	 * @throws SQLException the SQL exception
	 */
	public void setLoginFail(String username, int loginFail) throws SQLException {
		if (!isAccount(username)) {
			isError = true;
			return;
		}
		String sql = "update accounts set loginFail=? where username=?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, loginFail);
			stmt.setString(2, username);
			stmt.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}
	}

	/**
	 * Phương thức kiểm tra tài khoản đã bị khóa do nhập sai MK liên tiếp quá 5 lần
	 * *.
	 *
	 * @param username the username
	 * @return true, if is locked account
	 * @throws SQLException the SQL exception
	 */
	public boolean isLockedAccount(String username) throws SQLException {
		String sql = "select * from accounts where username=? and deleted=0 and locked=1";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Phương thức Kiểm tra có tồn tại username chưa bị xóa trong hệ thống *.
	 *
	 * @param username the username
	 * @return true, nếu tồn tại
	 * @throws SQLException the SQL exception
	 */
	public boolean isAccount(String username) throws SQLException {
		String sql = "select * from accounts where username=? and deleted=0";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Phương thức Kiểm tra có tồn tại email đã được sử dụng nếu tài khoản chưa bị
	 * xóa trong hệ thống *.
	 *
	 * @param email the email
	 * @return true, if is email
	 * @throws SQLException the SQL exception
	 */
	public boolean isEmail(String email) throws SQLException {
		String sql = "select * from accounts where email=? and deleted=0";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Lưu 1 tài khoản mới vào DB.
	 *
	 * @param a the a(account)
	 * @throws SQLException the SQL exception
	 */
	public void createAccount(Account a) throws SQLException {
		if (isAccount(a.getUsername())) {
			isError = true;
			return;
		}
		String sql = "insert into accounts(username,password,fullname,phonenumber,email,activated,created_date,created_by,part,deleted,locked) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getUsername());
			stmt.setString(2, a.getPassword());
			stmt.setString(3, a.getFullname());
			stmt.setString(4, a.getPhonenumber());
			stmt.setString(5, a.getEmail());
			stmt.setInt(6, a.getActivate());
			stmt.setString(7, a.getCreated_date());
			stmt.setString(8, a.getCreated_by());
			stmt.setString(9, a.getPart());
			stmt.setInt(10, 0);
			stmt.setInt(11, 0);

			stmt.executeUpdate();
			stmt.close();
			isError = false;
		} catch (SQLException e) {
			isError = true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Cập nhật thông tin 1 tài khoản.
	 *
	 * @param a the a
	 * @throws SQLException the SQL exception
	 */
	public void updateAccount(Account a) throws SQLException {
		if (!isAccount(a.getUsername())) {
			isError = true;
			return;
		}
		String sql = "update accounts set fullname=?, phonenumber=?,email=?,part=? where username=?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getFullname());
			stmt.setString(2, a.getPhonenumber());
			stmt.setString(3, a.getEmail());
			stmt.setString(4, a.getPart());
			stmt.setString(5, a.getUsername());
			stmt.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}
	}

	/**
	 * Khóa tài khoản.
	 *
	 * @param username the username
	 * @throws SQLException the SQL exception
	 */

	// Khóa tài khoản
	public void lockAccount(String username) throws SQLException {
		if (!isAccount(username)) {
			isError = true;
			return;
		}
		String sql = "update accounts set locked=1 where username=?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}
	}

	/**
	 * Khôi phục tài khoản *.
	 *
	 * @param username the username
	 * @throws SQLException the SQL exception
	 */

	public void unlockAccount(String username) throws SQLException {
		if (!isAccount(username)) {
			isError = true;
			return;
		}
		String sql = "update accounts set locked=0 where username=?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}
	}

	/**
	 * set value of activated of account to 0 when logout or admin deletes a account.
	 *
	 * @param username the username
	 * @param activate the activate
	 */
	public void setActivated(String username, int activate) {
		String sql = "update accounts set activated=? where username=? and deleted=0;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, activate);
			stmt.setString(2, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the activated.
	 *
	 * @param username the username
	 * @return the activated
	 * @throws SQLException the SQL exception
	 */
	public int getActivated(String username) throws SQLException {
		String s = "select * from accounts where username=? and deleted=0;";
		PreparedStatement stm = conn.prepareStatement(s);
		stm.setString(1, username);
		ResultSet r = stm.executeQuery();
		int activated = 1;
		if (r.next()) {
			activated = r.getInt("activated");
		}
		return activated;
	}

	/**
	 * Kiểm tra quyền Admin của tài khoản.
	 *
	 * @param username the username
	 * @return true, if is admin
	 */
	public boolean isAdmin(String username) {
		String result = "";
		try {
			String s = "select * from accounts WHERE username =? and deleted=0";
			PreparedStatement stm = conn.prepareStatement(s);
			stm.setString(1, username);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				result = rs.getString("part");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.equals("admin");
	}

	/**
	 * Delete an account.
	 *
	 * @param username the username
	 */
	public void deleteAccount(String username) {
		try {
			// Kiểm tra tk có đang hoạt động không? online --> offline
			if (getActivated(username) == 1) {
				setActivated(username, 0);
				isError = true;
				System.out.println("xoa " + username + " khong thanh cong");
				return;
			}

			String sql = "update accounts set deleted=1 where username=?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			System.out.println("xoa " + username + " khong thanh cong");
			e.printStackTrace();
		}

	}

	/**
	 * Delete accounts.	 *
	 * @param accountsList the accounts list
	 */
	public void deleteAccount(List<String> accountsList) {
		isError = false;
		for (String i : accountsList) {
			try {
				if (getActivated(i) == 1) {
					setActivated(i, 0);
					isError = true;
					System.out.println("xoa " + i + " khong thanh cong");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(i + " ");
				e.printStackTrace();
			}
		}
		if (isError)
			return;

		for (String i : accountsList) {
			deleteAccount(i);
		}
	}

	/**
	 * Change password.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public void changePassword(String username, String password) {
		String sql = "update accounts set password=? where username=? and deleted=0;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, username);
			stmt.executeUpdate();
			isError = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isError = true;
			e.printStackTrace();
		}
	}

	/**
	 * Gets the email.
	 *
	 * @param username the username
	 * @return the email
	 */
	public String getEmail(String username) {
		String result = new String();
		try {
			String sql = "select * from accounts where username=? and deleted=0;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				result = rs.getString("email");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Gets the account by email.
	 *
	 * @param email the email
	 * @return the account by email
	 */
	public Account getAccountByEmail(String email) {
		Account result = new Account();
		try {
			String sql = "select * from accounts where email=? and deleted=0;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result.setUsername(rs.getString("username"));
				result.setFullname(rs.getString("fullname"));
				result.setPassword(rs.getString("password"));
				result.setPart(rs.getString("part"));
				result.setPhonenumber(rs.getString("phonenumber"));
				result.setEmail(rs.getString("email"));
				result.setActivate(rs.getInt("activated"));
				result.setCreated_date(rs.getString("created_date"));
				result.setCreated_by(rs.getString("created_by"));
				result.setLoginFail(rs.getInt("loginFail"));
				result.setLocked(rs.getInt("locked"));
				result.setDeleted(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Gets the account by username	 *.
	 *
	 * @param username the username
	 * @return the account
	 */
	public Account getAccount(String username) {
		Account result = new Account();
		try {
			String sql = "select * from accounts where username=? and deleted=0;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result.setUsername(username);
				result.setFullname(rs.getString("fullname"));
				result.setPassword(rs.getString("password"));
				result.setPart(rs.getString("part"));
				result.setPhonenumber(rs.getString("phonenumber"));
				result.setEmail(rs.getString("email"));
				result.setActivate(rs.getInt("activated"));
				result.setCreated_date(rs.getString("created_date"));
				result.setCreated_by(rs.getString("created_by"));
				result.setLoginFail(rs.getInt("loginFail"));
				result.setLocked(rs.getInt("locked"));
				result.setDeleted(0);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Gets all accounts in DB.
	 * @return the accounts
	 * @throws SQLException the SQL exception
	 */
	public List<Account> getAccounts() throws SQLException {
		List<Account> accounts = new ArrayList<>();
		String sql = "select * from accounts where deleted=0;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Account a = new Account();
			a.setUsername(rs.getString("username"));
			a.setPassword(rs.getString("password"));
			a.setEmail(rs.getString("email"));
			a.setFullname(rs.getString("fullname"));
			a.setPhonenumber(rs.getString("phonenumber"));
			a.setPart(rs.getString("part"));
			a.setCreated_date(rs.getString("created_date"));
			a.setActivate(rs.getInt("activated"));
			a.setCreated_by(rs.getString("created_by"));
			a.setLoginFail(rs.getInt("loginFail"));
			a.setLocked(rs.getInt("locked"));
			a.setDeleted(0);	
			accounts.add(a);
		}
		stmt.close();
		return accounts;
	}

	/**
	 * Gets the accounts: search accounts list.
	 *
	 * @param search the search
	 * @param col    the col
	 * @param order  the order
	 * @param page   the page
	 * @return the accounts
	 * @throws SQLException the SQL exception
	 */
	public List<Account> getAccounts(String search, String col, String order, int page) throws SQLException {
		List<Account> accounts = new ArrayList<>();
		if (search == null)
			search = "";
		String sql = "select * from accounts where (username like '%" + search + "%' or fullname like '%" + search
				+ "%' or phonenumber like '%" + search + "%' or email like '%" + search + "%' or activated like '%"
				+ search + "%' or created_date like '%" + search + "%' or part like '%" + search
				+ "%' or created_by like '%" + search + "%') and deleted=0 ";
		sql += " order by " + col + " " + order + " limit ?,10";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, page);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Account a = new Account();
			a.setUsername(rs.getString("username"));
			a.setPassword(rs.getString("password"));
			a.setEmail(rs.getString("email"));
			a.setFullname(rs.getString("fullname"));
			a.setPhonenumber(rs.getString("phonenumber"));
			a.setPart(rs.getString("part"));
			a.setCreated_date(rs.getString("created_date"));
			a.setActivate(rs.getInt("activated"));
			a.setCreated_by(rs.getString("created_by"));
			a.setLoginFail(rs.getInt("loginFail"));
			a.setLocked(rs.getInt("locked"));
			a.setDeleted(0);	
			accounts.add(a);
		}
		stmt.close();
		return accounts;
	}

	/**
	 * Gets the accounts pages.
	 *
	 * @param search the search
	 * @return the accounts pages
	 * @throws SQLException the SQL exception
	 */
	public int getAccountsPages(String search) throws SQLException {
		if (search == null)
			search = "";
		int result = 0;
		String sql = "SELECT count(username) as c FROM accounts where (username like '%" + search
				+ "%' or fullname like '%" + search + "%' or phonenumber like '%" + search + "%' or email like '%"
				+ search + "%' or activated like '%" + search + "%' or created_date like '%" + search
				+ "%' or part like '%" + search + "%' or created_by like '%" + search + "%') and deleted=0 ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			result = rs.getInt("c");
		}
		result = result % 10 == 0 ? result / 10 : result / 10 + 1;
		return result;
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
