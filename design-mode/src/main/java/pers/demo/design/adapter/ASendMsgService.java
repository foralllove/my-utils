package pers.demo.design.adapter;

/**
 * ASendMsgService
 *
 * @author: hh
 * @since: 2021/6/20 17:55
 */
public class ASendMsgService {

    public String sendMsg(String signature, String bodyText, String appKey, String appSecret, String phone) {
        System.out.println("signature=" + signature + ",body=" + bodyText);
        System.out.println("phone=" + phone);
        System.out.println("appKey=" + appKey + ",appSecret=" + appSecret);
        return "ok";
    }
}
