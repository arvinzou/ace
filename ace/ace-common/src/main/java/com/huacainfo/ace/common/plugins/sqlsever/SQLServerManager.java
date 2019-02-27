package com.huacainfo.ace.common.plugins.sqlsever;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.JsonUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/2/26 17:28
 * @Description:
 */
public class SQLServerManager {
    private final static String DRIVER_STR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static SQLServerManager manager;
    private String connUrl;// = "jdbc:sqlserver://192.168.2.100;DatabaseName=hnhr_currency";
    private String acct;
    private String pwd;

    /**
     * 单利模式
     */
    private SQLServerManager() {

    }

    private SQLServerManager(String connUrl, String acct, String pwd) {
        this.connUrl = connUrl;
        this.acct = acct;
        this.pwd = pwd;
    }

    public static void test() {
        String connUrl = "jdbc:sqlserver://192.168.2.100:52006;DatabaseName=hnhr_currency";
        String sa = "sa";
        String pwd = "admin123";

        String sql = "select  u.Name,  t.* from dbo.BorrowCurrency t \n" +
                "left join dbo.ReaderCurrency u on t.ReaderCord = u.ReaderCord\n" +
                "where 1=1\n" +
                "and t.Signs='未还'\n" +
                "and u.ReaderBar in  ('Z0000012','','')\n" +
                "and CONVERT(varchar(100), t.BorrowDate, 20) like '2014-10-17%'\n" +
                "order by t.BorrowDate asc";//查询test表

        SQLServerManager manager = SQLServerManager.newInstance(connUrl, sa, pwd);
        List<Map<String, Object>> rs = manager.query(sql);
        System.out.println(JsonUtil.toJson(rs));
    }

    public static void main(String[] args) {
        test();
    }

    /**
     * 创建对象实例
     *
     * @param connUrl 连接串
     * @param acct    账号
     * @param pwd     密码
     * @return SQLServerManager
     */
    public static SQLServerManager newInstance(String connUrl, String acct, String pwd) {
        if (manager == null) {
            return new SQLServerManager(connUrl, acct, pwd);
        }
        return manager;
    }

    /**
     * 创建连接
     *
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (!StringUtil.areNotEmpty(connUrl, acct, pwd)) {
            return null;
        }

        Class.forName(DRIVER_STR);
        return DriverManager.getConnection(connUrl, acct, pwd);//"sa", "admin123456");
    }

    /**
     * sql 查询
     *
     * @param sql
     */
    public List<Map<String, Object>> query(String sql) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        ResultSetMetaData data;
        try {
            con = getConnection();
            if (con == null) {

            }
            statement = con.prepareStatement(sql);
            res = statement.executeQuery();
            data = res.getMetaData();

            List<Map<String, Object>> rst = new LinkedList<>();
            Map<String, Object> row;

            String colTypeName;
            String colName;
            while (res.next()) {
                row = new HashMap<>();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    //获得指定列的数据类型名
                    colTypeName = data.getColumnTypeName(i);
                    colName = data.getColumnName(i);//.toUpperCase();
                    //整型数据
                    if ("int".equals(colTypeName)) {
                        row.put(colName, res.getInt(i));
                    }
                    //浮点型数据
                    else if ("money".equals(colTypeName)) {
                        row.put(colName, res.getBigDecimal(i));
                    } else {
                        row.put(colName, res.getString(i));
                    }
                }
                rst.add(row);
            }

            return rst;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }

        return null;
    }


}
