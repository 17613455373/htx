package com.example.htx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    public static Log logger = LogFactory.getLog(HttpUtils.class);
    /**
     * 发送get请求
     * @param requestUrl
     * @return
     */
    public static JSONObject sendGet(String requestUrl,Map<String, String> params,Map<String, String> headers){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
        JSONObject result = null;
        RequestConfig config = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
        //执行Get请求，
        try {
            String sb = "";
            if (params!=null){
                for (String key:params.keySet()) {
                    sb += key+"="+params.get(key)+"&";
                }
                sb = sb.substring(0,sb.length()-1);
                sb = URLDecoder.decode(sb, "UTF-8");
                requestUrl+="?"+sb;
            }
            //创建Get请
            HttpGet httpGet = new HttpGet(requestUrl);
            if (headers !=null){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.addHeader(entry.getKey(),entry.getValue());
                }
            }
            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                //得到响应体
                HttpEntity entity = response.getEntity();
                //获取响应内容
                responseContent  = EntityUtils.toString(entity,"UTF-8");
                //转换为map
                result = JSON.parseObject(responseContent);
            }else {
                logger.error("接口访问异常："+requestUrl);
            }

        } catch (IOException e) {
            logger.error("HttpUtil=====Start");
            logger.error(e.getMessage(),e);
            logger.error("HttpUtil=====End");
        }
       return result;
    }
    /**
     * 发送post请求
     * @param requestUrl
     * @return
     */
    public static JSONObject sendPost(String requestUrl, Map<String, String> params,Map<String, String> headers) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
        JSONObject result = null;
        RequestConfig config = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
        HttpPost httpPost = new HttpPost(requestUrl);
        try {
            List <NameValuePair> nvps = new ArrayList<NameValuePair>();
            if(null!=params) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            //http header
            if (headers !=null){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(),entry.getValue());
                }
            }
            httpPost.setConfig(config);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                /* 读返回数据 */
                responseContent = EntityUtils.toString(response.getEntity(),"UTF-8");
                // System.out.println("conResult:"+conResult);
                //转换为map
                result = JSON.parseObject(responseContent);
            }else {
                logger.error("接口访问异常："+requestUrl);
            }
        } catch (Exception e) {
            logger.error("HttpUtil=====Start");
            logger.error(e.getMessage(),e);
            logger.error("HttpUtil=====End");
        }
        return result;
    }
}
