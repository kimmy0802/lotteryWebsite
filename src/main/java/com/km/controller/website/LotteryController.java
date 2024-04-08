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

import com.km.database.DBContext;
import com.km.dao.LotteryDAO;
import com.km.dao.ProvincesDAO;
import com.km.model.Account;
import com.km.model.GetLottery;
import com.km.model.LotteryShow;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Xem KQXS theo miền.
 */
@WebServlet("/LotteryController")
public class LotteryController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new lottery controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LotteryController() {
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
			List<LotteryShow> lotteryShow = new ArrayList<LotteryShow>();
			List<String> regions = provincesDAO.getRegions();
			List<String> provinces = provincesDAO.getProvincesName("Tất cả");
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);
			session.setAttribute("searchLotteries", null);
			regions.add(0, "Tất cả");
			provinces.add(0, "Tất cả");
			session.setAttribute("regions", regions);
			
			int sum = 3;
			int page = 1;
			String search = "";
			String region = "Tất cả";
			String colOrder = "lottery_date";
			String order = "desc";
			String action = request.getParameter("action");
			if (action == null) {
				lotteryShow = lotteryDAO.getLotteries(search, region, colOrder, order, page, sum);
				session.setAttribute("lotteryShow", lotteryShow);
				session.setAttribute("lotteryShowMB",
						lotteryDAO.getLotteriesList(search, "Miền Bắc", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMT",
						lotteryDAO.getLotteriesList(search, "Miền Trung", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMN",
						lotteryDAO.getLotteriesList(search, "Miền Nam", colOrder, order, page, sum));
				session.setAttribute("page", page);

				int pages = lotteryDAO.getLotteriesPages(search, region, colOrder, sum);
				if (region.equals("Tất cả")) {
					int pagesMB = lotteryDAO.getLotteriesPages(search, "Miền Bắc", colOrder, sum);
					int pagesMT = lotteryDAO.getLotteriesPages(search, "Miền Trung", colOrder, sum);
					int pagesMN = lotteryDAO.getLotteriesPages(search, "Miền Nam", colOrder, sum);
					pages = Math.max(pagesMN, Math.max(pagesMT, pagesMB));
				}
				session.setAttribute("pages", pages);
				session.setAttribute("region", region);
				session.setAttribute("order", order);
				session.setAttribute("colOrder", colOrder);
				
				
				request.getRequestDispatcher("/view/website/lotteryList.jsp").forward(request, response);
				return;

			}
			if (action!=null&&action.equals("home")) {
				
				request.setAttribute("lotteryShowMB",
						lotteryDAO.getLotteriesList("", "Miền Bắc", "lottery_date", "desc", 1, sum));
				request.setAttribute("lotteryShowMT",
						lotteryDAO.getLotteriesList("", "Miền Trung","lottery_date", "desc", 1, sum));
				request.setAttribute("lotteryShowMN",
						lotteryDAO.getLotteriesList("", "Miền Nam", "lottery_date", "desc", 1, sum));
				request.getRequestDispatcher("/view/website/home.jsp").forward(request, response);
				return;

			}
			request.getRequestDispatcher("index.jsp").forward(request, response);

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

			List<LotteryShow> lotteryShow = new ArrayList<LotteryShow>();
			ProvincesDAO provincesDAO=new ProvincesDAO(conn);
			List<String> regions = provincesDAO.getRegions();
			regions.add(0, "Tất cả");
			session.setAttribute("regions", regions);

			int sum = 3;
			int page = 1;
			String search = (String) session.getAttribute("searchLotteries");
			if (search == null)
				search = "";
			String region = "Tất cả";
			String colOrder = "lottery_date";
			String order = "desc";

			String action = request.getParameter("action");
			// filter
			if (action != null & action.equals("filter")) {
				region = request.getParameter("region");
				colOrder = request.getParameter("colOrder");
				order = request.getParameter("order");
				page = 1;
				sum = 3;
				out.println("region: " + region);
				out.println("colOrder: " + colOrder);
				out.println("order: " + order);
				out.println("filter");
				int pages = lotteryDAO.getLotteriesPages(search, region, colOrder, sum);
				if (region.equals("Tất cả")) {
					int pagesMB = lotteryDAO.getLotteriesPages(search, "Miền Bắc", colOrder, sum);
					int pagesMT = lotteryDAO.getLotteriesPages(search, "Miền Trung", colOrder, sum);
					int pagesMN = lotteryDAO.getLotteriesPages(search, "Miền Nam", colOrder, sum);
					pages = Math.max(pagesMN, Math.max(pagesMT, pagesMB));
				}
				if (pages < page)
					page = pages;
				lotteryShow = lotteryDAO.getLotteriesList(search, region, colOrder, order, page, sum);

				session.setAttribute("lotteryShow", lotteryShow);
				session.setAttribute("lotteryShowMB",
						lotteryDAO.getLotteriesList(search, "Miền Bắc", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMT",
						lotteryDAO.getLotteriesList(search, "Miền Trung", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMN",
						lotteryDAO.getLotteriesList(search, "Miền Nam", colOrder, order, page, sum));
				session.setAttribute("page", page);
				session.setAttribute("pages", pages);
				session.setAttribute("region", region);
				session.setAttribute("order", order);
				session.setAttribute("colOrder", colOrder);
				request.setAttribute("page", page);
				request.setAttribute("pages", pages);

				request.getRequestDispatcher("/view/website/lotteryList.jsp").forward(request, response);
				return;
			}
			
			// chuyển đến trang			
			if (action != null & action.equals("goToPage")) {				
				region = request.getParameter("region");				
				colOrder = request.getParameter("colOrder");
				order = request.getParameter("order");
				String p=request.getParameter("page");
				if(region==null) region="Tất cả";
				if(colOrder==null) colOrder="lottery_date";
				if(order==null) order="desc";
				if(p.equals("")) p="1";
				page = Integer.parseInt(p);
				sum = 3;
				out.println("region: " + region);
				out.println("colOrder: " + colOrder);
				out.println("order: " + order);
				out.println("filter");
				int pages = lotteryDAO.getLotteriesPages(search, region, colOrder, sum);
				if (region.equals("Tất cả")) {
					int pagesMB = lotteryDAO.getLotteriesPages(search, "Miền Bắc", colOrder, sum);
					int pagesMT = lotteryDAO.getLotteriesPages(search, "Miền Trung", colOrder, sum);
					int pagesMN = lotteryDAO.getLotteriesPages(search, "Miền Nam", colOrder, sum);
					pages = Math.max(pagesMN, Math.max(pagesMT, pagesMB));
				}
				if (pages < page)
					page = pages;
				lotteryShow = lotteryDAO.getLotteriesList(search, region, colOrder, order, page, sum);

				session.setAttribute("lotteryShow", lotteryShow);
				session.setAttribute("lotteryShowMB",
						lotteryDAO.getLotteriesList(search, "Miền Bắc", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMT",
						lotteryDAO.getLotteriesList(search, "Miền Trung", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMN",
						lotteryDAO.getLotteriesList(search, "Miền Nam", colOrder, order, page, sum));
				session.setAttribute("page", page);
				session.setAttribute("pages", pages);
				session.setAttribute("region", region);
				session.setAttribute("order", order);
				session.setAttribute("colOrder", colOrder);
				request.setAttribute("page", page);
				request.setAttribute("pages", pages);

				request.getRequestDispatcher("/view/website/lotteryList.jsp").forward(request, response);
				return;
			}

			// search- Tìm kiếm ở thanh navbar ----------------------------------
			if (action != null && action.equals("searchLotteries")) {

				if (request.getParameter("searchLotteries") != null)
					search = request.getParameter("searchLotteries");
				search = search.trim();

				int pages = lotteryDAO.getLotteriesPages(search, region, colOrder, sum);
				if (region.equals("Tất cả")) {
					int pagesMB = lotteryDAO.getLotteriesPages(search, "Miền Bắc", colOrder, sum);
					int pagesMT = lotteryDAO.getLotteriesPages(search, "Miền Trung", colOrder, sum);
					int pagesMN = lotteryDAO.getLotteriesPages(search, "Miền Nam", colOrder, sum);
					pages = Math.max(pagesMN, Math.max(pagesMT, pagesMB));
				}
				lotteryShow = lotteryDAO.getLotteriesList(search, region, colOrder, order, page, sum);

				session.setAttribute("lotteryShow", lotteryShow);
				session.setAttribute("lotteryShowMB",
						lotteryDAO.getLotteriesList(search, "Miền Bắc", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMT",
						lotteryDAO.getLotteriesList(search, "Miền Trung", colOrder, order, page, sum));
				session.setAttribute("lotteryShowMN",
						lotteryDAO.getLotteriesList(search, "Miền Nam", colOrder, order, page, sum));
				request.setAttribute("page", page);
				request.setAttribute("pages", pages);
				request.setAttribute("region", region);
				request.setAttribute("order", order);
				request.setAttribute("colOrder", colOrder);
				session.setAttribute("searchLotteries", search.length() == 0 ? null : search);
				request.setAttribute("searchLotteries", search.length() == 0 ? null : search);
				request.getRequestDispatcher("/view/website/lotteryList.jsp").forward(request, response);

				return;
			}

			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteryController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
