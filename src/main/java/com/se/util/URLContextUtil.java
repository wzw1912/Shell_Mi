package com.se.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * 获取页面元素工具
 * 
 * @author Xzavier
 *
 */
public class URLContextUtil {

	/**
	 * 根据地址返回页面元素
	 * 
	 * @param targetUrl
	 *            请求地址
	 * @param coding
	 *            网页编码
	 * @return
	 * @throws IOException
	 */
	public static String getURLContext(String targetUrl, String coding) throws IOException {
		StringBuffer res = new StringBuffer();
		URL url;
		url = new URL(targetUrl);

		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), coding));
		String str = null;
		while ((str = br.readLine()) != null) {
			res.append(str);
		}
		br.close();
		String result = res.toString();

		return result;
	}


}
