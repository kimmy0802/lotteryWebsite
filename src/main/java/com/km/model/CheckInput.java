/*
 * 
 */
package com.km.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class CheckInput.
 */
public class CheckInput {

	/**
	 * Instantiates a new check input.
	 */
	public CheckInput() {
		super();
	}

	// ---------------------------- Tài Khoản
	// -------------------------------
/**
	 * Checks if is true username.
	 *
	 * @param username the username
	 * @return true, if is true username
	 */
	// Kiểm tra username hợp lệ
	public boolean isTrueUsername(String username) {
		String regex = "^[\\w]{6,30}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);
		return matcher.matches();
	}

	/**
	 * Checks if is true phonenumber.
	 *
	 * @param phonenumber the phonenumber
	 * @return true, if is true phonenumber
	 */
	// Kiểm tra sdt hợp lệ
	public boolean isTruePhonenumber(String phonenumber) {
		String regex = "^[\\d]{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phonenumber);
		return matcher.matches();
	}

	

	/**
	 * Checks if is true email.
	 *
	 * @param email the email
	 * @return true, if is true email
	 */
	// Kiểm tra email hợp lệ
	public boolean isTrueEmail(String email) {

		if (email == null)
			return false;

		if (email.equals(""))
			return false;
		String regex = "^[\\w]+(?:\\.[\\w-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(email);

		if (!matcher.matches())
			return false;
		regex = "^.*[-.]+[-.]+.*$";
		p = Pattern.compile(regex);
		matcher = p.matcher(email);

		// System.out.println(email + " : " + !matcher.matches() + "\n");

		return !matcher.matches();

	}

	/**
	 * Checks if is true account.
	 *
	 * @param username the username
	 * @param phonenumber the phonenumber
	 * @param fullname the fullname
	 * @param email the email
	 * @return true, if is true account
	 */
	public boolean isTrueAccount(String username, String phonenumber, String fullname, String email) {
		if (username == null || phonenumber == null || fullname == null || email == null)
			return false;
		if (username.equals("") || phonenumber.equals("") || fullname.equals("") || email.equals(""))
			return false;
		if (!isTrueEmail(email))
			return false;

		return true;
	}

	// ---------------------------- Vé số ----------------------------------------------
	/**
	 * Creates the number.
	 *
	 * @param leng the leng
	 * @return the string
	 */
	// Tạo 1 chuỗi chỉ có chữ số
	public String createNumber(int leng) {
		String result = "";
		String s = "0123456789";//
		for (int i = 0; i < leng; i++) {
			Random generator = new Random();
			int value = generator.nextInt(10);
			result += s.charAt(value);
		}
		return result;
	}

