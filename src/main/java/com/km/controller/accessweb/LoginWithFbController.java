/* ================================================
 * System name	:LotteryWeb
 * Version		:1.0
 * Created date	:25 Oct 2023
 * Description	:Created by Kimmy
 * Copyright (c) 2023 by tom.vn.
================================================ */

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

import com.restfb.types.User;

import com.km.model.facebook.*;
import com.km.database.DBContext;
import com.km.controller.admin.UpdateAccountsController;
import com.km.dao.AccountDAO;
import com.km.dao.HistoryAccountsAccessDAO;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng đăng nhập bằng Facebook.
 */
@WebServlet("/LoginWithFbController")
public class LoginWithFbController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new login with fb controller.
     */
    public LoginWithFbController() {
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
				return;
			} else {
				String accessToken = RestFB.getToken(code);
				User user = RestFB.getUserInfo(accessToken);
				
				AccountDAO accountDAO = new AccountDAO(conn);
				CheckInput checkInput = new CheckInput();
				Password passW = new Password();
				
				String username = user.getId()+"66666666666666666";
				String password = passW.getMd5(passW.createPassword());
				String fullname = user.getName();
				String phonenumber = "";
				String email = user.getEmail()==null?"":user.getEmail();
				String part = "user";
				String created_date = checkInput.getCurrentDate();
				int activate = 0;
				int deleted = 0;
				int locked=0;

				Account account = new Account(username, password, fullname, phonenumber, email, part, created_date,"facebook",
						activate, deleted,locked,0);

				if (!accountDAO.isAccount(username)) {
					accountDAO.createAccount(account);
				}
				accountDAO.setActivated(account.getUsername(), 1);
				// Lưu lịch sử đăng nhập
				HistoryAccountsAccessDAO historyAccountsAccessDAO = new HistoryAccountsAccessDAO(conn);
				historyAccountsAccessDAO.updateHistoryAccess(username, new CheckInput().getCurrentDate());
				accountDAO.setActivated(username, 1);
				
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
