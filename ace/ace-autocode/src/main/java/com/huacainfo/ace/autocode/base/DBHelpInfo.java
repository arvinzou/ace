package com.huacainfo.ace.autocode.base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.huacainfo.ace.common.tools.CommonUtils;

public class DBHelpInfo {

    /**
     * 这里是Oracle连接方法 private static final String driver =
     * "oracle.jdbc.driver.OracleDriver"; private static final String url =
     * "jdbc:oracle:thin:@localhost:1521:orcl"; private static final String uid
     * = "system"; private static final String pwd = "sys"; 这里是SQL Server连接方法
     * private static final String url =
     * "jdbc:sqlserver://localhost:1433;DateBaseName=数据库名"; private static final
     * String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver" private
     * static final String uid = "sa"; private static final String pwd = "sa";
     * <p>
     * <p>
     * 这里是MySQL连接方法
     */
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String pwd = "123456";
    private static final String user = "root";
    private static final String url = "jdbc:mysql://127.0.0.1/scc"
            + "?user=" + user + "&password=" + pwd
            + "&useUnicode=true&characterEncoding=UTF-8";
    private static Connection getConnection = null;

    public static void main(String[] args) {
        DBHelpInfo.getPreviewJs("writing");
    }

    public static List<ColumsInfo> getTableInfo(String tableName) {
        Logger logger = Logger.getLogger(DBHelpInfo.class);
        logger.info("table name ：" + tableName);
        List<ColumsInfo> list = new ArrayList<ColumsInfo>();
        getConnection = getConnections();
        try {
            DatabaseMetaData dbmd = getConnection.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%",
                    new String[]{"TABLE"});
            while (resultSet.next()) {
                if (resultSet.getString("TABLE_NAME").equals(tableName)) {
                    ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");

                    while (rs.next()) {
                        ColumsInfo o = new ColumsInfo();
                        o.setColumName(rs.getString("COLUMN_NAME"));
                        o.setRemarks(rs.getString("REMARKS"));
                        o.setTypeName(rs.getString("TYPE_NAME"));
                        o.setIsNullAble(rs.getString("IS_NULLABLE"));
                        list.add(o);
                        logger.info(o.toString());
                    }
                    logger.info("generate success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Connection getConnections() {
        try {
            Class.forName(driver).newInstance();
            getConnection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getConnection;
    }

    public static String getSchema() throws Exception {
        String schema;
        schema = getConnection.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();
    }

    public static void getModelJs() {
        List<ColumsInfo> list = DBHelpInfo.getTableInfo("member_info");
        StringBuffer _colNames = new StringBuffer();
        StringBuffer _colModel = new StringBuffer();
        _colNames.append("[");
        for (ColumsInfo o : list) {
            if((!o.getColumName().equals("createUserId"))&&(!o.getColumName().equals("lastModifyUserId"))) {
                _colNames.append("'");
                _colNames.append(o.getRemarks());
                _colNames.append("'");
                _colNames.append(",");
                _colModel.append("{\r");
                _colModel.append("name : '"
                        + CommonUtils.getJavaName(o.getColumName()) + "',\r");
                if(o.getColumName().equals("lastModifyUserName")||o.getColumName().equals("lastModifyDate")){
                    _colModel.append("hidden : true,\r");
                }
                _colModel.append("width : 100,\r");
                _colModel.append("editable : true,\r");
                _colModel.append("editoptions : {\r");
                _colModel.append("size : \"20\",\r");

                _colModel.append("maxlength : \"50\"\r");
                _colModel.append("}");

                if (o.getIsNullAble().equals("NO")) {
                    _colModel.append(",\rformoptions : {\r");
                    _colModel.append("elmprefix : \"\",\r");
                    _colModel
                            .append("elmsuffix : \"<span style='color:red;font-size:16px;font-weight:800\'>*</span>\"\r");
                    _colModel.append("},");
                    _colModel.append("editrules : {\r");
                    _colModel.append("required : true\r");
                    _colModel.append("}\r");
                }
                _colModel.append("},\r");
            }

        }
        _colNames.append("]");
        System.out.println(_colNames.toString());
        System.out.println(_colModel.toString());
    }

    public static String getPreviewJs(String tableName) {
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        StringBuffer html = new StringBuffer();
        html.append("<div class=\"profile-user-info profile-user-info-striped  profile-bg \">");
        html.append("\r\n");
        html.append("<div class=\"profile-group-title\">基本信息</div>");
        html.append("\r\n");
        html.append("<div class=\"profile-info-row\">");
        html.append("\r\n");
        int i = 1;
        for (ColumsInfo o : list) {
            html.append("<div class=\"profile-info-name\">");
            html.append("\r\n");
            html.append(o.getRemarks());
            html.append("</div>");
            html.append("\r\n");
            html.append("<div class=\"profile-info-value\">");
            html.append("\r\n");
            html.append("<span class=\"editable editable-click\" id=\"");
            html.append(CommonUtils.getJavaName(o.getColumName()));
            html.append("\">");
            html.append("\r\n");
            html.append("</span>");
            html.append("\r\n");
            html.append("</div>");
            html.append("\r\n");
            if (i % 3 == 0) {
                html.append("</div>");
                html.append("\r\n");
                html.append("<div class=\"profile-info-row\">");
                html.append("\r\n");
            }
            i++;
        }
        html.append("</div>");
        html.append("\r\n");
        html.append("</div>");
        html.append("\r\n");

        System.out.println(html.toString());
        return  html.toString();
    }

}