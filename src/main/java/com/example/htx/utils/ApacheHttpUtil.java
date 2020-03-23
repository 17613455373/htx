package com.example.htx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/** 
* @ClassName: ApacheHttpUtil 
* @Description: apachehttpclient 工具类
* @author muchunfa
* @date 2016年6月14日 下午4:47:09  
*/
public class ApacheHttpUtil {
	private static Logger log = Logger.getLogger(ApacheHttpUtil.class);
	/**
	 * httpget请求
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendGet(String url) throws ClientProtocolException, IOException {
		log.error("get params:"+url);
		String result = null;
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = builder.build();
		HttpGet httpget = new HttpGet(url);  
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity);
		    log.error(url+":result:"+result);
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		return result;
	}
	
	
	/**
	 * POST 请求 字符流数据 content-type:application/x-www-form-urlencoded
	 * @param url
	 * @param params
	 * @throws Exception 
	 */
	public static String sendPost(String url,Map<String, String> params) throws Exception {
		String logurl =url+"?"+params.toString(); 
		String result = null;
		log.error("post params:"+logurl);
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = builder.build();
		HttpPost httpPost = new HttpPost(url);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		if(null!=params) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps,StandardCharsets.UTF_8));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity,StandardCharsets.UTF_8);
		    log.error(logurl+":result::"+result);
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		return result;
	}
	
	/**
	 * post 请求 获取JSONObject类型数据
	 * @param url
	 * @param params
	 * @return
	 */
	public static JSONObject getJsonDataByPost(String url,Map<String, String> params) {
		JSONObject json = null;
		try {
			String result = sendPost(url, params);
			log.error(url+" result:"+result);
			if(StringUtils.isNotBlank(result)) {
				json = JSON.parseObject(result);
			}
		} catch (Exception e) {
			log.error("sendPost getJsonDataByPost",e);
		}
		return json;
	}

	/**
	 * GET 请求 获取JSONObject类型数据
	 * @param url
	 * @return
	 */
	public static JSONObject getJsonDataByGet(String url) {
		JSONObject json = null;
		try {
			String result = sendGet(url);
			if(StringUtils.isNotBlank(result)) {
				System.out.println(result);
				json = JSON.parseObject(result);
			}
		} catch (Exception e) {
			log.error("sendPost getJsonDataByPost",e);
		}
		return json;
	}

	/**
	 * GET 请求 获取JSONObject类型数据
	 * @param url
	 * @return
	 */
	public static JSONArray getJsonArrayDataByGet(String url) {
		JSONArray json = null;
		try {
			String result = sendGet(url);
			if(StringUtils.isNotBlank(result)) {
				json = JSON.parseArray(result);
			}
		} catch (Exception e) {
			log.error("sendPost getJsonDataByPost",e);
		}
		return json;
	}


	/**
	 *
	 * @param url
	 * @return
	 */
	public static String getStringDataByGet(String url) {
		String result = null;
		try {
			 result = sendGet(url);

		} catch (Exception e) {
			log.error("sendPost getJsonDataByPost",e);
		}
		return result;
	}
	/**
	 * post方式发送content-type:multipart/form-data 二进制流数据,发送交大数据时使用
	 * @throws ClientProtocolException 
	 * @throws IOException
	 */
	public static String multipartPost(String url,Map<String, String> params) throws ClientProtocolException, IOException  {
		String result = "";
		String logurl =url+"?"+params.toString(); 
        HttpPost httpPost = new HttpPost(url);
        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient httpClient = httpClientBuilder.build();
            RequestConfig config = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
            httpPost.setConfig(config);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
            if(null!=params) {
    			for (Map.Entry<String, String> entry : params.entrySet()) {
    				multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue());
    			}
    		}
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
		    log.error(logurl+":result::"+result);
		    EntityUtils.consume(entity);
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }

	/**
	 * POST 请求 字符流数据 content-type:application/x-www-form-urlencoded
	 * @param url
	 * @param jsonParam
	 * @throws Exception
	 */
	public static String sendJsonDataPost(String url,JSONObject jsonParam) throws Exception {
		String logurl =url+"?"+jsonParam.toString();
		String result = null;
		log.error("post params:"+logurl);
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = builder.build();
		HttpPost httpPost = new HttpPost(url);
		StringEntity stringEntity = new StringEntity(jsonParam.toJSONString(),StandardCharsets.UTF_8);
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity,StandardCharsets.UTF_8);
			log.error(logurl+":result::"+result);
			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		return result;
	}
}
