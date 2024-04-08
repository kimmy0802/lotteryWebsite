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
import com.km.model.LotteryShow;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Chỉnh sửa kết quả xổ số.
 */
@WebServlet("/UpdateLotteriesController")
public class UpdateLotteriesController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new update lotteries controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateLotteriesController() {
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

			conn = new DBContext().getConnection();
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
			String province = provinces.get(0).getProvince();

			String action = request.getParameter("action");
			if (action == null) {
				request.setAttribute("region", region);
				request.setAttribute("date", date);
				request.setAttribute("province", province);
				provinces = provincesDAO.getProvinces(region, dayOfWeek);
				request.setAttribute("provinces", provinces);

				LotteryShow lotteryShow = lotteryDAO.getLottery(checkInput.getCurrentDate(),
						provinces.get(0).getProvince());
				if (lotteryShow.getRegion() == null) {
					request.setAttribute("special_prize", "");
					request.setAttribute("first_prize", "");
					request.setAttribute("second_prize", "");
					request.setAttribute("third_prize", "");
					request.setAttribute("fourth_prize", "");
					request.setAttribute("fifth_prize", "");
					request.setAttribute("sixth_prize", "");
					request.setAttribute("seventh_prize", "");
					request.setAttribute("eighth_prize", "");
					request.setAttribute("lotteryShow", null);
				} else {
					request.setAttribute("special_prize", lotteryShow.getLottery().getSpecial_prize());
					request.setAttribute("first_prize", lotteryShow.getLottery().getFirst_prize());
					request.setAttribute("second_prize", lotteryShow.getLottery().getSecond_prize());
					request.setAttribute("third_prize", lotteryShow.getLottery().getThird_prize());
					request.setAttribute("fourth_prize", lotteryShow.getLottery().getFourth_prize());
					request.setAttribute("fifth_prize", lotteryShow.getLottery().getFifth_prize());
					request.setAttribute("sixth_prize", lotteryShow.getLottery().getSixth_prize());
					request.setAttribute("seventh_prize", lotteryShow.getLottery().getSeventh_prize());
					request.setAttribute("eighth_prize", lotteryShow.getLottery().getEighth_prize());
					request.setAttribute("lotteryShow", lotteryShow);
				}
				request.getRequestDispatcher("/view/admin/admin_lotteries_update.jsp").forward(request, response);
				return;

			}

			// chỉnh sửa lottery từ trang LotteryList
			if (action != null && action.equals("updateLotteryGet")) {
				String s = request.getParameter("lotteryID");
				int lotteryID = 1;
				if (s != null)
					lotteryID = Integer.parseInt(s);
				LotteryShow lotteryShow = lotteryDAO.getLottery(lotteryID);
				request.setAttribute("region", lotteryShow.getRegion());
				request.setAttribute("date", lotteryShow.getDate());
				request.setAttribute("province", lotteryShow.getProvince());
				request.setAttribute("special_prize", lotteryShow.getLottery().getSpecial_prize());
				request.setAttribute("first_prize", lotteryShow.getLottery().getFirst_prize());
				request.setAttribute("second_prize", lotteryShow.getLottery().getSecond_prize());
				request.setAttribute("third_prize", lotteryShow.getLottery().getThird_prize());
				request.setAttribute("fourth_prize", lotteryShow.getLottery().getFourth_prize());
				request.setAttribute("fifth_prize", lotteryShow.getLottery().getFifth_prize());
				request.setAttribute("sixth_prize", lotteryShow.getLottery().getSixth_prize());
				request.setAttribute("seventh_prize", lotteryShow.getLottery().getSeventh_prize());
				request.setAttribute("eighth_prize", lotteryShow.getLottery().getEighth_prize());

				request.setAttribute("lotteryShow", lotteryShow);
				request.getRequestDispatcher("/view/admin/admin_lotteries_update.jsp").forward(request, response);
				return;
			}

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
			
			conn = new DBContext().getConnection();
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
			String province = provinces.get(0).getProvince();

