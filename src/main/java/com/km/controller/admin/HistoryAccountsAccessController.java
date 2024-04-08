package com.km.controller.admin;

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

import com.km.dao.HistoryAccountsAccessDAO;
import com.km.dao.ProvincesDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.HistoryAccess;
import com.km.model.Province;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Hiển thị Lịch sử Tài khoản truy cập.
 */
@WebServlet("/HistoryAccountsAccessController")
public class HistoryAccountsAccessController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new history accounts access controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryAccountsAccessController() {
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
		Connection conn; 
		try {
			conn = new DBContext().getConnection();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);		
			HistoryAccountsAccessDAO historyAccountAccessDAO = new HistoryAccountsAccessDAO(conn);
			// Phân quyền tài khoản
			Account account = (Account) session.getAttribute("account");
			if(account==null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if(account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			request.setAttribute("account", account);

			List<HistoryAccess> historyAccess = historyAccountAccessDAO.getHistoryAccess(1);
			request.setAttribute("historyAccess", historyAccess);

			int pages = historyAccountAccessDAO.getPages();
			request.setAttribute("page", 1);
			request.setAttribute("pages", pages);

			request.getRequestDispatcher("/view/admin/admin_accounts_access_history.jsp").forward(request, response);

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

			conn = new DBContext().getConnection();
			HttpSession session = request.getSession(true);
			session.setAttribute("searchLotteries", null);	
			List<Province> provinces = new ProvincesDAO(conn).getProvinces();
			session.setAttribute("provinces", provinces);
			HistoryAccountsAccessDAO historyAccountAccessDAO = new HistoryAccountsAccessDAO(conn);
			// Phân quyền tài khoản
			Account account = (Account) session.getAttribute("account");
			if(account==null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if(account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);

			String action = request.getParameter("action");
			if (action != null && action.equals("changePage")) {
				int page = Integer.parseInt(request.getParameter("page"));
				int pages = historyAccountAccessDAO.getPages();
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

				return;
			}

			// ajax - load Accounts
			if (action != null && action.equals("loadAccounts")) {
				int page = Integer.parseInt(request.getParameter("page"));
				List<HistoryAccess> historyAccess = historyAccountAccessDAO.getHistoryAccess(page);
				if(historyAccess.size()==0) {
					out.println("<p style=\"color:red;\"Không có kết quả</p>");
					return;
				}
				out.println("<table class=\"table table-striped table-sm\">\r\n"
						+ "				<thead>\r\n"
						+ "					<tr>\r\n"
						+ "						<th scope=\"col\">STT</th>\r\n"
						+ "						<th scope=\"col\">Tài khoản truy cập</th>\r\n"
						+ "						<th scope=\"col\">Ngày truy cập</th>\r\n"
						+ "					</tr>\r\n"
						+ "				</thead>\r\n"
						+ "				<tbody>\r\n");
				int i=page*10-10;
				for(HistoryAccess result:historyAccess) {
					i++;
					out.println("<tr onmouseover=\"addColor(this)\" onmouseout=\"removeColor(this)\">\r\n <td>"+i+"</td>\r\n <td>"+result.getUsername()+"</td>\r\n"
							+ " <td>"+result.getDate_access()+"</td>\r\n </tr>\r\n");
				}
				out.println("</tbody>\r\n </table>");
			}
			/*
			 * // pagination - phân trang if (action != null && action.equals("goToPage")) {
			 * 
			 * String page = request.getParameter("page"); int p = Integer.parseInt(page);
			 * List<HistoryAccess> historyAccess = historyAccountAccess.getHistoryAccess(p);
			 * request.setAttribute("historyAccess", historyAccess);
			 * 
			 * int pages = historyAccountAccess.getPages(); request.setAttribute("page", p);
			 * request.setAttribute("pages", pages);
			 * request.getRequestDispatcher("/view/admin/admin_accounts_access_history.jsp").forward(
			 * request, response); return; }
			 */
			//response.getWriter().write("action: post" + action);
			// request.getRequestDispatcher("home.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
