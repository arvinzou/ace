package com.huacainfo.ace.common.tools;

/**
 * Created by chenxiaoke on 2018/1/19.
 */

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author :wang_ll
 * @version :1.0.0
 * @title :WeChatApiUtil
 * @description :微信公众接口API
 * @update :2014-10-23 上午11:38:02
 * @since :2014-10-23
 */
public class WeChatApiUtil {

    private static Logger logger = Logger.getLogger(WeChatApiUtil.class.getName());

    //文件上传使用到的对象
    private static HttpClient httpClient = null;

    // token 接口
    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    // 创建菜单
    private static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    // 删除菜单
    private static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    // 获取账号粉丝信息
    private static final String GET_FANS_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s";

    // 生成二维码
    private static final String GET_QCODE = " https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";

    // 生成二维码
    private static final String SHOW_QCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

    // 素材上传
    private static final String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload";

    // 素材下载:不支持视频文件的下载
    private static final String DOWNLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";


    public static String getTokenUrl(String appId, String appSecret) {
        return String.format(ACCESS_TOKEN, appId, appSecret);
    }

    public static String getMenuCreateUrl(String token) {
        return String.format(MENU_CREATE, token);
    }

    public static String getMenuDeleteUrl(String token) {
        return String.format(MENU_DELETE, token);
    }

    public static String getFansInfoUrl(String token, String openid) {
        return String.format(GET_FANS_INFO, token, openid);
    }

    public static String getUploadUrl() {
        return UPLOAD_MEDIA;
    }

    public static String getDownloadUrl(String token, String mediaId) {
        return String.format(DOWNLOAD_MEDIA, token, mediaId);
    }

    public static String getQCodeUrl(String token) {
        return String.format(GET_QCODE, token);
    }

    public static String getShowQCodeUrl(String ticket) {
        return String.format(SHOW_QCODE, ticket);
    }

    /**
     * 通用接口获取Token凭证
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public static Token getToken(String appId, String appSecret) {
        Token token = null;
        String tockenUrl = WeChatApiUtil.getTokenUrl(appId, appSecret);
        String response = httpsRequestToString(tockenUrl, "GET", null);
        JSONObject jsonObject = JSONObject.fromObject(response);
        if (null != jsonObject) {
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                token = null;// 获取token失败
                logger.error(e);
            }
        }
        return token;
    }

    /**
     * 发送请求以https方式发送请求并将请求响应内容以String方式返回
     *
     * @param path   请求路径
     * @param method 请求方法
     * @param body   请求数据体
     * @return 请求响应内容转换成字符串信息
     */
    public static String httpsRequestToString(String path, String method, String body) {
        if (path == null || method == null) {
            return null;
        }

        String response = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection conn = null;
        try {
            TrustManager[] tm = {new JEEWeiXinX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(path);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            response = buffer.toString();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException execption) {
                logger.error(execption);
            }
        }
        return response;
    }

    /**
     * 发送请求已https方式发送请求并将请求响应内容以String方式返回
     *
     * @param fileName 保存文件名称
     * @param path     请求路径
     * @param method   请求方式
     * @param body     请求数据体
     * @return 请求响应内容输出到文件
     */
    public static File httpsRequestToFile(String fileName, String path, String method, String body) {
        if (fileName == null || path == null || method == null) {
            return null;
        }

        InputStream inputStream = null;
        HttpsURLConnection conn = null;
        File file = null;
        try {
            TrustManager[] tm = {new JEEWeiXinX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(path);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            if (inputStream != null) {
                file = new File(fileName);
            } else {
                return file;
            }

            //写入到文件
            FileOutputStream fileOut = new FileOutputStream(file);
            if (fileOut != null) {
                int c = inputStream.read();
                while (c != -1) {
                    fileOut.write(c);
                    c = inputStream.read();
                }
            }

        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                inputStream.close();
            } catch (IOException execption) {
                logger.error(execption);
            }
        }

        return file;
    }


    /**
     * 以http方式发送请求，并将请求响应内容以String格式返回
     *
     * @param path   请求路径
     * @param method 请求方法
     * @param body   请求数据
     * @return 返回响应的字符串
     */
    public static String httpRequestToString(String path, String method, String body) {
        String response = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(
                    inputStream, "UTF-8");
            bufferedReader = new BufferedReader(
                    inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            response = buffer.toString();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException execption) {
                logger.error(execption);
            }
        }
        return response;
    }

    /**
     * 以http方式发送请求,并将请求响应内容输出到文件
     *
     * @param path   请求路径
     * @param method 请求方法
     * @param body   请求数据
     * @return 返回响应的存储到文件
     */
    public static File httpRequestToFile(String fileName, String path, String method, String body) {
        if (fileName == null || path == null || method == null) {
            return null;
        }

        File file = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            if (inputStream != null) {
                file = new File(fileName);
            } else {
                return file;
            }

            //写入到文件
            FileOutputStream fileOut = new FileOutputStream(file);
            if (fileOut != null) {
                int c = inputStream.read();
                while (c != -1) {
                    fileOut.write(c);
                    c = inputStream.read();
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return file;
    }

    /**
     * 微信服务器素材上传
     *
     * @param file  表单名称media
     * @param token access_token
     * @param type  type只支持四种类型素材(video/image/voice/thumb)
     */
    public static JSONObject uploadMedia(File file, String token, String type) {
        if (file == null || token == null || type == null) {
            return null;
        }

        if (!file.exists()) {
            logger.info("上传文件不存在,请检查!");
            return null;
        }

        String url = getUploadUrl();
        JSONObject jsonObject = null;
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Connection", "Keep-Alive");
        post.setRequestHeader("Cache-Control", "no-cache");
        FilePart media = null;

        try {
            media = new FilePart("media", file);
            Part[] parts = new Part[]{new StringPart("access_token", token),
                    new StringPart("type", type), media};
            MultipartRequestEntity entity = new MultipartRequestEntity(parts,
                    post.getParams());
            post.setRequestEntity(entity);
            int status = getHttpClient().executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String text = post.getResponseBodyAsString();
                jsonObject = JSONObject.fromObject(text);
            } else {
                logger.info("upload Media failure status is:" + status);
            }
        } catch (FileNotFoundException execption) {
            logger.error(execption);
        } catch (HttpException execption) {
            logger.error(execption);
        } catch (IOException execption) {
            logger.error(execption);
        }
        return jsonObject;
    }

    /**
     * 多媒体下载接口
     *
     * @param fileName 素材存储文件路径
     * @param token    认证token
     * @param mediaId  素材ID（对应上传后获取到的ID）
     * @return 素材文件
     * @comment 不支持视频文件的下载
     */
    public static File downloadMedia(String fileName, String token,
                                     String mediaId) {
        String url = getDownloadUrl(token, mediaId);
        return httpRequestToFile(fileName, url, "GET", null);
    }

    /**
     * 获取httpClient对象
     *
     * @return
     */
    public synchronized static HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new HttpClient();
        }
        return httpClient;
    }

}