			String action = request.getParameter("action");
			if (action != null && action.equals("update")) {
				region = request.getParameter("region");
				province = request.getParameter("province");
				date = request.getParameter("date");
				dayOfWeek = checkInput.getDayOfWeek(date);
				provinces = provincesDAO.getProvinces(region, dayOfWeek);

				String special_prize = request.getParameter("special_prize");
				String first_prize = request.getParameter("first_prize");
				String second_prize = request.getParameter("second_prize");
				String third_prize = request.getParameter("third_prize");
				String fourth_prize = request.getParameter("fourth_prize");
				String fifth_prize = request.getParameter("fifth_prize");
				String sixth_prize = request.getParameter("sixth_prize");
				String seventh_prize = request.getParameter("seventh_prize");
				String eighth_prize = request.getParameter("eighth_prize");
				if (eighth_prize == null)
					eighth_prize = "";
				int provinceID = 1;
				for (Province p : provinces) {
					if (p.getProvince().equals(province)) {
						provinceID = p.getProvince_id();
						break;
					}
				}
				lotteryDAO.updateLottery(provinceID, date, special_prize, first_prize, second_prize, third_prize,
						fourth_prize, fifth_prize, sixth_prize, seventh_prize, eighth_prize);
				out.println(lotteryDAO.isError() ? "Cập nhật thất bại" : "Cập nhật thành công!");
				return;

			}
			// ajax - load info lottery
			if (action != null && action.equals("changeRDP")) {
				region = request.getParameter("region");
				province = request.getParameter("province");
				date = request.getParameter("date");
				dayOfWeek = checkInput.getDayOfWeek(date);
				provinces = provincesDAO.getProvinces(region, dayOfWeek);
				
				province = provinces.get(0).getProvince();
				
				String s = request.getParameter("province");
				for (Province p : provinces) {
					if (p.getProvince().equals(s)) {
						province = s;
						break;
					}
				}

				LotteryShow lotteryShow = lotteryDAO.getLottery(date, province);

				out.println(
						"<div class=\"col-12 row\">\r\n" 
								+ "   <div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
								+ "		 <select name=\"region\" class=\"form-select\" id=\"selectRegion\"\r\n"
								+ "			 onchange=\"changeRDP()\" aria-label=\"select region\">\r\n");
				for (String r : regions) {
					if (r.equals(region)) {
						out.println("<option value=\"" + r + "\" selected=\"selected\">" + r + "</option>\r\n");
					} else {
						out.println("<option value=\"" + r + "\">" + r + "</option>\r\n");
					}
				}
				out.println(" </select> <label for=\"selectRegion\">Chọn Miền</label>\r\n <p>\r\n"
						+ " </div>\r\n "
						+ "<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "	 <input name=\"date\" type=\"date\" class=\"form-control\"\r\n"
						+ "  id=\"selectDateLottery\" value=\"" + date + "\"\r\n"
						+ "	 onchange=\"changeRDP()\" />\r\n"
						+ " <label for=\"selectDateLottery\">Chọn ngày:</label>\r\n <p>\r\n "
						+ " </div>\r\n"
						+ " <div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n "
						+ "	 <select name=\"province\" class=\"form-select\" id=\"selectProvince\"\r\n"
						+ "	 aria-label=\"select province\" onchange=\"changeRDP()\">\r\n");
				for (Province p : provinces) {
					if (p.getProvince().equals(province)) {
						out.println("<option value=\"" + p.getProvince()+ "\" selected=\"selected\">"+ p.getProvince()
								+ "</option>\r\n");
					} else {
						out.println("<option value=\"" + p.getProvince() + "\">" + p.getProvince()
								+ "</option>\r\n");
					}
				}
				out.println(" </select> <label for=\"selectProvince\">Chọn nhà đài</label>\r\n "
						+ " <p>\r\n </div>\r\n");
				// khai baos giai
				String special_prize = "";
				String first_prize = "";
				String second_prize = "";
				String third_prize = "";
				String fourth_prize = "";
				String fifth_prize = "";
				String sixth_prize = "";
				String seventh_prize = "";
				String eighth_prize = "";

				if (lotteryShow.getLottery() != null) {
					special_prize = lotteryShow.getLottery().getSpecial_prize();
					first_prize = lotteryShow.getLottery().getFirst_prize();
					second_prize = lotteryShow.getLottery().getSecond_prize();
					third_prize = lotteryShow.getLottery().getThird_prize();
					fourth_prize = lotteryShow.getLottery().getFourth_prize();
					fifth_prize = lotteryShow.getLottery().getFifth_prize();
					sixth_prize = lotteryShow.getLottery().getSixth_prize();
					seventh_prize = lotteryShow.getLottery().getSeventh_prize();
					eighth_prize = lotteryShow.getLottery().getEighth_prize();
				}

				
				out.println("	<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "						<input name=\"special_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "							id=\"special_prize\" value=\"" + special_prize + "\"\r\n"
						+ "							oninput='checkInput(\"special_prize\")' /> <label\r\n"
						+ "							for=\"special_prize\">Giải đặc biệt</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"special_prizeMess\"></p>\r\n"
						+ "					</div>\r\n");
				out.println("<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "	 <input name=\"first_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "	 id=\"first_prize\" value=\"" + first_prize + "\"\r\n"
						+ "	 oninput='checkInput(\"first_prize\")' /> <label for=\"first_prize\">Giải Nhất</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"first_prizeMess\"></p>\r\n"
						+ "\r\n </div>\r\n");
				out.println("<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "						<input name=\"second_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "							id=\"second_prize\" value=\"" + second_prize + "\"\r\n"
						+ "							oninput='checkInput(\"second_prize\")' /> <label\r\n"
						+ "							for=\"second_prize\">Giải Nhì</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"second_prizeMess\"></p>\r\n"
						+ "  </div>\r\n"
						+ "	</div>\r\n");
				out.println("<div class=\"col-12 row\">\r\n"
						+ "					<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "						<input name=\"third_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "							id=\"third_prize\" value=\"" + third_prize + "\"\r\n"
						+ "							oninput='checkInput(\"third_prize\")' /> <label for=\"third_prize\">Giải\r\n"
						+ "							Ba</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"third_prizeMess\"></p>\r\n"
						+ "					</div>\r\n");
				out.println("<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "						<input name=\"fourth_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "							id=\"fourth_prize\" value=\"" + fourth_prize + "\"\r\n"
						+ "							oninput='checkInput(\"fourth_prize\")' /> <label\r\n"
						+ "							for=\"fourth_prize\">Giải Bốn</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"fourth_prizeMess\"></p>\r\n"
						+ "					</div>\r\n");
				out.println("<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "						<input name=\"fifth_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "							id=\"fifth_prize\" value=\"" + fifth_prize + "\"\r\n"
						+ "							oninput='checkInput(\"fifth_prize\")' /> <label for=\"fifth_prize\">Giải\r\n"
						+ "							Năm</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"fifth_prizeMess\"></p>\r\n"
						+ "					</div>\r\n");
				out.println("<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "						<input name=\"sixth_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "							id=\"sixth_prize\" value=\"" + sixth_prize + "\"\r\n"
						+ "							oninput='checkInput(\"sixth_prize\")' /> <label for=\"sixth_prize\">Giải\r\n"
						+ "							Sáu</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"sixth_prizeMess\"></p>\r\n"
						+ "					</div>\r\n");
				out.println("<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
						+ "						<input name=\"seventh_prize\" type=\"text\" class=\"form-control\"\r\n"
						+ "							id=\"seventh_prize\" value=\"" + seventh_prize + "\"\r\n"
						+ "							oninput='checkInput(\"seventh_prize\")' /> <label\r\n"
						+ "							for=\"seventh_prize\">Giải Bảy</label>\r\n"
						+ "						<p class=\"text-danger\" id=\"seventh_prizeMess\"></p>\r\n"
						+ "					</div>\r\n");
				if (!region.equals("Miền Bắc")) {
					out.println("<div class=\"col-12 col-md-6 mb-2 form-floating\">\r\n"
							+ "							<input name=\"eighth_prize\" type=\"text\" class=\"form-control\"\r\n"
							+ "								id=\"eighth_prize\" value=\"" + eighth_prize + "\"\r\n"
							+ "								oninput='checkInput(\"eighth_prize\")' /> <label\r\n"
							+ "								for=\"eighth_prize\">Giải Tám</label>\r\n"
							+ "							<p class=\"text-danger\" id=\"eighth_prizeMess\"></p>\r\n"
							+ "						</div>\r\n");
				}
				out.println("				</div>");
				return;

			}
			request.getRequestDispatcher("/view/admin/admin_lotteries_update.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
