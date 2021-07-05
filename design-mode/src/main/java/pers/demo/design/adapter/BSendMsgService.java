package pers.demo.design.adapter;

import java.util.List;

/**
 * BSendMsgService
 *
 * @author: hh
 * @since: 2021/6/20 18:01
 */
public class BSendMsgService {

    public String sendMsg(String msg, String token, List<String> phoneList) {
        System.out.println("msg=" + msg);
        System.out.println("token=" + msg);
        for (String phone : phoneList) {
            System.out.println("phone=" + phone);
        }

        return "suc";
    }
}
