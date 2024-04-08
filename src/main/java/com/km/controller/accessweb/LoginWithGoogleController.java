package com.km.controller.accessweb;

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

import com.km.model.google.*;
import com.km.database.DBContext;
import com.km.controller.admin.UpdateAccountsController;
import com.km.dao.AccountDAO;
import com.km.dao.HistoryAccountsAccessDAO;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng đăng nhập bằng tài khoản Google.
 */
@WebServlet("/LoginWithGoogleController")
public class LoginWithGoogleController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new login with google controller.
     */
    public LoginWithGoogleController() {
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
		Connection conn;
		try (PrintWriter out = response.getWriter()) {
			conn = new DBContext().getConnection();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession(true);
			String code = request.getParameter("code");
			request.setAttribute("username", "");
			request.setAttribute("password", "");
			request.setAttribute("rememberme", "");
			request.setAttribute("errorMess", "");
			
			if (code == null || code.isEmpty()) {
				request.getRequestDispatcher("login.jsp").forward(request, response);

			} else {
				/*
				 * Kết nối với tài khoản Google thành công
				 */
				String accessToken = GoogleUtils.getToken(code);
				GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
				AccountDAO accountDAO = new AccountDAO(conn);
				CheckInput checkInput = new CheckInput();
				Password passW = new Password();
				
				String username = googlePojo.getId()+"55555555555";
				String password = passW.getMd5(passW.createPassword());
				String email = googlePojo.getEmail();
				String fullname = googlePojo.getName();
				if (fullname == null)
					fullname = email.substring(0, 4);
				String phonenumber = "";
				String part = "user";
				String created_date = checkInput.getCurrentDate();
				int activate = 0;
				int deleted = 0;
				int locked=0;
				Account account=new Account();
				// nếu email đã được sử dụng để đăng ký tài khoản ->> sử dụng tài khoản đó để đăng nhập
				if(accountDAO.isEmail(email))
				{
					account=accountDAO.getAccountByEmail(email);
					
				}else {
					account = new Account(username, password, fullname, phonenumber, email, part, created_date,"google",
							activate, deleted,locked,0);

					if (!accountDAO.isAccount(username)) {
						accountDAO.createAccount(account);
					}
				}
				
				accountDAO.setActivated(account.getUsername(), 1);
				// Lưu lịch sử đăng nhập
				HistoryAccountsAccessDAO historyAccountsAccessDAO = new HistoryAccountsAccessDAO(conn);
				historyAccountsAccessDAO.updateHistoryAccess(account.getUsername(), new CheckInput().getCurrentDate());
				
				session.setAttribute("account", account);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(UpdateAccountsController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