	/**
	 * Creates the random lottery.
	 *
	 * @param region the region
	 * @param prize the prize
	 * @return the string
	 */
	// Tạo số trúng thưởng
	public String createRandomLottery(String region, String prize) {
		String result = "";
		if (region.equals("Miền Bắc")) {
			if (prize.equals("special_prize")) {
				result = createNumber(5);
				return result;
			}
			if (prize.equals("first_prize")) {
				result = createNumber(5);
				return result;
			}
			if (prize.equals("second_prize")) {
				int i = 0;
				result = createNumber(5);
				while (i < 1) {
					String x = createNumber(5);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("third_prize")) {
				int i = 0;
				result = createNumber(5);
				while (i < 5) {
					String x = createNumber(5);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("fourth_prize")) {
				int i = 0;
				result = createNumber(4);
				while (i < 3) {
					String x = createNumber(4);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("fifth_prize")) {
				int i = 0;
				result = createNumber(4);
				while (i < 5) {
					String x = createNumber(4);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("sixth_prize")) {
				int i = 0;
				result = createNumber(3);
				while (i < 2) {
					String x = createNumber(3);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("seventh_prize")) {
				int i = 0;
				result = createNumber(2);
				while (i < 3) {
					String x = createNumber(2);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("eighth_prize"))
				result = "";
			return result;
		} else {
			if (prize.equals("special_prize")) {
				result = createNumber(6);
				return result;
			}
			if (prize.equals("first_prize")) {
				result = createNumber(5);
				return result;
			}
			if (prize.equals("second_prize")) {
				result = createNumber(5);
				return result;
			}
			if (prize.equals("third_prize")) {
				int i = 0;
				result = createNumber(5);
				while (i < 1) {
					String x = createNumber(5);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("fourth_prize")) {
				int i = 0;
				result = createNumber(5);
				while (i < 6) {
					String x = createNumber(5);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("fifth_prize")) {
				result = createNumber(4);
				return result;
			}
			if (prize.equals("sixth_prize")) {
				int i = 0;
				result = createNumber(4);
				while (i < 2) {
					String x = createNumber(4);
					if (result.indexOf(x) == -1) {
						result += " " + x;
						i++;
					}
				}
				return result;
			}
			if (prize.equals("seventh_prize")) {
				result = createNumber(3);
				return result;
			}
			if (prize.equals("eighth_prize"))
				result = createNumber(2);
		}

		return result;

	}

	/**
	 * Checks if is full lottery numbers.
	 *
	 * @param region the region
	 * @param prize the prize
	 * @param s the s
	 * @return true, if is full lottery numbers
	 */
	// Kiểm tra số lượng số trúng thưởng trong giải đã đủ chưa
	public boolean isFullLotteryNumbers(String region, String prize, String s) {

		if (s == null || s.equals(""))
			return false;
		s = s + " ";
		String[] s1 = s.split(" ");
		if (region.equals("Miền Bắc")) {
			if (prize.equals("special_prize") && s1.length >= 1)
				return true;
			if (prize.equals("first_prize") && s1.length >= 1)
				return true;
			if (prize.equals("second_prize") && s1.length >= 2)
				return true;
			if (prize.equals("third_prize") && s1.length >= 6)
				return true;
			if (prize.equals("fourth_prize") && s1.length >= 4)
				return true;
			if (prize.equals("fifth_prize") && s1.length >= 6)
				return true;
			if (prize.equals("sixth_prize") && s1.length >= 3)
				return true;
			if (prize.equals("seventh_prize") && s1.length >= 4)
				return true;
		} else {
			{
				if (prize.equals("special_prize") && s1.length >= 1)
					return true;
				if (prize.equals("first_prize") && s1.length >= 1)
					return true;
				if (prize.equals("second_prize") && s1.length >= 1)
					return true;
				if (prize.equals("third_prize") && s1.length >= 2)
					return true;
				if (prize.equals("fourth_prize") && s1.length >= 7)
					return true;
				if (prize.equals("fifth_prize") && s1.length >= 1)
					return true;
				if (prize.equals("sixth_prize") && s1.length >= 3)
					return true;
				if (prize.equals("seventh_prize") && s1.length >= 1)
					return true;
				if (prize.equals("eighth_prize") && s1.length >= 1)
					return true;

			}
		}
		return false;
	}

	/**
	 * Checks if is in string.
	 *
	 * @param s1 the s 1
	 * @param s2 the s 2
	 * @return true, if is in string
	 */
	// Kiểm tra xâu s1 chứa xâu s2: true: có; false: không
	public boolean isInString(String s1, String s2) {
		if (s1 == null)
			return false;
		return (s1.indexOf(s2) == -1) ? false : true;
	}

	/**
	 * Checks if is valid number.
	 *
	 * @param s the s
	 * @return true, if is valid number
	 */
	// Kiểm tra xâu s là xâu số
	public boolean isValidNumber(String s) {
		if (s.matches("^[0-9]+$"))
			return true;
		return false;
	}

	// ------------------------- DATETIME-------------------------------------------------------------
	
	/**
	 * Gets the day of week.
	 *
	 * @param date the date
	 * @return the day of week
	 */
	// Trả về thứ trong tuần của 1 chuỗi
		public String getDayOfWeek(String date) {
			Map<String, String> map = new HashMap<>();
			map.put("Monday", "Thứ Hai");
			map.put("Tuesday", "Thứ Ba");
			map.put("Wednesday", "Thứ Tư");
			map.put("Thursday", "Thứ Năm");
			map.put("Friday", "Thứ Sáu");
			map.put("Saturday", "Thứ Bảy");
			map.put("Sunday", "Chủ Nhật");
			// yyyy-MM-dd
			date=formDateToYMD(date);
			int d = Integer.parseInt(date.substring(8, 10));
			int m = Integer.parseInt(date.substring(5, 7)) - 1;// vì giá trị các tháng trong Calendar là từ 0-11
			int y = Integer.parseInt(date.substring(0, 4));
			Calendar calendar = Calendar.getInstance();
			calendar.set(y, m, d);
			Date day = calendar.getTime();
			String dayOfWeek = new SimpleDateFormat("EEEE").format(day);
			return map.get(dayOfWeek);
		}
	
	/**
	 * Gets the current date.
	 *
	 * @return the current date
	 */
	// trả về ngày hiện tại
	public String getCurrentDate() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	// trả về ngày giờ hiện tại
	public String getCurrentTime() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	/**
	 * Checks if is valid date.
	 *
	 * @param inDate the in date
	 * @return true, if is valid date
	 */
	// Kiểm tra 1 xâu có định dạng ngày: yyyy-MM-dd
	public boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	// yyyy-MM-dd ----> dd-MM-yyyy
	
	/**
	 * Form date to DMY.
	 *
	 * @param date the date
	 * @return the string
	 */
	public String formDateToDMY(String date) {
		date=date.trim();
		date=date.replaceAll(" ", "-");
		if(date.indexOf("-")==2) return date;
		
		return date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4);

	}
	
	/**
	 * Form date to YMD.
	 *
	 * @param date the date
	 * @return the string
	 */
	//  dd-MM-yyyy ----> yyyy-MM-dd 
	public String formDateToYMD(String date) {
		date=date.trim();
		date=date.replaceAll(" ", "-");
		if(date.indexOf("-")==4) return date;		
		return date.substring(6,10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);

	}


	/**
	 * Checks if is true lottery.
	 *
	 * @param region the region
	 * @param prize the prize
	 * @param number the number
	 * @return true, if is true lottery
	 */
	// Kiểm tra xâu number có định dạng đúng số trúng theo giải và khu vực
	public boolean isTrueLottery(String region, String prize, String number) {
		if (number.equals("") || number == null)
			return false;
		int x = 0;
		if (region.equals("Miền Bắc")) {
			if (prize.equals("special_prize") || prize.equals("first_prize") || prize.equals("third_prize")
					|| prize.equals("second_prize"))
				x = 5;
			else if (prize.equals("fourth_prize") || prize.equals("fifth_prize"))
				x = 4;
			else if (prize.equals("sixth_prize"))
				x = 3;
			else
				x = 2;
		} else {
			if (prize.equals("special_prize"))
				x = 6;
			else if (prize.equals("first_prize") || prize.equals("third_prize") || prize.equals("second_prize")
					|| prize.equals("fourth_prize"))
				x = 5;
			else if (prize.equals("sixth_prize") || prize.equals("fifth_prize"))
				x = 4;
			else if (prize.equals("seventh_prize"))
				x = 3;
			else
				x = 2;
		}
		number = number.replace("-", " ");
		number = number.replace("  ", " ");
		number = number.trim();
		number = number + " ";
		String[] a = number.split(" ");
		for (int i = 0; i < a.length; i++) {
			if (a[i].length() != x || !isValidNumber(a[i]))
				return false;
		}

		return true;
	}

	/**
	 * Check prize.
	 *
	 * @param lotteryShow the lottery show
	 * @param numberSearch the number search
	 * @param region the region
	 * @return the string
	 */
	// Kieemr tra 1 soos cos trung thuowng
	public String checkPrize(LotteryShow lotteryShow, String numberSearch, String region) {
		if (lotteryShow.getDate() == null) {
			return "9_";
		}
		String s = numberSearch;
		int prize = 9;

		if (region.equals("Miền Bắc")) {
			// trúng giải đặc biệt
			s = numberSearch.substring(1, 6);
			if (lotteryShow.getLottery().getSpecial_prize().equals(s)) {
				prize = 0;
			} else {
				// trúng giải nhất
				s = numberSearch.substring(1, 6);
				if (lotteryShow.getLottery().getFirst_prize().indexOf(s) != -1) {
					prize = 1;
				} else {
					// trúng giải nhì
					s = numberSearch.substring(1, 6);
					if (lotteryShow.getLottery().getSecond_prize().indexOf(s) != -1) {
						prize = 2;
					} else {
						// trúng giải ba
						s = numberSearch.substring(1, 6);
						if (lotteryShow.getLottery().getThird_prize().indexOf(s) != -1) {
							prize = 3;
						} else {
							// trúng giải tư
							s = numberSearch.substring(2, 6);
							if (lotteryShow.getLottery().getFourth_prize().indexOf(s) != -1) {
								prize = 4;
							} else {
								// trúng giải năm
								s = numberSearch.substring(2, 6);
								if (lotteryShow.getLottery().getFifth_prize().indexOf(s) != -1) {
									prize = 5;
								} else {
									// trúng giải sáu
									s = numberSearch.substring(3, 6);
									if (lotteryShow.getLottery().getSixth_prize().indexOf(s) != -1) {
										prize = 6;
									} else {
										// trúng giải bảy
										s = numberSearch.substring(4, 6);
										if (lotteryShow.getLottery().getSeventh_prize().indexOf(s) != -1) {
											prize = 7;
										}
									}
								}
							}
						}
					}
				}
			}

		} else {
			// Miền Trung + Miền Nam
			// trúng giải đặc biệt
			s = numberSearch;
			if (lotteryShow.getLottery().getSpecial_prize().equals(s)) {
				prize = 0;
			} else {
				// trúng giải nhất
				s = numberSearch.substring(1, 6);
				if (lotteryShow.getLottery().getFirst_prize().indexOf(s) != -1) {
					prize = 1;
				} else {
					// trúng giải nhì
					s = numberSearch.substring(1, 6);
					if (lotteryShow.getLottery().getSecond_prize().indexOf(s) != -1) {
						prize = 2;
					} else {
						// trúng giải ba
						s = numberSearch.substring(1, 6);
						if (lotteryShow.getLottery().getThird_prize().indexOf(s) != -1) {
							prize = 3;
						} else {
							// trúng giải tư
							s = numberSearch.substring(1, 6);
							if (lotteryShow.getLottery().getFourth_prize().indexOf(s) != -1) {
								prize = 4;
							} else {
								// trúng giải năm
								s = numberSearch.substring(2, 6);
								if (lotteryShow.getLottery().getFifth_prize().indexOf(s) != -1) {
									prize = 5;
								} else {
									// trúng giải sáu
									s = numberSearch.substring(2, 6);
									if (lotteryShow.getLottery().getSixth_prize().indexOf(s) != -1) {
										prize = 6;
									} else {
										// trúng giải bảy
										s = numberSearch.substring(3, 6);
										if (lotteryShow.getLottery().getSeventh_prize().indexOf(s) != -1) {
											prize = 7;
										} else {
											// trúng giải tám
											s = numberSearch.substring(4, 6);
											if (lotteryShow.getLottery().getEighth_prize().indexOf(s) != -1) {
												prize = 8;
											}
										}
									}
								}
							}
						}
					}
				}

			}
		}

		return prize + "_" + s;

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		CheckInput cI = new CheckInput();

		System.out.println(cI.getDayOfWeek("2023 10 22"));
		System.out.println(cI.getDayOfWeek("23-10-2023"));
		//System.out.println(cI.isValidNumber("5560"));
		System.out.println(cI.formDateToYMD("2023-09-22"));
		System.out.println(cI.formDateToYMD("22-09-2023"));
		System.out.println(cI.formDateToDMY("2023-09-22"));
		System.out.println(cI.formDateToDMY("22-09-2023"));
		// Calendar.get(Calendar.DAY_OF_WEEK);

	}

}
