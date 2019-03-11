package com.huacainfo.ace.common.plugins.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/2/19 09:30
 * @Description:
 */
public class AccessHelper {

    private static Logger logger = LoggerFactory.getLogger(AccessHelper.class);

    public static void main(String[] args) {
//        USERID	CHECKTIME	CHECKTYPE	VERIFYCODE	SENSORID	Memoinfo	WorkCode	sn	UserExtFmt

        String sql = "select u.USERID, u.SSN, u.Name, c.CHECKTIME \n" +
                "from CHECKINOUT c\n" +
                "left join USERINFO u on c.USERID = u.USERID\n";

        String path = "D:\\zk\\dbfile\\20190221105448.mdb";
        List<Map<String, Object>> list = AccessHelper.query(sql, path);

        for (Map<String, Object> map : list) {
            System.out.println("===================");
            System.out.println(map.toString());
//            System.out.println(map.get("Name") + "|" + map.get("SSN") + "|" + map.get("USERID"));
        }
    }

    /**
     * 查询数据
     *
     * @param sql    sql执行语句
     * @param dbpath db文件路径
     */
    public static List<Map<String, Object>> query(String sql, String dbpath) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData data;
        try {
            conn = AccessConnector.connect(dbpath);
            if (conn == null) {
                return null;
            }
            ps = conn.prepareStatement(sql);
            //设置查询参数
//            ps.setString(1, startTime);
//            ps.setString(2, endTime);

            //执行sql语句
            rs = ps.executeQuery();
            data = rs.getMetaData();
            List<Map<String, Object>> rows = new LinkedList<>();
            Map<String, Object> row;
            while (rs.next()) {
                row = new HashMap<>();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    //获得指定列的数据类型名
//                    String columnTypeName = data.getColumnTypeName(i);
//                    //获得指定列的列名
//                    String columnName = data.getColumnName(i);
//                    //获得指定列的列值
//                    String columnValue = rs.getString(i);
                    //输出查询结果
//                    System.out.println(columnName + "(" + columnTypeName + ")=" + columnValue);
                    row.put(data.getColumnName(i).toUpperCase(), rs.getString(i));
                }
                rows.add(row);
            }

            return rows;
        } catch (SQLException e) {
            logger.debug("ACCESS SQLException:{}", e);
        } catch (Exception e) {
            logger.debug("ACCESS Exception:{}", e);
        } finally {
            //关闭相关的连接
            AccessConnector.close(conn);
            AccessConnector.close(ps);
            AccessConnector.close(rs);
        }

        return null;
    }
}
