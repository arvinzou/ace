/**
 * @Title: BeanUtils.java
 * @Package org.platform.snail.utils
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:15:42
 * @version V1.0
 */

package com.huacainfo.ace.autocode.base;

/**
 * @ClassName: BeanUtils
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:15:42
 */

/**
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.huacainfo.ace.common.tools.CommonUtils;

public class AutoCodeUtils {

    private String BEAN_SERVICE_TEMPLATE_VM_PATH;
    private String BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH;
    private String BEAN_ACTION_TEMPLATE_VM_PATH;
    private String BEAN_QVO_PATH;
    private String BEAN_VO_PATH;

    private String INDEX_JSP_PATH;
    private String CONFIG_JS_PATH;
    private String MODEL_JS_PATH;
    private String VIEW_JS_PATH;
    private String CRON_JS_PATH;

    private String ACTION_PATH;
    private String SERVICE_PATH;
    private String SERVICE_IMPL_PATH;

    private String VO_PATH;


    private String BEAN_DAO_PATH;
    private String DAO_PATH;


    private String clientws;
    private String serverws;
    private String webws;

    private static Bean bean = new Bean();
    private String cfg;
    private static Annotation annotation = new Annotation();

    public AutoCodeUtils(String cfg) {
        this.cfg = cfg;
        this.BEAN_ACTION_TEMPLATE_VM_PATH = this
                .getProperty("BEAN_ACTION_TEMPLATE_VM_PATH");
        this.BEAN_SERVICE_TEMPLATE_VM_PATH = this
                .getProperty("BEAN_SERVICE_TEMPLATE_VM_PATH");
        this.BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH = this
                .getProperty("BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH");
        this.ACTION_PATH = this.getProperty("ACTION_PATH");
        this.SERVICE_PATH = this.getProperty("SERVICE_PATH");
        this.SERVICE_IMPL_PATH = this.getProperty("SERVICE_IMPL_PATH");


        this.BEAN_QVO_PATH = this.getProperty("BEAN_QVO_PATH");
        this.BEAN_VO_PATH = this.getProperty("BEAN_VO_PATH");
        this.VO_PATH = this.getProperty("VO_PATH");

        this.BEAN_DAO_PATH = this.getProperty("BEAN_DAO_PATH");
        this.DAO_PATH = this.getProperty("DAO_PATH");
        this.clientws = System.getProperty("user.dir");
        this.serverws = System.getProperty("user.dir");
        this.webws = System.getProperty("user.dir");

        this.INDEX_JSP_PATH = this.getProperty("INDEX_JSP_PATH");
        this.CONFIG_JS_PATH = this.getProperty("CONFIG_JS_PATH");
        this.MODEL_JS_PATH = this.getProperty("MODEL_JS_PATH");
        this.VIEW_JS_PATH = this.getProperty("VIEW_JS_PATH");
        this.CRON_JS_PATH = this.getProperty("CRON_JS_PATH");

        if (this.getProperty("local").equals("true")) {
            String userDir=System.getProperty("user.dir");
            System.out.println(userDir);
            this.clientws =userDir+ this.getProperty("clientws");
            this.serverws =userDir+ this.getProperty("serverws");
            this.webws =userDir+ this.getProperty("webws");

        }
    }

    /**
     * 初始化bean和注解
     *
     * @param c
     */
    public void init(Class c) {
        if (c != null) {
            String cName = c.getName();
            bean.setName(getLastChar(cName));
            bean.setTableChineseName("会议");
            bean.setLowerName(getLowercaseChar(getLastChar(cName)));

            annotation.setAuthorName("陈晓克");
            annotation.setAuthorMail("249134995@qq.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            annotation.setDate(simpleDateFormat.format(new Date()));
            annotation.setVersion("1.0");
        }
    }

    /**
     * 创建bean的Dao<br>
     *
     * @param c
     * @throws Exception
     */

    /**
     * 创建bean的Dao的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void createAction(Class c) throws Exception {
        String cName = c.getName();
        String path = webws + "/src/main/java/" + ACTION_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Controller.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        // bean.setBeanDaoUrl(ACTION_URL);
        // bean.setBeanDaoImplUrl(ACTION_IMPL_URL);

        fw.write(createCode(BEAN_ACTION_TEMPLATE_VM_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createVO(Class c) throws Exception {
        String cName = c.getName();
        String path = clientws + "/src/main/java/" + VO_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Vo.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        // bean.setBeanDaoUrl(ACTION_URL);
        // bean.setBeanDaoImplUrl(ACTION_IMPL_URL);

        fw.write(createCode(BEAN_VO_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createQVO(Class c) throws Exception {
        String cName = c.getName();
        String path = clientws + "/src/main/java/" + VO_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "QVo.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        // bean.setBeanDaoUrl(ACTION_URL);
        // bean.setBeanDaoImplUrl(ACTION_IMPL_URL);

        fw.write(createCode(BEAN_QVO_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createDAO(Class c) throws Exception {
        String cName = c.getName();
        String path = serverws + "/src/main/java/" + DAO_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Dao.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        // bean.setBeanDaoUrl(ACTION_URL);
        // bean.setBeanDaoImplUrl(ACTION_IMPL_URL);

        fw.write(createCode(BEAN_DAO_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    /**
     * 创建bean的Service<br>
     *
     * @param c
     * @throws Exception
     */
    public void createService(Class c) throws Exception {
        String cName = c.getName();
        String path = clientws + "/src/main/java/" + SERVICE_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Service.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);


        fw.write(createCode(BEAN_SERVICE_TEMPLATE_VM_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    /**
     * 创建bean的Service的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void createServiceImpl(Class c) throws Exception {
        StringBuffer validate = new StringBuffer();
        String cName = c.getName();
        String path = serverws + "/src/main/java/" + SERVICE_IMPL_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + getLastChar(cName) + "ServiceImpl.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        // System.out.println(CommonUtils.propertyToField(c.getSimpleName()));
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        for (ColumsInfo o : list) {
            if (o.getIsNullAble().equals("NO")&&(!o.getColumName().equals("createUserId"))&&(!o.getColumName().equals("createUserName"))&&(!o.getColumName().equals("createDate"))) {
                validate.append("if (CommonUtils.isBlank(o.get"
                        + CommonUtils.firstToUpper(CommonUtils.getJavaName(o
                        .getColumName())) + "())) {\r");
                validate.append("return new MessageResponse(1, \""
                        + o.getRemarks() + "不能为空！\");\r");
                validate.append("}\r");
            }

        }
        bean.setValidate(validate.toString());

        fw.write(createCode(BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH, bean,
                annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createJsp(Class c) throws Exception {
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        String path = webws + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "index.jsp";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        bean.setView(DBHelpInfo.getPreviewJs(tableName));
        fw.write(createCode(INDEX_JSP_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createJsConfig(Class c) throws Exception {
        String path = webws + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "config.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        fw.write(createCode(CONFIG_JS_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createJsView(Class c) throws Exception {
        String path = webws + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "view.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(createCode(VIEW_JS_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createModel(Class c) throws Exception {
        String path = webws + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "model.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        StringBuffer _colNames = new StringBuffer();
        StringBuffer _colModel = new StringBuffer();
        _colNames.append("[");
        int i = 1;
        for (ColumsInfo o : list) {
            _colNames.append("'");
            _colNames.append(o.getRemarks());
            _colNames.append("'");
            if (i != list.size()) {
                _colNames.append(",");
            }
            _colModel.append("{\r");
            _colModel.append("name : '"
                    + CommonUtils.getJavaName(o.getColumName()) + "',\r");
            boolean status=o.getColumName().equals("lastModifyUserName") || o.getColumName().equals("lastModifyDate") || o.getColumName().equals("createUserId") || o.getColumName().equals("lastModifyUserId")|| o.getColumName().equals("createUserName")|| o.getColumName().equals("createDate");
            if (status) {
                _colModel.append("hidden : true,\r");
                _colModel.append("editable : false,\r");
                _colModel.append("width : 100\r");
            } else {
                _colModel.append("editable : true,\r");
                _colModel.append("width : 100,\r");
            }

            if(!status){
                _colModel.append("editoptions : {\r");
                _colModel.append("size : \"20\",\r");
                _colModel.append("maxlength : \"50\"\r");
                _colModel.append("}");
            }


            if (o.getIsNullAble().equals("NO")&&(!status)) {
                _colModel.append(",\rformoptions : {\r");
                _colModel.append("elmprefix : \"\",\r");
                _colModel
                        .append("elmsuffix : \"<span style='color:red;font-size:16px;font-weight:800\'>*</span>\"\r");
                _colModel.append("},");
                _colModel.append("editrules : {\r");
                _colModel.append("required : true\r");
                _colModel.append("}\r");
            }
            _colModel.append("}\r");
            if (i != list.size()) {
                _colModel.append(",");
            }
            i++;
        }
        _colNames.append(",'操作'");
        _colNames.append("]");
        bean.setColumModel(_colModel.toString());
        bean.setColumName(_colNames.toString());
        fw.write(createCode(MODEL_JS_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public void createJsController(Class c) throws Exception {
        String path = webws + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "controller.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(createCode(CRON_JS_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    public String lowerCase(String str) {
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1)
                .toLowerCase());
    }

    /**
     * 根据模板生成代码
     *
     * @param fileVMPath
     *            模板路径
     * @param bean
     *            目标bean
     * @param annotation
     *            注释
     * @return
     * @throws Exception
     */
    public String createCode(String fileVMPath, Bean bean, Annotation annotation)
            throws Exception {
        //fileVMPath=this.serverws+"/ace-autocode/"+fileVMPath;
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine
                .setProperty("class.resource.loader.class",
                        "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(fileVMPath);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("bean", bean);
        velocityContext.put("annotation", annotation);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 创建文件
     *
     * @param file
     */
    public void createFilePath(File file) {
        if (!file.exists()) {
            System.out.println("创建[" + file.getAbsolutePath() + "]情况："
                    + file.mkdirs());
        } else {
            System.out.println("存在目录：" + file.getAbsolutePath());
        }
    }

    /**
     * 获取路径的最后面字符串<br>
     * 如：<br>
     * <code>str = "com.b510.base.bean.User"</code><br>
     * <code> return "User";<code>
     *
     * @param str
     * @return
     */
    public String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }

    /**
     * 把第一个字母变为小写<br>
     * 如：<br>
     * <code>str = "UserDao";</code><br>
     * <code>return "userDao";</code>
     *
     * @param str
     * @return
     */
    public String getLowercaseChar(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 显示信息
     *
     * @param info
     */
    public void showInfo(String info) {
        System.out.println("创建文件：" + info + "成功！");
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    public String getProperty(String key) {
        ResourceBundle resource = ResourceBundle.getBundle(this.cfg);
        return resource.getString(key);
    }
}