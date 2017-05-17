package com.bx.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
public class BaseController {

	
	
	public static String executeSuccess( Map<Object,Object> datamap) {
		//成功传入1 
		Map <Object,Object> map=new HashMap<Object,Object>();
		map.put("result", "1"); 
		map.put("data", datamap);
		return JSON.toJSONString(map);
	}
	public static String executeSuccess( List<Object> datalist) {
		//成功传入1 
		Map <Object,Object> map=new HashMap<Object,Object>();
		map.put("result", "1"); 
		map.put("data", datalist);
		return JSON.toJSONString(map);
	}
	
	public static String executeSuccess() {
		//成功返回1  
		return "{\"result\":\"1\"}";
	}
	public static String executeSuccess(String data) {
		//成功返回1  
		Map <Object,Object> map=new HashMap<Object,Object>();
		map.put("result", "1"); 
		map.put("data", data);
		return JSON.toJSONString(map);
	}
	  
	/**
	 * 
	 * @param map
	 * @param datamap
	 * @param 错误返回的信息
	 * @return
	 */
	public static String executeFailed(Map<Object,Object> datamap,String value) {
		//失败传入0
		Map <Object,Object> map=new HashMap<Object,Object>();
		map.put("result", "0");
		map.put("error_msg", value);
		map.put("data", datamap);
		//return JSON.toJSONString(map);
		return JSON.toJSONString(map);
	}
	public static String executeFailed(String value) {
		//失败传入0
		Map <Object,Object> map=new HashMap<Object,Object>();
		map.put("result", "0");
		map.put("error_msg", value);
		return JSON.toJSONString(map);
	}
	
	public static String getJson(Map<Object, Object> map,Map<Object, Object> datamap){
		StringBuffer result = new StringBuffer();
		if(map != null && map.size() != 0){
			result.append("{");
			for(Object key : map.keySet()){
				if(map.get(key) != null) {
					if(key.equals("result"))
					{
						result.append("\"" + key + "\":\"" + map.get(key)+"\",");
						if(map.get(key).equals("0"))
						{
							result.append("\"" + "error_msg" + "\":\"" + map.get("error_msg")+"\",");
						}						
					}					
					if(key.equals("data")) {//遍历dataMap里的数据
						result.append("\"data\":");
						result.append("{");
						if(datamap != null && datamap.size() != 0){
							
							for(Object datakey : datamap.keySet()){
								if(datamap.get(datakey) != null) {
									result.append("\"" + datakey + "\":\"" + datamap.get(datakey)+"\",");
								}
							}
							result.deleteCharAt(result.lastIndexOf(","));//删除最后一个，
						}
						result.append("}");
					} 
				}
			}
			result.append("}");
		}
		return result.toString();
	}

	
	

	
	public static String getJson(Map<Object, Object> map){
		StringBuffer result = new StringBuffer();
		if(map != null && map.size() != 0){
			result.append("{");
			int i = 0;
			for(Object key : map.keySet()){
				if(map.get(key) != null) {
					if(i != 0){
						result.append(", ");
					}
					if(key.equals("data")) {
						result.append("\"" + key + "\":" + map.get(key));
					} else {
						result.append("\"" + key + "\":\"" + map.get(key) + "\"");
					}
				}
				i++;
			}
			result.append("}");
		}
		return result.toString();
	}
}
