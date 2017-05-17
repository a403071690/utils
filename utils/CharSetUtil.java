package com.bx.utils;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * char set编码解码工具类
 * </p>
 * 
 * @author gaoxudong
 * @date 2017-5-11
 */
public class CharSetUtil {
	/**
	 * 不加\\u转译符
	 * 
	 * @param str
	 * @return
	 */
	public static String utf8ToUnicode(String str) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		do {
			char c = str.charAt(i);
			sb.append(Integer.toHexString(c));
		} while (++i < str.length());
		return sb.toString();
	}

	/**
	 * 不加\\u转译符
	 * 
	 * @param str
	 * @return
	 */
	public static String utf8ToGb2312(String str) {
		StringBuffer gbkStr = new StringBuffer();
		byte[] gbkDecode = null;
		try {
			gbkDecode = str.getBytes("gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (byte b : gbkDecode) {
			gbkStr.append(Integer.toHexString(b & 0xFF));
		}
		return gbkStr.toString();
	}

	/**
	 * gbk编码16进制码转utf8字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String gbkToUtf8(String str) throws Exception {
		byte[] bytes = new byte[str.length() / 2]; // 定义字节数组，长度为字符串的一半。
		byte tempByte = 0; // 临时变量。
		byte tempHigh = 0;
		byte tempLow = 0;
		for (int i = 0, j = 0; i < str.length(); i += 2, j++) // 每循环处理2个字符，最后新城一个字节。
		{
			tempByte = (byte) (((int) str.charAt(i)) & 0xff); // 处理高位。
			if (tempByte >= 48 && tempByte <= 57) {
				tempHigh = (byte) ((tempByte - 48) << 4);
			} else if (tempByte >= 97 && tempByte <= 101)// 'a'--'e'
			{
				tempHigh = (byte) ((tempByte - 97 + 10) << 4);
			}

			tempByte = (byte) (((int) str.charAt(i + 1)) & 0xff); // 处理低位。
			if (tempByte >= 48 && tempByte <= 57) {
				tempLow = (byte) (tempByte - 48);
			} else if (tempByte >= 97 && tempByte <= 101) // 'a'--'e'
			{
				tempLow = (byte) (tempByte - 97 + 10); // 'a'对应10.（或0xa.）
			}
			bytes[j] = (byte) (tempHigh | tempLow); // 通过‘或’加在一起。
		}
		String result = new String(bytes, "GBK");
		return result;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 把16进制字符串转换成字节数组
	 * 
	 * @param hexstr
	 * @return
	 */
	public static byte[] HexStringToBytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	public static void main(String[] args) {
		System.out.println(utf8ToGb2312("多云"));
	}
}
