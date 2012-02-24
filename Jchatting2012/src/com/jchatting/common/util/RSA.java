/**
 * 
 */
package com.jchatting.common.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-6 下午06:14:02
 */
public class RSA {

	public KeyPair keyPair = null;
	/**
	 * 
	 */
	public RSA() {
		// TODO Auto-generated constructor stub
	}
	public RSAPublicKey getPublicKey() throws NoSuchAlgorithmException {
		if (keyPair == null) {
			generateKey();
		}
		return (RSAPublicKey)keyPair.getPublic();
	}
	public RSAPrivateKey getPrivateKey() throws NoSuchAlgorithmException {
		if (keyPair == null) {
			generateKey();
		}
		return (RSAPrivateKey)keyPair.getPrivate();
	}
	/**
	 * 生成密匙对
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-6 下午06:31:18
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private KeyPair generateKey() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048, new SecureRandom());
		this.keyPair = keyPairGen.generateKeyPair();
		return this.keyPair;
	}
	/**
	 * 解密
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-6 下午06:37:02
	 * @param privateKey
	 * @param string
	 * @return
	 */
	public String decrypt(RSAPrivateKey privateKey, String string) {
		if (string != null) {
			byte[] data = decrypt(privateKey, string2Bytes(string));
			if (data != null) {
				return new String(data);
			}
			return null;
		}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-6 下午06:35:39
	 * @param publicKey
	 * @param string
	 * @return
	 */
	public String encrypt(RSAPublicKey publicKey, String string) {
		if (string != null) {
			byte[] data = encrypt(publicKey, string.getBytes());
			if (data != null) {
				return byte2String(data);
			}
			return null;
		}
		return null;
	}

	/**
	 * 使用RSAPublicKey加密
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-6 下午06:31:48
	 * @param publicKey
	 * @param data
	 * @return
	 */
	private byte[] encrypt(RSAPublicKey publicKey, byte[] data) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				// 返回实现指定转换的 Cipher 对象。
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				// 用密钥初始化此 Cipher,第一个参数表示加密
				return cipher.doFinal(data);
				// 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此 Cipher 的初始化方式）。
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 使用RSAPrivateKey解密
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-6 下午06:32:18
	 * @param privateKey
	 * @param raw
	 * @return
	 */
	private byte[] decrypt(RSAPrivateKey privateKey, byte[] raw) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey); // 第一个参数表示解密
				return cipher.doFinal(raw);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 将bytes转化成string，便于网络传送
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-6 下午07:46:58
	 * @param bytes
	 * @return
	 */
	private String byte2String(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		// 构造一个不带任何字符的字符串生成器，其初始容量由 capacity 参数指定。
		for (int i = 0; i < bytes.length; i++) {
			sb.append((char)(bytes[i] & 0xff));
		}
		return sb.toString();
	}
	/**
	 * 将string转化成bytes，从网络中获得数据
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-6 下午07:47:22
	 * @param string
	 * @return
	 */
	private byte[] string2Bytes(String string) {
		int length = string.length();
		byte[] bytes = new byte[length];
		for (int i = 0; i < bytes.length; i++) {
			byte b = new Byte((byte)(string.charAt(i) & 0xff));
			bytes[i] = b;
		}
		return bytes;
	}
	/**
	 * @return the keyPair
	 */
	public KeyPair getKeyPair() {
		return keyPair;
	}
	/**
	 * @param keyPair the keyPair to set
	 */
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}
}
