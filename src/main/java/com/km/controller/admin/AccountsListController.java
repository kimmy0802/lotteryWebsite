package com.km.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.model.Province;

import com.km.database.DBContext;
import com.km.dao.AccountDAO;
import com.km.dao.ProvincesDAO;
import com.km.model.Account;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Hiển thị danh sách Tài khoản có trong hệ thống.
 */
@WebServlet("/AccountsListController")
public class AccountsListController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new accounts list controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountsListController() {
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
			session.setAttribute("searchLotteries", null);
			Account account=(Account) session.getAttribute("account");
			// Phân quyền tài khoản
			if(account==null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);
			if(account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			AccountDAO accountDAO = new AccountDAO(conn);
			ProvincesDAO provincesDAO=new ProvincesDAO(conn);

			List<Province> provinces = provincesDAO.getProvinces();
			session.setAttribute("provinces", provinces);

			Map<String, String> listColOrderMap = new HashMap<String, String>();
			listColOrderMap.put("username", "Tên đăng nhập");
			listColOrderMap.put("fullname", "Họ và Tên");
			listColOrderMap.put("phonenumber", "Số điện thoại");
			listColOrderMap.put("email", "Emai");
			listColOrderMap.put("part", "Quyền truy cập");
			listColOrderMap.put("created_date", "Ngày tham gia");			
			listColOrderMap.put("activated", "Hoạt động");
			listColOrderMap.put("created_by", "Tài khoản");
			session.setAttribute("listColOrderMap", listColOrderMap);

			String action = request.getParameter("action");

			if (action == null) {
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				List<Account> accounts = accountDAO.getAccounts("","username", "asc", 0);
				int pages = accountDAO.getAccountsPages("");

				session.setAttribute("page", 1);
				session.setAttribute("pages", pages);
				session.setAttribute("order", "asc");
				session.setAttribute("colOrder", "username");
				session.setAttribute("searchAccounts", null);
				session.setAttribute("accounts", accounts);
				session.setAttribute("deleteAccountsList", new ArrayList<>());
				request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);
				return;
			}

