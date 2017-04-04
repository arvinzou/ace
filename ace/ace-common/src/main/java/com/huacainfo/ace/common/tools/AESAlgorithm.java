package com.huacainfo.ace.common.tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESAlgorithm {
	private static final BouncyCastleProvider provider = new BouncyCastleProvider();
	private static final String instance = "AES/ECB/ZeroBytePadding";

	/**
	 * 密码加密
	 * 
	 * @param content
	 *            密码
	 * @param password
	 *            秘钥
	 * @param instance
	 *            加密算法
	 * @return 加密后的密文
	 */
	public static String encryptAndBase64(String content, String password)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException {
		byte[] encryptResult = encrypt(content, password, instance);
		return new String(new Base64().encode(encryptResult));
	}

	/**
	 * 密码解密
	 * 
	 * @param content
	 *            密文
	 * @param password
	 *            秘钥
	 * @param instance
	 *            算法
	 * @return 解密后的明文
	 */
	public static String decryptFromBase64(String content, String password)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		byte[] decryptFrom = Base64.decodeBase64(content);
		byte[] decryptResult = decrypt(decryptFrom, password, instance);
		return new String(decryptResult);
	}

	public static byte[] encrypt(String content, String password,
			String instance) throws NoSuchAlgorithmException,
			NoSuchPaddingException, UnsupportedEncodingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(instance, provider);// 创建密码器
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密
	}

	public static byte[] decrypt(byte[] content, String password,
			String instance) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(instance, provider);// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(content);
		return result;
	}

	public static byte[] encrypt(String content, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(instance, provider);// 创建密码器
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密
	}

	public static byte[] decrypt(byte[] content, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance(instance, provider);// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(content);
		return result;
	}

	public static void main(String[] args) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException,
			BadPaddingException {
		String encryptResult = encryptAndBase64(
				"601366" + System.currentTimeMillis(), "1r39df456j8wet45");
		System.out.println("加密前：" + encryptResult);
		String decryptResult = decryptFromBase64(encryptResult,
				"1r39df456j8wet45");
		System.out.println("解密后：" + decryptResult);
	}
}
