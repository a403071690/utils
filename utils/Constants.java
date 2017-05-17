package com.bx.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class Constants {
	private static Logger log = Logger.getLogger(Constants.class);
	/**
	 * 短信验证码缓存池
	 */
	public static CacheMap<Object, Object> Cache_ShortMessageCde = CacheMap.getDefault();
	
	/**
	 * 配置文件路径
	 */
	public final static String propertyFile = "/Resource/prop.properties";
	
	/**
	 * 上传文件目录
	 */
	public static String UPLOAD_FILE_PATH = "";
	
	/**
	 * 图片服务器上下文
	 */
	public static String IMG_SERVER_CONTEXT = "";
	
	/**
	 * 结果文件目录
	 */
	
	
	/*
	public static String RESULT_FILE_PATH = "";
	
	public static ApplicationContext BEAN_FACTORY = new ClassPathXmlApplicationContext("classpath:Config/ApplicationContext-all.xml");
	
	//public static String Route_File_Path = "D:\\DB Server\\SQLite\\route.db";
	private static String[] TABLE_NMES = {"t_shard_group", "t_shard", "t_fragment_table"};
	private static String[] CREATE_TABLE_SQLS = {"CREATE TABLE if not exists `t_shard_group` ( `c_gid` integer(10) NOT NULL ,"
													+ "`c_gname` varchar(50) NOT NULL,"
													+ "`c_writable` integer(10) NOT NULL,"
													+ "`c_start_id` integer(20) NOT NULL,"
													+ "`c_end_id` integer(20) NOT NULL,"
													+ "`c_shard_cnt` integer(10) NOT NULL);",
													
												"CREATE TABLE if not exists `t_shard` ( `c_sid` integer(10) NOT NULL ,"
														+ "`c_sname` varchar(50) NOT NULL,"
														+ "`c_gid` integer(10) NOT NULL,"
														+ "`c_hash_value` integer(10) NOT NULL,"
														+ "`c_table_cnt` integer(10) NOT NULL,"
														+ "`c_ip` varchar(15) NOT NULL,"
														+ "`c_port` varchar(5) NOT NULL,"
														+ "`c_db` varchar(20) NOT NULL);",
												
												"CREATE TABLE if not exists `t_fragment_table` ( `c_tid` integer(10) NOT NULL ,"
														+ "`c_tname` varchar(50) NOT NULL,"
														+ "`c_sid` integer(10) NOT NULL,"
														+ "`c_start_id` integer(20) NOT NULL,"
														+ "`c_end_id` integer(20) NOT NULL,"
														+ "`c_hash_value` integer(10) NOT NULL);",
	};
	public static Connection MEM_CACHE_CONNECTION;
	public static Statement MEM_CACHE_STATEMENT;
	public static ResultSet MEM_CACHE_RESULTSET;
	
//	private static String driverClass = "com.mysql.jdbc.Driver";
//	private static String jdbcUrl = "jdbc:mysql://localhost:3306/bx_db_0";
//	private static String user = "root";
//	private static String password = "";
//	
//	private static ComboPooledDataSource DATASOURCE_POOL = null;
	
	public static void INIT(){
		log.debug("--------------开始初始化----------------");
		PropertyConfigurator.configure("bin/Resource/log4j.properties");
		//PropertyConfigurator.configure("src/config/Resource/log4j.properties");
		Connection conn_tmp = null;
		Statement stat_tmp = null;
		try {
//			DATASOURCE_POOL = new ComboPooledDataSource();
//			DATASOURCE_POOL.setDriverClass(driverClass);
//			DATASOURCE_POOL.setJdbcUrl(jdbcUrl);
//			DATASOURCE_POOL.setUser(user);
//			DATASOURCE_POOL.setPassword(password);
			
			
			/*1。初始化数据库路由表
			Class.forName("org.sqlite.JDBC");
			conn_tmp = DriverManager.getConnection("jdbc:sqlite:" + Route_File_Path);
			stat_tmp = conn_tmp.createStatement();
			ResultSet rs_tmp = null;
			
			MEM_CACHE_CONNECTION = DriverManager.getConnection("jdbc:sqlite::memory:");
			MEM_CACHE_STATEMENT = MEM_CACHE_CONNECTION.createStatement();
			
			for(int i = 0; i < TABLE_NMES.length; i++){
				MEM_CACHE_STATEMENT.executeUpdate(CREATE_TABLE_SQLS[i]);
				
				rs_tmp = stat_tmp.executeQuery("select * from " + TABLE_NMES[i]);
				while (rs_tmp.next()) {
					ResultSetMetaData rsmd = rs_tmp.getMetaData();
					int col_cnt = rsmd.getColumnCount();
					String insert_sql = "insert into " + TABLE_NMES[i] + " values(";
					for(int j = 1; j <= col_cnt; j++){
						insert_sql += "'" + rs_tmp.getString(j) + "'";
						if(j != col_cnt){
							insert_sql += ", ";
						}
					}
					insert_sql += ");";
//					log.debug(insert_sql);
					MEM_CACHE_STATEMENT.executeUpdate(insert_sql);
				}
				rs_tmp.close();
			}
			
			
		
			/*2。初始化系统常量
			log.debug("1 从数据库读取配置信息...");
			SystemOptionDao systemOptionDao = (SystemOptionDao) BEAN_FACTORY.getBean("systemOptionDao");
			List<SystemOption> propList = systemOptionDao.getSystemOptions();
			log.debug("SystemOption List size:"+propList.size());
			Map<Object, Object> propMap = new HashMap<Object, Object>();
			for(SystemOption o : propList){ 
				propMap.put(o.getOptionCde(), o.getOptionValue());
			}
			
			/*log.debug("2 从文件读取配置信息...");
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(propertyFile));
			propMap.putAll(properties);
			properties = null;
			
			UPLOAD_FILE_PATH = (String) propMap.get("UPLOAD_FILE_PATH");
			IMG_SERVER_CONTEXT = (String) propMap.get("IMG_SERVER_CONTEXT");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*try {
				stat_tmp.close();
				conn_tmp.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String[] GET_DB_INFO(String id){
		String[] result = new String[6];
		try {
			String sql = " SELECT                                                  "
							+ " 	b.c_gname,                                            "
							+ " 	b.c_sname,                                            "
							+ " 	b.c_ip,                                            "
							+ " 	b.c_port,                                            "
							+ " 	b.c_db,                                            "
							+ " 	ft.c_tname                                            "
							+ " FROM                                                    "
							+ " 	t_fragment_table ft,                                  "
							+ " 	(                                                     "
							+ " 		SELECT                                              "
							+ " 			s.c_sid,                                          "
							+ " 			s.c_table_cnt,                                    "
							+ " 			s.c_sname,                                        "
							+ " 			a.c_gname,                                        "
							+ " 			s.c_ip,                                        "
							+ " 			s.c_port,                                        "
							+ " 			s.c_db                                        "
							+ " 		FROM                                                "
							+ " 			t_shard s,                                        "
							+ " 			(                                                 "
							+ " 				SELECT                                          "
							+ " 					sg.c_gid,                                     "
							+ " 					sg.c_shard_cnt,                               "
							+ " 					sg.c_gname                                    "
							+ " 				FROM                                            "
							+ " 					t_shard_group sg                              "
							+ " 				WHERE                                           "
							+ " 					sg.c_start_id <= " + id
							+ " 				AND sg.c_end_id >= " + id
							+ " 			) a                                               "
							+ " 		WHERE                                               "
							+ " 			s.c_gid = a.c_gid                                 "
							+ " 		AND s.c_hash_value = (" + id + " % a.c_shard_cnt)   "
							+ " 	) b                                                   "
							+ " WHERE                                                   "
							+ " 	ft.c_sid = b.c_sid                                    "
							+ " AND ft.c_hash_value = (" + id + " % b.c_table_cnt);			";
			MEM_CACHE_RESULTSET = MEM_CACHE_STATEMENT.executeQuery(sql);
			while (MEM_CACHE_RESULTSET.next()) {
				result[0] = MEM_CACHE_RESULTSET.getString(1);
				result[1] = MEM_CACHE_RESULTSET.getString(2);
				result[2] = MEM_CACHE_RESULTSET.getString(3);
				result[3] = MEM_CACHE_RESULTSET.getString(4);
				result[4] = MEM_CACHE_RESULTSET.getString(5);
				result[5] = MEM_CACHE_RESULTSET.getString(6);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				MEM_CACHE_RESULTSET.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
	}
	*/
	
	public static boolean CheckShortMessage(Object key, Object value){
		boolean b = false;
		try{
			if(Cache_ShortMessageCde.get(key) != null && Cache_ShortMessageCde.get(key).equals(value)){
				b = true;
				Cache_ShortMessageCde.remove(key);
			}
		} catch (Exception e) {
			b = false;
		}
		return b;
	}
}
