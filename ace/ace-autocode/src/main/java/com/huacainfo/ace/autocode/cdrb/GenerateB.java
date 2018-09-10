package com.huacainfo.ace.generator.cdrb;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenxiaoke on 2017/9/30.
 */
public class GenerateB extends HtmlGenerateService{
    private static final Logger logger = LoggerFactory.getLogger(HtmlGenerateService.class);
    public static List<Map<String, Object>> getAPagePathList(String date) {
        final String url = "http://cdwb.cdyee.com/html/" + date + "/node_21.htm";
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element container = doc.getElementById("bmdh");
            String html = container.html();

            html = html.replace("../../../", "http://cdwb.cdyee.com/");
            Document o = Jsoup.parse(html);
            Elements pdf = o.getElementsByTag("a");
            for (Element e : pdf) {
                String href = e.attr("href");
                if (href.endsWith(".pdf")) {
                    list1.add(href);
                    // logger.info("{}",href);
                }
                if (href.endsWith(".htm")) {
                    href = href.replace("./", "");
                    list2.add("http://cdwb.cdyee.com/html/" + date + "/" + href);
                    list3.add(e.text());
                    // logger.info("{}",href);
                }
            }

        } catch (IOException e) {
            logger.error("{}", e);
        }
        for (int i = 0; i < list1.size(); i++) {
            Map<String, Object> o = new HashMap<String, Object>();
            o.put("pdf", list1.get(i));
            o.put("htm", list2.get(i));
            o.put("title", list3.get(i));
            logger.info("{}", list3.get(i));
            list.add(o);
        }

        return list;
    }
    public void generateHtml(String date) throws Exception {
        List<Map<String, Object>> list = getAPagePathList(date);
        String generateHtmlPath = this.getProperty("generateHtmlPath");
        generateHtmlPath = generateHtmlPath + File.separator + date + File.separator;
        int i = 0;
        String body = "";
        for (Map<String, Object> e : list) {
            String text = getPageBody((String) e.get("htm"), getPage(i), date);
            e.put("text", text);
            e.put("pages", i);
            e.put("total", list.size());
            body = generateText("com/huacainfo/ace/generator/cdrb/index.html.vm", e);
            this.writeFile(generateHtmlPath, "index-" + i + ".html", body);
            // logger.info("{}",body);
            i++;
        }
    }


    public String getPageBody(String url, String page, String date) {
        String rst = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements container = doc.getElementsByClass("Ltd1");
            String html = container.html();
            html = html.replace("../../../", "http://cdwb.cdyee.com/");
            Document o = Jsoup.parse(html);
            Elements areas = o.getElementsByTag("area");
            for (Element e : areas) {
                String href = e.attr("href");
                href = "http://cdwb.cdyee.com/html/" + date + "/" + href;
                e.attr("href", "http://zx.huacainfo.com/portal/www/cdrb/preview.jsp?url=" + href);
            }
            Elements images = o.getElementsByTag("img");
            for (Element i : images) {
                i.attr("width", "100%");
                i.attr("height", "auto");
                i.attr("src", page + ".png");
                i.attr("USEMAP", "#PagePicMap" + page);
            }
            Elements maps = o.getElementsByTag("map");
            for (Element e : maps) {
                e.attr("name", "PagePicMap" + page);
            }
            rst = o.getElementsByTag("body").html();
        } catch (IOException e) {
            logger.error("{}", e);
        }
        return rst;
    }
    public void initPageFiles(String date) throws Exception {
        List<Map<String, Object>> list = getAPagePathList(date);
        String path = this.getProperty("generateHtmlPath");
        int i = 0;
        for (Map<String, Object> e : list) {
            downPdf((String) e.get("pdf"), path + File.separator + date + File.separator, getPage(i));
            pdf2Pic(path + File.separator + date + File.separator + getPage(i) + ".pdf");
            i++;
        }
    }
    public String getProperty(String key) {
        ResourceBundle resource = ResourceBundle.getBundle("cdwb");
        return resource.getString(key);
    }
    public static void main(String[] args) throws Exception {
        String date = "";
        GenerateB service = new GenerateB();
        date = service.getProperty("date");
        service.initPageFiles(date);
        service.generateHtml(date);
    }
}
