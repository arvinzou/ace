package com.huacainfo.ace.operana.service.common;

import java.math.BigDecimal;
import java.util.*;

import com.huacainfo.ace.common.tools.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenxiaoke on 2017/5/9.
 */
public class DataCalUtils {
	private static final Logger logger = LoggerFactory.getLogger(DataCalUtils.class);
	private static final double fl = 4.3482;
	public static java.math.BigDecimal calQuarterY(int quarter, Map<String, BigDecimal> row, String calType) {
		java.math.BigDecimal rst = new java.math.BigDecimal("0");
		// logger.info("->row {}",row);
		if (calType.equals("1")) {
			for (int i = ((quarter * 13) - 13 + 1); i <= quarter * 13; i++) {
				logger.info("->{}", i);
				rst = rst.add(row.get("wkt" + i));
			}
		} else {
			java.math.BigDecimal wkt = new java.math.BigDecimal("0");
			java.math.BigDecimal wkc = new java.math.BigDecimal("0");
			for (int i = ((quarter * 13) - 13 + 1); i <= quarter * 13; i++) {
				logger.info("-----i->{}", i);
				wkt = wkt.add(row.get("wkt" + i));
				wkc = wkc.add(row.get("wkc" + i));

			}
			logger.info("-----wkc->{}", wkc);
			logger.info("-----wkt->{}", wkt);
			rst = wkc.divide(wkt, 4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}

	public static java.math.BigDecimal calMonthY(int month, Map<String, java.math.BigDecimal> row, String calType) {
		java.math.BigDecimal rst = new java.math.BigDecimal("0");
		// logger.info("->row {}",row);
		if (calType.equals("1")) {
			for (int i = (int) ((month * fl) - fl + 1); i <= month * fl; i++) {
				logger.info("->{}", i);
				rst = rst.add(row.get("wkt" + i));
			}
		} else {
			java.math.BigDecimal wkt = new java.math.BigDecimal("0");
			java.math.BigDecimal wkc = new java.math.BigDecimal("0");
			for (int i = (int) ((month * fl) - fl + 1); i <= month * fl; i++) {
				wkt = wkt.add(row.get("wkt" + i));
				wkc = wkc.add(row.get("wkc" + i));
			}
			rst = wkc.divide(wkt, 4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}
	public static java.math.BigDecimal calWeekY(int cwk, Map<String, java.math.BigDecimal> row, String calType) {
		java.math.BigDecimal rst = new java.math.BigDecimal("0");
		// logger.info("->row {}",row);
		if (calType.equals("1")) {
			rst = rst.add(row.get("wkt" + cwk));
		} else {
			java.math.BigDecimal wkt = new java.math.BigDecimal("0");
			java.math.BigDecimal wkc = new java.math.BigDecimal("0");
			wkt = wkt.add(row.get("wkt" + cwk));
			wkc = wkc.add(row.get("wkc" + cwk));
			rst = wkc.divide(wkt, 4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}
	public static java.math.BigDecimal calYearY(int cwk, Map<String, java.math.BigDecimal> row, String calType) {
		java.math.BigDecimal rst = new java.math.BigDecimal("0");
		// logger.info("->row {}",row);
		if (calType.equals("1")) {
			for (int i = 1; i <= cwk; i++) {
				logger.info("->{}", i);
				rst = rst.add(row.get("wkt" + i));
			}
		} else {
			java.math.BigDecimal wkt = new java.math.BigDecimal("0");
			java.math.BigDecimal wkc = new java.math.BigDecimal("0");
			for (int i = 1; i <= cwk; i++) {
				wkt = wkt.add(row.get("wkt" + i));
				wkc = wkc.add(row.get("wkc" + i));
			}
			rst = wkc.divide(wkt, 4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}

	public static void calX(int cwk, List<Integer> quarters, List<Integer> months, List<Integer> weeks,
			String viewType) {
		if (CommonUtils.isBlank(viewType) || viewType.equals("1")) {
			calXDefault(cwk, quarters, months, weeks);
		} else if (viewType.equals("2")) {
			calXMonth(cwk, quarters, months, weeks);
		} else if (viewType.equals("3")) {
			calXWeek(cwk, quarters, months, weeks);
		}

	}
	public static void calXDefault(int cwk, List<Integer> quarters, List<Integer> months, List<Integer> weeks) {
		int wkl = 0;
		int month = (int) (cwk / fl);
		wkl = cwk - (int) (month * fl);
		int jd = 0;
		if (month >= 3) {
			jd = month / 3;
			month = month % 3;
		}
		if (jd > 0) {
			for (int i = 1; i <= jd; i++) {
				quarters.add(i);
				logger.info("{}季度", i);
			}
		}
		if (month > 0) {
			for (int j = 1; j <= month; j++) {
				months.add((jd * 3 + j));
				logger.info("{}月", (jd * 3 + j));
			}
		}
		if (wkl > 0) {
			for (int k = (cwk - wkl + 1); k <= cwk; k++) {
				weeks.add(k);
				logger.info("第{}周", k);
			}
		}
	}
	public static void calXMonth(int cwk, List<Integer> quarters, List<Integer> months, List<Integer> weeks) {
		int wkl = 0;
		int month = (int) (cwk / fl);
		wkl = cwk - (int) (month * fl);
		if (month > 0) {
			for (int j = 1; j <= month; j++) {
				months.add((j));
				logger.info("{}月", (j));
			}
		}
		if (wkl > 0) {
			for (int k = (cwk - wkl + 1); k <= cwk; k++) {
				weeks.add(k);
				logger.info("第{}周", k);
			}
		}
	}
	public static void calXWeek(int cwk, List<Integer> quarters, List<Integer> months, List<Integer> weeks) {

		if (cwk > 0) {
			for (int k = 1; k <= cwk; k++) {
				weeks.add(k);
				logger.info("第{}周", k);
			}
		}
	}
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}
	public static void main(String args[]) {
		Map<String, java.math.BigDecimal> row = new HashMap<String, BigDecimal>();
		row.put("lastYear", new java.math.BigDecimal("0.26"));
		for (int i = 1; i <= 53; i++) {
			row.put("wkt" + i, new java.math.BigDecimal("100"));
			row.put("wkc" + i, new java.math.BigDecimal("10"));
		}
		/*
		 * System.out.println(calQuarterY(1,row,"1"));
		 * System.out.println(calQuarterY(2,row,"1"));
		 * System.out.println(calQuarterY(3,row,"1"));
		 * System.out.println(calQuarterY(4,row,"1"));
		 * System.out.println(calQuarterY(1,row,"2"));
		 * System.out.println(calQuarterY(2,row,"2"));
		 * System.out.println(calQuarterY(3,row,"2"));
		 * System.out.println(calQuarterY(4,row,"2"));
		 */

		// System.out.println(getWeekOfYear(new Date()));

		// calX(19,new ArrayList<Integer>(),new ArrayList<Integer>(),new
		// ArrayList<Integer>());

		/*
		 * System.out.println(calMonthY(1,row,"1"));
		 * System.out.println(calMonthY(2,row,"1"));
		 * System.out.println(calMonthY(3,row,"1"));
		 * System.out.println(calMonthY(4,row,"1"));
		 * System.out.println(calMonthY(5,row,"1"));
		 * 
		 * System.out.println(calMonthY(1,row,"2"));
		 * System.out.println(calMonthY(2,row,"2"));
		 * System.out.println(calMonthY(3,row,"2"));
		 * System.out.println(calMonthY(4,row,"2"));
		 * System.out.println(calMonthY(5,row,"2"));
		 */
		Map<String, Object> rst = new HashMap<String, Object>();
		int year = 2017;
		List<String> x = new ArrayList<String>();
		List<java.math.BigDecimal> y = new ArrayList<java.math.BigDecimal>();
		int lastYear = year - 1;
		int cwk = 19;
		String calType = "2";
		List<Integer> quarters = new ArrayList<Integer>();
		List<Integer> months = new ArrayList<Integer>();
		List<Integer> weeks = new ArrayList<Integer>();

		DataCalUtils.calX(cwk, quarters, months, weeks, "1");
		x.add(lastYear + "年");
		y.add(row.get("lastYear"));
		for (Integer e : quarters) {
			x.add(e + "季度");
			y.add(DataCalUtils.calQuarterY(e, row, calType));
		}
		for (Integer e : months) {
			x.add(e + "月");
			y.add(DataCalUtils.calMonthY(e, row, calType));
		}
		for (Integer e : weeks) {
			x.add(e + "周");
			y.add(DataCalUtils.calWeekY(e, row, calType));
		}
		x.add(year + "年累计");
		y.add(DataCalUtils.calYearY(cwk, row, calType));
		rst.put("dataX", x);
		rst.put("dataY", y);
		logger.info("{}", rst);
	}
	public static int getQuarterByMonth(int month) {
		int months[] = {1, 2, 3, 4};
		if (month >= 0 && month <= 3) // 1-3月;0,1,2
			return months[0];
		else if (month >= 4 && month <= 6) // 4-6月;3,4,5
			return months[1];
		else if (month >= 7 && month <= 9) // 7-9月;6,7,8
			return months[2];
		else
			// 10-12月;10,11,12
			return months[3];
	}
	public static int getQuarterByWeek(int week) {
		int quarters[] = {1, 2, 3, 4};

		return quarters[3];
	}

	public static java.math.BigDecimal getNormInex(Map<String,java.math.BigDecimal> data,int i,String category,java.math.BigDecimal defaultValue){
		if(category.equals("quarters")){
			return data.get("value"+i);
		}
		if(category.equals("months")){
			return data.get("value"+getQuarterByMonth(i));
		}
		if(category.equals("weeks")){
			return data.get("value"+getQuarterByWeek(i));
		}
		return defaultValue;
	}
}
