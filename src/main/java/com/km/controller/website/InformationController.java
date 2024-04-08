package com.km.controller.website;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.model.Account;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Xem các thông tin.
 */
@WebServlet("/InformationController")
public class InformationController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new information controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public InformationController() {
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
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);
			session.setAttribute("searchLotteries", null);
			String action = request.getParameter("action");
			
			if (action != null && action.equals("xsmb")) {
				request.getRequestDispatcher("/view/website/xo_so_mien_bac.jsp").forward(request, response);
				return;
			}
			if (action != null && action.equals("xsmt")) {
				request.getRequestDispatcher("/view/website/xo_so_mien_trung.jsp").forward(request, response);
				return;
			}
			if (action != null && action.equals("xsmn")) {
				request.getRequestDispatcher("/view/website/xo_so_mien_nam.jsp").forward(request, response);
				return;
			}
			if(action!=null&&action.equals("ttlt")) {
				request.getRequestDispatcher("/view/website/thu_tuc_lanh_thuong.jsp").forward(request, response);
				return;
			}
			if(action!=null&&action.equals("qdlt")) {
				request.getRequestDispatcher("/view/website/quy_dinh_lanh_thuong.jsp").forward(request, response);
				return;
			}
			if(action!=null&&action.equals("diachilanhthuong")){
				request.getRequestDispatcher("/view/website/dia_chi_lanh_thuong.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
