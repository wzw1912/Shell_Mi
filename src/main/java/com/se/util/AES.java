package com.se.util;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class AES {
//	public static final String CipherMode = "AES/ECB/PKCS7Padding";
	public static final String CipherMode = "AES/ECB/PKCS5Padding";
    private AES(){
        throw new RuntimeException("NOT_ALLOWED_INSTANCE");
    }
    /**
     * 生成一个AES密钥对象
     * @return
     */
    public static SecretKeySpec generateKey(){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
	        kgen.init(128, new SecureRandom());
	        SecretKey secretKey = kgen.generateKey();  
	        byte[] enCodeFormat = secretKey.getEncoded();  
	        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			return key;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
    }
    public static String generateKey2String() {
        byte[] buf = generateKey()==null? null : generateKey().getEncoded();
        if(buf == null){
            return null;
        }
        //使用Base64，加密生成的密钥
        String secret = Base64.getEncoder().encodeToString(buf);
        return secret;
    }
    /**
     * 加密字节数据
     * @param content
     * @param key
     * @return
     */
    public static byte[] encrypt(byte[] content,byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 通过byte[]类型的密钥解密byte[]
     * @param content
     * @param key
     * @return
     */
    public static byte[] decrypt(byte[] content,byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 通过String类型的密钥加密String
     * @param content
     * @param key
     * @return 16进制密文字符串
     */
    public static String encrypt(String content,String key) {
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将AES密钥，Base64转码回字节数组
//        byte[] se = Base64.decode(key, Base64.DEFAULT);
        byte[] se = Base64.getDecoder().decode(key);
//        data = encrypt(data,new SecretKeySpec(se, "AES").getEncoded());
        data = encrypt(data,se);
        //返回内容，Base64转码返回
//        String result = Base64.encodeToString(data, Base64.DEFAULT);
        String result = Base64.getEncoder().encodeToString(data);
        return result;
    }
    /**
     * 通过String类型的密钥 解密String类型的密文
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        byte[] data = null;
        try {
            //将密文，Base64转码回字节数组
//            data = Base64.decode(content, Base64.DEFAULT);
            data = Base64.getDecoder().decode(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将密钥，转码回字节数组
//        byte[] se = Base64.decode(key, Base64.DEFAULT);
        byte[] se = Base64.getDecoder().decode(key);
        data = decrypt(data, se);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
	private static final String AES_KEY = "D5ue9bB6qnrZflU1WPK/Iw==";
//	private static final String AES_KEY = "7xM8qyxfBYTDxj7avL6ChQ==";
    public static void main(String[] args) {
//		String s = "E3JHxOHNPeaYH2L5HyUYyTKvRI5qw3L7D6rg4GirqpdxxTF3kVHXIDh4lkVpuxs1ZRK08Y6Q9hSMMJr9mSeaOaxRnmI396Q31AaLqbYdDbBeMdY8AxM+RHPSlK7gPdFxYKpqkE0UiZymvE2OTG+iWLWDhAB+XvxyjuhvL27fpd5q4qKSA+nzbbLzK/lBH5qxbleXHIlrmifkvqMKS8gzDxT/t7nKJrpdNnlxqeNGPqoNg8Go12mjRgYxBF3j0GXvc4cqi37WtzT79r7AdtwPBN86AMZL5iOTF1mh4Tnd8dMkQEZkm4PwkkjISVNEh/Anu2A763L5LYj5J+rEyp1bfQ==";
		String s = "a+OM7RhwKHreDnC2u0oIt3lIlAHNPQZ2y3XPb5BLkUG3CSMt8HSz10oudAOWtHtT";
//		s = encrypt(s, AES_KEY);
//		System.out.println("encrypt: " + s);
		System.out.println("decrypt: " + decrypt(s, AES_KEY));
		
	}
}
