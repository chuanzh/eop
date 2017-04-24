package com.github.chuanzh.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FuncDate {

	/**
	 * 获取季度
	 * 
	 * @param date 时间
	 * @return 季度
	 */
	public static int getQuarter(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int m = c.get(Calendar.MONTH) + 1;

		switch (m) {
		case 1:
		case 2:
		case 3:
			return 1;
		case 4:
		case 5:
		case 6:
			return 2;
		case 7:
		case 8:
		case 9:
			return 3;
		case 10:
		case 11:
		case 12:
			return 4;
		default:
			return 0;
		}
	}

	public static String getWeekOfDate() {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	public static String getWeekByDateStr(String dateStr) {
		String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
		Date date = formatToDate(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}
	
	public static int getNowYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public static String formatStrToDateStr(String s) {
		if (s == null)
			return s;
		s = s.trim();
		String[] formats = new String[] { "yyyy-MM-dd", "yyyy年MM月dd日",
				"yyyy/MM/dd", "yyyy MM dd" };
		Date date = null;
		for (String format : formats) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				date = sdf.parse(s);
				break;
			} catch (ParseException e) {
			}
		}
		if (date == null)
			return s;
		else
			return FuncDate.dateToDayStr(date);
	}

	public static Date formatToDate(String s) {
		if (s == null || "NULL".equals(s) || "".equals(s))
			return null;
		String format = "yyyy-MM-dd HH:mm:ss";
		if (s.indexOf(":") == -1)
			format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			date = sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	public static Date formatToDate(Timestamp timestamp) {

		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			date = sdf.parse(sdf.format(timestamp));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	/**
	 * yyyy-MM-dd
	 * @param date 日期
	 * @return 字符串（yyyy-MM-dd）
	 */
	public static String dateToDayStr(Date date) {
		if (date == null)
			return "";
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}
	
	/**
	 * 得到当前的日期(yyyy-MM-dd)
	 * @return 日期（yyyy-MM-dd）
	 */
	public static String getNowDate() {
		Date date = new Date();
		return dateToDayStr(date);
	}
	
	/**
	 * 得到当前小时
	 * @return 小时
	 */
	public static String getNowHour() {
		Date date = new Date();
		String format = "H";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String hour = sdf.format(date);
		return hour;
	}
 
	/**
	 * 得到当前小时
	 * @return 小时
	 */
	public static int getNowHourInt() {
		Date date = new Date();
		String format = "H";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String hour = sdf.format(date);
		return Integer.parseInt(hour);
	}
	/**
	 * 得到当前分钟
	 * @return 分钟
	 */
	public static int getNowMinuteInt() {
		Date date = new Date();
		String format = "m";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String hour = sdf.format(date);
		return Integer.parseInt(hour);
	}
	/**
	 * 根据毫秒数得到对应的字符串时间
	 * @param time 时间戳
	 * @return 日期
	 */
	public static String timeToTimeStr(Long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return dateToString(c.getTime());
	}
	
	/**
	 * 比较时间
	 * @param timeStr1 时间1
	 * @param timeStr2 时间2
	 * @param operator 操作符
	 * @return 比较结果
	 */
	public static boolean compare(String timeStr1, String timeStr2, String operator) {
		if(timeStr1 == null) {
			timeStr1 = FuncDate.getNowDateTime();
		}
		
		if(timeStr2 == null) {
			timeStr2 = FuncDate.getNowDateTime();
		}
		
		long time1 = FuncDate.getMillisecondByDateStr(timeStr1);
		long time2 = FuncDate.getMillisecondByDateStr(timeStr2);
		
		if("=".equals(operator) || "==".equals(operator)) {
			return time1 == time2;
		}else if(">".equals(operator)) {
			return time1 > time2;
		}else if("<".equals(operator)) {
			return time1 < time2;
		}else if(">=".equals(operator)) {
			return time1 >= time2;
		}else if("<=".equals(operator)) {
			return time1 <= time2;
		}else if("!=".equals(operator)) {
			return time1 != time2;
		}
		
		return false;
	}
	
	/**
	 * 得到当前日期
	 * @return 日期（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getNowDateTime() {
		Date date = new Date();
		return dateToString(date);
	}
	
	/**
	 * 得到日期的毫秒数
	 * @param dateStr 日期
	 * @return 毫秒
	 */
	public static long getMillisecondByDateStr(String dateStr) {
		Date date = formatToDate(dateStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	} 
	/**
	 * 得到日期的毫秒数
	 * @param date 日期
	 * @return 毫秒
	 */
	public static long getMillisecondByDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	} 
	/**
	 * 根据时间增减分钟
	 * @param time 日期
	 * @param count 分钟数
	 * @return 结果
	 */
	public static String addMinute(String time, int count) {
		Date date = null;
		if(time == null) {
			date = new Date();
		}else {
			date = formatToDate(time);
		}
		
		Date d = AddMinute(date, count);
		return dateToString(d);
	}
	
	/**
	 * 根据时间增减秒钟
	 * @param time 时间
	 * @param count 秒数
	 * @return 结果
	 */
	public static String addSecondStr(String time, int count) {
		Date date = null;
		if(time == null) {
			date = new Date();
		}else {
			date = formatToDate(time);
		}
		
		Date d = addSecond(date, count);
		return dateToString(d);
	}
	 
	
	/**
	 * 将字符串的日期格式化为yyyy-MM-dd格式的字符串日期
	 * @param str 字符串时间
	 * @return 字符串时间（yyyy-MM-dd）
	 */
	public static String formatDataStrToStr(String str) {
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(str);
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * yyyy年MM月dd日
	 * 
	 * @param date 日期
	 * @return 字符串（yyyy年MM月dd日）
	 */
	public static String dateToDayStrC(Date date) {
		if (date == null)
			return "";
		String format = "yyyy年MM月dd日";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date 日期
	 * @return 字符串（yyyy-MM-dd HH:mm:ss）
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	/**
	 * yyyy-MM-dd HH:mm
	 * 
	 * @param date 日期
	 * @return 字符串（yyyy-MM-dd HH:mm）
	 */
	public static String dateToStringYMDHM(Date date) {
		if (date == null)
			return "";
		String format = "yyyy-MM-dd HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	public static String dateToStringYYMM(Date date) {
		if (date == null)
			return "";
		String format = "yyyyMM";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	public static String dateToStringYY_MM(Date date) {
		if (date == null)
			return "";
		String format = "yyyy-MM";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	public static String dateToStringMMDD(Date date) {
		if (date == null)
			return "";
		String format = "MMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	/**
	 * HH:mm:ss
	 * 
	 * @param date 日期
	 * @return 字符串
	 */
	public static String dateToTimeStr(Date date) {
		if (date == null)
			return "";
		String format = "HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	public static String dateToStringShort(Date date) {
		if (date == null)
			return null;
		String format = "MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	public static String AddDay(String date) {
		try {
			String format = "yyyy-MM-dd";
			if (!date.contains("-"))
				format = "yyyyMMdd";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d = sdf.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.DAY_OF_MONTH, 1);
			d = c.getTime();
			return sdf.format(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 天数进行加减，count为负数时为减
	 * 
	 * @param date 日期
	 * @param count 天数
	 * @return 结果
	 */
	public static String AddDay(String date, int count) {
		try {
			String format = "yyyy-MM-dd";
			if (date!=null && !date.contains("-"))
				format = "yyyyMMdd";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d = sdf.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.DAY_OF_MONTH, count);
			d = c.getTime();
			return sdf.format(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 小时进行加减，count为负数时为减
	 * 
	 * @param date 日期
	 * @param count 小时数
	 * @return 结果
	 */
	public static String addHour(Date date, int count) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(c.HOUR_OF_DAY, count);
			return dateToString(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String addHour(String date, int count) {
		Date d = null;
		if(date == null) {
			d = new Date();
		}else {
			d = formatToDate(date);
		}
		return addHour(d, count);
	}
	/**
	 * 分钟进行加减，count为负数时为减
	 * 
	 * @param date 日期
	 * @param count 分钟
	 * @return 结果
	 */
	public static Date AddMinute(Date date, int count) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(c.MINUTE, count);
			date = c.getTime();
			return date;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 月进行加减，count为负数时为减
	 * 
	 * @param date 日期
	 * @param count 月数
	 * @return 结果
	 */
	public static String AddMonth(String date, int count) {
		try {
			boolean isShort = false;
			if (date.length() < 8) {
				isShort = true;
				date = date + "-01";
			}
			String format = "yyyy-MM-dd";
			if (!date.contains("-"))
				format = "yyyyMMdd";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d = sdf.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.MONTH, count);
			d = c.getTime();
			String res = sdf.format(d);
			if (isShort)
				res = res.substring(0, 7);
			return res;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 返回时间差 ， 单位为毫秒
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 毫秒
	 */
	public static long difference(Date date1, Date date2) {
		long l1 = 0;
		long l2 = 0;
		if (date1 != null) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date1);
			l1 = c1.getTimeInMillis();
		}
		if (date2 != null) {
			Calendar c2 = Calendar.getInstance();
			c2.setTime(date2);
			l2 = c2.getTimeInMillis();
		}
		return l2 - l1;
	}
	/**
	 * 返回时间差 ， 单位为毫秒
	 * 
	 * @param datestr1 时间1
	 * @param datestr2 时间2
	 * @return 毫秒
	 */
	public static long difference(String datestr1, String datestr2) {
		Date date1 = FuncDate.formatToDate(datestr1);
		Date date2 = FuncDate.formatToDate(datestr2);
		long l1 = 0;
		long l2 = 0;
		if (date1 != null) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date1);
			l1 = c1.getTimeInMillis();
		}
		if (date2 != null) {
			Calendar c2 = Calendar.getInstance();
			c2.setTime(date2);
			l2 = c2.getTimeInMillis();
		}
		return l2 - l1;
	}
	public static String dateToUpper(Date date) {
		if (date == null)
			return "";
		return DateUtil.dataToUpper(date);
	}

	public static List<String> betweenMonth(String startMonth, String endMonth) {
		List<String> arrayList = new ArrayList<String>();
		arrayList.add(startMonth);
		if (startMonth.equals(endMonth))
			return arrayList;
		arrayList.addAll(betweenMonth(AddMonth(startMonth, 1), endMonth));
		return arrayList;
	}
	 

	/**
	 * 根据时间增减秒
	 * @param time 日期
	 * @param count 秒
	 * @return 结果
	 */
	public static Date addSecond(Date time, int count) {
		Calendar c = Calendar.getInstance();
		if(time != null){
			c.setTime(time);
		}
		c.add(c.SECOND, count);
		time = c.getTime(); 
		return time;
	} 
	
	
	public static int getNowMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH);
	}
	
	/**
	 * 使用参数Format格式化Date成字符串
	 * 
	 * @param date 日期
	 * @param pattern 格式
	 * @return 返回数据
	 */
	public static String format(String date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(FuncDate.formatToDate(date));
	}
}

/**
 * 日期操作工具类
 * 
 */
class DateUtil {

	/**
	 * 日期转化为大小写
	 * @param date 日期
	 * @return 结果
	 */
	public static String dataToUpper(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return numToUpper(year) + "年" + monthToUppder(month) + "月"
				+ dayToUppder(day) + "日";
	}

	/**
	 * 将数字转化为大写
	 * @param num 数字
	 * @return 结果
	 */
	public static String numToUpper(int num) {
		// String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
		String u[] = { "O", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(num).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	/**
	 * 月转化为大写
	 * @param month 月
	 * @return 结果
	 */
	public static String monthToUppder(int month) {
		if (month < 10) {
			return numToUpper(month);
		} else if (month == 10) {
			return "十";
		} else {
			return "十" + numToUpper(month - 10);
		}
	}

	/**
	 * 日转化为大写
	 * @param day 日
	 * @return 结果
	 */
	public static String dayToUppder(int day) {
		if (day < 20) {
			return monthToUppder(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十";
			} else {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十"
						+ numToUpper(Integer.parseInt(str[1] + ""));
			}
		}
	} 
	
}
