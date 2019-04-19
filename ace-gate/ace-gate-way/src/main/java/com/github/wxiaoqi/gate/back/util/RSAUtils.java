package com.github.wxiaoqi.gate.back.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * <p>
	 * 用私钥对信息生成数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey){
		byte[] keyBytes;
		String signData = "";
		try {
			keyBytes = Base64Utils.decode(privateKey.getBytes());
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateK);
			signature.update(data);
			signData = Base64Utils.encode(signature.sign()).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return signData;
	}

	/**
	 * <p>
	 * 校验数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey.getBytes());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64Utils.decode(sign.getBytes()));
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData,
			String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey.getBytes());
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher
						.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher
						.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData,
			String publicKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey.getBytes());
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher
						.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher
						.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey.getBytes());
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey.getBytes());
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		byte[] bytes = key.getEncoded().toString().getBytes();
		return Base64Utils.encode(bytes).toString();
	}

	public static void main(String[] args) throws Exception {
		String data = "appid=wx2421b1c4370ec43b&business=57&channel_id=24006513&contact=厦门华夏&contact_email=123456@qq.com&mch_id=1900009211&merchant_name=厦门华夏银行测试商户&merchant_remark=111900009211&merchant_shortname=厦门华夏&service_phone=18650788888";
		String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDA7HglcvDgABHnUWxjkzme9AuyKBc2N30XLthZfYsX8dfl816mBpF3O9RgFfezC8Rlbkl6mwtfYOuqMVL5p9C8vcwPJOsQhjM3Am2xKiqA5O6JYqt4H1z9hkiXmazegzqo1F964skuLF0vlrWZXmo496VMHOTDncEx24+/q85g199ofJCH1X60c4LbUKvyjhR6I9rNF1dHLBW9nzyJj/uc2VQeYZiVR4yTBRjS9EaenVG+jLx/TXyRmxKeMRCxgL5sGzVWef4Y/tfrMgvkInQKe1xfwpI/CqoKrEWPMb4VQt33y12wpbaQgf9q1hpgQWOTvd0HvnsQGzrJJ5KZ/4otAgMBAAECggEAWZMx8cldd9PwfCO9HLq17UzIxW6B4IWBCiuQ/nQhCfwbT0RhdNrl3aOk5vwsJzDWfnXnngqxDBb3NO1z5kD51TiWr41nqyN0Uh1JixHV7ETfUGDE1qBRT9FykRkkP6hUqyD4OBlmaY7lsXvbU5uX3F13nVBpEz6C/kBAjTEbQLEMPRUAqx2ApdFz8kGsoO/9vTCfERxhPAXhvKUEZH2j0N3goyaYhCWHLM+Y8G3dz4EHwAoG60H5xDN2xuBBDUZ/nJI0vMPPlBGIQFY5jcS8YClWpV16gxxRkKwu9pS633bDA05JVaAdFV+D4fx0A5/pSOoJN7IbYoPbWU5X0JTHwQKBgQDiTbe1+oGS/eps3uJQSUsyIfrqIOXmKgVh2M1apZA0D6TqywvBL82Y2Qqcd63Hcj4jVXJiLf0tvMqAcv4KmjketD+o8o8vGooLkqTRGl5XQlRD84LktuNo6Jwer5BUbG1wYLqzMi4fa0l3b13krfLK4fWhKEnElYP5ZCAa6e71nQKBgQDaPWlqJQWMB2rgbmFcY1GNAEhnFpbD9ByIi6TSaAhs7jwYwPSBZo2I7bzAybKvsCnIG4lljhcdsHfwnnL4yRWgUJ91wEd7zG5zvbKPJUfRMRDJIe94FlwzSQjLI8/w/WVnlScfllbs0c8dIfTS0Ur2eddcASW441+A4up3gNiJ0QKBgFAIHiUsT3C1fZc9B5pPIVm8bKkqM0O/rqGY857QGHxg3/jtD94lUrdwYnFNXdbADzudt1MDYpsvPgpJIJCNVBAIvM654WtOHm3TUZhlk+GWIojZcHwENc5fHP85JXjF07o/ayd+YpNX9OZZK0J5Rfj8CksRHW15Vu/2uefqvUh9AoGAWQt9m2WHod7U6MWgzAVqHNHkuMsqsMFFcyEnGwJ/jZKWyrLQEIw7a4c04KRrV+vU4GT75ofXPrHl/jNoTcIeJM9AgNb3U7fRyT+5P1bCusP+SVKjVqgo7nP6NohiK734Rg9Ba034IaBplUVpkyr6Hx8Pk+aT8aA0M25ipGfZ8kECgYBoiT8JbQnJ7iNvPln8robXkh6T3tqKAEKpkkw571LHlyvR9TB3f9e9HFGCnGSSZ0y3PBbvg78dk3JKtqykIIl9zduY5mXVRq/TSdPQqCPS1adjYgBtcRbI6E/nAEwK9uT4KH1x8V4y/o1VNvxYmI9CAc2QeOPFCYtFk6zdkOOpjw==";
		String sign = RSAUtils.sign(data.getBytes(), privateKey);
		sign = sign.replaceAll("\n", "");
	    System.out.println(sign);
	   String publicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwyFjugBEq4Pt+B4G1PZkqYHn9LJemr69XWw/yI42WDOt0qEItwmR2VMqMswSqHUc6MCa8F5nfoQpnXCH2r3b38IusfqbSTafXRLaXxAJyZPuKrq4kBY9XTYr+rj/AEYJ9O1DIYHWAew/GgNY++2JNUTse/ixXqO62Q3XXtlNaKT8YyKjj6oj56O/CTF6RZ+y4hj17u+W8GNq2CBhBCJp8qYImLltb6PVDhuOzTFk57CHcuvBarj0sORSqeX6C/NuRjV96CUmbovHdlA5DCwWrH9Oic9CSs+QFW5WiPrNuP4gn6PK/ulmgrPHugQ5GooyZEf270QkHkKW191fFqEy1QIDAQAB";
	   System.out.println(RSAUtils.verify(data.getBytes(), publicKey, sign));
	}
}