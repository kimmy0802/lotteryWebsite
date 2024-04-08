package com.km.controller.website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.dao.PrizesDAO;
import com.km.database.DBContext;
import com.km.model.Account;
import com.km.model.Prizes;

// TODO: Auto-generated Javadoc
/**
 * Servlet triển khai chức năng xem giá trị giải thưởng.
 */
@WebServlet("/CostOfPrizesController")
public class CostOfPrizesController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new cost of prizes controller.
     */
    public CostOfPrizesController() {
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
		// TODO Auto-generated method stub
		Connection conn;

		try (PrintWriter out = response.getWriter()){
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession(true);
			conn = (Connection) new DBContext().getConnection();
			Account account = (Account) session.getAttribute("account");
			session.setAttribute("account", account);
			session.setAttribute("searchLotteries", null);
		
			PrizesDAO prizesDAO=new PrizesDAO(conn);
			List<Prizes> prizes=prizesDAO.getCostOfPrizes();		
			
			request.setAttribute("prizes",prizes );
			request.getRequestDispatcher("/view/website/costOfPrizes.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
