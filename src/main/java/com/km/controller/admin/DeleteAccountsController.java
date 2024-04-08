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

import com.km.database.DBContext;
import com.km.dao.AccountDAO;
import com.km.model.Account;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Xóa Tài khoản của Admin.
 */
@WebServlet("/DeleteAccountsController")
public class DeleteAccountsController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new delete accounts controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAccountsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(true);
			conn = new DBContext().getConnection();
			AccountDAO accountDAO = new AccountDAO(conn);
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

			List<String> deleteAccountsList = new ArrayList<>();
			String search = (String) session.getAttribute("searchAccounts");

			if (session.getAttribute("deleteAccountsList") != null)
				deleteAccountsList = (List<String>) session.getAttribute("deleteAccountsList");

			String p = request.getParameter("page");
			if (p == null)
				p = "1";
			int page = Integer.parseInt(p);
			session.setAttribute("page", page);

			String action = request.getParameter("action");
			if (action != null && action.equals("deleteAccount")) {
				String usernameDelete = request.getParameter("usernameDelete");
				int activate = accountDAO.getActivated(usernameDelete);
				if (usernameDelete != null) {
					if (activate == 1) {
						accountDAO.setActivated(usernameDelete, 0);
						request.setAttribute("isError", 1);
					} else {
						accountDAO.deleteAccount(usernameDelete);
						request.setAttribute("isError", accountDAO.isError() ? 1 : 0);
						if (!accountDAO.isError() && deleteAccountsList.contains(usernameDelete)) {
							deleteAccountsList.remove(usernameDelete);
							session.setAttribute("deleteAccountsList", deleteAccountsList);
						}
					}

					int pages = accountDAO.getAccountsPages(search);
					session.setAttribute("pages", pages);
					request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);

					return;
				}
			}

			if (action != null && action.equals("deleteAccounts")) {
				if (!deleteAccountsList.isEmpty()) {
					for (String i : deleteAccountsList)
						System.out.print(i + ", ");

					accountDAO.deleteAccount(deleteAccountsList);
					session.setAttribute("deleteAccountsList", new ArrayList<>());

					int pages = accountDAO.getAccountsPages(search);
					session.setAttribute("pages", pages);
					session.setAttribute("page", 1);
					request.setAttribute("isError", accountDAO.isError() ? 1 : 0);
					System.out.println(accountDAO.isError());
					request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);

					return;
				} else {

					request.setAttribute("isError", 1);
					System.out.println("danh sach xoa rong");
					response.getWriter().write("ds xoa rong, pages: page: 1");
					request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);
					return;
				}
			}
			int pages = accountDAO.getAccountsPages(search);
			session.setAttribute("pages", pages);
			session.setAttribute("deleteAccountsList", deleteAccountsList);
			request.getRequestDispatcher("/AccountsList?action=accountsList").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(DeleteAccountsController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Do post.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(true);
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
			List<String> deleteAccountsList = new ArrayList<String>();
			deleteAccountsList = (List<String>) session.getAttribute("deleteAccountsList");
			int page = Integer.parseInt(request.getParameter("page"));

			String action = request.getParameter("action");

			if (action != null && action.equals("addDelete")) {
				String isDelete = request.getParameter("isDelete");
				String usernameDelete = request.getParameter("usernameDelete");

				if (isDelete != null && isDelete.equals("add")) {
					if (usernameDelete != null) {
						deleteAccountsList.add(usernameDelete);
						session.setAttribute("deleteAccountsList", deleteAccountsList);
					}
				} else {
					if (!deleteAccountsList.isEmpty()) {
						if (usernameDelete != null && deleteAccountsList.contains(usernameDelete)) {
							deleteAccountsList.remove(usernameDelete);
							session.setAttribute("deleteAccountsList", deleteAccountsList);
						}
					}
				}
				System.out.println("isDelete:"+isDelete);
				for(String s:deleteAccountsList) {
					System.out.print(s+", ");
				}
				System.out.println();
				System.out.println("----------------");

				if (deleteAccountsList.size() == 0) {
					out.println("<div class=\"col-4 \">\r\n"
							+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"alertCantDelete()\">Xóa</button>\r\n"
							+ "	</div>\r\n");
				} else {
					out.println("<div class=\"col-4 col-sm-2 me-1  \">\r\n"
							+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"confirmDeleteAccounts()\">Xóa</button>\r\n"
							+ "	</div>\r\n" + "	<div class=\"col-7 col-sm-9 \">\r\n"
							+ "							<button class=\" btn btn-primary ms-3 py-1 px-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"unSelectedList("
							+ page + ")\">Bỏ chọn tất cả</button>\r\n"
							+ "</div>\r\n");

				}
				return;
			}

			// ajax unselected lotteries list
			if (action != null && action.equals("removeAllDeleteList")) {
				deleteAccountsList.clear();
				session.setAttribute("deleteAccountsList", deleteAccountsList);
				out.println("<div class=\"col-4  \">\r\n"
						+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
						+ "								title=\"Xóa các mục đã chọn\" onclick=\"alertCantDelete()\">Xóa</button>\r\n"
						+ " </div>\r\n");
				return;
			}
			
			out.print("POST: deleteAccountsList");

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteriesListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
