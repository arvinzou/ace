package com.huacainfo.ace.common.tools;

/**
 * Created by chenxiaoke on 2017/11/16.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLException;

public class FaceUtils {
    public static void main(String[] args) throws Exception {

        //FaceUtils.detect("http://zx.huacainfo.com/group1/M00/00/0C/i-AA41nPsfaAXmgJAAGMo6HaSs8263.jpg?filename=%E9%99%88%E6%99%93%E5%85%8B.jpg");

        //FaceUtils.faceSetAdd("c159ecda6f1e7fac4de5d5e85fe42948","58d66bd4cc1d74bea21f71d887ae6774");
       // FaceUtils.faceSetGetDetail("c159ecda6f1e7fac4de5d5e85fe42948");
        FaceUtils.search("c159ecda6f1e7fac4de5d5e85fe42948","http://zx.huacainfo.com/group1/M00/00/0C/i-AA41nPsfaAXmgJAAGMo6HaSs8263.jpg?filename=%E9%99%88%E6%99%93%E5%85%8B.jpg");
    }

    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();
    private final static Logger logger = LoggerFactory.getLogger(FaceUtils.class);

    private final static String api_key="pR2lqv_5hDyvmQSNsZlnFl8DfPDT-MjJ";
    private final static String api_secret="Xb9cAcjc23_p_Igj0we-Al0u4eE6cRer";

    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if (fileMap != null && fileMap.size() > 0) {
            Iterator fileIter = fileMap.entrySet().iterator();
            while (fileIter.hasNext()) {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try {
            if (code == 200) {
                ins = conne.getInputStream();
            } else {
                ins = conne.getErrorStream();
            }
        } catch (SSLException e) {
            logger.error("{}",e);
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while ((len = ins.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }

    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            logger.error("{}",e);
        }
        return null;
    }
    public static void up(){

        File file = new File("你的本地图片路径");
        byte[] buff = getBytesFromFile(file);
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "你的KEY");
        map.put("api_secret", "你的SECRET");
        byteMap.put("image_file", buff);
        try {
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            logger.info(str);
        } catch (Exception e) {
            logger.error("{}",e);
        }
    }
    public static String faceSetCreate(String display_name,String outer_id){
        String str=null;
        String url="https://api-cn.faceplusplus.com/facepp/v3/faceset/create";
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", api_key);
        map.put("api_secret", api_secret);
        map.put("display_name", display_name==null?"统战人士": display_name);
        map.put("outer_id", outer_id==null?"hc201711161209aekj94885uffmj19485750olkj":outer_id);
        map.put("tags", "01");
        map.put("force_merge", "1");
        //{"faceset_token": "c159ecda6f1e7fac4de5d5e85fe42948", "time_used": 98, "face_count": 0, "face_added": 0, "request_id": "1510797914,c332d8bf-b073-420d-a2d6-3a3aa39bb7d0", "outer_id": "hc201711161209aekj94885uffmj19485750olkj", "failure_detail": []}
        try {
            byte[] bacd = post(url, map, null);
            str = new String(bacd);
            logger.info(str);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        return str;
    }
    public static String faceSetAdd(String faceset_token,String face_tokens){
        String str=null;
        String url="https://api-cn.faceplusplus.com/facepp/v3/faceset/addface";
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", api_key);
        map.put("api_secret", api_secret);
        map.put("faceset_token", faceset_token);
        map.put("face_tokens", face_tokens);
        try {
            byte[] bacd = post(url, map, null);
            str = new String(bacd);
            logger.info(str);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        return str;
    }
    public static String faceSetDel(String faceset_token,String face_tokens){
        String str=null;
        String url="https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface";
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", api_key);
        map.put("api_secret", api_secret);
        map.put("faceset_token", faceset_token);
        map.put("face_tokens", face_tokens);
        try {
            byte[] bacd = post(url, map, null);
            str = new String(bacd);
            logger.info(str);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        return str;
    }
    public static String faceSetGetDetail(String faceset_token){
        String str=null;
        String url="https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail";
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", api_key);
        map.put("api_secret", api_secret);
        map.put("faceset_token", faceset_token);
        try {
            byte[] bacd = post(url, map, null);
            str = new String(bacd);
            logger.info(str);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        return str;
    }
    public static String detect(String image_url){
        String str=null;
        String url="https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", api_key);
        map.put("api_secret", api_secret);
        map.put("image_url", image_url);
        try {
            byte[] bacd = post(url, map, null);
            str = new String(bacd);
            logger.info(str);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        return str;
    }
    public static String search(String faceset_token,String image_url){
        String str=null;
        String url="https://api-cn.faceplusplus.com/facepp/v3/search";
        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", api_key);
        map.put("api_secret", api_secret);
        map.put("image_url", image_url);
        map.put("faceset_token", faceset_token);

        try {
            byte[] bacd = post(url, map, null);
            str = new String(bacd);
            logger.info(str);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        return str;
    }
}

