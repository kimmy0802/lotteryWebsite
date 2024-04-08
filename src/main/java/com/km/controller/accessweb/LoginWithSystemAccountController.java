package com.km.controller.accessweb;

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

import com.km.controller.admin.UpdateAccountsController;
import com.km.dao.AccountDAO;
import com.km.dao.HistoryAccountsAccessDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.Password;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng đăng nhập bằng tài khoản đã đăng ký trước đó.
 */
@WebServlet("/LoginWithSystemAccountController")
public class LoginWithSystemAccountController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
   
    /**
     * Instantiates a new login with system account controller.
     */
    public LoginWithSystemAccountController() {
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
		//Connection conn;
		try (PrintWriter out = response.getWriter()) {
			//conn = new DBContext().getConnection();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
	        Cookie[] cookies =  request.getCookies();	 	 
	        String username="";
	        String password="";	
	        		
	        if (cookies != null) {	
	        	for(int i=0; i<cookies.length; i++) {
	        		if(cookies[i].getName().compareTo("username")==0)username=cookies[i].getValue();
	        		if(cookies[i].getName().compareTo("password")==0)password=cookies[i].getValue();
	        	}
	        } 			

			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("rememberme", "");
			request.setAttribute("errorMess", "");

			request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = new DBContext().getConnection();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			AccountDAO accountDAO = new AccountDAO(conn);
			Password passwordCheck = new Password();
			HttpSession session = request.getSession(true);
			

			String action = request.getParameter("action");
			if (action != null && action.equals("login")) {
				// collect data from a login form
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String rbm = request.getParameter("rememberme");

				// check input: username, password
				username = username.trim();
				String username2=username.toLowerCase();
				if(username.length()==0) {
					request.setAttribute("errorMess", "Tên đăng nhập không để trống.");
					request.setAttribute("username", "");
					request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);
					return;
				}

				if (!accountDAO.isAccount(username2)) {
					request.setAttribute("errorMess", "Tên đăng nhập không tồn tại.");
					request.setAttribute("username", username);
					request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);
					return;
				}
				
				if(accountDAO.isLockedAccount(username2)) {
					request.setAttribute("errorMess", "Tài khoản của bạn đã bị khóa, để lấy lại Tài khoản, vui lòng liên hệ qua Emai: xosokienthiet.tailoc@gmail.com");							
					request.setAttribute("username", username);
					request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);
					return;
				}
				Account account = accountDAO.getAccount(username2);
				
				if (password == null) {
					request.setAttribute("errorMess", "Vui lòng nhập Mật khẩu.");
					request.setAttribute("username", username);
					request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);
					return;
				}
				password = password.trim();
				// Nhập mật khẩu sai
				if (!passwordCheck.getMd5(password).equals(account.getPassword())) {
					request.setAttribute("errorMess", "Mật khẩu không đúng.");
					request.setAttribute("username", username);
					request.setAttribute("password", password);
					
					// Nếu nhập mk sai quá 5 lần, khóa tài khoản
					int loginFail=accountDAO.getLoginFail(username2);
					loginFail++;
					accountDAO.setLoginFail(username2, loginFail);
					if(loginFail>5) {
						accountDAO.lockAccount(username2);						
						request.setAttribute("errorMess", "Tài khoản đã bị khóa do nhập sai mật khẩu quá 5 lần liên tiếp, để lấy lại Tài khoản, vui lòng liên hệ qua Emai: xosokienthiet.tailoc@gmail.com");						
					}else {
						request.setAttribute("errorMess", "Mật khẩu không đúng. Bạn còn "+(5-loginFail+1)+" lần nhập");					
					}
					
					request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);
					return;
				}

				// đăng nhập thành công
				// đặt lại số lần đăng nhập sai là 0
				accountDAO.setLoginFail(username2, 0);			
				
				// Lưu lịch sử đăng nhập
				HistoryAccountsAccessDAO historyAccountsAccessDAO = new HistoryAccountsAccessDAO(conn);
				historyAccountsAccessDAO.updateHistoryAccess(username2, new CheckInput().getCurrentDate());
				accountDAO.setActivated(username2, 1);
				// Dùng cookie để lưu username vs password đã đăng nhập
				if (rbm != null) {
					// xóa username + password cũ đã lưu
					 Cookie[] cookies =  request.getCookies();	
					for (int i = 0; i < cookies.length; i++) {		 
						Cookie cookie=cookies[i];
		                if ((cookie.getName()).compareTo("username") == 0||(cookie.getName()).compareTo("password") == 0) {
		                    cookie.setMaxAge(0);
		                    response.addCookie(cookie);
		                }		               
		            }
					Cookie usernameRmb = new Cookie("username", username2);
					usernameRmb.setMaxAge(60 * 60 * 24*7); 
					response.addCookie(usernameRmb);
					Cookie passwordRmb = new Cookie("password", password);
					passwordRmb.setMaxAge(60 * 60 * 24*7); 
					response.addCookie(passwordRmb);
				}
				session.setAttribute("account", account);
				response.sendRedirect(request.getContextPath() + "/Lottery?action=home");
				return;
			}

			request.getRequestDispatcher("/view/website/login.jsp").forward(request, response);

		} catch (Exception ex) {
			response.getWriter().println(ex);
		}

	}

}
