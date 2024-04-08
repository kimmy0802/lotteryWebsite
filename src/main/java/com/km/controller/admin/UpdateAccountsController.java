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
import com.km.model.CheckInput;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Admin Cập nhật thông tài khoản, phân quyền cho người dùng.
 */
@WebServlet("/UpdateAccountsController")
public class UpdateAccountsController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new update accounts controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAccountsController() {
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
		Connection conn;
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

			conn = new DBContext().getConnection();

			String action = request.getParameter("action");
				if (action != null && action.equals("updateFromAdmin")) {
					String username = request.getParameter("username");

					Account accountUpadte = new AccountDAO(conn).getAccount(username);
					if (accountUpadte == null) {
						response.sendRedirect(request.getContextPath() + "/AccountsList");
						return;
					}
					request.setAttribute("accountUpdate", accountUpadte);
					response.getWriter().write("action get: UpdateAccounts");
					request.getRequestDispatcher("/view/admin/admin_accounts_update.jsp").forward(request, response);
					return;
				}

			request.getRequestDispatcher("index.jsp").forward(request,response);
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

			conn = new DBContext().getConnection();
			AccountDAO accountDAO = new AccountDAO(conn);
			CheckInput checkInput = new CheckInput();

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

			String action = request.getParameter("action");

			// Admin Cập nhật thông tin tài khoản {
			if (action != null && action.equals("updateFromAdmin")) {

				String username = request.getParameter("username");

				Account accountUpdate = accountDAO.getAccount(username);
				String fullname = request.getParameter("fullname");
				String phonenumber = request.getParameter("phonenumber");
				String email = request.getParameter("email");
				String part = request.getParameter("part");
				String submit = request.getParameter("submit");

				request.setAttribute("accountUpdate", accountUpdate);
				if (submit != null && submit.equals("cancel")) {
					request.getRequestDispatcher("/view/admin/admin_accounts_update.jsp").forward(request, response);
					return;
				}
				// Kiểm tra thông tin nhập vào
				if (fullname.equals("")) {
					request.setAttribute("errorMess", "Họ và tên không được để trống");
					request.getRequestDispatcher("/view/admin/admin_accounts_update.jsp").forward(request, response);
					return;
				}
				if (phonenumber.equals("")) {
					request.setAttribute("errorMess", "Số điện thoại không được để trống và chỉ gồm số");
					request.getRequestDispatcher("/view/admin/admin_accounts_update.jsp").forward(request, response);
					return;
				}
				if (email.equals("") || !checkInput.isTrueEmail(email)) {
					request.setAttribute("errorMess", "Email không được để trống hoặc không hợp lệ");
					request.getRequestDispatcher("/view/admin/admin_accounts_update.jsp").forward(request, response);
					return;
				}
				// cập nhật thông tin
				accountUpdate.setFullname(fullname);
				accountUpdate.setPhonenumber(phonenumber);
				accountUpdate.setEmail(email);
				accountUpdate.setPart(part);
				request.setAttribute("isError", 3);
				if (submit != null && submit.equals("update")) {
					accountDAO.updateAccount(accountUpdate);
					request.setAttribute("isError", accountDAO.isError() ? 1 : 0);
				}
				request.setAttribute("username", username);
				//request.getRequestDispatcher("alert/alertUpdateAccount.jsp").forward(request, response);
				request.getRequestDispatcher("/view/admin/admin_accounts_update.jsp").forward(request, response);
				
				return;
			}
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(UpdateAccountsController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
