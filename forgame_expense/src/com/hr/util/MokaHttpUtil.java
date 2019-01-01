/**  
 * All rights Reserved, Designed By forgame
 * @Title:  HttpUtil.java   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: fengguosen@forgame.com
 * @date:   2018年5月23日 下午5:53:52   
 * @version V1.0 
 * 注意：本内容仅限于云游控股有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.hr.util;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eas.util.ConfigUtil;

/**
 * @author Employee
 *
 */
public class MokaHttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MokaHttpUtil.class);
	/**
	   * 构造Basic Auth认证头信息
	   * 
	   * @return
	   */
	  private static String getHeader() {
	    String auth = ConfigUtil.getProperty("moka.api.key") + ":" + "";
	    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
	    String authHeader = "Basic " + new String(encodedAuth);
	    return authHeader;
	  }
	  

	  public static CloseableHttpClient createSSLClientDefault() {
	  		try {
	  			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	  				// 信任所有
	  				@Override
	  				public boolean isTrusted(X509Certificate[] chain, String authType) {
	  					return true;
	  				}

	  			}).build();
	  			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
	  			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	  		} catch (KeyManagementException e) {
	  			logger.error("SSLUtilsErrorKetManage");
	  		} catch (NoSuchAlgorithmException e) {
	  			logger.error("SSLUtilsErrorNOAlgorithm");
	  		} catch (KeyStoreException e) {
	  			logger.error("SSLUtilsErrorKeyStore");
	  		}
	  		return HttpClients.createDefault();
	  	}


	public static String doGet(String url,Map<String, Object>  paramsMap) throws Exception {
		URIBuilder uriBuilder = new URIBuilder(url);
		
		if(paramsMap!=null){
			List<NameValuePair> list = new LinkedList<NameValuePair>();
			Iterator<String> iter = paramsMap.keySet().iterator();
			  while(iter.hasNext()){
			   String key=iter.next();
			   String value =  paramsMap.get(key).toString();
			   BasicNameValuePair param = new BasicNameValuePair(key, value);
			   list.add(param);
			  }
			  uriBuilder.setParameters(list);
		}
		CloseableHttpClient httpCilent = createSSLClientDefault();
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Authorization", getHeader());
        httpGet.addHeader("Content-Type", "application/json"); 

        String srtResult = "";
        try {
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() != 200){
                logger.error("请求moka 接口 失败");
            }
            srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return srtResult;
	}
	
	public static String doPost(String url, Map<String, Object> paramsMap){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Authorization", getHeader());
        httpPost.setHeader("Content-Type","application/json");  //
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (String key : paramsMap.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }
        String strResult = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
            	logger.error("请求moka 接口 失败");
            } 
            strResult = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strResult;
    }
	
	public static String doPostForJson(String url, String jsonParams){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Authorization", getHeader());
        httpPost.setHeader("Content-Type","application/json");  //
        String strResult = "";
        try {
            httpPost.setEntity(new StringEntity(jsonParams,ContentType.create("application/json", "utf-8")));
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
            	 logger.error("请求moka 接口 失败");
            } 
            strResult = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strResult;
	}
	
	public static String doPutForJson(String url, String jsonParams){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        httpPut.addHeader("Authorization", getHeader());
        httpPut.setHeader("Content-Type","application/json");  //
        String strResult = "";
        try {
        	httpPut.setEntity(new StringEntity(jsonParams,ContentType.create("application/json", "utf-8")));
            HttpResponse response = httpClient.execute(httpPut);
            if (response.getStatusLine().getStatusCode() != 200) {
            	 logger.error("请求moka 接口 失败");
           } 
           strResult = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strResult;
	}
	
	public static void main(String[] args) {

	}
}
