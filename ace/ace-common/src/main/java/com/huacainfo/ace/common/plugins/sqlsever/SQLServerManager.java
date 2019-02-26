package com.huacainfo.ace.common.plugins.sqlsever;

import java.sql.*;

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

    public static void main(String[] args) {
        String connUrl = "jdbc:sqlserver://192.168.2.100:52006;DatabaseName=hnhr_currency";
        String sa = "sa";
        String pwd = "admin123";

        String sql = "select  u.Name,  t.* from dbo.BorrowCurrency t \n" +
                "left join dbo.ReaderCurrency u on t.ReaderCord = u.ReaderCord\n" +
                "where 1=1\n" +
                "and t.Signs='未还'\n" +
                "and u.ReaderBar in  ('Z0000012','','')\n" +
                "order by t.BorrowDate asc";//查询test表

        SQLServerManager manager = SQLServerManager.newInstance(connUrl, sa, pwd);
        manager.query(sql);
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
        Class.forName(DRIVER_STR);
        return DriverManager.getConnection(connUrl, acct, pwd);//"sa", "admin123456");
    }

    /**
     * sql 查询
     *
     * @param sql
     */
    public void query(String sql) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try {
            con = getConnection();
            statement = con.prepareStatement(sql);
            res = statement.executeQuery();
            while (res.next()) {
                String title = res.getString("Name");//获取test_name列的元素                                                                                                                                                    ;
                System.out.println("姓名：" + title);
            }

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
    }


}
