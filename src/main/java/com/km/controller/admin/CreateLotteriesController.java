package com.km.controller.admin;

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

import com.km.model.Province;
import com.km.dao.LotteryDAO;
import com.km.dao.ProvincesDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.GetLottery;
import com.km.model.Lottery;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng thêm Kết quả xổ số mới.
 */
@WebServlet("/CreateLotteriesController")
public class CreateLotteriesController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new creates the lotteries controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateLotteriesController() {
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
			conn = new DBContext().getConnection();
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
			// request.setAttribute("account", account);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			CheckInput checkInput = new CheckInput();

			List<String> regions = provincesDAO.getRegions();
			session.setAttribute("regions", regions);
			List<Province> provinces = new ArrayList<>();
			String region = regions.get(0);
			String date = checkInput.getCurrentDate();
			String dayOfWeek = checkInput.getDayOfWeek(date);
			provinces = provincesDAO.getProvinces(region, dayOfWeek);
			int provinceID = provinces.get(0).getProvince_id();

			request.setAttribute("region", region);
			request.setAttribute("date", date);
			request.setAttribute("provinceID", provinceID);
			request.setAttribute("provinces", provinces);

			response.getWriter().write("action get: CreateLotteries");
			request.getRequestDispatcher("/view/admin/admin_lotteries_create.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(CreateLotteriesController.class.getName()).log(Level.SEVERE, null, e);
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

			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			conn = new DBContext().getConnection();
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
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			CheckInput checkInput = new CheckInput();

			List<String> regions = provincesDAO.getRegions();
			session.setAttribute("regions", regions);
			List<Province> provinces = new ArrayList<>();
			String region = regions.get(0);
			String date = checkInput.getCurrentDate();
			String dayOfWeek = checkInput.getDayOfWeek(date);
			provinces = provincesDAO.getProvinces(region, dayOfWeek);
			int provinceID = provinces.get(0).getProvince_id();

			String action = request.getParameter("action");

			// ajax - load info lottery
			if (action != null && action.equals("changeRDP")) {
				region = request.getParameter("region");
				date = request.getParameter("date");
				dayOfWeek = checkInput.getDayOfWeek(date);
				provinces = provincesDAO.getProvinces(region, dayOfWeek);
				provinceID = provinces.get(0).getProvince_id();
				int p = Integer.parseInt(request.getParameter("provinceID"));

				System.out.println("region:" + region);
				for (Province province : provinces) {
					System.out.println(province.getProvince());
					if (province.getProvince_id() == p) {
						provinceID = p;
						break;
					}
				}

				out.println("		<h2 class=\"col-12  text-center\" >Thêm số trúng thưởng mới</h2>"
						+ "		<div class=\"form-floating col-12 col-md-7\">\r\n"
						+ "					<select name=\"region\" class=\"form-select\" id=\"selectRegion\"\r\n"
						+ "						onchange=\"changeRDP()\" aria-label=\"select region\">\r\n");
				for (String r : regions) {
					if (r.equals(region)) {
						out.println("<option value=\"" + r + "\" selected=\"selected\">" + r + "</option>\r\n");
					} else {
						out.println("<option value=\"" + r + "\">" + r + "</option>\r\n");
					}
				}
				out.println(" </select> <label for=\"selectRegion\">Chọn Miền</label>\r\n" + "				</div>\r\n"
						+ "				<div class=\"form-floating col-12 col-md-7 \">\r\n"
						+ "		 <input name=\"date\" type=\"date\" class=\"form-control\"\r\n"
						+ "			 id=\"selectDateLottery\" value=\"" + date + "\" onchange=\"changeRDP()\" />\r\n"
						+ "		 <label for=\"selectDateLottery\">Chọn ngày:</label>\r\n" + "	 </div>\r\n"
						+ "	 <div class=\"form-floating col-12 col-md-7 \">\r\n"
						+ "		 <select name=\"provinceID\" class=\"form-select\" id=\"selectProvince\"\r\n"
						+ "			 aria-label=\"select province\" onchange=\"changeRDP()\">\r\n");

				for (Province province : provinces) {
					if (province.getProvince_id() == provinceID) {
						out.println("<option value=\"" + province.getProvince_id() + "\" selected=\"selected\">"
								+ province.getProvince() + "</option>\r\n");
					} else {
						out.println("<option value=\"" + province.getProvince_id() + "\">" + province.getProvince()
								+ "</option>\r\n");
					}
				}
				out.println(" </select> <label for=\"selectProvince\">Chọn nhà đài</label>\r\n </div>");
				return;
			}
			// create lottery
			if (action != null && action.equals("create")) {

				GetLottery getLottery = new GetLottery();

				region = request.getParameter("region");
				date = request.getParameter("date");
				provinceID = Integer.parseInt(request.getParameter("provinceID"));
				// Thông báo nếu đã tồn tại
				if (lotteryDAO.isLottery(provinceID, date)) {
					out.println("Kết quả xổ số đã tồn tại!");
					return;
				}
				Lottery lottery = new Lottery();
				if (region.equals("Miền Bắc")) {
					lottery = getLottery.getMB(date);
				} else {
					lottery = getLottery.getProvince(provincesDAO.getProvince(provinceID), date);
				}
				if (lottery == null) {
					out.println("Kết quả xổ số chưa mở!");
					return;

				}

				lotteryDAO.createLottery(lottery);
				out.println(lotteryDAO.isError() ? "Thêm không thành công!" : "Thêm thành công!");
				return;
			}
			request.setAttribute("region", region);
			request.setAttribute("date", date);
			request.setAttribute("provinceID", provinceID);
			request.setAttribute("provinces", provinces);
			response.getWriter().write("action post: " + action + " CreateLotteries");
			request.getRequestDispatcher("/view/admin/admin_lotteries_create.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(CreateLotteriesController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
