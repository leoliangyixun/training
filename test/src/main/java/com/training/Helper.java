package com.training;


import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Helper {

	public static String encrypt(String value) throws InvalidKeyException,
			InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		if (true == StringUtils.isBlank(value)) {
			throw new IllegalArgumentException(value);
		}

		KeySpec keySpec = new DESKeySpec(Contracts.PASSWORD_KEY);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(
				Contracts.PASSWORD_IV);
		SecretKey secretKey = SecretKeyFactory.getInstance("DES")
				.generateSecret(keySpec);

		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

		byte[] bytes = cipher
				.doFinal(value.getBytes(StandardCharsets.US_ASCII));

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (2 > hex.length()) {
				// b -> 0b
				stringBuilder.append(0);
			}
			stringBuilder.append(hex);
		}
		return stringBuilder.toString();
	}

	public static String decrypt(String value) throws InvalidKeyException,
			InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		if (true == StringUtils.isBlank(value)) {
			throw new IllegalArgumentException(value);
		}

		KeySpec keySpec = new DESKeySpec(Contracts.PASSWORD_KEY);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(
				Contracts.PASSWORD_IV);
		SecretKey secretKey = SecretKeyFactory.getInstance("DES")
				.generateSecret(keySpec);

		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

		byte[] bytes = cipher.doFinal(Helper.hexStringToByte(value));

		return new String(bytes, StandardCharsets.US_ASCII);
	}

	private static byte[] hexStringToByte(String hexString) {
		if (true == StringUtils.isBlank(hexString)
				|| 0 != hexString.length() % 2) {
			throw new IllegalArgumentException(hexString);
		}

		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] chars = hexString.toCharArray();
		byte[] bytes = new byte[length];

		for (int i = 0; i < length; i++) {
			bytes[i] = (byte) ("0123456789ABCDEF".indexOf(chars[i * 2]) << 4 | "0123456789ABCDEF"
					.indexOf(chars[i * 2 + 1]));
		}

		return bytes;
	}



}
