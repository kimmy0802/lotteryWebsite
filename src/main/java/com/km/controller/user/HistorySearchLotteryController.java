/*
 * 
 */
package com.km.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.controller.admin.AccountsListController;
import com.km.dao.HistorySearchLotteryDAO;
import com.km.dao.ProvincesDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.HistorySearch;
import com.km.model.Province;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Lịch sử dò vé số
 */
@WebServlet("/HistorySearchLotteryController")
public class HistorySearchLotteryController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new history search lottery controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public HistorySearchLotteryController() {
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
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			HistorySearchLotteryDAO historySearchLottery = new HistorySearchLotteryDAO(conn);
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			List<Province> provinces = provincesDAO.getProvinces();
			request.setAttribute("provinces", provinces);

			Account account = (Account) session.getAttribute("account");
			// Nếu chưa đăng nhập
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);

			List<HistorySearch> historySearch = historySearchLottery.getHistorySearch(account.getUsername(), 1);
			request.setAttribute("historySearch", historySearch);
			request.setAttribute("page", 1);
			int pages = historySearchLottery.getPages(account.getUsername());
			request.setAttribute("pages", pages);

			request.getRequestDispatcher("/view/user/historySearchLottery.jsp").forward(request, response);

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);
			Account account = (Account) session.getAttribute("account");
			// Nếu chưa đăng nhập
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);
			
			conn = new DBContext().getConnection();
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			List<Province> provinces = provincesDAO.getProvinces();
			request.setAttribute("provinces", provinces);

			HistorySearchLotteryDAO historySearchLottery = new HistorySearchLotteryDAO(conn);
			String action = request.getParameter("action");
			
			// Load loadHistorySearchLottery - ajax
			if (action != null && action.equals("loadHistorySearchLottery")) {
				int page = Integer.parseInt(request.getParameter("page"));
				List<HistorySearch> historySearch = historySearchLottery.getHistorySearch(account.getUsername(), page);
				if (historySearch.size() == 0) {
					out.print("<p>Lịch sử dò vé số trống.</p>");
				} else {
					int i = page * 10 - 10;
					out.println("<table class=\"table table-striped table-sm\">\r\n" + "				<thead>\r\n"
							+ "					<tr>\r\n" + "						<th scope=\"col\">STT</th>\r\n"
							+ "						<th scope=\"col\">Ngày - tháng - năm</th>\r\n"
							+ "						<th scope=\"col\">Tỉnh</th>\r\n"
							+ "						<th scope=\"col\">Số đã dò</th>\r\n"
							+ "						<th scope=\"col\">Kết quả dò</th>\r\n"
							+ "						<th scope=\"col\">Thời gian dò</th>\r\n"
							+ "					</tr>\r\n" + "				</thead>\r\n"
							+ "				<tbody>\r\n");
					for (HistorySearch result : historySearch) {
						i++;
						out.println("<tr class=\"\" title=\"click để đến trang Dò vé số\" onmouseover=\"addColor(this)\" onmouseout=\"removeColor(this)\"  "
								+ " onclick='goToSearchLotteryPage("+result.getProvince_id()+",\""+result.getLottery_date()+"\",\""+result.getLottery_number()+"\")'>\r\n"
								+ " <td>" + i + "</td>\r\n" + "	<td>" + result.getLottery_date()
								+ "</td>\r\n <td> ");
						for (Province p : provinces) {
							if (p.getProvince_id() == result.getProvince_id()) {
								out.println("" + p.getProvince());
								break;
							}
						}
						out.println(" </td>\r\n <td>" + result.getLottery_number() + "</td>\r\n <td>"
														+ result.getResultSearch() + "</td>\r\n <td>"
								+ result.getSearch_time() + "</td>\r\n </tr>\r\n");
					}
					out.println(" </tbody>\r\n </table>");

				}
				return;
			}
			// load page - ajax changepage

			if (action != null && action.equals("changePage")) {
				int page = Integer.parseInt(request.getParameter("page"));

				int pages = historySearchLottery.getPages(account.getUsername());
				if (page > 1) {
					out.println("<li class=\"page-item\">\r\n"
							+ "							<button type=\"submit\" class=\"btn btn-outline-primary\"\r\n"
							+ "								title=\"Chuyển đến trang 1\" onclick=\"doAction(1);changePage(1)\">\r\n"
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
				return;
			}

			 request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
