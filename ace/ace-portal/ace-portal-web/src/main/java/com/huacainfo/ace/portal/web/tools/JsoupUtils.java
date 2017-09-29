package com.huacainfo.ace.portal.web.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class JsoupUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsoupUtils.class);
    public static String getHtml01(){
        String rst="";
        String date=getDate();
        final String url="http://cdrb.cdyee.com/html/"+date+"/node_3.htm" ;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements container = doc.getElementsByClass("Ltd1");
            String html=container.html();
            html=html.replace("../../../","http://cdrb.cdyee.com/");
            Document o = Jsoup.parse(html);
            Elements areas = o.getElementsByTag("area");
            for (Element e : areas){
                String href=e.attr("href");
                href="http://cdrb.cdyee.com/html/"+date+"/"+href;
                e.attr("href","http://zx.huacainfo.com/portal/www/cdrb/preview.jsp?url="+href);
            }
            Elements images = o.getElementsByTag("img");
            for (Element i : images){
                i.attr("width","100%");
                i.attr("height","auto");
                i.attr("src","b.png");
            }
            rst=o.getElementsByTag("body").html();
        } catch (IOException e) {
            logger.error("{}",e);
        }
        return rst;
    }
    public static String[] getHtml02(String url){
        String [] rst=new String[2];
        String st0="<!--文章部分开始---------->";
        String st1="<!--文章部分结束---------->";
        try {
            Document next = Jsoup.connect(url).get();
            Elements title=next.getElementsByTag("title");
            String text=next.html();
            //logger.info("{}",text);
            int a=text.indexOf(st0);
            int b=text.indexOf(st1);
            logger.info("{}",a);
            logger.info("{}",b);
            rst[0]=title.text().replace("- 常德日报多媒体数字报刊平台","");
            text=text.substring(a,b);
            text=text.replace("../../../","http://cdrb.cdyee.com/");
            rst[1]=text;
        } catch (IOException e) {
            logger.error("{}",e);
        }
        return rst;
    }

    public  static   List<Map<String,String>>  getUrlList(){
        String date=getDate();
        final String url="http://cdrb.cdyee.com/html/"+date+"/node_3.htm" ;
        List<String> list1=new ArrayList<String>();
        List<String> list2=new ArrayList<String>();
        List<String> list3=new ArrayList<String>();
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element container = doc.getElementById("bmdh");
            String html=container.html();

            html=html.replace("../../../","http://cdrb.cdyee.com/");
            Document o = Jsoup.parse(html);
            Elements pdf = o.getElementsByTag("a");
            for (Element e : pdf){
                String href=e.attr("href");
                if(href.endsWith(".pdf")){
                    list1.add(href);
                    //logger.info("{}",href);
                }
                if(href.endsWith(".htm")){
                    href=href.replace("./","");
                    list2.add("http://cdrb.cdyee.com/html/"+date+"/"+href);
                    list3.add(e.text());
                    //logger.info("{}",href);
                }
            }

        } catch (IOException e) {
            logger.error("{}",e);
        }
        for(int i=0;i<list1.size();i++){
            Map<String,String> o=new HashMap<String,String>();
            o.put("pdf",list1.get(i));
            o.put("htm",list2.get(i));
            o.put("title",list3.get(i));
            list.add(o);
        }
        logger.info("{}",list);
        return list;
    }

    public static String getPageBody(String url,String page){
        String rst="";
        String date=getDate();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements container = doc.getElementsByClass("Ltd1");
            String html=container.html();
            html=html.replace("../../../","http://cdrb.cdyee.com/");
            Document o = Jsoup.parse(html);
            Elements areas = o.getElementsByTag("area");
            for (Element e : areas){
                String href=e.attr("href");
                href="http://cdrb.cdyee.com/html/"+date+"/"+href;
                e.attr("href","http://zx.huacainfo.com/portal/www/cdrb/preview.jsp?url="+href);
            }
            Elements images = o.getElementsByTag("img");
            for (Element i : images){
                i.attr("width","100%");
                i.attr("height","auto");
                i.attr("src",date+"/"+page+".png");
                i.attr("USEMAP","#PagePicMap"+page);
            }
            Elements maps = o.getElementsByTag("map");
            for (Element e : maps){
                e.attr("name","PagePicMap"+page);
            }
            rst=o.getElementsByTag("body").html();
        } catch (IOException e) {
            logger.error("{}",e);
        }
        return rst;
    }
    public static void downPdf(String dowUrl,String path,String page){
        try{
            download(dowUrl,page+".pdf",path+getDate());
        }catch (Exception e){
            logger.error("{}",e);
        }
    }

    public static String getDate(){
        Calendar    rightNow    =    Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        java.util.Date dd  = Calendar.getInstance().getTime();
        Integer day = rightNow.get(rightNow.DAY_OF_MONTH);
        Integer hour=rightNow.get(Calendar.HOUR_OF_DAY);
        if(hour<12){
            day=day-1;
            if(day<=0){
                day=1;
            }
        }
        String date=sdf.format(dd)+"/"+day;
        return date;
    }
    public static void download(String urlString, String filename,String savePath) throws Exception {
        logger.error("{}",savePath);
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(30*1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+ File.separator+filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    public static void initPageFiles() throws Exception{
        List<Map<String,String>>  list=getUrlList();
        String path="/Users/chenxiaoke/Desktop/";
        int i=0;
        for(Map<String,String> e:list){
            downPdf(e.get("pdf"),path,getPage(i));
            Icepdf.pdf2Pic(path+ File.separator+getDate()+File.separator+getPage(i)+".pdf");
            i++;
        }
    }

    public static List<Map<String,String>>  getPageBody(){
        List<Map<String,String>>  list=getUrlList();
        int i=0;
        for(Map<String,String> e:list){
            String text=getPageBody(e.get("htm"),getPage(i));
            e.put("text",text);
            i++;
        }
        logger.info("{}",list);
        return list;
    }

    public static void main(String[] args) throws Exception{
        initPageFiles();
        //getUrlList();

    }
    public static String getPage(int i){
        return "A"+i;
    }
}