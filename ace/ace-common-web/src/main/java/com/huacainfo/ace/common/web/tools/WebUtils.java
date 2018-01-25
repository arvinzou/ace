package com.huacainfo.ace.common.web.tools;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.apache.commons.io.FileUtils;
import com.huacainfo.ace.common.tools.CommonKeys;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.File;
public class WebUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

	public static String getIpAddr(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip; 
	}

	@SuppressWarnings("static-access")
	public static int getDayOfWeek() {
		TimeZone zone = TimeZone.getTimeZone("Asia/Beijing");
		Calendar cal = Calendar.getInstance(zone);

		int c = cal.get(cal.WEEK_OF_YEAR);
		System.out.println(c);
		return c;
	}



	public static void flushRoleResourceCache(
			RedisOperations<String, String> redisTemplateString,
			List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			String resource = map.get("RESOURCES");
			String keyString = getRoleResourceRedisKey(resource);
			LOGGER.info("{}={}",keyString,"," + map.get("ROLES") + ",");
			redisTemplateString.opsForValue().set(keyString,
					"," + map.get("ROLES") + ",");
		}
		LOGGER.info("加载资源与角色列表到redis缓存成功");
	}

	public static String getRoleResourceRedisKey(String resource) {
		String keyString = CommonKeys.REDIS_RESOURCE_ROLE_PRIFEX + ":"
				+ resource;
		return keyString;
	}

    public static void downloadByApacheCommonIO(String url, String saveDir, String fileName) {
        try {
            FileUtils.copyURLToFile(new java.net.URL(url), new java.io.File(saveDir, fileName));
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }
    }

    public static void changeToMp3(String sourcePath, String targetPath) {
        LOGGER.info("============start changeToMp3==================");
        LOGGER.info("{}", sourcePath);
        File source = new File(sourcePath);
        File target = new File(targetPath);
        if (!source.exists()) {
            LOGGER.error("===============source {} not exists=================", source.getPath());
            return;
        }
        AudioAttributes audio = new AudioAttributes();
        Encoder encoder = new Encoder();
        audio.setCodec("libmp3lame");
        //audio.setBitRate(new Integer(128000));
        //audio.setChannels(new Integer(2));
        //audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException e) {
            LOGGER.error("{}", e);
            e.printStackTrace();
        } catch (InputFormatException e) {
            LOGGER.error("{}", e);
            e.printStackTrace();
        } catch (EncoderException e) {
            LOGGER.error("{}", e);
            e.printStackTrace();
        }
        LOGGER.info("{}", targetPath);
        LOGGER.info("============complete changeToMp3==================");
    }

    public static void main(String args[]) {

        String sourcePath = "/tmp/2c3ad216-c777-462a-a36b-3923e0f29a69.amr";
        String targetPath = "/tmp/2c3ad216-c777-462a-a36b-3923e0f29a69.mp3";
        System.out.println(sourcePath);
        System.out.println(targetPath);
        System.out.println("start changeToMp3");
        WebUtils.changeToMp3(sourcePath, targetPath);
        System.out.println("end changeToMp3");
    }
}
