package com.bx.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return 
	 */  
	public static byte[] encrypt(String content, String password) {  
	        try {             
	        	
	        		//==
		        	//KeyGenerator kgen = KeyGenerator.getInstance("AES");
		            
		          //  kgen.init(128, random);//加密解密时都加上OK了
	        		//==
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
		            random.setSeed(password.getBytes());
	                kgen.init(128,random );  
	                
	                SecretKey secretKey = kgen.generateKey();  
	                byte[] enCodeFormat = secretKey.getEncoded();  
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
	                Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
	                byte[] byteContent = content.getBytes("utf-8");  
	                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
	                byte[] result = cipher.doFinal(byteContent);  
	                return result; // 加密   
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
	}  
	
	/**解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 */  
	public static byte[] decrypt(byte[] content, String password) {  
	        try {  
	        		KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
		            random.setSeed(password.getBytes());
	                kgen.init(128,random );  
	                 SecretKey secretKey = kgen.generateKey();  
	                 byte[] enCodeFormat = secretKey.getEncoded();  
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
	                byte[] result = cipher.doFinal(content);  
	                return result; // 加密   
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
	}  
	 
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
                String hex = Integer.toHexString(buf[i] & 0xFF);  
                if (hex.length() == 1) {  
                        hex = '0' + hex;  
                }  
                sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
}  
	
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}  
	
	
	public static void main(String[] args) {
		String content = "{\"friend_data\":[{\"c_uid\":\"316\",\"c_fid\":\"292\",\"c_src_uid\":\"318\",\"c_des_uid\":\"316\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"杨丹歌\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-10/316_224404354.png\",\"p002\":\"92\"},{\"c_uid\":\"318\",\"c_fid\":\"508\",\"c_src_uid\":\"318\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"我是大波峰\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-20/318_160824330.png\",\"p002\":\"0\"},{\"c_uid\":\"541\",\"c_fid\":\"34535\",\"c_src_uid\":\"541\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"草原\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-20/541_100754532.png\",\"p002\":\"0\"},{\"c_uid\":\"314\",\"c_fid\":\"290\",\"c_src_uid\":\"314\",\"c_des_uid\":\"318\",\"c_src_remark\":\"周洲粥\",\"c_des_remark\":\"二逼\",\"c_name\":\"这了so\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"313\",\"c_fid\":\"294\",\"c_src_uid\":\"318\",\"c_des_uid\":\"313\",\"c_src_remark\":\"峰峰\",\"c_des_remark\":\"二逼坤\",\"c_name\":\"新年快乐\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-28/313_065234454.png\",\"p002\":\"0\"},{\"c_uid\":\"8\",\"c_fid\":\"302\",\"c_src_uid\":\"8\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"七剑下江南\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"6\",\"c_fid\":\"316\",\"c_src_uid\":\"318\",\"c_des_uid\":\"6\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"坤小白\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"319\",\"c_fid\":\"324\",\"c_src_uid\":\"318\",\"c_des_uid\":\"319\",\"c_src_remark\":\"\",\"c_des_remark\":\"苗子哦哦哦\",\"c_name\":\"苗子\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-05/319_045154149.png\",\"p002\":\"0\"},{\"c_uid\":\"336\",\"c_fid\":\"325\",\"c_src_uid\":\"318\",\"c_des_uid\":\"336\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"JAD\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-03-08/336_10373780.png\",\"p002\":\"0\"},{\"c_uid\":\"325\",\"c_fid\":\"336\",\"c_src_uid\":\"325\",\"c_des_uid\":\"318\",\"c_src_remark\":\"玲玲\",\"c_des_remark\":\"\",\"c_name\":\"小蚊子\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"367\",\"c_fid\":\"345\",\"c_src_uid\":\"367\",\"c_des_uid\":\"318\",\"c_src_remark\":\"保洲\",\"c_des_remark\":\"sbbb\",\"c_name\":\"steve cook\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-03-17/367_065343669.png\",\"p002\":\"0\"},{\"c_uid\":\"352\",\"c_fid\":\"376\",\"c_src_uid\":\"318\",\"c_des_uid\":\"352\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"小欢欢\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-11/352_135401263.png\",\"p002\":\"0\"},{\"c_uid\":\"312\",\"c_fid\":\"388\",\"c_src_uid\":\"318\",\"c_des_uid\":\"312\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"保洲\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"382\",\"c_fid\":\"391\",\"c_src_uid\":\"318\",\"c_des_uid\":\"382\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"杨丹歌\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"390\",\"c_fid\":\"484\",\"c_src_uid\":\"318\",\"c_des_uid\":\"390\",\"c_src_remark\":\"雪峰\",\"c_des_remark\":\"\",\"c_name\":\"黄焖鸡米饭\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-06/390_063714559.png\",\"p002\":\"0\"},{\"c_uid\":\"509\",\"c_fid\":\"585\",\"c_src_uid\":\"509\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"小屏幕大脸盘\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-19/509_034022897.png\",\"p002\":\"0\"},{\"c_uid\":\"533\",\"c_fid\":\"595\",\"c_src_uid\":\"318\",\"c_des_uid\":\"533\",\"c_src_remark\":\"点点滴滴\",\"c_des_remark\":\"\",\"c_name\":\"戴良康\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-02-22/533_070518615.png\",\"p002\":\"0\"},{\"c_uid\":\"317\",\"c_fid\":\"684\",\"c_src_uid\":\"318\",\"c_des_uid\":\"317\",\"c_src_remark\":\"sb\",\"c_des_remark\":\"\",\"c_name\":\"丽莉\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-02-23/317_084934441.png\",\"p002\":\"0\"}],\"caring_people_data\":[{\"c_uid\":\"318\",\"c_name\":\"我是大波峰\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-20/318_160824330.png\",\"c_e_mobile\":\"15801292713\",\"p002\":\"0\"},{\"c_uid\":\"367\",\"c_name\":\"steve cook\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-03-17/367_065343669.png\",\"c_e_mobile\":\"13141332212\",\"p002\":\"0\"},{\"c_uid\":\"338\",\"c_name\":\"第五\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-06/338_032012166.png\",\"c_e_mobile\":\"13782609730\",\"p002\":\"0\"}]}{\"friend_data\":[{\"c_uid\":\"316\",\"c_fid\":\"292\",\"c_src_uid\":\"318\",\"c_des_uid\":\"316\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"杨丹歌\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-10/316_224404354.png\",\"p002\":\"92\"},{\"c_uid\":\"318\",\"c_fid\":\"508\",\"c_src_uid\":\"318\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"我是大波峰\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-20/318_160824330.png\",\"p002\":\"0\"},{\"c_uid\":\"541\",\"c_fid\":\"34535\",\"c_src_uid\":\"541\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"草原\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-20/541_100754532.png\",\"p002\":\"0\"},{\"c_uid\":\"314\",\"c_fid\":\"290\",\"c_src_uid\":\"314\",\"c_des_uid\":\"318\",\"c_src_remark\":\"周洲粥\",\"c_des_remark\":\"二逼\",\"c_name\":\"这了so\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"313\",\"c_fid\":\"294\",\"c_src_uid\":\"318\",\"c_des_uid\":\"313\",\"c_src_remark\":\"峰峰\",\"c_des_remark\":\"二逼坤\",\"c_name\":\"新年快乐\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-28/313_065234454.png\",\"p002\":\"0\"},{\"c_uid\":\"8\",\"c_fid\":\"302\",\"c_src_uid\":\"8\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"七剑下江南\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"6\",\"c_fid\":\"316\",\"c_src_uid\":\"318\",\"c_des_uid\":\"6\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"坤小白\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"319\",\"c_fid\":\"324\",\"c_src_uid\":\"318\",\"c_des_uid\":\"319\",\"c_src_remark\":\"\",\"c_des_remark\":\"苗子哦哦哦\",\"c_name\":\"苗子\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-05/319_045154149.png\",\"p002\":\"0\"},{\"c_uid\":\"336\",\"c_fid\":\"325\",\"c_src_uid\":\"318\",\"c_des_uid\":\"336\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"JAD\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-03-08/336_10373780.png\",\"p002\":\"0\"},{\"c_uid\":\"325\",\"c_fid\":\"336\",\"c_src_uid\":\"325\",\"c_des_uid\":\"318\",\"c_src_remark\":\"玲玲\",\"c_des_remark\":\"\",\"c_name\":\"小蚊子\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"367\",\"c_fid\":\"345\",\"c_src_uid\":\"367\",\"c_des_uid\":\"318\",\"c_src_remark\":\"保洲\",\"c_des_remark\":\"sbbb\",\"c_name\":\"steve cook\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-03-17/367_065343669.png\",\"p002\":\"0\"},{\"c_uid\":\"352\",\"c_fid\":\"376\",\"c_src_uid\":\"318\",\"c_des_uid\":\"352\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"小欢欢\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-11/352_135401263.png\",\"p002\":\"0\"},{\"c_uid\":\"312\",\"c_fid\":\"388\",\"c_src_uid\":\"318\",\"c_des_uid\":\"312\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"保洲\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"382\",\"c_fid\":\"391\",\"c_src_uid\":\"318\",\"c_des_uid\":\"382\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"杨丹歌\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-04/_035528175.png\",\"p002\":\"0\"},{\"c_uid\":\"390\",\"c_fid\":\"484\",\"c_src_uid\":\"318\",\"c_des_uid\":\"390\",\"c_src_remark\":\"雪峰\",\"c_des_remark\":\"\",\"c_name\":\"黄焖鸡米饭\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-06/390_063714559.png\",\"p002\":\"0\"},{\"c_uid\":\"509\",\"c_fid\":\"585\",\"c_src_uid\":\"509\",\"c_des_uid\":\"318\",\"c_src_remark\":\"\",\"c_des_remark\":\"\",\"c_name\":\"小屏幕大脸盘\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-19/509_034022897.png\",\"p002\":\"0\"},{\"c_uid\":\"533\",\"c_fid\":\"595\",\"c_src_uid\":\"318\",\"c_des_uid\":\"533\",\"c_src_remark\":\"点点滴滴\",\"c_des_remark\":\"\",\"c_name\":\"戴良康\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-02-22/533_070518615.png\",\"p002\":\"0\"},{\"c_uid\":\"317\",\"c_fid\":\"684\",\"c_src_uid\":\"318\",\"c_des_uid\":\"317\",\"c_src_remark\":\"sb\",\"c_des_remark\":\"\",\"c_name\":\"丽莉\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-02-23/317_084934441.png\",\"p002\":\"0\"}],\"caring_people_data\":[{\"c_uid\":\"318\",\"c_name\":\"我是大波峰\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-20/318_160824330.png\",\"c_e_mobile\":\"15801292713\",\"p002\":\"0\"},{\"c_uid\":\"367\",\"c_name\":\"steve cook\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-03-17/367_065343669.png\",\"c_e_mobile\":\"13141332212\",\"p002\":\"0\"},{\"c_uid\":\"338\",\"c_name\":\"第五\",\"c_head\":\"http://106.2.197.29:8080/img/heads/2016-01-06/338_032012166.png\",\"c_e_mobile\":\"13782609730\",\"p002\":\"0\"}]}";  
		String password = "456345ddsdfe4r6353";  
		//加密  
		//System.out.println("======================================AES加密===================================");
		//System.out.println("加密前：" + content);  
		long start = System.currentTimeMillis();
		byte[] encryptResult = encrypt(content, password); 
		long end1  = System.currentTimeMillis();
		String encryptResultStr = parseByte2HexStr(encryptResult);  
		
		//System.out.println("加密用时："+(end1-start));
		//System.out.println("加密后：" + encryptResultStr);  
		//解密  
	
		byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);  
		byte[] decryptResult = decrypt(decryptFrom,password);  
		long end2 = System.currentTimeMillis();
		//System.out.println("解密用时："+(end2-end1));
		//System.out.println("解密后：" + new String(decryptResult));  
 
	}
}
