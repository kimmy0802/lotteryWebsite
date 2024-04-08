package com.km.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.dao.AccountDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.Mail;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Khôi phục tài khoản người dùng.
 */
@WebServlet("/ResetAccountForUserController")
public class ResetAccountForUserController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new reset account for user controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetAccountForUserController() {
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
		try (PrintWriter out = response.getWriter()) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			// Phân quyền tài khoản
			Account account = (Account) session.getAttribute("account");
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if (account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);

			request.setAttribute("username", "");
			request.setAttribute("email", "");
			response.getWriter().write("action get: UpdateAccounts");
			request.getRequestDispatcher("/view/admin/resetAccountForUser.jsp").forward(request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(UpdateAccountsController.class.getName()).log(Level.SEVERE, null, e);
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

			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			Account account = (Account) session.getAttribute("account");
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if (account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);

			conn = new DBContext().getConnection();
			AccountDAO accountDAO=new AccountDAO(conn);
			Password checkPassword=new Password();
			
			String action = request.getParameter("action");
			if (action != null && action.equals("resetAccount")) {
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				
				if(!accountDAO.isAccount(username)||!accountDAO.isEmail(email)) {
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("errorMess", "Không tồn tài Tên đăng nhập hoặc email trong hệ thống ");
					request.getRequestDispatcher("/view/admin/resetAccountForUser.jsp").forward(request, response);
					return;
				}
				Account accountReset=accountDAO.getAccount(username);
				if(!accountReset.getEmail().equalsIgnoreCase(email)) {
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("errorMess", "Tên đăng nhập và Email không đúng. ");
					request.getRequestDispatcher("/view/admin/resetAccountForUser.jsp").forward(request, response);
					return;
				}
				if(accountReset.getLocked()==0) {
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("errorMess", "Tài khoản không bị khóa.");
					request.getRequestDispatcher("/view/admin/resetAccountForUser.jsp").forward(request, response);
					return;
				}
				if(!accountReset.getPart().equals("user")&&account.getPart().equals("admin")) {
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("errorMess", "Bạn không có quyền khôi phục tài khoản này.");
					request.getRequestDispatcher("/view/admin/resetAccountForUser.jsp").forward(request, response);
					return;
				}
				
				// Tạo mật khẩu mới
				String password = checkPassword.createPassword();
				System.out.println("password: " + password);
				// Gửi mật khẩu mới cho người dùng qua email
				Mail mail = new Mail();
				boolean sendSucc= mail.sendUnlockAccountkMail(email, password);
				accountDAO.setError(true);
				if (sendSucc) {
					// Mã hóa mật khẩu mới (MD5) và lưu vào CSDL
					String newPasswordMD5 = checkPassword.getMd5(password);
					accountDAO.unlockAccount(username);
					accountDAO.changePassword(username, newPasswordMD5);
				}
				request.setAttribute("isError", accountDAO.isError() ? 1 : 0);		
				request.getRequestDispatcher("/view/admin/resetAccountForUser.jsp").forward(request, response);
				return;
			}
			response.sendRedirect(request.getContextPath() + "/view/admin/resetAccountForUser.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(UpdateAccountsController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
