//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huacainfo.ace.common.tools;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class XmlConverUtil {
    protected static Logger logger = LoggerFactory.getLogger(XmlConverUtil.class);

    public XmlConverUtil() {
    }

    public static String maptoXml(Map map) {
        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("node");
        Iterator var3 = map.keySet().iterator();

        while (var3.hasNext()) {
            Object obj = var3.next();
            Element keyElement = nodeElement.addElement("key");
            keyElement.addAttribute("label", String.valueOf(obj));
            keyElement.setText(String.valueOf(map.get(obj)));
        }

        return doc2String(document);
    }

    public static String listtoXml(List list) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element nodesElement = document.addElement("nodes");
        int i = 0;

        for (Iterator var4 = list.iterator(); var4.hasNext(); ++i) {
            Object o = var4.next();
            Element nodeElement = nodesElement.addElement("node");
            if (o instanceof Map) {
                Iterator var10 = ((Map) o).keySet().iterator();

                while (var10.hasNext()) {
                    Object obj = var10.next();
                    Element keyElement1 = nodeElement.addElement("key");
                    keyElement1.addAttribute("label", String.valueOf(obj));
                    keyElement1.setText(String.valueOf(((Map) o).get(obj)));
                }
            } else {
                Element keyElement = nodeElement.addElement("key");
                keyElement.addAttribute("label", String.valueOf(i));
                keyElement.setText(String.valueOf(o));
            }
        }

        return doc2String(document);
    }

    public static String jsontoXml(String json) {
        try {
            XMLSerializer e = new XMLSerializer();
            JSON jsonObject = JSONSerializer.toJSON(json);
            return e.write(jsonObject);
        } catch (Exception var3) {
            logger.error("run error", var3);
            return null;
        }
    }

    public static Map xmltoMap(String xml) {
        HashMap map = new HashMap();
        if (CommonUtils.isEmpty(xml)) {
            return map;
        } else {
            try {
                Document e = DocumentHelper.parseText(xml);
                Element root = e.getRootElement();
                Iterator iterator = root.elementIterator();

                while (iterator.hasNext()) {
                    Element e1 = (Element) iterator.next();
                    List list = e1.elements();
                    if (list.size() > 0) {
                        map.put(e1.getName(), Dom2Map(e1));
                    } else {
                        map.put(e1.getName(), e1.getText());
                    }
                }
            } catch (Exception var7) {
                logger.error("xmltoMap.run error", var7);
            }

            return map;
        }
    }

    public static Map xmltoLinkMap(String xml) {
        LinkedHashMap map = new LinkedHashMap();
        if (CommonUtils.isEmpty(xml)) {
            return map;
        } else {
            try {
                Document e = DocumentHelper.parseText(xml);
                Element root = e.getRootElement();
                Iterator iterator = root.elementIterator();

                while (iterator.hasNext()) {
                    Element e1 = (Element) iterator.next();
                    List list = e1.elements();
                    if (list.size() > 0) {
                        map.put(e1.getName(), Dom2Map(e1));
                    } else {
                        map.put(e1.getName(), e1.getText());
                    }
                }
            } catch (Exception var7) {
                logger.error("xmltoMap.run error", var7);
            }

            return map;
        }
    }

    public static Map Dom2Map(Element e) {
        HashMap map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                Element iter = (Element) list.get(i);
                Object mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map obj = Dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj1 = map.get(iter.getName());
                        if (!obj1.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            ((List) mapList).add(obj1);
                            ((List) mapList).add(obj);
                        }

                        if (obj1.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj1;
                            ((List) mapList).add(obj);
                        }

                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), obj);
                    }
                } else if (map.get(iter.getName()) != null) {
                    Object var8 = map.get(iter.getName());
                    if (!var8.getClass().getName().equals("java.util.ArrayList")) {
                        mapList = new ArrayList();
                        ((List) mapList).add(var8);
                        ((List) mapList).add(iter.getText());
                    }

                    if (var8.getClass().getName().equals("java.util.ArrayList")) {
                        mapList = (List) var8;
                        ((List) mapList).add(iter.getText());
                    }

                    map.put(iter.getName(), mapList);
                } else {
                    map.put(iter.getName(), iter.getText());
                }
            }
        } else {
            map.put(e.getName(), e.getText());
        }

        return map;
    }

    public static List xmltoList(String xml) {
        try {
            ArrayList e = new ArrayList();
            Document document = DocumentHelper.parseText(xml);
            Element nodesElement = document.getRootElement();
            List nodes = nodesElement.elements();

            Map map;
            for (Iterator its = nodes.iterator(); its.hasNext(); map = null) {
                Element nodeElement = (Element) its.next();
                map = xmltoMap(nodeElement.asXML());
                e.add(map);
            }

            nodes = null;
            nodesElement = null;
            document = null;
            return e;
        } catch (Exception var8) {
            logger.error("run error", var8);
            return null;
        }
    }

    public static String xmltoJson(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        return xmlSerializer.read(xml).toString();
    }

    public static String doc2String(Document document) {
        String s = "";

        try {
            ByteArrayOutputStream ex = new ByteArrayOutputStream();
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(ex, format);
            writer.write(document);
            s = ex.toString("UTF-8");
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return s;
    }

    public static Map doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if (null != strxml && !"".equals(strxml)) {
            HashMap m = new HashMap();
            ByteArrayInputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
            SAXBuilder builder = new SAXBuilder();
            org.jdom.Document doc = builder.build(in);
            org.jdom.Element root = doc.getRootElement();
            List list = root.getChildren();

            String k;
            String v;
            for (Iterator it = list.iterator(); it.hasNext(); m.put(k, v)) {
                org.jdom.Element e = (org.jdom.Element) it.next();
                k = e.getName();
                v = "";
                List children = e.getChildren();
                if (children.isEmpty()) {
                    v = e.getTextNormalize();
                } else {
                    v = getChildrenText(children);
                }
            }

            in.close();
            return m;
        } else {
            return null;
        }
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();

            while (it.hasNext()) {
                org.jdom.Element e = (org.jdom.Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }

                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public static String getRequestXml(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Iterator var2 = parameters.entrySet().iterator();

        while (true) {
            while (var2.hasNext()) {
                Entry entry = (Entry) var2.next();
                String k = (String) entry.getKey();
                Object v = entry.getValue();
                if (!"attach".equalsIgnoreCase(k) && !"body".equalsIgnoreCase(k) && !"sign".equalsIgnoreCase(k)) {
                    sb.append("<" + k + ">" + v + "</" + k + ">");
                } else {
                    sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
                }
            }

            sb.append("</xml>");
            return sb.toString();
        }
    }
}
