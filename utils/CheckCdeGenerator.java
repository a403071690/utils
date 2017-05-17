package com.bx.utils;

import java.util.Random;

public class CheckCdeGenerator {
	
	
	
	public static String getCheckCde() {
		int rdNum = generateRandom(1000, 9999);
		String result = String.valueOf(rdNum);
		return result;
	}
	
	/**
	 * java中如何获取某个范围内的随机数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int generateRandom(int start, int end) {
		int temp = 0;
		try {
			if (start > end) {
				temp = new Random().nextInt(start - end);
				return temp + end;
			} else {
				temp = new Random().nextInt(end - start);
				return temp + start;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp + start;
	}
	
	public static void main(String[] a){
		//System.out.println(getCheckCde());
		
	}
}
