import com.huacainfo.ace.rvc.vo.ChatDTO;
import org.junit.Test;

/**
 * Created by Arvin on 2017/12/25.
 */
public class HelloWorld {

    @Test
    public void test() {

        ChatDTO chatMessage = new ChatDTO();
        chatMessage.setAction("text");
        chatMessage.setContent("你好");

        System.out.println(chatMessage.toString());
    }
}
