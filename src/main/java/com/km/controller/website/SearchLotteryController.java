package com.km.controller.website;

import java.io.IOException;
import java.io.PrintWriter;
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

import java.sql.Connection;

import com.km.database.DBContext;
import com.km.controller.admin.AccountsListController;
import com.km.dao.HistorySearchLotteryDAO;
import com.km.dao.LotteryDAO;
import com.km.dao.PrizesDAO;
import com.km.dao.ProvincesDAO;
import com.km.model.Account;
import com.km.model.CheckInput;
import com.km.model.HistorySearch;
import com.km.model.LotteryShow;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Dò vé số.
 */
@WebServlet("/SearchLotteryController")
public class SearchLotteryController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new search lottery controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchLotteryController() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn;

		try (PrintWriter out = response.getWriter()) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession(true);
			Account account = (Account) session.getAttribute("account");
			
			conn = (Connection) new DBContext().getConnection();
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			CheckInput checkInput = new CheckInput();

			List<String> regions = provincesDAO.getRegions();
			session.setAttribute("regions", regions);
			session.setAttribute("searchLotteries", null);
			String region = regions.get(0);
			
			String date = checkInput.getCurrentDate();
			String dayOfWeek = checkInput.getDayOfWeek(date);
			List<Province> provinces = provincesDAO.getProvinces(region, dayOfWeek);
			int provinceID = provinces.get(0).getProvince_id();

			LotteryShow lotteryShow = lotteryDAO.getLottery(date, provinceID);

			String action = request.getParameter("action");
			
			// xem lại kết quả dò vé số từ lịch sử dò vé số của người dùng
			if(account!=null) {
				request.setAttribute("account", account);
				session.setAttribute("account", account);
				if (action != null && action.equals("getResultSearch")) {
					date = request.getParameter("date");
					dayOfWeek = checkInput.getDayOfWeek(date);
					int p = Integer.parseInt(request.getParameter("provinceID"));
					
					String numberSearch = request.getParameter("numberSearch");
					region=provincesDAO.getRegion(p);
					provinces=provincesDAO.getProvinces(region, dayOfWeek);
					provinceID=p;
					lotteryShow = lotteryDAO.getLottery(date, provinceID);
					int prize = 9;
					List<String> prizes = new ArrayList<String>();
					prizes.add(0, "special_prize");
					prizes.add(1, "first_prize");
					prizes.add(2, "second_prize");
					prizes.add(3, "third_prize");
					prizes.add(4, "fourth_prize");
					prizes.add(5, "fifth_prize");
					prizes.add(6, "sixth_prize");
					prizes.add(7, "seventh_prize");
					prizes.add(8, "eighth_prize");
					prizes.add(9, "");
					
					List<String> resultSearchLottery = new ArrayList<String>();
					resultSearchLottery.add(0, "Giải Đặc Biệt");
					resultSearchLottery.add(1, "Giải Nhất");
					resultSearchLottery.add(2, "Giải Nhì");
					resultSearchLottery.add(3, "Giải Ba");
					resultSearchLottery.add(4, "Giải Tư");
					resultSearchLottery.add(5, "Giải Năm");
					resultSearchLottery.add(6, "Giải Sáu");
					resultSearchLottery.add(7, "Giải Bảy");
					resultSearchLottery.add(8, "Giải Tám");
					resultSearchLottery.add(9, "Không trúng thưởng");
					// Lấy kết quả dò vé số
					String rs=checkInput.checkPrize(lotteryShow, numberSearch, region);
					prize=Integer.parseInt(rs.substring(0,1));
					String numberResult="";
					if(prize!=9) numberResult=rs.substring(2);
					PrizesDAO prizesDAO = new PrizesDAO(conn);
					String costOfPrize = prizesDAO.getCostOfPrize(region, prizes.get(prize));
					
					request.setAttribute("region", region);
					request.setAttribute("date", date);
					request.setAttribute("prize", prize);
					request.setAttribute("costOfPrize",costOfPrize);
					request.setAttribute("provinceID", provinceID);
					request.setAttribute("provinces", provinces);
					request.setAttribute("numberSearch", numberSearch);
					request.setAttribute("numberResult", numberResult);
					request.setAttribute("resultSearchLottery", resultSearchLottery.get(prize));
					request.setAttribute("lotteryShow", lotteryShow);
					request.getRequestDispatcher("/view/website/searchLottery.jsp").forward(request, response);
					return;
				}
			}
			
			request.setAttribute("region", region);
			request.setAttribute("date", date);
			request.setAttribute("costOfPrize", "");
			request.setAttribute("prize", -1);
			request.setAttribute("provinceID", provinceID);
			request.setAttribute("provinces", provinces);
			
			request.getRequestDispatcher("/view/website/searchLottery.jsp").forward(request, response);
			
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
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession(true);
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);
			session.setAttribute("searchLotteries", null);

			conn = new DBContext().getConnection();
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			CheckInput checkInput = new CheckInput();
			PrizesDAO prizesDAO = new PrizesDAO(conn);

			List<String> regions = provincesDAO.getRegions();
			session.setAttribute("regions", regions);
			List<Province> provinces = new ArrayList<>();
			String region = regions.get(0);
			String date = checkInput.getCurrentDate();
			String dayOfWeek = checkInput.getDayOfWeek(date);
			provinces = provincesDAO.getProvinces(region, dayOfWeek);
			int provinceID = provinces.get(0).getProvince_id();
			LotteryShow lotteryShow = lotteryDAO.getLottery(date, provinceID);

			String action = request.getParameter("action");
			// ajax: load nhà đài, miền, ngày
			if (action != null && action.equals("changeRDP")) {
				String numberSearch = request.getParameter("numberSearch");
				String numberSearchMess = "";
				numberSearch = numberSearch.trim();
				region = request.getParameter("region");
				if (!regions.contains(region))
					region = regions.get(0);

				date = request.getParameter("date");
				if (!checkInput.isValidDate(date))
					date = checkInput.getCurrentDate();
				dayOfWeek = checkInput.getDayOfWeek(date);

				provinces = provincesDAO.getProvinces(region, dayOfWeek);
				provinceID = provinces.get(0).getProvince_id();
				int p = Integer.parseInt(request.getParameter("provinceID"));
				for (Province pro : provinces) {
					if (pro.getProvince_id() == p)
						provinceID = p;
				}
				out.println("<div class=\"form-floating col-12 col-md-6 mb-4 mb-md-2\">\r\n"
						+ "				<select name=\"region\" class=\"form-select\" id=\"selectRegion\"\r\n"
						+ "					onchange=\"changeRDP()\" aria-label=\"select region\">\r\n");
				for (String r : regions) {
					if (r.equals(region)) {
						out.println("<option value=\"" + r + "\" selected=\"selected\">" + r + "</option>\r\n");
					} else {
						out.println("<option value=\"" + r + "\">" + r + "</option>\r\n");
					}
				}
				out.println(  "				</select> <label for=\"selectRegion\">Chọn Miền</label>\r\n"
						+ "			</div>\r\n"
						+ "			<div class=\"form-floating col-12 col-md-6 mb-4 mb-md-2\">\r\n"
						+ "					<input name=\"date\" type=\"date\" class=\"form-control\"\r\n"
						+ "						id=\"selectDateLottery\" value=\""+date+"\" onchange=\"changeRDP()\" />\r\n"
						+ "				<label for=\"selectDateLottery\">Chọn ngày:</label>\r\n"
						+ "			</div>\r\n"
						+ "			<div class=\"form-floating col-12 col-md-6 mb-4 mb-md-2\">\r\n"
						+ "				<select name=\"provinceID\" class=\"form-select\" id=\"selectProvince\"\r\n"
						+ "					aria-label=\"select province\" onchange=\"changeRDP()\">\r\n");
				for (Province province : provinces) {
					if (province.getProvince_id() == provinceID) {
						out.println(" <option value=\"" + province.getProvince_id() + "\" selected=\"selected\">"
								+ province.getProvince() + "</option>\r\n");
					} else {
						out.println(" <option value=\"" + province.getProvince_id() + "\" >" + province.getProvince()
								+ "</option>\r\n");
					}

				}
				out.println("				</select> <label for=\"selectProvince\">Chọn nhà đài</label>\r\n"
						+ "			</div>\r\n"
						+ "			<div id=\"numberSearchContext\"\r\n"
						+ "				class=\"form-floating col-12 col-md-6 mb-4 mb-md-2 \">\r\n"
						+ "				<input name=\"numberSearch\" type=\"text\" class=\"form-control\"\r\n"
						+ "					id=\"numberSearch\" value=\""+numberSearch+"\" oninput=\"checkLotterySearch(this)\" />\r\n"
						+ "				<label for=\"numberSearch\">Số cần dò:</label>\r\n"
						+ "				<p class=\"text-danger\" id=\"numberSearchMess\">"+numberSearchMess+"</p>\r\n"
						+ "			</div>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "			<div class=\"col-12 col-sm-7 row justify-content-center\">\r\n"
						+ "				<div class=\"col-5\">\r\n"
						+ "					<button name=\"submit\" type=\"submit\" value=\"search\"\r\n"
						+ "						class=\"btn btn-primary \">Dò</button>\r\n"
						+ "				</div>\r\n"
						+ "			</div>");
				return;
			}

			// Dò và trả về kết quả
			if (action != null && action.equals("searchLottery")) {
				region = request.getParameter("region");
				date = request.getParameter("date");
				dayOfWeek = checkInput.getDayOfWeek(date);
				provinces = provincesDAO.getProvinces(region, dayOfWeek);
				provinceID = Integer.parseInt(request.getParameter("provinceID"));

				String numberSearch = request.getParameter("numberSearch");
				numberSearch = numberSearch.trim();
				// check input value
				if (numberSearch == null||numberSearch.length() != 6||!checkInput.isValidNumber(numberSearch)) {
					request.setAttribute("region", region);
					request.setAttribute("date", date);
					request.setAttribute("prize", 10);					
					request.setAttribute("provinceID", provinceID);
					request.setAttribute("provinces", provinces);
					request.setAttribute("numberSearch", numberSearch);
					request.getRequestDispatcher("/view/website/searchLottery.jsp").forward(request, response);
					return;
					
				}
				
				lotteryShow = lotteryDAO.getLottery(date, provinceID);
				// Không có kết quả xổ số của ngày, nhà đài đã dò
				if(lotteryShow.getDate()==null) {
					request.setAttribute("region", region);
					request.setAttribute("date", date);
					request.setAttribute("prize", 11);
					request.setAttribute("provinceID", provinceID);
					request.setAttribute("provinces", provinces);
					request.setAttribute("numberSearch", numberSearch);
					request.getRequestDispatcher("/view/website/searchLottery.jsp").forward(request, response);
					return;
				}
				// Có kết quả xổ số của ngày, nhà đài đã dò 
				int prize = 9;
				List<String> prizes = new ArrayList<String>();
				prizes.add(0, "special_prize");
				prizes.add(1, "first_prize");
				prizes.add(2, "second_prize");
				prizes.add(3, "third_prize");
				prizes.add(4, "fourth_prize");
				prizes.add(5, "fifth_prize");
				prizes.add(6, "sixth_prize");
				prizes.add(7, "seventh_prize");
				prizes.add(8, "eighth_prize");
				prizes.add(9, "");
				
				List<String> resultSearchLottery = new ArrayList<String>();
				resultSearchLottery.add(0, "Giải Đặc Biệt");
				resultSearchLottery.add(1, "Giải Nhất");
				resultSearchLottery.add(2, "Giải Nhì");
				resultSearchLottery.add(3, "Giải Ba");
				resultSearchLottery.add(4, "Giải Tư");
				resultSearchLottery.add(5, "Giải Năm");
				resultSearchLottery.add(6, "Giải Sáu");
				resultSearchLottery.add(7, "Giải Bảy");
				resultSearchLottery.add(8, "Giải Tám");
				resultSearchLottery.add(9, "Không trúng thưởng");
				// lấy kết quả dò vé số
				String rs=checkInput.checkPrize(lotteryShow, numberSearch, region);
				prize=Integer.parseInt(rs.substring(0,1));
				String numberResult="";
				if(prize!=9) numberResult=rs.substring(2);
				String costOfPrize = prizesDAO.getCostOfPrize(region, prizes.get(prize));

				// Lưu số vừa tìm vào Lịch sử tìm kiếm vé số khi đăng nhập
				if (account != null) {
					HistorySearchLotteryDAO historySearchLotteryDAO = new HistorySearchLotteryDAO(conn);
					HistorySearch historySearch=new HistorySearch(account.getUsername(), provinceID, numberSearch, date, checkInput.getCurrentTime(), resultSearchLottery.get(prize));
					historySearchLotteryDAO.create(historySearch);
				}
				
				request.setAttribute("region", region);
				request.setAttribute("date", date);
				request.setAttribute("prize", prize);
				request.setAttribute("costOfPrize",costOfPrize);
				request.setAttribute("provinceID", provinceID);
				request.setAttribute("provinces", provinces);
				request.setAttribute("numberSearch", numberSearch);
				request.setAttribute("numberResult", numberResult);
				request.setAttribute("resultSearchLottery", resultSearchLottery.get(prize));
				request.setAttribute("lotteryShow", lotteryShow);
				request.getRequestDispatcher("/view/website/searchLottery.jsp").forward(request, response);
				return;
				
			}
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
