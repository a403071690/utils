package com.bx.utils;

public class Constant {
	// --正确返回码
		public static final int RET_OK = 1;
		// --参数错误码
		public static final int SYSTEM_ERROR = -200;// 系统错误
		public static final int BLANK_PARAM_ERROR = -201;// 参数为null
		// --系统错误码
		public static final int DB_ERROR = -500;// 数据库异常

		// 常量
		/** 配置文件项目名 */
		public static final String PROJECT_CONFIG_NAME = "bengxin_HttpServer";
		/** 签名key对应的属性名 */
		public static final String SIGN_KEY = "key";
		/** 返回码对应的属性名 */
		public static final String RESULT_CODE_KEY = "resultCode";
		
		/** 返回码对应的属性名 */
		public static final String RESULT_INFO_KEY = "resultInfo";
		/** passport信息对应的属性名 */
		public static final String PASSPORT_INFO_KEY = "passportInfo";
		/** account信息对应的属性名 */
		public static final String ACCOUNT_INFO_KEY = "account";
		/** requestId */
		public static final String REQUEST_ID_KEY = "request_id";
		/** 默认连接pfserver客户端线程数 */
		public static final int DEFAULT_PFSERVER_CLIENT_THREADS = 100;
		/** 业务上用来识别新增保存和修改保存的状态吗*/
		public static final int DEFAULT_SAVE_FLAG = -1;
		
		//session user key
		public static final String SESSION_USER = "session_user";
		//与手表交互的服务器地址
		/**
		 * 与手表交互的服务器地址
		 */
		public static final String WATCH_SERVER_URL =  "http://106.2.197.29:8099"; 
		/**
		 *厂商
		 */
		public static final String FIRM_NAME =  "3G";
}
