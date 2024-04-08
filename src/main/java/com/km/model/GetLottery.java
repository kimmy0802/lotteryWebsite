package com.km.model;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.km.dao.ProvincesDAO;
import com.km.database.DBContext;

// TODO: Auto-generated Javadoc
/**
 * The Class GetLottery.
 */
// Class CreateLottery: tạo tự động vé số vào lúc 17h00 hàng ngày
public class GetLottery {

	/**
	 * Gets the mien bac MN.
	 *
	 * @param date the date
	 * @return the mien bac MN
	 */
	// Lấy kqxs từ trung Minh Ngọc
	public Lottery getMienBacMN(String date) {
		// date=yyyy-MM-dd
		Lottery results = new Lottery();
		String url = "https://www.minhngoc.net.vn/xo-so-truc-tiep/mien-bac.html";
		Document doc = null;
		Connection conn;
		try {

			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();
			conn = new DBContext().getConnection();

			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			// Lấy tên nhà đài
			Elements e = doc.select("table.bkqtinhmienbac tbody tr td span span a");
			int provinceID = provincesDAO.getProvinceID(e.text());
			// Lấy kết quả xổ số
			Elements lstArticles = doc.select("table.bkqtinhmienbac tbody tr");
			Lottery lottery = new Lottery();
			lottery.setProvince_id(provinceID);
			lottery.setLottery_date(date);
			if (lstArticles.size() == 0) {
				System.out.println("chua cos kq: getLotteryMB-MN");
				return null;
			}

			lottery.setSpecial_prize("" + lstArticles.get(1).child(1).text());
			lottery.setFirst_prize("" + lstArticles.get(2).child(1).text());
			lottery.setSecond_prize("" + lstArticles.get(3).child(1).text());
			lottery.setThird_prize("" + lstArticles.get(4).child(1).text());
			lottery.setFourth_prize("" + lstArticles.get(5).child(1).text());
			lottery.setFifth_prize("" + lstArticles.get(6).child(1).text());
			lottery.setSixth_prize("" + lstArticles.get(7).child(1).text());
			lottery.setSeventh_prize("" + lstArticles.get(8).child(1).text());
			lottery.setDeleted(0);
			results = lottery;

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("khong truy cap duoc trang lay kqxs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi vs database loi");
		}
		return results;

	}

