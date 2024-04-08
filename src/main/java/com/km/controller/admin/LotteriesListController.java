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

import com.km.dao.LotteryDAO;
import com.km.dao.ProvincesDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.GetLottery;
import com.km.model.Lottery;
import com.km.model.LotteryShow;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Hiển thị vé số.
 */
@WebServlet("/LotteriesListController")
public class LotteriesListController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new lotteries list controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LotteriesListController() {
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			conn = new DBContext().getConnection();
			HttpSession session = request.getSession(true);
			Account account=(Account) session.getAttribute("account");
			// Phân quyền tài khoản
			if(account==null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if(account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			List<Integer> deleteLotteriesList = new ArrayList<Integer>();
			List<LotteryShow> lotteryShow = new ArrayList<LotteryShow>();
			List<String> regions = provincesDAO.getRegions();
			session.setAttribute("account", account);
			regions.add(0, "Tất cả");
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

				int pages = lotteryDAO.getLotteriesPages(search, region,colOrder, sum);
				if (region.equals("Tất cả")) {
					int pagesMB = lotteryDAO.getLotteriesPages(search, "Miền Bắc",colOrder, sum);
					int pagesMT = lotteryDAO.getLotteriesPages(search, "Miền Trung",colOrder, sum);
					int pagesMN = lotteryDAO.getLotteriesPages(search, "Miền Nam",colOrder, sum);
					pages = Math.max(pagesMN, Math.max(pagesMT, pagesMB));
				}
				session.setAttribute("pages", pages);
				session.setAttribute("region", region);
				session.setAttribute("order", order);
				session.setAttribute("colOrder", colOrder);
				session.setAttribute("deleteLotteriesList", deleteLotteriesList);
				session.setAttribute("searchLotteries", null);
				request.getRequestDispatcher("/view/admin/lottery_list.jsp").forward(request, response);
				return;

			}
			if (action != null && action.equals("lotteriesList")) {
				deleteLotteriesList = (List<Integer>) session.getAttribute("deleteLotteriesList");
				Object o = session.getAttribute("searchLotteries");
				String s = (String) o;
				if (s != null && !s.equals(search))
					search = s;

				o = session.getAttribute("region");
				s = (String) o;
				if (s != null && !s.equals(region))
					region = s;

				o = session.getAttribute("colOrder");
				s = (String) o;
				if (s != null && !s.equals(colOrder))
					colOrder = s;

				o = session.getAttribute("order");
				s = (String) o;
				if (s != null && !s.equals(order))
					order = s;

				//o = session.getAttribute("page");
				int p = Integer.parseInt(request.getParameter("page"));
				if (p > page)
					page = p;
				int pages = lotteryDAO.getLotteriesPages(search, region,colOrder, sum);
				if (region.equals("Tất cả")) {
					int pagesMB = lotteryDAO.getLotteriesPages(search, "Miền Bắc",colOrder, sum);
					int pagesMT = lotteryDAO.getLotteriesPages(search, "Miền Trung",colOrder, sum);
					int pagesMN = lotteryDAO.getLotteriesPages(search, "Miền Nam",colOrder, sum);
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
				session.setAttribute("deleteLotteriesList", deleteLotteriesList);
				request.setAttribute("page", page);
				request.setAttribute("pages", pages);

				request.getRequestDispatcher("/view/admin/lottery_list.jsp").forward(request, response);
				return;
			}
			response.getWriter().write("action get: " + action + " LotteriesListController");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteriesListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession(true);
			Account account=(Account) session.getAttribute("account");
			if(account==null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if(account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			conn = new DBContext().getConnection();
			session.setAttribute("account", account);
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			
			List<Integer> deleteLotteriesList = new ArrayList<Integer>();
			List<LotteryShow> lotteryShow = new ArrayList<LotteryShow>();
			List<String> regions = new ProvincesDAO(conn).getRegions();
			regions.add(0, "Tất cả");
			session.setAttribute("regions", regions);

			int sum = 3;
			int page = 1;
			String search = "";
			String region = "Tất cả";
			String colOrder = "province";
			String order = "asc";

			String action = request.getParameter("action");
			
			// search- Tìm kiếm ----------------------------------
			if (action != null && action.equals("searchLotteries")) {

				if (request.getParameter("searchLotteries") != null)
					search = request.getParameter("searchLotteries");
				response.getWriter().write("search" + search + "\n");

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
				request.setAttribute("searchLotteries", search.equals("") ? null : search);
				session.setAttribute("deleteLotteriesList", deleteLotteriesList);
				request.getRequestDispatcher("/view/admin/lottery_list.jsp").forward(request, response);
				return;
			}

			page = Integer.parseInt(request.getParameter("page"));
			search = request.getParameter("search");
			if (search == null)
				search = "";
			region = request.getParameter("region");
			colOrder = request.getParameter("colOrder");
			order = request.getParameter("order");
			
			deleteLotteriesList = (List<Integer>) session.getAttribute("deleteLotteriesList");	
			session.setAttribute("deleteLotteriesList", deleteLotteriesList);
			// ajax - load lottery -----------------------------------------------------------
			if (action != null && action.equals("loadLottery")) {
				if (region != null && region.equals("Tất cả")) {
					int d = 0;
					// Miền Bắc-------------------------------------------------------------------
					lotteryShow = lotteryDAO.getLotteriesList(search, "Miền Bắc", colOrder, order, page, sum);
					if (lotteryShow.size() > 0) {
						d++;
						out.println("<p>" + d + ". Miền Bắc:</p><table\r\n"
								+ "class=\"table table-striped  table-sm text-start border border-3 border-dark\"> <thead class=\"table-danger\">\r\n"
								+ "	<tr class=\"align-top border \"> <th class=\"border \" scope=\"col-1\">Giải</th>");

						for (LotteryShow result : lotteryShow) {							
								out.println("<th onmouseover=\"showButton(" + result.getLottery().getLottery_id()
										+ ")\" onmouseout=\"hiddenButton(" + result.getLottery().getLottery_id()
										+ ")\" "
										+ "class=\"border text-center item-lottery position-relative\" scope=\"col\">\r\n"
										+ "	 <p>" + result.getDayOfWeek() + " - " + result.getDate() + " - "
										+ result.getRegion() + "</p>\r\n" + "	<p>" + result.getProvince() + "\r\n");
								boolean check = false;
								for (Integer i : deleteLotteriesList) {
									if (i == result.getLottery().getLottery_id()) {
										out.println("<input class=\"form-check-input deleteList\" id=\"delete"
												+ result.getLottery().getLottery_id() + "\" type=\"checkbox\"\r\n"
												+ "name=\"delete\" checked onchange=\"deleteLottery("
												+ result.getLottery().getLottery_id() + "," + page + ")\" />\r\n");
										check = true;
										break;
									}
								}

								if (!check) {
									out.println("<input class=\"form-check-input deleteList\"\r\n id=\"delete"
											+ result.getLottery().getLottery_id()
											+ "\" type=\"checkbox\"\r\n name=\"delete\"\r\n"
											+ " onchange=\"deleteLottery(" + result.getLottery().getLottery_id() + ","
											+ page + ")\" />\r\n");
								}

								out.println(
										" </p>\r\n <div class=\"show-button d-none bg-white position-absolute badge top-50 start-0 h-50\"\r\n"
												+ "	id=\"no_" + result.getLottery().getLottery_id() + "\">\r\n"
												+ " <button type=\"submit\" class=\"btn  btn-outline-none\"\r\n title=\"Xóa\"\r\n onclick=\"confirmDeleteLottery("
												+ result.getLottery().getLottery_id() + "," + page
												+ ")\">\r\n <i class=\"bi bi-trash\"></i>\r\n </button>\r\n"
												+ " <button type=\"submit\" class=\"btn btn-outline-none\"\r\n title=\"Cập nhật\"\r\n onclick='updateLottery("
												+ result.getLottery().getLottery_id()
												+ ")'>\r\n <i class=\"bi bi-pencil-square\"></i>\r\n </button>\r\n </div>\r\n </th>\r\n");
							

						}
						out.println("</tr>\r\n" + "					</thead>\r\n"
								+ "					<tbody class=\"table-group-divider\">\r\n"
								+ "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">ĐB</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSpecial_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">1</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFirst_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">2</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSecond_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">3</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getThird_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">4</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFourth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">5</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFifth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">6</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSixth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">7</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSeventh_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" +"					</tbody>\r\n" + "				</table>");

					}

					// Miền Trung-------------------------------------------------------------------
					lotteryShow = lotteryDAO.getLotteriesList(search, "Miền Trung", colOrder, order, page, sum);
					if (lotteryShow.size() > 0) {
						d++;
						out.println("<p>" + d + ". Miền Trung:</p><table\r\n"
								+ "					class=\"table table-striped  table-sm text-start border border-3 border-dark\">\r\n"
								+ "					<thead class=\"table-danger\">\r\n"
								+ "						<tr class=\"align-top border \">\r\n"
								+ "							<th class=\"border \" scope=\"col-1\">Giải</th>");

						for (LotteryShow result : lotteryShow) {
								out.println("<th onmouseover=\"showButton(" + result.getLottery().getLottery_id()
										+ ")\"\r\n" + "										onmouseout=\"hiddenButton("
										+ result.getLottery().getLottery_id() + ")\"\r\n"
										+ "										class=\"border text-center item-lottery position-relative\"\r\n"
										+ "										scope=\"col\">\r\n"
										+ "										<p>" + result.getDayOfWeek() + " - "
										+ result.getDate() + " - " + result.getRegion() + "</p>\r\n"
										+ "										<p>" + result.getProvince() + "\r\n");
								boolean check = false;

								for (Integer i : deleteLotteriesList) {
									if (i == result.getLottery().getLottery_id()) {
										out.println("<input class=\"form-check-input deleteList\" id=\"delete"
												+ result.getLottery().getLottery_id() + "\" type=\"checkbox\"\r\n"
												+ "name=\"delete\" checked onchange=\"deleteLottery("
												+ result.getLottery().getLottery_id() + "," + page + ")\" />\r\n");
										check = true;

										break;

									}
								}
								if (!check) {
									out.println("<input class=\"form-check-input deleteList\"\r\n"
											+ "													id=\"delete"
											+ result.getLottery().getLottery_id() + "\" type=\"checkbox\"\r\n"
											+ "													name=\"delete\"\r\n"
											+ "													onchange=\"deleteLottery("
											+ result.getLottery().getLottery_id() + "," + page + ")\" />\r\n");
								}

								out.println("										</p>\r\n"
										+ "										<div\r\n"
										+ "											class=\"show-button d-none bg-white position-absolute badge top-50 start-0 h-50\"\r\n"
										+ "											id=\"no_"
										+ result.getLottery().getLottery_id() + "\">\r\n"
										+ "											<button type=\"submit\" class=\"btn  btn-outline-none\"\r\n"
										+ "												title=\"Xóa\"\r\n"
										+ "												onclick=\"confirmDeleteLottery("
										+ result.getLottery().getLottery_id() + "," + page + ")\">\r\n"
										+ "												<i class=\"bi bi-trash\"></i>\r\n"
										+ "											</button>\r\n"
										+ "											<button type=\"submit\" class=\"btn btn-outline-none\"\r\n"
										+ "												title=\"Cập nhật\"\r\n"
										+ "												onclick='updateLottery("
										+ result.getLottery().getLottery_id() + ")'>\r\n"
										+ "												<i class=\"bi bi-pencil-square\"></i>\r\n"
										+ "											</button>\r\n"
										+ "										</div>\r\n"
										+ "									</th>\r\n");

						}
						out.println("</tr>\r\n" + "					</thead>\r\n"
								+ "					<tbody class=\"table-group-divider\">\r\n"
								+ "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">ĐB</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSpecial_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">1</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFirst_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">2</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSecond_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">3</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getThird_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">4</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFourth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">5</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFifth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">6</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSixth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">7</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSeventh_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">8</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getEighth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "					</tbody>\r\n" + "				</table>");

					}
					// Miền Nam-------------------------------------------------------------------
					lotteryShow = lotteryDAO.getLotteriesList(search, "Miền Nam", colOrder, order, page, sum);
					if (lotteryShow.size() > 0) {
						d++;
						out.println("<p>" + d + ". Miền Nam:</p><table\r\n"
								+ "					class=\"table table-striped  table-sm text-start border border-3 border-dark\">\r\n"
								+ "					<thead class=\"table-danger\">\r\n"
								+ "						<tr class=\"align-top border \">\r\n"
								+ "							<th class=\"border \" scope=\"col-1\">Giải</th>");

						for (LotteryShow result : lotteryShow) {
								out.println("<th onmouseover=\"showButton(" + result.getLottery().getLottery_id()
										+ ")\"\r\n" + "										onmouseout=\"hiddenButton("
										+ result.getLottery().getLottery_id() + ")\"\r\n"
										+ "										class=\"border text-center item-lottery position-relative\"\r\n"
										+ "										scope=\"col\">\r\n"
										+ "										<p>" + result.getDayOfWeek() + " - "
										+ result.getDate() + " - " + result.getRegion() + "</p>\r\n"
										+ "										<p>" + result.getProvince() + "\r\n");
								boolean check = false;

								for (Integer i : deleteLotteriesList) {
									if (i == result.getLottery().getLottery_id()) {
										out.println("<input class=\"form-check-input deleteList\" id=\"delete"
												+ result.getLottery().getLottery_id() + "\" type=\"checkbox\"\r\n"
												+ "name=\"delete\" checked onchange=\"deleteLottery("
												+ result.getLottery().getLottery_id() + "," + page + ")\" />\r\n");
										check = true;

										break;

									}
								}
								if (!check) {
									out.println("<input class=\"form-check-input deleteList\"\r\n"
											+ "													id=\"delete"
											+ result.getLottery().getLottery_id() + "\" type=\"checkbox\"\r\n"
											+ "													name=\"delete\"\r\n"
											+ "													onchange=\"deleteLottery("
											+ result.getLottery().getLottery_id() + "," + page + ")\" />\r\n");
								}

								out.println("										</p>\r\n"
										+ "										<div\r\n"
										+ "											class=\"show-button d-none bg-white position-absolute badge top-50 start-0 h-50\"\r\n"
										+ "											id=\"no_"
										+ result.getLottery().getLottery_id() + "\">\r\n"
										+ "											<button type=\"submit\" class=\"btn  btn-outline-none\"\r\n"
										+ "												title=\"Xóa\"\r\n"
										+ "												onclick=\"confirmDeleteLottery("
										+ result.getLottery().getLottery_id() + "," + page + ")\">\r\n"
										+ "												<i class=\"bi bi-trash\"></i>\r\n"
										+ "											</button>\r\n"
										+ "											<button type=\"submit\" class=\"btn btn-outline-none\"\r\n"
										+ "												title=\"Cập nhật\"\r\n"
										+ "												onclick='updateLottery("
										+ result.getLottery().getLottery_id() + ")'>\r\n"
										+ "												<i class=\"bi bi-pencil-square\"></i>\r\n"
										+ "											</button>\r\n"
										+ "										</div>\r\n"
										+ "									</th>\r\n");
							
						}
						out.println("</tr>\r\n" + "					</thead>\r\n"
								+ "					<tbody class=\"table-group-divider\">\r\n"
								+ "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">ĐB</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSpecial_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">1</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFirst_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">2</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSecond_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">3</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getThird_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">4</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFourth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">5</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFifth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">6</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSixth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">7</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSeventh_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">8</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getEighth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "					</tbody>\r\n" + "				</table>");

					}
					if (d == 0) {
						out.println("<p style=\"color: red;\">Không có kết quả.<p>");
					}

				} else {
					// Hiển thị chỉ 1 miền
					lotteryShow = lotteryDAO.getLotteriesList(search, region, colOrder, order, page, sum);
					if (lotteryShow.size() == 0) {
						out.println("<p style=\"color: red;\">Không có kết quả.<p>");

					} else {

						out.println("<table\r\n"
								+ "					class=\"table table-striped  table-sm text-start border border-3 border-dark\">\r\n"
								+ "					<thead class=\"table-danger\">\r\n"
								+ "						<tr class=\"align-top border \">\r\n"
								+ "							<th class=\"border \" scope=\"col-1\">Giải</th>");

						for (LotteryShow result : lotteryShow) {
							out.println("<th onmouseover=\"showButton(" + result.getLottery().getLottery_id()
										+ ")\"\r\n" + "										onmouseout=\"hiddenButton("
										+ result.getLottery().getLottery_id() + ")\"\r\n"
										+ "										class=\"border text-center item-lottery position-relative\"\r\n"
										+ "										scope=\"col\">\r\n"
										+ "										<p>" + result.getDayOfWeek() + " - "
										+ result.getDate() + " - " + result.getRegion() + "</p>\r\n"
										+ "										<p>" + result.getProvince() + "\r\n");
								boolean check = false;

								for (Integer i : deleteLotteriesList) {
									if (i == result.getLottery().getLottery_id()) {
										out.println("<input class=\"form-check-input deleteList\" id=\"delete"
												+ result.getLottery().getLottery_id() + "\" type=\"checkbox\"\r\n"
												+ "name=\"delete\" checked onchange=\"deleteLottery("
												+ result.getLottery().getLottery_id() + "," + page + ")\" />\r\n");
										check = true;

										break;

									}
								}
								if (!check) {
									out.println("<input class=\"form-check-input deleteList\"\r\n"
											+ "													id=\"delete"
											+ result.getLottery().getLottery_id() + "\" type=\"checkbox\"\r\n"
											+ "													name=\"delete\"\r\n"
											+ "													onchange=\"deleteLottery("
											+ result.getLottery().getLottery_id() + "," + page + ")\" />\r\n");
								}

								out.println("										</p>\r\n"
										+ "										<div\r\n"
										+ "											class=\"show-button d-none bg-white position-absolute badge top-50 start-0 h-50\"\r\n"
										+ "											id=\"no_"
										+ result.getLottery().getLottery_id() + "\">\r\n"
										+ "											<button type=\"submit\" class=\"btn  btn-outline-none\"\r\n"
										+ "												title=\"Xóa\"\r\n"
										+ "												onclick=\"confirmDeleteLottery("
										+ result.getLottery().getLottery_id() + "," + page + ")\">\r\n"
										+ "												<i class=\"bi bi-trash\"></i>\r\n"
										+ "											</button>\r\n"
										+ "											<button type=\"submit\" class=\"btn btn-outline-none\"\r\n"
										+ "												title=\"Cập nhật\"\r\n"
										+ "												onclick='updateLottery("
										+ result.getLottery().getLottery_id() + ")'>\r\n"
										+ "												<i class=\"bi bi-pencil-square\"></i>\r\n"
										+ "											</button>\r\n"
										+ "										</div>\r\n"
										+ "									</th>\r\n");
							
						}
						out.println("</tr>\r\n" + "					</thead>\r\n"
								+ "					<tbody class=\"table-group-divider\">\r\n"
								+ "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">ĐB</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSpecial_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">1</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFirst_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">2</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSecond_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">3</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getThird_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">4</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFourth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">5</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getFifth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">6</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSixth_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" + "						<tr>\r\n"
								+ "							<th class=\"border col-1\" scope=\"col\">7</th>");
						for (LotteryShow result : lotteryShow) {
							out.println("<td class=\"border text-center\">" + result.getLottery().getSeventh_prize()
									+ "</td>");
						}
						out.println("</tr>\r\n" );
						if(!region.equals("Miền Bắc")) {
							out.println("<tr>\r\n"
									+ "							<th class=\"border col-1\" scope=\"col\">8</th>");
							for (LotteryShow result : lotteryShow) {
								out.println("<td class=\"border text-center\">" + result.getLottery().getEighth_prize()
										+ "</td>");
							}
							out.println("</tr>\r\n");
						}
							
						out.println("					</tbody>\r\n" + "				</table>");
					}
				}
			}

			// ajax - change page
			if (action != null && action.equals("changePage")) {
				int pages = lotteryDAO.getLotteriesPages(search, region, colOrder, sum);
				if (region != null && region.equals("Tất cả")) {
					pages = Math.max(lotteryDAO.getLotteriesPages(search, "Miền Bắc", colOrder, sum),
							Math.max(lotteryDAO.getLotteriesPages(search, "Miền Trung", colOrder, sum),
									lotteryDAO.getLotteriesPages(search, "Miền Nam", colOrder, sum)));

				}

				if (page > 1) {
					out.println("<li class=\"page-item\">\r\n"
							+ "							<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
							+ "								title=\"Chuyển đến trang 1\" onclick=\"doAction(1);changepage(1)\">\r\n"
							+ "								<span aria-hidden=\"true\">&laquo;</span>\r\n"
							+ "							</button>\r\n" + "						</li>\r\n"
							+ "						<li class=\"page-item\">\r\n"
							+ "							<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
							+ "								title=\"Chuyển đến trang " + (page - 1)
							+ "\" onclick=\"doAction(" + (page - 1) + ");changePage(" + (page - 1) + ")\">\r\n"
							+ "								<span aria-hidden=\"true\">&lsaquo;</span>\r\n"
							+ "							</button>\r\n" + "						</li>\r\n");
				}
				if (page < 4) {
					for (int i = 1; i < page; i++) {
						out.println("							<li class=\"page-item\">\r\n"
								+ "								<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
								+ "									title=\"Chuyển đến trang " + i
								+ "\" onclick=\"doAction(" + i + ");changePage(" + i + ")\">\r\n" + i + "</button>\r\n"
								+ "							</li>\r\n");
					}

				}
				if (page > 3) {
					for (int i = page - 3; i < page; i++) {
						out.println("<li class=\"page-item\">\r\n"
								+ "								<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
								+ "									title=\"Chuyển đến trang " + i
								+ "\" onclick=\"doAction(" + i + ");changePage(" + i + ")\">\r\n" + i
								+ "									</button>\r\n"
								+ "							</li>\r\n");
					}
				}
				out.println("					<!-- current page -->\r\n"
						+ "					<li class=\"page-item \">\r\n"
						+ "						<button type=\"submit\" class=\"btn  btn-primary active\"\r\n"
						+ "							title=\"Chuyển đến trang " + page + "\" onclick=\"doAction(" + page
						+ ");changePage(" + page + ")\">\r\n" + page + "							</button>\r\n"
						+ "					</li>\r\n" + "\r\n" + "					<!-- next pages -->\r\n");
				if (page < pages) {
					for (int i = page + 1; i <= page + 3; i++) {
						if (i <= pages) {
							out.println("<li class=\"page-item\">\r\n"
									+ "									<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
									+ "										title=\"Chuyển đến trang " + i
									+ "\" onclick=\"doAction(" + i + ");changePage(" + i + ")\">\r\n" + i
									+ "										</button>\r\n"
									+ "								</li>\r\n");
						}
					}
				}
				if (page + 1 <= pages) {
					out.println("<li class=\"page-item\">\r\n"
							+ "							<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
							+ "								title=\"Chuyển đến trang " + (page + 1)
							+ "\" onclick=\"doAction(" + (page + 1) + ");changePage(" + (page + 1) + ")\">\r\n"
							+ "								<span aria-hidden=\"true\">&rsaquo;</span>\r\n"
							+ "							</button>\r\n" + "						</li>\r\n"
							+ "						<li class=\"page-item\">\r\n"
							+ "							<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
							+ "								title=\"Chuyển đến trang " + pages
							+ "\" onclick=\"doAction(" + pages + ");changePage(" + pages + ") \">\r\n"
							+ "								<span aria-hidden=\"true\">&raquo;</span>\r\n"
							+ "							</button>\r\n 							</li>\r\n"

					);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteriesListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
