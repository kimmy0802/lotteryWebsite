/*
 * 
 */
package com.km.controller.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.database.DBContext;
import com.km.dao.AccountDAO;
import com.km.model.Account;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Đăng xuất tài khoản User
 */
public class LogoutController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new logout controller.
     *
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
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
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = new DBContext().getConnection();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			AccountDAO accountDAO = new AccountDAO(conn);
		
			HttpSession session = request.getSession(true);
			Account account=(Account) session.getAttribute("account");
			// Nếu đã đăng nhập
			if(account!=null) {
				accountDAO.setActivated(account.getUsername(), 0);
			}
			
			session.setAttribute("error", "");
			session.setAttribute("account",null);
			session.setAttribute("searchLotteries", null);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
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
		// TODO Auto-generated method stub
		
	}

}
