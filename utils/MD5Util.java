package com.bx.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String encodeCipher(String digest) {
		if (digest == null) {
			return null;
		}
		byte[] cipher = (byte[]) null;
		MessageDigest messDigest = null;
		try {
			messDigest = MessageDigest.getInstance("MD5");
			messDigest.update(digest.getBytes("UTF8"));
			cipher = messDigest.digest();
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		} finally {
			if (messDigest != null) {
				messDigest = null;
			}
		}
		return new BASE64Encoder().encode(cipher);
	}
}
