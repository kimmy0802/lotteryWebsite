/*
 * 
 */
package com.km.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// TODO: Auto-generated Javadoc
/**
 * The Class DailySchedulerMT.
 */
//Lịch lấy kết quả xổ số Miền Trung: 17h15 -17h35
public class DailySchedulerMT implements ServletContextListener {
	
	/** The executor. */
	private ScheduledExecutorService executor;

	/**
	 * Context initialized.
	 *
	 * @param event the event
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		executor = Executors.newSingleThreadScheduledExecutor();

		// Lấy thời gian hiện tại
		LocalTime currentTime = LocalTime.now();
		// lấy thời gian đợi từ hiện tại đến 17h15
		Duration initialDelay = Duration.between(currentTime, LocalTime.of(17, 15, 30, 0));
		// nếu đã qua 17h15: chuyển sang ngày sau
		if (initialDelay.isNegative()) {
			initialDelay = initialDelay.plusDays(1);
		}
		// Thời gian lặp
		long period = 24 * 60 * 60 * 1000;
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("chay ct 2p 1 lan: " + Calendar.getInstance().getTime());
				// Cập nhật kết quả xổ số 2 phút 1 lần sau khi nhà đài bắt đầu quay số trúng
				// thưởng
				ScheduledExecutorService executor1 = Executors.newSingleThreadScheduledExecutor();
				executor1.scheduleAtFixedRate(new RunnableGetLottery().new GetMT(), 0, 1, TimeUnit.MINUTES);
				// dừng cập nhật sau 25 phút:
				executor1.schedule(new Runnable() {
					@Override
					public void run() {
						// System.out.println("dung ct");
						executor1.shutdown();
					}
				}, 25, TimeUnit.MINUTES);

			}
		}, initialDelay.toMillis(), period, TimeUnit.MILLISECONDS);
	}

	/**
	 * Context destroyed.
	 *
	 * @param event the event
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		executor.shutdownNow();
	}
}
