package com.km.controller.website;

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

import com.km.controller.admin.AccountsListController;
import com.km.dao.AccountDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.Mail;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Quên mật khẩu.
 */
@WebServlet("/ForgetPasswordController")
public class ForgetPasswordController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new forget password controller.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPasswordController() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			request.getRequestDispatcher("/view/website/forgetPassword.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			conn = new DBContext().getConnection();
			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			
			AccountDAO accountDAO = new AccountDAO(conn);
			Password passwordCheck = new Password();
			String action = request.getParameter("action");
			

			if (action != null && action.equals("resetPassword")) {			
				String emailInput=request.getParameter("email");
				System.out.println("emailInput:"+emailInput);
				emailInput=emailInput.trim();
				if(emailInput.length()==0) {
					request.setAttribute("errorMess", "Vui lòng nhập  Email!");
					request.getRequestDispatcher("/view/website/forgetPassword.jsp").forward(request, response);			
					return;
				}
				
				if(!accountDAO.isEmail(emailInput)) {
					request.setAttribute("email", emailInput);
					request.setAttribute("errorMess", "Email này chưa đăng ký!");
					request.getRequestDispatcher("/view/website/forgetPassword.jsp").forward(request, response);			
					return;
				}
				Account account=accountDAO.getAccountByEmail(emailInput);
				if(accountDAO.isLockedAccount(account.getUsername())) {
					request.setAttribute("email", emailInput);
					request.setAttribute("errorMess", "Tài khoản này đã bị khóa để lấy lại Tài khoản, vui lòng liên hệ qua Emai: xosokienthiet.tailoc@gmail.com");
					request.getRequestDispatcher("/view/website/forgetPassword.jsp").forward(request, response);			
					return;
				}
				// Tạo mật khẩu mới
				String password = passwordCheck.createPassword();
				// Gửi mật khẩu mới cho người dùng qua email
				Mail mail = new Mail();
				boolean sendSucc = mail.sendMail(emailInput, password);
				accountDAO.setError(true);
				if (sendSucc) {
					// Mã hóa mật khẩu mới (MD5) và lưu vào CSDL
					String newPasswordMD5 = passwordCheck.getMd5(password);
					account=accountDAO.getAccountByEmail(emailInput);
					accountDAO.changePassword(account.getUsername(), newPasswordMD5);
				}
				request.setAttribute("isError", accountDAO.isError() ? 1 : 0);				
				request.getRequestDispatcher("/view/website/forgetPassword.jsp").forward(request, response);			
				return;
			}
			 request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