	/**
	 * Gets the mien nam MN.
	 *
	 * @param date the date
	 * @return the mien nam MN
	 */
	// Lấy kqxs từ trung Minh Ngọc
	public List<Lottery> getMienNamMN(String date) {
		// date=yyyy-MM-dd
		List<Lottery> results = new ArrayList<>();
		String url = "https://www.minhngoc.net.vn/xo-so-truc-tiep/mien-nam.html";
		Document doc = null;
		Connection conn;
		try {

			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(30000).get();

			conn = new DBContext().getConnection();
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			Elements e = doc.getElementsByClass("rightcl");
			if (e.size() == 0) {
				System.out.println("chuwa co kq");
				return null;
			}
			for (Element el : e) {
				Lottery lottery = new Lottery();
				// Lấy tên nhà đài
				String province = el.child(0).firstElementChild().firstElementChild().text();
				province = province.trim();
				if (province.equals("Đà Lạt")) {
					province = "Đà Lạt - Lâm Đồng";
				} else {
					if (province.equals("TP. HCM"))
						province = "TP. Hồ Chí Minh";

				}
				int provinceID = provincesDAO.getProvinceID(province);
				lottery.setProvince_id(provinceID);
				// Lấy ksxs
				Elements elements = el.child(0).children();
				lottery.setEighth_prize(elements.get(2).text());
				lottery.setSeventh_prize(elements.get(3).text());
				lottery.setSixth_prize(elements.get(4).text());
				lottery.setFifth_prize(elements.get(5).text());
				lottery.setFourth_prize(elements.get(6).text());
				lottery.setThird_prize(elements.get(7).text());
				lottery.setSecond_prize(elements.get(8).text());
				lottery.setFirst_prize(elements.get(9).text());
				lottery.setSpecial_prize(elements.get(10).text());
				lottery.setLottery_date(date);
				lottery.setDeleted(0);
				results.add(lottery);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("khong truy cap duoc trang lay kqxs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi vs database loi");
		}
		return results;

	}

	/**
	 * Gets the mien trung MN.
	 *
	 * @param date the date
	 * @return the mien trung MN
	 */
	// Lấy kqxs từ trung Minh Ngọc
	public List<Lottery> getMienTrungMN(String date) {

		// date=yyyy-MM-dd
		List<Lottery> results = new ArrayList<>();
		String url = "https://www.minhngoc.net.vn/xo-so-truc-tiep/mien-trung.html";
		Document doc = null;
		Connection conn;
		try {

			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();

			conn = new DBContext().getConnection();
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);
			Elements e = doc.getElementsByClass("rightcl");
			if (e.size() == 0) {
				System.out.println("chua co kq");
				return null;
			}
			for (Element el : e) {
				Lottery lottery = new Lottery();
				// Lấy tên nhà đài
				String province = el.child(0).firstElementChild().firstElementChild().text();
				if (province.equals("Thừa T. Huế")) {
					province = "Thừa Thiên Huế";
				}
				int provinceID = provincesDAO.getProvinceID(province);
				lottery.setProvince_id(provinceID);
				// Lấy ksxs
				Elements elements = el.child(0).children();
				lottery.setEighth_prize(elements.get(2).text());
				lottery.setSeventh_prize(elements.get(3).text());
				lottery.setSixth_prize(elements.get(4).text());
				lottery.setFifth_prize(elements.get(5).text());
				lottery.setFourth_prize(elements.get(6).text());
				lottery.setThird_prize(elements.get(7).text());
				lottery.setSecond_prize(elements.get(8).text());
				lottery.setFirst_prize(elements.get(9).text());
				lottery.setSpecial_prize(elements.get(10).text());
				lottery.setLottery_date(date);
				lottery.setDeleted(0);
				results.add(lottery);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("khong truy cap duoc trang lay kqxs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi vs database loi");
		}
		return results;
	}

	// Lấy kqxs từ xskt

	// -----------------------------

	/**
	 * Gets the mb tu xskt.
	 *
	 * @param date the date
	 * @return the mb
	 */

	public Lottery getMB(String date) {
		// date=yyyy-MM-dd
		String y = date.substring(0, 4);
		String m = date.substring(5, 7);
		String d = date.substring(8, 10);
		Lottery results = new Lottery();

		if(date.substring(8, 9).equals("0")) d=date.substring(9,10);

		if(date.substring(5, 6).equals("0")) m=date.substring(6,7);
		String url = "https://xskt.com.vn/xsmb/ngay-" + d + "-" + m + "-" + y;
		Document doc = null;
		Connection conn;
		try {

			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();

			conn = new DBContext().getConnection();
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);

			Province province = provincesDAO.getProvinces("Miền Bắc", new CheckInput().getDayOfWeek(date)).get(0);

			int provinceID = province.getProvince_id();

			// Lấy kết quả xổ số
			Elements lstArticles = doc.select("table#MB0 tbody tr");

			Lottery lottery = new Lottery();
			lottery.setProvince_id(provinceID);
			lottery.setLottery_date(date);
			if (lstArticles.size() == 0) {
				return null;
			}
			lottery.setSpecial_prize(lstArticles.get(1).child(1).text());
			lottery.setFirst_prize(lstArticles.get(2).child(1).text());
			lottery.setSecond_prize(lstArticles.get(3).child(1).text());
			// Giải 3 và giải 5 có 2 thẻ <tr>
			lottery.setThird_prize(lstArticles.get(4).child(1).text());
			lottery.setFourth_prize(lstArticles.get(6).child(1).text());
			lottery.setFifth_prize(lstArticles.get(7).child(1).text());
			lottery.setSixth_prize(lstArticles.get(9).child(1).text());
			lottery.setSeventh_prize(lstArticles.get(10).child(1).text());
			lottery.setDeleted(0);
			results = lottery;

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("khong truy cap duoc trang lay kqxs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi vs database loi");
		}
		return results;

	}

	// -----------------------------

