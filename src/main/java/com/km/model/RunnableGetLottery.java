/*
 * 
 */
package com.km.model;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import com.km.dao.LotteryDAO;
import com.km.database.DBContext;

// TODO: Auto-generated Javadoc
/**
 * The Class RunnableGetLottery.
 */
public class RunnableGetLottery {
	
	/**
	 * The Class GetMB.
	 * Có chức năng cập nhật kết quả xổ số Miền Bắc
	 */
	public class GetMB implements Runnable {

		/**
		 * Run.
		 */
		@Override
		public void run() {
			Connection conn;
			try {
				conn = new DBContext().getConnection();
				LotteryDAO lotteryDAO = new LotteryDAO(conn);
				GetLottery getLottery = new GetLottery();
				CheckInput checkInput = new CheckInput();
				String date = checkInput.getCurrentDate();
				Lottery lottery = getLottery.getMienBacMN(date);
				if (lottery != null) {
					lotteryDAO.updateLottery(lottery);
					System.out.println("Cập nhật Miền Bắc " + Calendar.getInstance().getTime());
				} else {
					System.out.println("chưa có kqxs Miền Bắc " + Calendar.getInstance().getTime());
				}
			} catch (Exception e) {
				System.out.println("Lỗi ở GetMienBacMN class RunnableGetLottery");
				e.printStackTrace();
			}

		}
	}

	/**
	 * The Class GetMT.
	 * Có chức năng cập nhật kết quả xổ số Miền Trung
	 */
	public class GetMT implements Runnable {

		/**
		 * Run.
		 */
		@Override
		public void run() {
			Connection conn;
			try {
				conn = new DBContext().getConnection();
				LotteryDAO lotteryDAO = new LotteryDAO(conn);
				GetLottery getLottery = new GetLottery();
				CheckInput checkInput = new CheckInput();
				String date = checkInput.getCurrentDate();

				List<Lottery> lotteries = getLottery.getMienTrungMN(date);
				if (lotteries == null) {
					System.out.println("chưa có kqxs Miền Trung" + Calendar.getInstance().getTime());
					return;
				}
				for (Lottery lottery : lotteries) {
					lotteryDAO.updateLottery(lottery);
					System.out.println("Cập nhật Miền Trung" + Calendar.getInstance().getTime());
				}

			} catch (Exception e) {
				System.out.println("Lỗi ở GetMienTrungMN  class RunnableGetLottery");
				e.printStackTrace();
			}

		}
	}

	/**
	 * The Class GetMN.
	 * Có chức năng cập nhật kết quả xổ số Miền Nam
	 */
	public class GetMN implements Runnable {

		/**
		 * Run.
		 */
		@Override
		public void run() {
			Connection conn;
			try {
				conn = new DBContext().getConnection();
				LotteryDAO lotteryDAO = new LotteryDAO(conn);
				GetLottery getLottery = new GetLottery();
				CheckInput checkInput = new CheckInput();
				String date = checkInput.getCurrentDate();
				List<Lottery> lotteries = getLottery.getMienNamMN(date);
				if (lotteries == null) {
					System.out.println("chưa có kqxs Miền Nam" + Calendar.getInstance().getTime());
					return;
				}
				for (Lottery lottery : lotteries)
					{lotteryDAO.updateLottery(lottery);

				System.out.println("Cập nhật Miền Nam " + Calendar.getInstance().getTime());}

			} catch (Exception e) {
				System.out.println("Lỗi ở GetMienNamMN  class RunnableGetLottery");
				e.printStackTrace();

			}

		}
	}

}
