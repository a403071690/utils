package com.bx.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;


public class PostWatchUtil {
	/**
	 * [厂商*设备ID*内容长度*内容]格式,其中厂商标识固定为两个字节,内容]
	 * @param content 内容
	 * @param device_num 设备ID
	 * @param producer 厂商名
	 * @return 请求结果：1为正常 ，否则错误，可查看错误信息
	 * @throws IOException 
	 */
	public static JSONObject postwatch(String content, String device_num, String producer) throws Exception{
		final Logger log = Logger.getLogger(PostWatchUtil.class);
		Map<String,Object> postparam = new HashMap<String,Object>();
		String len = StringUtils.formatContentLength(content.length());//获取数据长度用16进制表示
		postparam.put("sendmsg", "["+producer+"*"+device_num+"*"+len+"*"+content+"]");
		String backString = HttpRequestUtil.sendUrlByPost(Constant.WATCH_SERVER_URL, postparam);

		
		byte[] utf8 = backString.getBytes("UTF-8");
		// Convert from UTF-8 to Unicode 
		String re = new String(utf8, "UTF-8");
		JSONObject json = JSONObject.parseObject(re); 
		log.debug("watch服务器返回：" + re);
		return json;
	}
}