		/**
		 * Gets the mn tu xskt.
		 *
		 * @param date the date
		 * @return the mn
		 */
	public List<Lottery> getMN(String date) {
		// date=yyyy-MM-dd
		String y = date.substring(0, 4);
		String m = date.substring(5, 7);
		String d = date.substring(8, 10);
		List<Lottery> results = new ArrayList<>();
		if(date.substring(8, 9).equals("0")) d=date.substring(9,10);

		if(date.substring(5, 6).equals("0")) m=date.substring(6,7);
		String url = "https://xskt.com.vn/xsmn/ngay-" + d + "-" + m + "-" + y;
		Document doc = null;
		Connection conn;
		try {

			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();

			conn = new DBContext().getConnection();
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);

			// Lay nha dai
			List<String> provinces = new ArrayList<>();
			Elements lstArticles = doc.select("table#MN0 tbody tr");

			for (int i = 1; i < lstArticles.first().children().size(); i++) {
				String province = lstArticles.first().child(i).text();
				if(province.equals("TP.HCM")) province="TP. Hồ Chí Minh";
				if(province.equals("Đà Lạt")) province="Đà Lạt - Lâm Đồng";
				provinces.add(province);

			}
			
			for (int i = 0; i < provinces.size(); i++) {
				Lottery lottery = new Lottery();
				lottery.setProvince_id(provincesDAO.getProvinceID(provinces.get(i)));
				lottery.setLottery_date(date);
				lottery.setEighth_prize(lstArticles.get(1).child(i + 1).text());
				lottery.setSeventh_prize(lstArticles.get(2).child(i + 1).text());
				lottery.setSixth_prize(lstArticles.get(3).child(i + 1).text());
				lottery.setFifth_prize(lstArticles.get(4).child(i + 1).text());
				lottery.setFourth_prize(lstArticles.get(5).child(i + 1).text());
				lottery.setThird_prize(lstArticles.get(6).child(i + 1).text());
				lottery.setSecond_prize(lstArticles.get(7).child(i + 1).text());
				lottery.setFirst_prize(lstArticles.get(8).child(i + 1).text());
				lottery.setSpecial_prize(lstArticles.get(9).child(i + 1).text());
				results.add(lottery);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("khong truy cap duoc trang lay kqxs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi vs database loi");
		}
		return results;

	}

	// -----------------------------

		/**
		 * Gets the mt tu xskt.
		 *
		 * @param date the date
		 * @return the mt
		 */
	public List<Lottery> getMT(String date) {
		// date=yyyy-MM-dd
		String y = date.substring(0, 4);
		String m = date.substring(5, 7);
		String d = date.substring(8, 10);
		List<Lottery> results = new ArrayList<>();

		if(date.substring(8, 9).equals("0")) d=date.substring(9,10);

		if(date.substring(5, 6).equals("0")) m=date.substring(6,7);
		String url = "https://xskt.com.vn/xsmt/ngay-" + d + "-" + m + "-" + y;
		Document doc = null;
		Connection conn;
		try {

			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();

			conn = new DBContext().getConnection();
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);

			// Lay nha dai
			List<String> provinces = new ArrayList<>();
			Elements lstArticles = doc.select("table#MT0 tbody tr");

			for (int i = 1; i < lstArticles.first().children().size(); i++) {
				String province = lstArticles.first().child(i).text();
				if(province.equals("Đắc Lắc")) province="Đắk Lắk";
				if(province.equals("Đắc Nông")) province="Đắk Nông";				
				provinces.add(province);

			}
			
			for (int i = 0; i < provinces.size(); i++) {
				Lottery lottery = new Lottery();
				lottery.setProvince_id(provincesDAO.getProvinceID(provinces.get(i)));
				lottery.setLottery_date(date);
				lottery.setEighth_prize(lstArticles.get(1).child(i + 1).text());
				lottery.setSeventh_prize(lstArticles.get(2).child(i + 1).text());
				lottery.setSixth_prize(lstArticles.get(3).child(i + 1).text());
				lottery.setFifth_prize(lstArticles.get(4).child(i + 1).text());
				lottery.setFourth_prize(lstArticles.get(5).child(i + 1).text());
				lottery.setThird_prize(lstArticles.get(6).child(i + 1).text());
				lottery.setSecond_prize(lstArticles.get(7).child(i + 1).text());
				lottery.setFirst_prize(lstArticles.get(8).child(i + 1).text());
				lottery.setSpecial_prize(lstArticles.get(9).child(i + 1).text());
				results.add(lottery);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("khong truy cap duoc trang lay kqxs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi vs database loi");
		}
		return results;

	}

