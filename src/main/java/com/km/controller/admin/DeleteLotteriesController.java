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
import com.km.dao.LotteryDAO;
import com.km.model.Account;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Xóa kết quả xổ số của Admin.
 */
@WebServlet("/DeleteLotteriesController")
public class DeleteLotteriesController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new delete lotteries controller.
	 */
	public DeleteLotteriesController() {
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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn;
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(true);
			conn = new DBContext().getConnection();
			LotteryDAO lotteryDAO = new LotteryDAO(conn);

			@SuppressWarnings("unchecked")
			List<Integer> deleteLotteriesList = (List<Integer>) session.getAttribute("deleteLotteriesList");

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

			// Nếu tài khoản có quyền là admin
			String action = request.getParameter("action");
			if (action == null) {
				request.getRequestDispatcher("/LotteriesList").forward(request, response);
				return;
			}

			// Thông báo kết quả xóa 1 kết quả xổ số
			if (action != null && action.equals("deleteLottery")) {
				String id = request.getParameter("lotteryId");
				if (id != null) {
					int lotteryId = Integer.parseInt(id);
					lotteryDAO.deleteLottery(lotteryId);
					request.setAttribute("isError", lotteryDAO.isError() ? 1 : 0);

					if (!lotteryDAO.isError() && deleteLotteriesList.contains(lotteryId)) {
						deleteLotteriesList.remove(deleteLotteriesList.indexOf(lotteryId));
					}
					session.setAttribute("deleteLotteriesList", deleteLotteriesList);
					int page = Integer.parseInt(request.getParameter("page"));
					request.setAttribute("page", page);
					request.getRequestDispatcher("/view/admin/lottery_list.jsp").forward(request, response);

					return;
				}
			}

			// Thông báo kết quả xóa nhiều kết quả xổ số
			if (action != null && action.equals("deleteLotteries")) {
				if (!deleteLotteriesList.isEmpty()) {
					lotteryDAO.deleteLottery(deleteLotteriesList);
					session.setAttribute("deleteLotteriesList", new ArrayList<>());
					request.setAttribute("page", 1);
					request.setAttribute("isError", lotteryDAO.isError() ? 1 : 0);
					request.getRequestDispatcher("/view/admin/lottery_list.jsp").forward(request, response);

					return;
				} else {
					session.setAttribute("deleteLotteriesList", new ArrayList<>());
					request.setAttribute("page", 1);
					request.setAttribute("isError", 1);
					request.getRequestDispatcher("/view/admin/lottery_list.jsp").forward(request, response);
					return;
				}
			}

			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(DeleteLotteriesController.class.getName()).log(Level.SEVERE, null, e);
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

			Account account = (Account) session.getAttribute("account");
			// Phân quyền tài khoản
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			if (account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			// Nếu tài khoản có quyền là admin
			session.setAttribute("account", account);
			List<Integer> deleteLotteriesList = new ArrayList<Integer>();
			deleteLotteriesList = (List<Integer>) session.getAttribute("deleteLotteriesList");
			int page = Integer.parseInt(request.getParameter("page"));
			String isDelete = request.getParameter("isDelete");
			String action = request.getParameter("action");

			//ajax:  thêm KQXS vào danh sách xóa
			if (action != null && action.equals("addDelete")) {
				int lotteryId = Integer.parseInt(request.getParameter("lotteryId"));
				if (isDelete != null && isDelete.equals("add")) {
					deleteLotteriesList.add(lotteryId);
					session.setAttribute("deleteLotteriesList", deleteLotteriesList);
				} else {
					if (!deleteLotteriesList.isEmpty()) {
						if (deleteLotteriesList.contains(lotteryId))
							deleteLotteriesList.remove(deleteLotteriesList.indexOf(lotteryId));
						session.setAttribute("deleteLotteriesList", deleteLotteriesList);
						session.setAttribute("page", page);
					}
				}

				if (deleteLotteriesList.size() == 0) {
					out.println("<div class=\"col-4 \">\r\n"
							+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"alertCantDelete()\">Xóa</button>\r\n"
							+ "	</div>\r\n");
				} else {
					out.println("<div class=\"col-4 col-sm-2 me-1  \">\r\n"
							+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"confirmDeleteLotteries()\">Xóa</button>\r\n"
							+ "	</div>\r\n" + "	<div class=\"col-7 col-sm-9 \">\r\n"
							+ "							<button class=\" btn btn-primary ms-3 py-1 px-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"unSelectedList("
							+ page + ")\">Bỏ\r\n" + "								chọn tất cả</button>\r\n"
							+ "</div>\r\n");

				}

				return;
			}

			// ajax: Hủy chọn danh sách KQXS cần xóa
			if (action != null && action.equals("removeAllDeleteList")) {
				deleteLotteriesList.clear();
				session.setAttribute("deleteLotteriesList", deleteLotteriesList);
				out.println("<div class=\"col-4  \">\r\n"
						+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
						+ "								title=\"Xóa các mục đã chọn\" onclick=\"alertCantDelete()\">Xóa</button>\r\n"
						+ " </div>\r\n");

				return;
			}
			
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(LotteriesListController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
