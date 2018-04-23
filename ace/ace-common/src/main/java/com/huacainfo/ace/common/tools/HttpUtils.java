package com.huacainfo.ace.common.tools;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtils {

	public static String httpPost(String url, Map<String, Object> params)
			throws Exception {
		return httpPost(url, params, 30 * 1000, 30 * 1000, "UTF-8");
	}

	public static String httpPost(String url, Map<String, String> params,
			List<File> fileLists) throws Exception {
		return httpPost(url, params, fileLists, 120 * 1000, 120 * 1000, "UTF-8");
	}

	public static String httpPost(String url, Map<String, Object> params,
			int timeoutMillseconds, int requestTimeoutMillseconds,
			String encoding) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		RequestConfig.Builder builder = getRequestConfigBuider(
				timeoutMillseconds, requestTimeoutMillseconds);
		httpPost.setConfig(builder.build());

		List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				if (params.get(key) != null) {
					httpParams.add(new BasicNameValuePair(key, params.get(key)
							.toString()));
				}
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(httpParams, encoding));
		return sendHttpPost(httpPost, encoding);
	}


	public static String httpPost(String httpUrl, Map<String, String> params,
			List<File> fileLists, int timeoutMillseconds,
			int requestTimeoutMillseconds, String encoding)
			throws ParseException, IOException {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		RequestConfig.Builder builder = getRequestConfigBuider(
				timeoutMillseconds, requestTimeoutMillseconds);
		httpPost.setConfig(builder.build());
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : params.keySet()) {
			meBuilder.addPart(key, new StringBody(params.get(key),
					ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost, encoding);
	}

	public static String httpGet(String url, int timeoutMillseconds,
			int requestTimeoutMillseconds, String encoding)
			throws URISyntaxException, ClientProtocolException, IOException {
		RequestConfig.Builder builder = getRequestConfigBuider(
				timeoutMillseconds, requestTimeoutMillseconds);
		HttpGet httpGet = new HttpGet();
		httpGet.setConfig(builder.build());
		httpGet.setURI(new URI(url));

		return sendHttpGet(httpGet, encoding);
	}

	public static String httpsGet(String url, int timeoutMillseconds,
			int requestTimeoutMillseconds, String encoding,Map<String,String> header)
			throws URISyntaxException, MalformedURLException, IOException {
		RequestConfig.Builder builder = getRequestConfigBuider(
				timeoutMillseconds, requestTimeoutMillseconds);
		HttpGet httpGet = new HttpGet();
		httpGet.setConfig(builder.build());
		httpGet.setURI(new URI(url));
		if(header!=null){
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpGet.addHeader(entry.getKey(),entry.getValue());
			}
		}


		return sendHttpsGet(httpGet, encoding);
	}

	public static String httpsGet(String url) throws URISyntaxException,
			MalformedURLException, IOException {
		return httpsGet(url, 30 * 1000, 30 * 1000, "UTF-8",null);
	}

	public static String httpsGet(String url,Map<String,String> header) throws URISyntaxException,
			MalformedURLException, IOException {
		return httpsGet(url, 30 * 1000, 30 * 1000, "UTF-8",header);
	}

	public static String httpGet(String url) throws URISyntaxException,
			MalformedURLException, IOException {
		return httpGet(url, 30 * 1000, 30 * 1000, "UTF-8");
	}

	protected static String sendHttpGet(HttpGet httpGet, String encoding)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, encoding);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	protected static RequestConfig.Builder getRequestConfigBuider(
			int timeoutMillseconds, int requestTimeoutMillseconds) {
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		configBuilder.setConnectTimeout(timeoutMillseconds);
		configBuilder.setConnectionRequestTimeout(requestTimeoutMillseconds);
		return configBuilder;
	}

	protected static String sendHttpPost(HttpPost httpPost, String encoding)
			throws ParseException, IOException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			httpClient = HttpClients.createDefault();

			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, encoding);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送Get请求Https
	 * 
	 * @param httpGet
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	protected static String sendHttpsGet(HttpGet httpGet, String encoding)
			throws MalformedURLException, IOException {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
					.load(new URL(httpGet.getURI().toString()));
			DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(
					publicSuffixMatcher);
			httpClient = HttpClients.custom()
					.setSSLHostnameVerifier(hostnameVerifier).build();
            //httpGet.addHeader("WX-SESSION-ID","oCjYM0Ux6pr9MoumsfZITuMW5lE8");
            response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, encoding);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

    public static String sslPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            if (map != null) {
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, String> elem = (Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
                }
            }

            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

	public static void main(String args[]) throws URISyntaxException,
			MalformedURLException, IOException {
       // System.out.println(HttpUtils.sslPost("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx29ecb720b03ea466&secret=03ea9a47442c14208943043e62114fc6&code=0012s83S0BIVL92VMV1S0lg63S02s83a&grant_type=authorization_code", null, "utf-8"));
    }
}