	/**
	 * Gets the province.
	 *
	 * @param province the province
	 * @param date     the date
	 * @return the province
	 */
	// Lấy xổ số theo tên tỉnh: Miền trung + Miền nam
	public Lottery getProvince(String province, String date) {
		// date=yyyy-MM-dd
		String y = date.substring(0, 4);
		String m = date.substring(5, 7);
		String d = date.substring(8, 10);
		Lottery results = new Lottery();

		Map<String, String> provinceCode = new HashMap<>();
		provinceCode.put("Quảng Bình", "QT");
		provinceCode.put("Quảng Trị", "QB");
		provinceCode.put("Thừa Thiên Huế", "TTH");
		provinceCode.put("Đà Nẵng", "DNG");// xsdng-xsdna
		provinceCode.put("Quảng Nam", "QNM");// xsqnm-xsqna
		provinceCode.put("Quảng Ngãi", "QNG");
		provinceCode.put("Bình Định", "BDI");
		provinceCode.put("Phú Yên", "PY");
		provinceCode.put("Khánh Hòa", "KH");
		provinceCode.put("Ninh Thuận", "NT");
		provinceCode.put("Kon Tum", "KT");
		provinceCode.put("Gia Lai", "GL");
		provinceCode.put("Đắk Lắk", "DLK");
		provinceCode.put("Đắk Nông", "DNO");

		provinceCode.put("TP. Hồ Chí Minh", "HCM");// xshcm-xstp
		provinceCode.put("An Giang", "AG");
		provinceCode.put("Bình Dương", "BD");
		provinceCode.put("Bạc Liêu", "BL");
		provinceCode.put("Bình Phước", "BP");
		provinceCode.put("Bến Tre", "BT");
		provinceCode.put("Bình Thuận", "BTH");
		provinceCode.put("Cà Mau", "CM");
		provinceCode.put("Cần Thơ", "CT");
		provinceCode.put("Đà Lạt - Lâm Đồng", "LD");// xsld-xsdl
		provinceCode.put("Đồng Nai", "DN");
		provinceCode.put("Đồng Tháp", "DT");
		provinceCode.put("Hậu Giang", "HG");
		provinceCode.put("Kiên Giang", "KG");
		provinceCode.put("Long An", "LA");
		provinceCode.put("Sóc Trăng", "ST");
		provinceCode.put("Tiền Giang", "TG");
		provinceCode.put("Tây Ninh", "TN");
		provinceCode.put("Trà Vinh", "TV");
		provinceCode.put("Vĩnh Long", "VL");
		provinceCode.put("Vũng Tàu", "VT");
		String s = "xs" + provinceCode.get(province).toLowerCase();
		if (province.equals("Đà Nẵng"))
			s = "xsdng-xsdna";
		else if (province.equals("Quảng Nam"))
			s = "xsqnm-xsqna";
		else if (province.equals("TP. Hồ Chí Minh"))
			s = "xshcm-xstp";
		else if (province.equals("Đà Lạt - Lâm Đồng"))
			s = "xsld-xsdl";

		String url = "https://xskt.com.vn/" + s + "/ngay-" + d + "-" + m + "-" + y;
		System.out.println(url);
		String matinh = provinceCode.get(province);
		Document doc = null;
		Connection conn;
		try {

			doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();

			conn = new DBContext().getConnection();
			ProvincesDAO provincesDAO = new ProvincesDAO(conn);

			int provinceID = provincesDAO.getProvinceID(province);

			// Lấy kết quả xổ số
			s = "table#" + matinh + "0 tbody tr";
			Elements lstArticles = doc.select(s);

			Lottery lottery = new Lottery();
			lottery.setProvince_id(provinceID);
			lottery.setLottery_date(date);
			if (lstArticles.size() == 0) {
				return null;
			}
			lottery.setEighth_prize(lstArticles.get(1).child(1).text());
			lottery.setSeventh_prize(lstArticles.get(2).child(1).text());
			lottery.setSixth_prize(lstArticles.get(3).child(1).text());
			lottery.setFifth_prize(lstArticles.get(4).child(1).text());
			// Giải 4 và giải có 2 thẻ <tr>
			lottery.setFourth_prize(lstArticles.get(5).child(1).text());
			lottery.setThird_prize(lstArticles.get(7).child(1).text());
			lottery.setSecond_prize(lstArticles.get(8).child(1).text());
			lottery.setFirst_prize(lstArticles.get(9).child(1).text());
			lottery.setSpecial_prize(lstArticles.get(10).child(1).text());

			lottery.setDeleted(0);
			results = lottery;

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("khong truy cap duoc trang lay kqxs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi vs database loi");
		}
		return results;

	}

}
