package pers.demo.design.adapter;

/**
 * ObjectSendMsgTest
 *
 * @author: hh
 * @since: 2021/6/20 18:29
 */
public class ObjectSendMsgTest {
    public static void main(String[] args) {
        String msg = "测试|发送一条短信";
        String token = "TC123|Test123";
        String phone = "13113231234|13113231235";
        SendMsgService sendMsgService = new AObjectSendMsgAdapter(new ASendMsgService());
        sendMsgService.sendMsg(msg, phone, token);
    }
}
