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
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.km.database.DBContext;
import com.km.dao.AccountDAO;
import com.km.model.Account;
import com.km.model.Mail;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng reset mật khẩu tài khoản người dùng.
 */
@WebServlet("/ResetPasswordFromAdminController")
public class ResetPasswordFromAdminController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new reset password from admin controller.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordFromAdminController() {
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
		Connection conn; 
		try (PrintWriter out = response.getWriter()) {

			conn = new DBContext().getConnection();
			HttpSession session=request.getSession(true);
			session.setAttribute("searchLotteries", null);		
			// Phân quyền tài khoản
			Account account = (Account) session.getAttribute("account");
			if(account==null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if(account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);
			
			String action=request.getParameter("action");
		
			if(action!=null&&action.equals("resetPassword")) {
				String username=request.getParameter("username");
				AccountDAO accountDAO=new AccountDAO(conn);
				String emailUser=accountDAO.getEmail(username);
				// Tạo mật khẩu mới
				Password password=new Password();
				String newPassword=password.createPassword();
				
				// Gửi mật khẩu mới cho người dùng qua email
				Mail mail=new Mail();
				boolean sendSucc=mail.sendMail(emailUser, newPassword);
				accountDAO.setError(false);
				if(sendSucc) {
					// Mã hóa mật khẩu mới (MD5) và lưu vào CSDL
					String newPasswordMD5 =password.getMd5(newPassword);
					accountDAO.changePassword(username, newPasswordMD5);					
				}
				request.setAttribute("resetPasswordisError",accountDAO.isError()?1:0);
				request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);

				return;
			}
			response.getWriter().write("action get: " +  " reset password");
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}	
	}

}