			if (action != null && action.equals("accountsList")) {
				String search = (String) session.getAttribute("searchAccounts");
				
				String colOrder = (String) session.getAttribute("colOrder");
				if (colOrder == null)
					colOrder = "username";

				String order = (String) session.getAttribute("order");
				if (order == null)
					order = "asc";

				int page = 1;

				int pages = accountDAO.getAccountsPages(search);

				if (page > pages && pages != 0)
					page = pages;
				session.setAttribute("accounts", accountDAO.getAccounts(search, colOrder, order, page * 10 - 10));

				request.setAttribute("page", page);
				session.setAttribute("page", page);
				response.getWriter().write("action get: " + action + " AccountsListController'\n");
				response.getWriter()
						.write("page request: " + page + "\n session page: " + session.getAttribute("page"));
				request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);
				return;
			}

			 request.getRequestDispatcher("index.jsp").forward(request, response);

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

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		try (PrintWriter out = response.getWriter()) {
			
			conn = new DBContext().getConnection();
			HttpSession session = request.getSession(true);
			
			AccountDAO accountDAO = new AccountDAO(conn);			
			ProvincesDAO provincesDAO=new ProvincesDAO(conn);
			List<Province> provinces = provincesDAO.getProvinces();
			session.setAttribute("provinces", provinces);
			// Phân quyền tài khoản
			Account account=(Account) session.getAttribute("account");
			String action = request.getParameter("action");
			if(account==null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if(account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);
			if (action != null && action.equals("accountsList")) {
				int page = Integer.parseInt(request.getParameter("page"));
				request.setAttribute("page", page);
				response.getWriter().write("action post: " + action + " AccountsListController");
				request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);
			}
			// Tìm kiếm tài khoản
			if (action != null && action.equals("searchAccounts")) {
				String search = request.getParameter("searchAccounts");

				List<Account> accounts = new AccountDAO(conn).getAccounts(search, "username", "asc", 0);
				int pages = new AccountDAO(conn).getAccountsPages(search);
				session.setAttribute("page", 1);
				session.setAttribute("pages", pages);
				session.setAttribute("order", "asc");
				session.setAttribute("colOrder", "username");
				session.setAttribute("accounts", accounts);
				session.setAttribute("searchAccounts", search);
				session.setAttribute("deleteAccountsList", new ArrayList<>());
				if (search.equals(""))
					session.setAttribute("searchAccounts", null);
				request.getRequestDispatcher("/view/admin/admin_accounts_detail.jsp").forward(request, response);
				return;
			}

			// ajax - change page
			if (action != null && action.equals("changePage")) {				
				int page = Integer.parseInt(request.getParameter("page"));
				String search =request.getParameter("searchAccounts"); 				
				int pages=accountDAO.getAccountsPages(search);
				
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
				String search =request.getParameter("searchAccounts"); 
				String colOrder = request.getParameter("colOrder");
				String order = request.getParameter("order");
				
				List<Account> accounts = new ArrayList<>();
				accounts = accountDAO.getAccounts(search, colOrder, order, page * 10 - 10);
				List<String> deleteAccountsList = (List<String>) session.getAttribute("deleteAccountsList");
				if(accounts.size()==0) {
					out.println("<p style=\"color: red;\">Không có kết quả.</p>");
					return;
				}
				
				out.println("<table class=\"table table-striped table-sm text-start\">\r\n"
						+ "				<thead>\r\n"
						+ "					<tr class=\"align-top\">\r\n"
						+ "						<th scope=\"col\">STT</th>\r\n"
						+ "						<th scope=\"col\">Tên đăng nhập</th>\r\n"
						+ "						<th scope=\"col\">Họ và tên</th>\r\n"
						+ "						<th scope=\"col\">Số điện thoại</th>\r\n"
						+ "						<th scope=\"col\">Email</th>\r\n"
						+ "						<th scope=\"col\">Ngày tham gia</th>\r\n"
						+ "						<th scope=\"col\">Hoạt động</th>\r\n"
						+ "						<th scope=\"col\">Quyền</th>\r\n"
						+"						<th scope=\"col\">Tài khoản</th>"
						+ "						<th class=\"text-center\" scope=\"col\">Chọn</th>\r\n"
						+ "					</tr>\r\n"
						+ "				</thead>\r\n"
						+ "				<tbody>\r\n");
				int j=page*10-10;
				
				for(Account result:accounts) {
					j++;
					out.println("<tr onmouseover=\"showButton("+j+");addColor(this)\" onmouseout=\"hiddenButton("+j+");removeColor(this)\">\r\n"
							+ " <td class=\"item-account position-relative\"\r\n"
							+ "	 id=\"stt${result.username}\">\r\n"
							+ "	 <div\r\n"
							+ "	 class=\"show-button d-none bg-white position-absolute badge top-50 start-0 h-50\"\r\n id=\"no_"+j+"\">\r\n"
							+ " <button type=\"submit\" class=\"btn btn-outline-none \"\r\n title=\"Xóa\"\r\n"
							+ "	onclick='confirmDeleteAccount(\""+account.getPart()+"\",\""+result.getUsername()+"\","+page+",\""+result.getPart()+"\","+result.getActivate()+")'>\r\n"
							+ " <i class=\"bi bi-trash\"></i>\r\n </button>\r\n"
							+ "	 <button type=\"submit\" class=\"btn btn-outline-none \"\r\n title=\"Chỉnh sửa Tài khoản người dùng\"\r\n"
							+" onclick='updateAccount(\""+result.getUsername()+"\",\""+result.getPart()+"\",\""+account.getPart()+"\")'>\r\n"
							+ " <i class=\"bi bi-pencil-square\"></i>\r\n </button>\r\n"
							+ "	 <button type=\"submit\" class=\"btn btn-outline-none \"\r\n title=\"Đổi mật khẩu\"\r\n"
							+ " onclick='changePassword(\""+result.getUsername()+"\",\""+result.getPart()+"\",\""+account.getPart()+"\")'>\r\n"
							+ " <i class=\"bi bi-key\"></i>\r\n </button>\r\n </div>\r\n <div>\r\n"
							+ j+"</div>\r\n </td>\r\n"
							+ " <td height=\"60px;\" class=\"\">"
							+ result.getUsername()+"</td>\r\n"
							+ " <td>"+result.getFullname()+"</td>\r\n"
							+ " <td>"+(result.getPhonenumber().equals("")?"":(result.getPhonenumber().substring(0,3)+"*******"))+"</td>\r\n"
							+ " <td>"+(result.getEmail().equals("")?"":(result.getEmail().substring(0,3)+"*******"))+"</td>\r\n"
							+ " <td>"+result.getCreated_date()+"</td>\r\n"
							+ " <td>" + (result.getActivate()==0?"Offline":"Online")+"</td>\r\n"
							+ " <td>"+result.getPart()+"</td>\r\n"
							+ "	<td>"+result.getCreated_by()+"</td>\r\n"
							+ " <td class=\"text-center\">\r\n");
					boolean isChecked=false;
					for(String i:deleteAccountsList) {
						if(i.equals(result.getUsername())){
							isChecked=true;
							break;
						}
					}
					if(isChecked) {
						out.println("<input class=\"form-check-input deleteList\"\r\n"
								+ "	id=\"d_"+result.getUsername()+"\" type=\"checkbox\" name=\"delete\"  checked" 
								+" onchange='deleteAccount(\""+account.getPart()+"\",\""+result.getUsername()+"\","+page+",\""+result.getPart()+"\")' />\r\n");
					}else {
						out.println("<input class=\"form-check-input deleteList\"\r\n"
								+ "	id=\"d_"+result.getUsername()+"\" type=\"checkbox\" name=\"delete\"\r\n"
								+" onchange='deleteAccount(\""+account.getPart()+"\",\""+result.getUsername()+"\","+page+",\""+result.getPart()+"\")' />\r\n");
					}
						out.println( " </td>\r\n </tr>\r\n");
					
				}
				out.println("</tbody>\r\n </table>");
				return;
			}
			
			response.getWriter().write("action: post" + action);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
