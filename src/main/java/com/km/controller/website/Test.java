package com.km.controller.website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.km.dao.LotteryDAO;
import com.km.database.DBContext;
import com.km.model.GetLottery;
import com.km.model.Lottery;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		try (PrintWriter out=response.getWriter()){
			conn=new DBContext().getConnection();
			HttpSession session = request.getSession(true);
			LotteryDAO lotteryDAO = new LotteryDAO(conn);
			GetLottery getLottery=new GetLottery();
			// 01-10
			/*
			for(int i=1; i<10; i++) {
				Lottery lottery=new Lottery();
				lottery=getLottery.getMB("2024-03-0"+i);
				lotteryDAO.createLottery(lottery);
				System.out.print("2024-03-0"+i+"; ");
			}
			System.out.println(" ");
			
			for(int i=1; i<10; i++) {
				List<Lottery> lotteryList=new ArrayList<>();				
				lotteryList=getLottery.getMT("2024-03-0"+i);
				for(Lottery lottery:lotteryList) {
				lotteryDAO.createLottery(lottery);
				System.out.print("2024-03-0"+i+"; ");
				}
			}
			System.out.println(" ");
			for(int i=1; i<10; i++) {
				List<Lottery> lotteryList=new ArrayList<>();				
				lotteryList=getLottery.getMN("2024-03-0"+i);
				for(Lottery lottery:lotteryList) {
				lotteryDAO.createLottery(lottery);
				System.out.print("2024-03-0"+i+"; ");
				}
			}
			// 10-31
			
			System.out.println(" ");
			for(int i=10; i<12; i++) {
				Lottery lottery=new Lottery();
				lottery=getLottery.getMB("2024-03-"+i);
				lotteryDAO.createLottery(lottery);
				System.out.print("2024-03-"+i+"; ");
			}
			System.out.println(" ");
			
			for(int i=10; i<12; i++) {
				List<Lottery> lotteryList=new ArrayList<>();				
				lotteryList=getLottery.getMT("2024-03-"+i);
				for(Lottery lottery:lotteryList) {
				lotteryDAO.createLottery(lottery);
				System.out.print("2024-03-"+i+"; ");
				}
			}
			System.out.println(" ");
			for(int i=10; i<12; i++) {
				List<Lottery> lotteryList=new ArrayList<>();				
				lotteryList=getLottery.getMN("2024-03-"+i);
				for(Lottery lottery:lotteryList) {
				lotteryDAO.createLottery(lottery);
				System.out.print("2024-03-"+i+"; ");
				}
			}
			System.out.println(" ");
			*/
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
