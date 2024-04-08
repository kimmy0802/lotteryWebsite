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
import com.km.model.LotteryShow;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng xem KQXS theo tỉnh.
 */
@WebServlet("/LotteryOfProvinceController")
public class LotteryOfProvinceController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new lottery of province controller.
     *
     * @see HttpServlet#HttpServlet()
     */
    public LotteryOfProvinceController() {
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
			HttpSession session = request.getSession(true);
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			List<LotteryShow> lotteryShow = new ArrayList<LotteryShow>();
			List<String> regions = provincesDAO.getRegions();
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);
			session.setAttribute("searchLotteries", null);
			session.setAttribute("regions", regions);
			List<String> provinces = provincesDAO.getProvincesName(regions.get(0));
			provinces.add(0, "Tất cả");
			session.setAttribute("provinces", provinces);

			int sum = 3;
			int page = 1;
			String region = "Miền Bắc";
			String province = "Tất cả";
			String colOrder = "lottery_date";
			String order = "desc";
			String action = request.getParameter("action");

			if (action != null && action.equals("lotteryOfProvince")) {
				lotteryShow = lotteryDAO.getLotteryOfProvince(province, region, colOrder, order, page, sum);
				request.setAttribute("lotteryShow", lotteryShow);
				int pages = lotteryDAO.getLotteryOfProvincePages(province, region, sum);
				request.setAttribute("page", page);
				request.setAttribute("pages", pages);
				request.setAttribute("region", region);
				request.setAttribute("province", province);
				request.setAttribute("provinces", provinces);
				request.setAttribute("order", order);
				request.setAttribute("colOrder", colOrder);
				request.getRequestDispatcher("/view/website/lotteryOfProvince.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/index.jsp").forward(request, response);

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession(true);
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);

			conn = new DBContext().getConnection();
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);

			List<String> regions = new ProvincesDAO(conn).getRegions();
			session.setAttribute("regions", regions);
			List<String> provinces = provincesDAO.getProvincesName(regions.get(0));
			provinces.add(0, "Tất cả");
			session.setAttribute("provinces", provinces);

			int sum = 3;
			int page = 1;
			String region = "Miền Bắc";
			String province = "Tất cả";
			String colOrder = "lottery_date";
			String order = "desc";
			String action = request.getParameter("action");
			
			// filter
			if (action != null && action.equals("filter")) {
				
				page = 1;
				region = request.getParameter("region");
				colOrder = request.getParameter("colOrder");
				order = request.getParameter("order");
				provinces = provincesDAO.getProvincesName(region);
				provinces.add(0, "Tất cả");

				province = provinces.get(0);
				String s = request.getParameter("province");
				for (String pro : provinces) {
					if (s.equals(pro))
						province = s;
				}
				List<LotteryShow>lotteryShow = lotteryDAO.getLotteryOfProvince(province, region, colOrder, order, page, sum);
				request.setAttribute("lotteryShow", lotteryShow);
				int pages = lotteryDAO.getLotteryOfProvincePages(province, region, sum);
				request.setAttribute("page", page);
				request.setAttribute("pages", pages);
				request.setAttribute("region", region);
				request.setAttribute("province", province);
				request.setAttribute("provinces", provinces);
				request.setAttribute("order", order);
				request.setAttribute("colOrder", colOrder);
				request.getRequestDispatcher("/view/website/lotteryOfProvince.jsp").forward(request, response);
				return;
				
			}
			
			// chuyển đến trang			
			if (action != null && action.equals("goToPage")) {
				String s=request.getParameter("page");
				if(s==null) s="1";
				page = Integer.parseInt(s);
				region = request.getParameter("region");
				colOrder = request.getParameter("colOrder");
				order = request.getParameter("order");
				provinces = provincesDAO.getProvincesName(region);
				provinces.add(0, "Tất cả");

				province = provinces.get(0);
				s = request.getParameter("province");
				for (String pro : provinces) {
					if (s.equals(pro))
						province = s;
				}
				List<LotteryShow>lotteryShow = lotteryDAO.getLotteryOfProvince(province, region, colOrder, order, page, sum);
				request.setAttribute("lotteryShow", lotteryShow);
				int pages = lotteryDAO.getLotteryOfProvincePages(province, region, sum);
				request.setAttribute("page", page);
				request.setAttribute("pages", pages);
				request.setAttribute("region", region);
				request.setAttribute("province", province);
				request.setAttribute("provinces", provinces);
				request.setAttribute("order", order);
				request.setAttribute("colOrder", colOrder);
				request.getRequestDispatcher("/view/website/lotteryOfProvince.jsp").forward(request, response);
				return;				
			}
			
			// ajax: load nhà đài, miền, ngày
			if (action != null && action.equals("changeRDP")) {
				String s=request.getParameter("page");
				if(s==null) s="1";
				page = Integer.parseInt(s);
				region = request.getParameter("region");
				colOrder = request.getParameter("colOrder");
				order = request.getParameter("order");
				provinces = provincesDAO.getProvincesName(region);
				provinces.add(0, "Tất cả");

				province = provinces.get(0);
				s = request.getParameter("province");
				for (String pro : provinces) {
					if (s.equals(pro))
						province = s;
				}
				out.println("<div class=\"col col-md-3 \">\r\n"
						+ "					<select name=\"region\" id=\"region\" class=\"form-select\"\r\n"
						+ "						onchange=\"changeRDP()\"\r\n"
						+ "						aria-label=\"Default select \">\r\n");

				for (String r : regions) {
					if (r.equals(region))
						out.println(" <option value=\"" + r + "\" selected=\"selected\">" + r + "</option>\r\n");
					else
						out.println(" <option value=\"" + r + "\">" + r + "</option>\r\n");
				}
				out.println(" </select>\r\n" + "				</div>\r\n"
						+ "				<div class=\"col col-md-3 \">\r\n"
						+ "					<select name=\"province\" id=\"province\" class=\"form-select\"\r\n"
						+ "						onchange=\"changeRDP()\"\r\n"
						+ "						aria-label=\"Default select \">\r\n");

				for (String p : provinces) {
					if (p.equals(province))
						out.println(" <option value=\"" + p + "\" selected=\"selected\">" + p + "</option>\r\n");
					else
						out.println(" <option value=\"" + p + "\">" + p + "</option>\r\n");
				}
				out.println(" </select>\r\n" + "				</div>\r\n"
						+ "				<div class=\"col col-md-3 \">\r\n"
						+ "					<select name=\"colOrder\" id=\"colOrder\" class=\"form-select m-0\"\r\n"
						+ "						onchange=\"changeRDP()\"\r\n"
						+ "						aria-label=\"Default select \">\r\n");
				if (!province.equals("Tất cả")) {
					out.println(" <option value=\"lottery_date\" selected=\"selected\">Ngày</option>\r\n");
				} else {
					if (colOrder.equals("province"))
						out.println(" <option value=\"province\" selected=\"selected\">Nhà đài</option>\r\n"
								+ "	 <option value=\"lottery_date\">Ngày</option>\r\n");
					else
						out.println(" <option value=\"province\" >Nhà đài</option>\r\n"
								+ "	 <option value=\"lottery_date\" selected=\"selected\">Ngày</option>\r\n");
				}

				out.println("					</select>\r\n" + "				</div>\r\n"
						+ "				<div class=\"col col-md-3 \">\r\n"
						+ "					<select name=\"order\" id=\"order\" class=\"form-select\"\r\n"
						+ "						onchange=\"changeRDP()\" aria-label=\"Default select\">\r\n");
				if (order.equals("asc"))
					out.println(" <option value=\"asc\" selected=\"selected\">Tăng dần</option>\r\n"
							+ "	 <option value=\"desc\">Giảm dần</option>\r\n");
				else
					out.println(" <option value=\"asc\">Tăng dần</option>\r\n"
							+ "	 <option value=\"desc\" selected=\"selected\">Giảm dần</option>\r\n");
				out.println(" </select>\r\n" + "				</div>");

				return;
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
			

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteryController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
