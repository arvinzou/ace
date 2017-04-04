package com.huacainfo.ace.common.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


 

/**
 * 
 * 功能描述：
 * 
 * @author Administrator
 * @Date Jul 19, 2008
 * @Time 9:46:11 AM
 * @version 1.0
 */
public class XmlUtils {
	
	
	
	public static Document ReadXml(File file)
	{
		Document document = null;
		SAXReader saxReader = new SAXReader();
        try {
			  document = saxReader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;

	}
	
	/**
	 * 
	 * @param url
	 * @param label xml标签，从第二级开始
	 * @return
	 */
	public static String ReadXml(File inputXml,String... label){
		//File inputXml = new File("D:\\weather.xml");
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element rootElement = document.getRootElement();
			String [] s = new String[]{"environment","pm25"};
			for(int i = 0;i<label.length;i++){
				if(rootElement!=null){
				rootElement = diedai(rootElement,label[i]);
					if(i==s.length-1){
						return rootElement.getText();
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static String ReadXml(String xml,String... label){
		//File inputXml = new File("D:\\weather.xml");
		SAXReader saxReader = new SAXReader();
		try {
			Document document = DocumentHelper.parseText(xml);
			//InputStream is = new FileInputStream(xml);
			//Document document = saxReader.read(is);
			Element rootElement = document.getRootElement();
			String [] s = new String[]{"environment","pm25"};
			for(int i = 0;i<label.length;i++){
				if(rootElement!=null){
				rootElement = diedai(rootElement,label[i]);
					if(i==s.length-1){
						return rootElement.getText();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	
	public static Element diedai(Element e,String a){
		for(Iterator i =  e.elementIterator();i.hasNext();){
			Element next = (Element)i.next();
			if(next.getName().equals(a)){
				return next;
			}
		}
		return null;
	}
}
