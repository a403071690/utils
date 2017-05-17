package com.bx.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSUtil {

	/**
	 * 领先互联 短信验证码
	 * @param mobile
	 * @param content
	 * @param send_time
	 * @return
	 * @throws Exception
	 */
	public static String sendSMS_LXHL(String mobile,String content,String send_time) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer();
		BufferedWriter writer = null;
		BufferedReader reader = null;
		String line = null;
		String result = null;
		try {
			URL url = null;
			String CorpID = "bengxin";// 账户名
			String Pwd = "123456";// 密码
			url = new URL("http://101.200.29.88:8082/SendMT/SendMessage");
			String parameter="CorpID=" + CorpID + "&Pwd=" + Pwd + "&Content=" + content + "&Mobile=" + mobile;
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			//uc.setConnectTimeout(30000);
			uc.setConnectTimeout(120000);
			//uc.setReadTimeout(30000);
			uc.setReadTimeout(120000);
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			
			writer = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), "UTF-8")); // 向服务器传送数据
			writer.write(parameter);
			writer.flush();
			writer.close();
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8")); // 读取服务器响应信息
			
			while ((line = reader.readLine()) != null) {
				sbf.append(line);
			}
			result = sbf.toString().trim();
			reader.close();
			uc.disconnect();	
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("无法连接服务器");
		} finally {
			if (writer != null) {
				try {
					writer.close();
					writer = null;
				} catch (Exception e) {

				}
			}
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (Exception e) {

				}
			}
		}
		//System.out.println("返回值：" + result);
		return result;
	}
	
	/**
	 * 领先互联 邀请好友短信
	 * 短信接口（邀请好友）
		http://101.200.230.59/
		账号 bengxin02
		密码 bengxin02
	 * @param mobile
	 * @param content
	 * @param send_time
	 * @return
	 * @throws Exception
	 */
	public static String sendSMS_YQHY(String mobile,String content,String send_time) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer();
		BufferedWriter writer = null;
		BufferedReader reader = null;
		String line = null;
		String result = null;
		try {
			URL url = null;
			String CorpID = "bengxin02";// 账户名
			String Pwd = "bengxin02";// 密码
			url = new URL("http://101.200.230.59:8082/SendMT/SendMessage");
			String parameter="CorpID=" + CorpID + "&Pwd=" + Pwd + "&Content=" + content + "&Mobile=" + mobile;
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			//uc.setConnectTimeout(30000);
			uc.setConnectTimeout(120000);
			//uc.setReadTimeout(30000);
			uc.setReadTimeout(120000);
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			
			writer = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), "UTF-8")); // 向服务器传送数据
			writer.write(parameter);
			writer.flush();
			writer.close();
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8")); // 读取服务器响应信息
			
			while ((line = reader.readLine()) != null) {
				sbf.append(line);
			}
			result = sbf.toString().trim();
			reader.close();
			uc.disconnect();	
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("无法连接服务器");
		} finally {
			if (writer != null) {
				try {
					writer.close();
					writer = null;
				} catch (Exception e) {

				}
			}
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (Exception e) {

				}
			}
		}
		System.out.println("返回值：" + result);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		SMSUtil.sendSMS_LXHL("13871141231", "我是小呆", "");
	}

}
