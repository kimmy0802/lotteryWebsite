package com.km.controller.website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.dao.LotteryDAO;
import com.km.dao.ProvincesDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.LotteryShow;
import com.km.model.Province;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Xem KQXS theo ngày.
 */
@WebServlet("/LotteryOfDateController")
public class LotteryOfDateController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new lottery of date controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LotteryOfDateController() {
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

			conn = new DBContext().getConnection();
			HttpSession session = request.getSession(true);
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			
			List<String> regions = provincesDAO.getRegions();
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);
			session.setAttribute("searchLotteries", null);
			session.setAttribute("regions", regions);

			CheckInput checkInput = new CheckInput();
			
			String region = "Miền Bắc";			
			String date = checkInput.getCurrentDate();
			String dateOfWeek = checkInput.getDayOfWeek(date);
			List<Province> provincesList = provincesDAO.getProvinces(region, dateOfWeek);
			List<String> provinces = new ArrayList<>();
			for (Province p : provincesList) {
				provinces.add(p.getProvince());
			}
			provinces.add("Tất cả");
			String province = "Tất cả";
			session.setAttribute("provinces", provinces);
			List<LotteryShow> lotteryShow  = lotteryDAO.getLotteryOfDate(date, region, province);
			request.setAttribute("lotteryShow", lotteryShow.size()==0?null:lotteryShow);
			request.setAttribute("date", date);		
			request.setAttribute("region", region);
			request.setAttribute("province", province);
			request.setAttribute("provinces", provinces);
			request.getRequestDispatcher("/view/website/lotteryOfDate.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteryController.class.getName()).log(Level.SEVERE, null, e);
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

			HttpSession session = request.getSession(true);
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);

			conn = new DBContext().getConnection();
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			CheckInput checkInput=new CheckInput();

			List<String> regions = new ProvincesDAO(conn).getRegions();
			session.setAttribute("regions", regions);
			
			String region =  request.getParameter("region");			
			String date = request.getParameter("date");
			String dateOfWeek = checkInput.getDayOfWeek(date);
			List<Province> provincesList = provincesDAO.getProvinces(region, dateOfWeek);
			List<String> provinces = new ArrayList<>();
			for (Province p : provincesList) {
				provinces.add(p.getProvince());
			}
			provinces.add("Tất cả");
			String province = "Tất cả";
			String s = request.getParameter("province");
			for (String pro : provinces) {
				if (s.equals(pro))
					province = s;
			}			
			String action = request.getParameter("action");	

			// filter
			if (action != null && action.equals("filter")) {
				List<LotteryShow> lotteryShow=lotteryDAO.getLotteryOfDate(date, region, province);				
				request.setAttribute("lotteryShow", lotteryShow.size()==0?null:lotteryShow);
				request.setAttribute("date", date);		
				request.setAttribute("region", region);
				request.setAttribute("province", province);
				request.setAttribute("provinces", provinces);
				
				request.getRequestDispatcher("/view/website/lotteryOfDate.jsp").forward(request, response);
				return;

			}

			// ajax: load nhà đài, miền, ngày
			if (action != null && action.equals("changeRDP")) {	
				
				out.println("<div class=\"col col-md-3 \">\r\n"
						+ "						<input name=\"date\" type=\"date\" class=\"form-control\"\r\n"
						+ "							id=\"date\" value=\""+date+"\" onchange=\"changeRDP()\" />\r\n"
						+ "					</div>\r\n"
						+ "					<div class=\"col col-md-3\">\r\n"
						+ "						<select name=\"region\" id=\"region\" class=\"form-select\"\r\n"
						+ "							onchange=\"changeRDP()\" aria-label=\"Default select \">\r\n");
						for (String r : regions) {
							if (r.equals(region))
								out.println(" <option value=\"" + r + "\" selected=\"selected\">" + r + "</option>\r\n");
							else
								out.println(" <option value=\"" + r + "\">" + r + "</option>\r\n");
						}
					out.println("						</select>\r\n"
						+ "					</div>\r\n"
						+ "					<div class=\"col col-md-3\">\r\n"
						+ "						<select name=\"province\" id=\"province\" class=\"form-select\"\r\n"
						+ "							onchange=\"changeRDP()\" aria-label=\"Default select \">\r\n");
					for (String p : provinces) {
						if (p.equals(province))
							out.println(" <option value=\"" + p + "\" selected=\"selected\">" + p + "</option>\r\n");
						else
							out.println(" <option value=\"" + p + "\">" + p + "</option>\r\n");
					}
					out.println("						</select>\r\n"
						+ "					</div>");

				return;
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteryController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
