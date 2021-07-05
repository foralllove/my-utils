package pers.demo.design.adapter;

/**
 * SendMsgTest
 *
 * @author: hh
 * @since: 2021/6/20 18:10
 */
public class SendMsgTest {

    public static void main(String[] args) {
        String msg = "测试|发送一条短信";
        String token = "TC123|Test123";
        String phone = "13113231234|13113231235";

        SendMsgService sendMsgService = new AClassSendMsgAdapter();
        SendMsgService sendMsgService2 = new BClassSendMsgAdapter();

        sendMsgService.sendMsg(msg,phone,token);

        System.out.println("###############B测试##########");
        sendMsgService2.sendMsg(msg,phone,token);
    }
}
