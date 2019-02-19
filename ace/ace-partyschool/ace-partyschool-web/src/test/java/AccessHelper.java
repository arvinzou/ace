import java.sql.*;

/**
 * @Auther: Arvin
 * @Date: 2019/2/19 09:30
 * @Description:
 */
public class AccessHelper {
    public static void main(String[] args) {
        String sql = "select * from USERINFO";
        String path = "C:\\Users\\HuaCai008\\Desktop\\attBackup.mdb";
        query(sql, path);
    }

    /**
     * 查询数据
     *
     * @param sql
     * @param dbpath
     */
    public static void query(String sql, String dbpath) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData data = null;
        try {
            conn = AccessConnector.connect(dbpath);
            if (conn == null) {
                return;
            }
            ps = conn.prepareStatement(sql);
            //设置查询参数
//            ps.setString(1, startTime);
//            ps.setString(2, endTime);

            //执行sql语句
            rs = ps.executeQuery();
            data = rs.getMetaData();
            while (rs.next()) {
                System.out.println("====================================");
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    //获得指定列的数据类型名
                    String columnTypeName = data.getColumnTypeName(i);
                    //获得指定列的列名
                    String columnName = data.getColumnName(i);
                    //获得指定列的列值
                    String columnValue = rs.getString(i);
                    //输出查询结果
                    System.out.println(columnName + "(" + columnTypeName + ")=" + columnValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭相关的连接
            AccessConnector.close(conn);
            AccessConnector.close(ps);
            AccessConnector.close(rs);
        }
    }
}
