package com.bx.utils;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class AESUtil {//密钥算法   
    public static final String KEY_ALGORITHM = "AES";  
      
    //加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式   
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";  
      
    /** 
     * 生成密钥 
     */  
    public static String initkey() throws Exception{  
    	//实例化密钥生成器   
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM); 
        //初始化密钥生成器:AES要求密钥长度为128,192,256位 
        kg.init(128);
        //生成密钥 
        SecretKey secretKey = kg.generateKey();
      //获取二进制密钥编码形式   
        return Base64.encode(secretKey.getEncoded());  
    }  
      
      
    /** 
     * 转换密钥 
     */  
    public static Key toKey(byte[] key) throws Exception{  
        return new SecretKeySpec(key, KEY_ALGORITHM);  
    }  
      
      
    /** 
     * 加密数据 
     * @param data 待加密数据 
     * @param key  密钥 
     * @return 加密后的数据 
     * */  
    public static String encrypt(String data, String key) throws Exception{  
        Key k = toKey(Base64.decode(key));                           //还原密钥   
        //使用PKCS7Padding填充方式,这里就得这么写了(即调用BouncyCastle组件实现)   
        //Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");   
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);              //实例化Cipher对象，它用于完成实际的加密操作   
        cipher.init(Cipher.ENCRYPT_MODE, k);                               //初始化Cipher对象，设置为加密模式   
        return Base64.encode(cipher.doFinal(data.getBytes())); //执行加密操作。加密后的结果通常都会用Base64编码进行传输   
    }  
      
      
    /** 
     * 解密数据 
     * @param data 待解密数据 
     * @param key  密钥 
     * @return 解密后的数据 
     * */  
    public static String decrypt(String data, String key) throws Exception{  
        Key k = toKey(Base64.decode(key));  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        cipher.init(Cipher.DECRYPT_MODE, k);                          //初始化Cipher对象，设置为解密模式   
        return new String(cipher.doFinal(Base64.decode(data)), "utf-8"); //执行解密操作   
    }  
      
      
    public static void main(String[] args) throws Exception {  
        String source = "站在云端，敲下键盘，望着通往世界另一头的那扇窗，只为做那读懂0和1的人。。"; 
    	  //String source = "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"; 
//       
        	String key = "2d306bef7c134cdb";
//        System.out.println("原文：" + source);  
//          
      String keys = initkey();  
       //System.out.println("密钥：" + keys);  
//        System.out.println("密钥base64 decode：" + new String(Base64.decode(key)));  
//          
       String encryptData = encrypt(source, Base64.encode(key.getBytes("utf-8")));  
      //  String encryptData = encrypt(source, key);  
       System.out.println("加密：" + encryptData);  
//          
//        String decryptData = decrypt(source, Base64.encode(key.getBytes("utf-8")));  
////        String decryptData = decrypt(encryptData, key);  
//        System.out.println("解密: " + decryptData);  
    	
        String decryptData = decrypt(encryptData, Base64.encode(key.getBytes("utf-8")));  
       System.out.println("解密: " + decryptData); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // System.out.println("-------"+sdf.format(new Date(Long.parseLong(decryptData))));
    }  }
