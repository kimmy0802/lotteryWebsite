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

import com.km.dao.DateOpenDAO;
import com.km.dao.ProvincesDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.Province;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng Quản lý nhà đài: thêm, xóa, chỉnh sửa.
 */
@WebServlet("/ProvincesController")
public class ProvincesController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new provinces controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public ProvincesController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			conn = new DBContext().getConnection();

			HttpSession session = request.getSession(true);
			session.setAttribute("searchProvince", null);
			Account account = (Account) session.getAttribute("account");
			// Phân quyền tài khoản
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);
			if (account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);

			List<String> regions = provincesDAO.getRegions();
			regions.add(0, "Tất cả");
			session.setAttribute("regions", regions);

			List<String> dateOfWeeks = new ArrayList<>();
			dateOfWeeks.add("Tất cả");
			dateOfWeeks.add("Thứ Hai");
			dateOfWeeks.add("Thứ Ba");
			dateOfWeeks.add("Thứ Tư");
			dateOfWeeks.add("Thứ Năm");
			dateOfWeeks.add("Thứ Sáu");
			dateOfWeeks.add("Thứ Bảy");
			dateOfWeeks.add("Chủ Nhật");
			session.setAttribute("dateOfWeeks", dateOfWeeks);

			String dateOfWeek = dateOfWeeks.get(0);
			String region = regions.get(0);
			List<Province> provinces = provincesDAO.getProvinces(region, dateOfWeek);
			session.setAttribute("provinces", provinces);
			request.setAttribute("provinces", provinces);

			String action = request.getParameter("action");

			if (action == null) {
				request.setAttribute("region", region);
				request.setAttribute("dateOfWeek", dateOfWeek);
				
				session.setAttribute("deleteProvincesList", new ArrayList<>());
				request.getRequestDispatcher("/view/admin/provinceList.jsp").forward(request, response);
				return;
			}

			// delete a province
			if (action != null & action.equals("deleteProvince")) {
				List<Integer> deleteProvincesList = (List<Integer>) session.getAttribute("deleteProvincesList");
				int provinceID = Integer.parseInt(request.getParameter("provinceID"));
				region = request.getParameter("region");
				dateOfWeek = request.getParameter("dateOfWeek");
				provincesDAO.deleteProvince(provinceID);
				request.setAttribute("isError", provincesDAO.isError() ? 1 : 0);
				if (!provincesDAO.isError() && deleteProvincesList.contains(provinceID)) {
					deleteProvincesList.remove(provinceID);
					session.setAttribute("deleteProvincesList", deleteProvincesList);
				}
				provinces = provincesDAO.getProvinces(region, dateOfWeek);
				request.setAttribute("region", region);
				request.setAttribute("dateOfWeek", dateOfWeek);
				request.setAttribute("provinces", provinces);
				request.getRequestDispatcher("/view/admin/provinceList.jsp").forward(request, response);
				return;

			}
			// delete provinces
			if (action != null & action.equals("deleteProvinces")) {
				List<Integer> deleteProvincesList = (List<Integer>) session.getAttribute("deleteProvincesList");
				provincesDAO.deleteProvince(deleteProvincesList);
				request.setAttribute("isError", provincesDAO.isError() ? 1 : 0);
				if (!provincesDAO.isError()) {
					deleteProvincesList.clear();
					session.setAttribute("deleteProvincesList", deleteProvincesList);
				}

				provinces = provincesDAO.getProvinces(region, dateOfWeek);
				request.setAttribute("region", region);
				request.setAttribute("dateOfWeek", dateOfWeek);
				request.setAttribute("provinces", provinces);
				request.getRequestDispatcher("/view/admin/provinceList.jsp").forward(request, response);
				return;

			}
			// updateProvince
			if (action != null & action.equals("updateProvince")) {
				int provinceID = Integer.parseInt(request.getParameter("provinceID"));
				regions.remove(0);
				session.setAttribute("regions", regions);
				Province province = provincesDAO.getProvinces(provinceID);
				request.setAttribute("regions", regions);
				request.setAttribute("region", provincesDAO.getRegion(provinceID));
				boolean thu2=provincesDAO.isDateOpen(provinceID, "Thứ Hai");
				boolean thu3=provincesDAO.isDateOpen(provinceID, "Thứ Ba");
				boolean thu4=provincesDAO.isDateOpen(provinceID, "Thứ Tư");
				boolean thu5=provincesDAO.isDateOpen(provinceID, "Thứ Năm");
				boolean thu6=provincesDAO.isDateOpen(provinceID, "Thứ Sáu");
				boolean thu7=provincesDAO.isDateOpen(provinceID, "Thứ Bảy");
				boolean thu8=provincesDAO.isDateOpen(provinceID, "Chủ Nhật");
				request.setAttribute("thu2", thu2);
				request.setAttribute("thu3", thu3);
				request.setAttribute("thu4", thu4);
				request.setAttribute("thu5", thu5);
				request.setAttribute("thu6", thu6);
				request.setAttribute("thu7", thu7);
				request.setAttribute("thu8", thu8);
				request.setAttribute("province", province);
				request.getRequestDispatcher("/view/admin/update_province.jsp").forward(request, response);
				return;

			}
			// createProvince
			if (action != null & action.equals("createProvince")) {
				regions.remove(0);
				session.setAttribute("regions", regions);
				request.setAttribute("regions", regions);

				request.setAttribute("province","");
				request.setAttribute("region","Miền Bắc");
				request.getRequestDispatcher("/view/admin/create_province.jsp").forward(request, response);
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
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Connection conn;
		try (PrintWriter out = response.getWriter()) {

			conn = new DBContext().getConnection();

			HttpSession session = request.getSession(true);
			session.setAttribute("searchProvince", null);
			Account account = (Account) session.getAttribute("account");
			// Phân quyền tài khoản
			if (account == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("account", account);
			if (account.getPart().equals("user")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);

			List<String> regions = provincesDAO.getRegions();
			regions.add(0, "Tất cả");
			session.setAttribute("regions", regions);

			List<String> dateOfWeeks = new ArrayList<>();
			dateOfWeeks.add("Tất cả");
			dateOfWeeks.add("Thứ Hai");
			dateOfWeeks.add("Thứ Ba");
			dateOfWeeks.add("Thứ Tư");
			dateOfWeeks.add("Thứ Năm");
			dateOfWeeks.add("Thứ Sáu");
			dateOfWeeks.add("Thứ Bảy");
			dateOfWeeks.add("Chủ Nhật");
			session.setAttribute("dateOfWeeks", dateOfWeeks);

			String dateOfWeek = dateOfWeeks.get(0);
			String region = regions.get(0);
			List<Integer> deleteProvincesList = (List<Integer>) session.getAttribute("deleteProvincesList");

			String action = request.getParameter("action");
			// createProvince
			if (action != null & action.equals("createProvince")) {
				String province_name = request.getParameter("province");
				region = request.getParameter("region");
				request.setAttribute("region", region);
				request.setAttribute("province", province_name);
				
				regions.remove(0);				
				request.setAttribute("regions", regions);				
				
				request.setAttribute("thu2", request.getParameter("thu2")!=null?true:false);
				request.setAttribute("thu3", request.getParameter("thu3")!=null?true:false);
				request.setAttribute("thu4", request.getParameter("thu4")!=null?true:false);
				request.setAttribute("thu5", request.getParameter("thu5")!=null?true:false);
				request.setAttribute("thu6", request.getParameter("thu6")!=null?true:false);
				request.setAttribute("thu7", request.getParameter("thu7")!=null?true:false);
				request.setAttribute("thu8", request.getParameter("thu8")!=null?true:false);
				
				if (province_name.equals("")) {					
					request.setAttribute("errorMess", "Vui lòng nhập tên nhà đài.");
					request.getRequestDispatcher("/view/admin/create_province.jsp").forward(request, response);
					return;
				}

				if (provincesDAO.isProvinceName(province_name)) {
					request.setAttribute("errorMess", "Nhà đài đã tồn tại, vui lòng chọn tên khác");
					request.getRequestDispatcher("/view/admin/create_province.jsp").forward(request, response);
					return;
				}
				
				List<String> dateOpen = new ArrayList<>();
				dateOfWeek = request.getParameter("thu2");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Hai");
				dateOfWeek = request.getParameter("thu3");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Ba");
				dateOfWeek = request.getParameter("thu4");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Tư");
				dateOfWeek = request.getParameter("thu5");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Năm");
				dateOfWeek = request.getParameter("thu6");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Sáu");
				dateOfWeek = request.getParameter("thu7");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Bảy");
				dateOfWeek = request.getParameter("thu8");
				if (dateOfWeek != null)
					dateOpen.add("Chủ Nhật");
				if (dateOpen.size() == 0) {
					request.setAttribute("errorMess", "Vui lòng Chọn ngày mở thưởng");
					request.getRequestDispatcher("/view/admin/create_province.jsp").forward(request, response);
					return;
				}		
				DateOpenDAO dateOpenDAO = new DateOpenDAO(conn);
				provincesDAO.createProvince(province_name, region);
				
				if (provincesDAO.isError()) {
					request.setAttribute("isError", 1);
					request.getRequestDispatcher("/view/admin/create_province.jsp").forward(request, response);
					return;

				} else {
					int provinceID=provincesDAO.getProvinceID(province_name);
					dateOpenDAO.insertDateOpen(dateOpen, provinceID, region);
				}
				request.setAttribute("isError", dateOpenDAO.isError() ? 1 : 0);				
				request.getRequestDispatcher("/view/admin/create_province.jsp").forward(request, response);
				return;

			}
			// updateProvince
			if (action != null & action.equals("updateProvince")) {
				int provinceID = Integer.parseInt(request.getParameter("province_id"));
				Province province = provincesDAO.getProvinces(provinceID);
				String province_name = request.getParameter("province");
				province_name=province_name.trim();
				region = request.getParameter("region");
				
				
				regions.remove(0);				
				request.setAttribute("regions", regions);				
				request.setAttribute("thu2", request.getParameter("thu2")!=null?true:false);
				request.setAttribute("thu3", request.getParameter("thu3")!=null?true:false);
				request.setAttribute("thu4", request.getParameter("thu4")!=null?true:false);
				request.setAttribute("thu5", request.getParameter("thu5")!=null?true:false);
				request.setAttribute("thu6", request.getParameter("thu6")!=null?true:false);
				request.setAttribute("thu7", request.getParameter("thu7")!=null?true:false);
				request.setAttribute("thu8", request.getParameter("thu8")!=null?true:false);
				
				if (province_name.equals("")) {
					province.setProvince(province_name);
					province.setRegion(region);
					request.setAttribute("province", province);
					request.setAttribute("region", region);	
					request.setAttribute("errorMess", "Vui lòng nhập tên nhà đài.");
					request.getRequestDispatcher("/view/admin/update_province.jsp").forward(request, response);
					return;
				}

				if (provincesDAO.isProvinceName(province_name) && !province_name.equals(province.getProvince())) {
					province.setProvince(province_name);
					province.setRegion(region);
					request.setAttribute("province", province);
					request.setAttribute("region", region);	
					request.setAttribute("errorMess", "Nhà đài đã tồn tại, vui lòng chọn tên khác");
					request.getRequestDispatcher("/view/admin/update_province.jsp").forward(request, response);
					return;
				}

				
				List<String> dateOpen = new ArrayList<>();
				dateOfWeek = request.getParameter("thu2");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Hai");
				dateOfWeek = request.getParameter("thu3");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Ba");
				dateOfWeek = request.getParameter("thu4");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Tư");
				dateOfWeek = request.getParameter("thu5");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Năm");
				dateOfWeek = request.getParameter("thu6");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Sáu");
				dateOfWeek = request.getParameter("thu7");
				if (dateOfWeek != null)
					dateOpen.add("Thứ Bảy");
				dateOfWeek = request.getParameter("thu8");
				if (dateOfWeek != null)
					dateOpen.add("Chủ Nhật");
				if (dateOpen.size() == 0) {
					request.setAttribute("errorMess", "Vui lòng Chọn ngày mở thưởng");
					request.getRequestDispatcher("/view/admin/update_province.jsp").forward(request, response);
					return;
				}

				DateOpenDAO dateOpenDAO = new DateOpenDAO(conn);
				provincesDAO.updateProvince(provinceID, province_name, region);
				if (provincesDAO.isError()) {
					province.setProvince(province_name);
					province.setRegion(region);
					request.setAttribute("province", province);
					request.setAttribute("region", region);	
					request.setAttribute("isError", 1);
					request.getRequestDispatcher("/view/admin/update_province.jsp").forward(request, response);
					return;

				} else {
					dateOpenDAO.deleteDateOpen(provinceID);
					dateOpenDAO.insertDateOpen(dateOpen, provinceID, region);
				}
				province.setProvince(province_name);
				province.setRegion(region);
				request.setAttribute("province", province);
				request.setAttribute("region", region);	
				request.setAttribute("isError", dateOpenDAO.isError() ? 1 : 0);				
				request.getRequestDispatcher("/view/admin/update_province.jsp").forward(request, response);
				return;
			}

			// filter
			if (action != null && action.equals("filter")) {
				dateOfWeek = request.getParameter("dateOfWeek");
				region = request.getParameter("region");
				List<Province> provinces = provincesDAO.getProvinces(region, dateOfWeek);
				session.setAttribute("provinces", provinces);

				request.setAttribute("region", region);
				request.setAttribute("dateOfWeek", dateOfWeek);

				session.setAttribute("deleteProvincesList", deleteProvincesList);
				request.getRequestDispatcher("/view/admin/provinceList.jsp").forward(request, response);
				return;
			}
			// ajax: load provinces

			if (action != null && action.equals("loadProvince")) {
				dateOfWeek = request.getParameter("dateOfWeek");
				region = request.getParameter("region");
				List<Province> provinces = provincesDAO.getProvinces(region, dateOfWeek);
				session.setAttribute("provinces", provinces);

				request.setAttribute("region", region);
				request.setAttribute("dateOfWeek", dateOfWeek);

				session.setAttribute("deleteProvincesList", deleteProvincesList);

				out.println("<table class=\"table table-striped table-sm text-start\">\r\n" + "				<thead>\r\n"
						+ "					<tr class=\"align-top\">\r\n"
						+ "						<th scope=\"col\">STT</th>\r\n"
						+ "						<th scope=\"col\">Ngày mở thưởng</th>\r\n"
						+ "						<th scope=\"col\">Nhà đài</th>\r\n"
						+ "						<th scope=\"col\">Miền</th>\r\n"
						+ "						<th class=\"text-center\" scope=\"col\">Chọn</th>\r\n"
						+ "					</tr>\r\n" + "				</thead>\r\n" + "				<tbody>\r\n");
				int j = 1;
				for (Province province : provinces) {
					out.println(" <tr onmouseover=\"showButton(" + j + ");addColor(this)\"\r\n"
							+ "	 onmouseout=\"hiddenButton(" + j + ");removeColor(this)\">\r\n"
							+ "	  <td class=\"item-account position-relative\"\r\n" + "		 id=\"stt"
							+ province.getProvince_id() + "\">\r\n" + "		 <div\r\n"
							+ "			 class=\"show-button d-none bg-white position-absolute badge top-50 start-0 h-50\"\r\n"
							+ "			 id=\"no_" + j + "\">\r\n"
							+ "		 <button type=\"submit\" class=\"btn btn-outline-none \"\r\n"
							+ "			 title=\"	Xóa\"\r\n" + "			 onclick=\'confirmDeleteProvince("
							+ province.getProvince_id() + ",\"" + province.getRegion() + "\",\""
							+ province.getDateOpen() + "\")'>\r\n" + "			 <i class=\"bi bi-trash\"></i>\r\n"
							+ "		 </button>\r\n"
							+ "		 <button type=\"submit\" class=\"btn btn-outline-none \"\r\n"
							+ "			 title=\"Chỉnh sửa Nhà đài\"\r\n" + "			 onclick=\"updateProvince("
							+ province.getProvince_id() + ")\">\r\n"
							+ "			 <i class=\"bi bi-pencil-square\"></i>\r\n" + "			 </button>\r\n"
							+ "			 </div>\r\n" + "		 <div>\r\n" + (j++) + " </div>\r\n" + "		 </td>\r\n"
							+ "			 <td height=\"60px;\" class=\"\">" + province.getDateOpen() + "</td>\r\n"
							+ "			 <td>" + province.getProvince() + "</td>\r\n" + "			 <td>"
							+ province.getRegion() + "</td>\r\n" + "			 <td class=\"text-center\">\r\n");
					boolean isChecked = false;
					for (int i : deleteProvincesList) {
						if (i == province.getProvince_id()) {
							isChecked = true;
							out.println(" <input class=\"form-check-input deleteList\"\r\n" + "	 id=\"d_"
									+ province.getProvince_id() + "\" type=\"checkbox\" name=\"delete\" checked\r\n"
									+ "	 onchange=\"deleteProvince(" + province.getProvince_id() + ")\" />\r\n");
							break;
						}
					}
					if (!isChecked) {
						out.println(" <input class=\"form-check-input deleteList\"\r\n" + "	 id=\"d_"
								+ province.getProvince_id() + "\" type=\"checkbox\" name=\"delete\"\r\n"
								+ "	 onchange=\"deleteProvince(" + province.getProvince_id() + ")\" />\r\n");
					}
					out.println("</td>\r\n" + "	 </tr>\r\n");
				}
				out.println(" </tbody> </table>");

				return;
			}
			// ajax: add, remove a province in selections
			if (action != null && action.equals("addDelete")) {
				int provinceId = Integer.parseInt(request.getParameter("province_id"));
				String isDelete = request.getParameter("isDelete");

				if (isDelete != null && isDelete.equals("add")) {
					deleteProvincesList.add(provinceId);
				} else {
					for (int i = 0; i < deleteProvincesList.size(); i++)
						if (deleteProvincesList.get(i) == provinceId) {
							deleteProvincesList.remove(i);
							break;
						}
				}

				if (deleteProvincesList.size() == 0) {
					out.println("<div class=\"col-4 \">\r\n"
							+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"alertCantDelete()\">Xóa</button>\r\n"
							+ "	</div>\r\n");
				} else {
					out.println("<div class=\"col-4 col-sm-2 me-1  \">\r\n"
							+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
							+ "								title=\"Xóa các mục đã chọn\" onclick=\"confirmDeleteProvinces()\">Xóa</button>\r\n"
							+ "	</div>\r\n" + "	<div class=\"col-7 col-sm-9 \">\r\n"
							+ "							<button class=\" btn btn-primary ms-3 py-1 px-3\" type=\"submit\"\r\n"
							+ "	 title=\"Xóa các mục đã chọn\" onclick=\"unSelectedList()\">Bỏ chọn tất cả</button>\r\n"
							+ "</div>\r\n");

				}

				session.setAttribute("deleteProvincesList", deleteProvincesList);
				return;
			}
			// ajax:removeAllDeleteList
			if (action != null && action.equals("removeAllDeleteList")) {
				deleteProvincesList.clear();
				session.setAttribute("deleteProvincesList", deleteProvincesList);
				out.println("<div class=\"col-4  \">\r\n"
						+ "							<button class=\" btn btn-danger pt-1 pb-1 ps-3 pe-3\" type=\"submit\"\r\n"
						+ "								title=\"Xóa các mục đã chọn\" onclick=\"alertCantDelete()\">Xóa</button>\r\n"
						+ " </div>\r\n");
				return;

			}

			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(AccountsListController.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
