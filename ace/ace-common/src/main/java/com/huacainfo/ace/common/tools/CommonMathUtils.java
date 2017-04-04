package com.huacainfo.ace.common.tools;

import java.math.BigDecimal;

public class CommonMathUtils {
	/**
	 * 加法f1，f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal add(float f1, float f2) {
		BigDecimal b1 = new BigDecimal(Float.toString(f1));
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		b1 = b1.add(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
		return b1;
	}

	public static BigDecimal add(float f1, float f2, float f3) {
		BigDecimal rst = add(f1, f2);
		BigDecimal b3 = new BigDecimal(Float.toString(f3));
		rst = rst.add(b3).setScale(4, BigDecimal.ROUND_HALF_UP);
		return rst;
	}

	/**
	 * 加法f1，f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal add(double f1, double f2) {
		BigDecimal b1 = new BigDecimal(Double.toString(f1));
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		return b1.add(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal add(double f1, double f2, double f3) {
		BigDecimal rst = add(f1, f2);
		BigDecimal b3 = new BigDecimal(Double.toString(f3));
		rst = rst.add(b3).setScale(4, BigDecimal.ROUND_HALF_UP);
		return rst;
	}

	public static BigDecimal add(Object f1, Object f2, Object f3) {
		BigDecimal b1 = new BigDecimal(String.valueOf(f1));
		BigDecimal b2 = new BigDecimal(String.valueOf(f2));
		BigDecimal b3 = new BigDecimal(String.valueOf(f3));
		b1 = b1.add(b2).add(b3).setScale(4, BigDecimal.ROUND_HALF_UP);
		return b1;
	}
	public static BigDecimal add(Object f1, Object f2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(f1));
		BigDecimal b2 = new BigDecimal(String.valueOf(f2));
		b1 = b1.add(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
		return b1;
	}

	public static BigDecimal add(BigDecimal f1, float f2) {
		BigDecimal b1 = f1;
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		b1 = b1.add(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
		return b1;
	}

	public static BigDecimal add(BigDecimal f1, double f2) {
		BigDecimal b1 = f1;
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		b1 = b1.add(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
		return b1;
	}

	/**
	 * 减法,f1-f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal subtract(float f1, float f2) {
		BigDecimal b1 = new BigDecimal(Float.toString(f1));
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		return b1.subtract(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 减法,f1-f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal subtract(double f1, double f2) {
		BigDecimal b1 = new BigDecimal(Double.toString(f1));
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		return b1.subtract(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 减法,f1-f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal f1, double f2) {
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		return f1.subtract(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 减法,f1-f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal f1, float f2) {
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		return f1.subtract(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 乘法 f1*f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal multiply(float f1, float f2) {
		BigDecimal b1 = new BigDecimal(Float.toString(f1));
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		return b1.multiply(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 乘法 f1*f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal multiply(double f1, double f2) {
		BigDecimal b1 = new BigDecimal(Double.toString(f1));
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		b1 = b1.multiply(b2);
		b1 = b1.setScale(4, BigDecimal.ROUND_HALF_UP);
		return b1;
	}

	/**
	 * 乘法 f1*f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal f1, float f2) {
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		return f1.multiply(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 乘法 f1*f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal f1, double f2) {
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		return f1.multiply(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除法 f1/f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal divide(float f1, float f2) {
		BigDecimal b1 = new BigDecimal(Float.toString(f1));
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		return b1.divide(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除法 f1/f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal divide(BigDecimal f1, float f2) {
		BigDecimal b2 = new BigDecimal(Float.toString(f2));
		return f1.divide(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除法 f1/f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal divide(BigDecimal f1, double f2) {
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		return f1.divide(b2).setScale(4, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除法 f1/f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal divide(double f1, double f2) {
		BigDecimal b1 = new BigDecimal(Double.toString(f1));
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		b1 = b1.divide(b2);
		b1 = b1.setScale(4, BigDecimal.ROUND_HALF_UP);
		return b1;
	}

	/**
	 * 返回较小数值
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal backMin(double f1,double f2) {
		BigDecimal b1 = new BigDecimal(Double.toString(f1));
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		if (b1.compareTo(b2) < 0){
			return b1;
		}else{
			return b2;
		}
	}
	
	/**
	 * 返回较大数值
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static BigDecimal backMax(double f1,double f2) {
		BigDecimal b1 = new BigDecimal(Double.toString(f1));
		BigDecimal b2 = new BigDecimal(Double.toString(f2));
		if (b1.compareTo(b2) > 0){
			return b1;
		}else{
			return b2;
		}
	}
}
