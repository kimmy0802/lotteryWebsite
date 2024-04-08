/*
 * 
 */
package com.km.controller.website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.km.database.DBContext;
import com.km.dao.AccountDAO;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.Mail;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Đăng ký tài khoản mới.
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new register controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("searchLotteries", null);
		request.setAttribute("username", "");
		request.setAttribute("password", "");
		request.setAttribute("repassword", "");
		request.setAttribute("fullname", "");
		request.setAttribute("email", "");
		request.setAttribute("phonenumber", "");
		request.setAttribute("errorMess", "");
		request.getRequestDispatcher("/view/website/register.jsp").forward(request, response);

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("errorMess", "");
		Connection conn;
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			conn = new DBContext().getConnection();
			AccountDAO accountDAO = new AccountDAO(conn);
			// HttpSession session = request.getSession(true);
			CheckInput checkInput = new CheckInput();
			Password passwordCheck = new Password();
			Mail mail = new Mail();

			String action = request.getParameter("action");
			if (action != null && action.equals("doRegister")) {
				// collect data from a login form
				String username = request.getParameter("username");
				String fullname = request.getParameter("fullname");
				String email = request.getParameter("email");
				String phonenumber = request.getParameter("phonenumber");
				boolean checkCondition = true;

				if (!checkInput.isTrueUsername(username)) {
					request.setAttribute("errorMess",
							"Tên đăng nhập gồm 6-30 ký tự chữ và số, không phân biệt hoa thường.");
					checkCondition = false;
				} else {
					if (accountDAO.isAccount(username.toLowerCase())) {
						request.setAttribute("errorMess", "Tên đăng nhập đã tồn tại. Hãy thử tên khác!");
						checkCondition = false;
					} else {
						if (!checkInput.isTrueEmail(email)) {
							request.setAttribute("errorMess", "Email không hợp lệ.");
							checkCondition = false;
						} else {
							if (accountDAO.isEmail(email.toLowerCase())) {
								request.setAttribute("errorMess",
										"Email đã được sử dụng, vui lòng sử dụng email khác.");
								checkCondition = false;
							} else {
								if (fullname == null) {
									request.setAttribute("errorMess", "Không để trống họ và tên.");
									checkCondition = false;
								} else {
									if (phonenumber == null || !checkInput.isTruePhonenumber(phonenumber)) {
										request.setAttribute("errorMess", "Số điện thoại không hợp lệ.");
										checkCondition = false;
									}
								}
							}
						}
					}
				}

				System.out.println("username: " + username);
				System.out.println("fullname: " + fullname);
				System.out.println("email: " + email);
				System.out.println("phonenumber: " + phonenumber);
				if (!checkCondition) {
					request.setAttribute("username", username);
					request.setAttribute("fullname", fullname);
					request.setAttribute("email", email);
					request.setAttribute("phonenumber", phonenumber);
					request.getRequestDispatcher("/view/website/register.jsp").forward(request, response);
					return;
				}

				// Thông tin đăng ký hợp lệ
				System.out.println("register A account");
				String password = passwordCheck.createPassword();
				System.out.println("new Password:" + password);
				Account a = new Account(username.toLowerCase(), password, fullname, phonenumber, email.toLowerCase(),
						"user", checkInput.getCurrentDate(), "system", 0, 0, 0, 0);
				a.setPassword(passwordCheck.getMd5(password));
				accountDAO.createAccount(a);
				Boolean sendEmailSucc = mail.sendMail(email.toLowerCase(), password);
				if (!sendEmailSucc || accountDAO.isError()) {
					accountDAO.deleteAccount(username);
					request.setAttribute("success", 0);
				} else {
					request.setAttribute("success", 1);
				}
				System.out.println("password" + password);
				System.out.println("sen email: " + sendEmailSucc);
				System.out.println("isError: " + accountDAO.isError());
				request.getRequestDispatcher("/view/website/register.jsp").forward(request, response);
				return;
			}
			System.out.println("register fail");
			request.getRequestDispatcher("/view/website/register.jsp").forward(request, response);

		} catch (Exception ex) {
			response.getWriter().println(ex);
		}

	}

}
