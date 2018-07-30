import com.huacainfo.ace.common.plugins.wechat.util.ApiResult;
import com.huacainfo.ace.common.plugins.wechat.util.QrcodeApi;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Auther: Arvin
 * @Date: 2018/7/19 21:28
 * @Description:
 */
public class CommonTest {

    /**
     * 将InputStream写入本地文件
     *
     * @param destination 写入本地目录
     * @param inputStream 输入流
     * @throws IOException
     */
    private static void writeToLocal(String destination, InputStream inputStream) throws IOException {
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(destination);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test() {
//        String accessToken = "12_w2b662E5tXvLLGRewt84yVZWraYqjlsgNXVa-514hFjiQX7AQUuOumHDJFX8r8UdQZp8D3Kp2BZHrPflp8qYBNTT6eWk136IqLgddioQqTftHx1ldHOepP9rqHQxQ4aiNvZPeP1zAdOLaFKsKIKbAAAAYA";
//        String uri = temporary("arvin", accessToken);
//        System.out.println("======>" + uri);

    }

    private String temporary(String sceneStr, String accessToken) {
        ApiResult as = QrcodeApi.createTemporary(QrcodeApi.EXPIRE_SECONDS_30_DAY, sceneStr, accessToken);
        return getQRCodeUrl(as.getStr("ticket"));
    }

    private String permanent(String sceneStr, String accessToken) {
        ApiResult as = QrcodeApi.createPermanent(sceneStr, "");
        return getQRCodeUrl(as.getStr("ticket"));
    }

    private String getQRCodeUrl(String ticket) {
        try {
            ticket = URLEncoder.encode(ticket, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return QrcodeApi.getShowQrcodeUrl(ticket);
    }

}
