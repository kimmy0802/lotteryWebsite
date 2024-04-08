/*
 * 
 */
package com.km.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.dao.AccountDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Cập nhật thông tin tài khoản User
 */
@WebServlet("/UpdateAccountController")
// Cập nhật tài khoản từ người dùng có quyền truy cập là user
public class UpdateAccountController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new update account controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Connection conn;
		try (PrintWriter out = response.getWriter()) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			
			Account account = (Account) session.getAttribute("account");
			// Nếu chưa đăng nhập
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);

			String action = request.getParameter("action");

			if (action != null && action.equals("updateAccount")) {
				request.setAttribute("fullname", account.getFullname());
				request.setAttribute("phonenumber", account.getPhonenumber());
				request.getRequestDispatcher("/view/user/updateAccount.jsp").forward(request, response);
				return;
			}
			if (action != null && action.equals("changePassword")) {
				request.getRequestDispatcher("/view/user/changePassword.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(UpdateAccountController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		try (PrintWriter out = response.getWriter()) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			conn = new DBContext().getConnection();
			AccountDAO accountDAO = new AccountDAO(conn);
			CheckInput checkInput = new CheckInput();

			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			Account account = (Account) session.getAttribute("account");
			// Nếu chưa đăng nhập
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);

			String action = request.getParameter("action");

			// Cập nhật thông tin cá nhân của tài khoản đang đăng nhập: account
			if (action != null && action.equals("updateAccount")) {
				String fullname = request.getParameter("fullname");
				String phonenumber = request.getParameter("phonenumber");
				String submit = request.getParameter("submit");

				if (submit != null && submit.equals("cancel")) {
					request.setAttribute("fullname", account.getFullname());
					request.setAttribute("phonenumber", account.getPhonenumber());
					request.getRequestDispatcher("/view/user/updateAccount.jsp").forward(request, response);
					return;
				}

				// Kiểm tra thông tin nhập vào
				String errorMess = "";
				if (fullname.equals("")) {
					errorMess = "Họ và tên không được để trống";
				} else {
					if (phonenumber.equals("")) {
						errorMess = "Số điện thoại không được để trống";
					} else {
						if (!checkInput.isTruePhonenumber(phonenumber)) {
							errorMess = "Số điện thoại không hợp lệ. Số điện thoại gồm 10 ký tự số.";
						}
					}
				}
				if (errorMess.length() != 0) {
					request.setAttribute("errorMess", errorMess);
					request.setAttribute("fullname", fullname);
					request.setAttribute("phonenumber", phonenumber);
					request.getRequestDispatcher("/view/user/updateAccount.jsp").forward(request, response);
					return;
				}

				account.setFullname(fullname);
				account.setPhonenumber(phonenumber);
				request.setAttribute("isError", 1);
				if (submit != null && submit.equals("update")) {
					accountDAO.updateAccount(account);
					request.setAttribute("isError", accountDAO.isError() ? 1 : 0);
				}
				if (!accountDAO.isError()) {
					request.setAttribute("account", account);
					session.setAttribute("account", account);
				}
				request.getRequestDispatcher("/view/user/updateAccount.jsp").forward(request, response);
				return;
			}

			// Thay đổi mật khẩu tài khoản đăng nhập
			if (action != null && action.equals("changePassword")) {
				Password passwordCheck = new Password();
				String curPassword = account.getPassword();
				String curPasswordRepeat = request.getParameter("curPasswordRepeat");
				String newPassword = request.getParameter("newPassword");
				String newPassWordRepeat = request.getParameter("newPasswordRepeat");
				String submit = request.getParameter("submit");
				if (submit != null && submit.equals("submit")) {
					String s = passwordCheck.getMd5(curPasswordRepeat);
					request.setAttribute("account", account);
					request.setAttribute("curPasswordRepeat", curPasswordRepeat);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("newPasswordRepeat", newPassWordRepeat);

					if (!curPassword.equals(s)) {
						request.setAttribute("errorMess", "Mật khẩu hiện tại vừa nhập không đúng!");
						request.getRequestDispatcher("/view/user/changePassword.jsp").forward(request, response);
						return;
					}

					if (newPassword.equals(curPasswordRepeat)) {
						request.setAttribute("errorMess", "Mật khẩu mới phải khác mật khẩu hiện tại!");
						request.getRequestDispatcher("/view/user/changePassword.jsp").forward(request, response);
						return;
					}
					if (!passwordCheck.checkPassword(newPassword)) {
						request.setAttribute("errorMess", "Mật khẩu mới gồm 8-20 ký tự, bao gồm số hoặc chữ!");
						request.getRequestDispatcher("/view/user/changePassword.jsp").forward(request, response);
						return;
					}

					if (!newPassword.equals(newPassWordRepeat)) {
						request.setAttribute("errorMess", "Mật khẩu mới nhập lại không giống nhau!");
						request.getRequestDispatcher("/view/user/changePassword.jsp").forward(request, response);
						return;
					}

					// lưu mật khẩu mới vào database
					accountDAO.changePassword(account.getUsername(), passwordCheck.getMd5(newPassword));
					response.getWriter().write("changepassword susscess");
					request.setAttribute("isError", accountDAO.isError() ? 1 : 0);
					if (!accountDAO.isError()) {
						account.setPassword(passwordCheck.getMd5(newPassword));
						session.setAttribute("account", account);
					}
					// xóa
					boolean check=false;
					Cookie[] cookies = request.getCookies();
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];					
						if(cookie.getName().equalsIgnoreCase("username")&&cookie.getValue().equalsIgnoreCase(account.getUsername())) {
							check=true;
							cookie.setMaxAge(0);
							response.addCookie(cookie);
						}
						if (check&&cookie.getName().compareTo("password") == 0) {
							cookie.setMaxAge(0);
							response.addCookie(cookie);
						}
					}
					Cookie usernameRmb = new Cookie("username", account.getUsername());
					usernameRmb.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(usernameRmb);
					Cookie passwordRmb = new Cookie("password", newPassword);
					passwordRmb.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(passwordRmb);
					request.setAttribute("account", account);
					request.setAttribute("curPasswordRepeat", "");
					request.setAttribute("newPassword", "");
					request.setAttribute("newPasswordRepeat", "");
					request.getRequestDispatcher("/view/user/changePassword.jsp").forward(request, response);

					return;
				}

				response.getWriter().write("changepassword cancel");
				request.setAttribute("account", account);
				request.setAttribute("curPasswordRepeat", "");
				request.setAttribute("newPassword", "");
				request.setAttribute("newPasswordRepeat", "");
				request.getRequestDispatcher("/view/user/changePassword.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(UpdateAccountController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
