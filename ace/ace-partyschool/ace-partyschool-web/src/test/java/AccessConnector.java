import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.*;

/**
 * @Auther: Arvin
 * @Date: 2019/2/19 09:27
 * @Description:
 */
public class AccessConnector {
    private static final Logger log = LoggerFactory.getLogger(AccessConnector.class);

    public static Connection connect(String dbpath) throws Exception {
        File dbFile = new File(dbpath);
        if (!dbFile.exists()) {
            log.error("db数据库文件不存在，请确认路径是否正确：{}", dbpath);
            return null;
        } else {
            log.info("读取数据库文件，路径：{}", dbpath);
        }

        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//这个驱动的地址不要改
        String dbur1 = "jdbc:ucanaccess:///" + dbpath;
        return DriverManager.getConnection(dbur1, "", "");
    }

    public static void close(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
