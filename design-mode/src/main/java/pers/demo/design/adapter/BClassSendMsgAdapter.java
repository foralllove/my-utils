package pers.demo.design.adapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * BClassSendMsgAdapter
 *
 * @author: hh
 * @since: 2021/6/20 18:07
 */
public class BClassSendMsgAdapter extends BSendMsgService implements SendMsgService {
    @Override
    public String sendMsg(String msg, String phone, String token) {
        String[] split = phone.split(",");
        sendMsg(msg,token,new ArrayList<>(Arrays.asList(split)));
        return "成功";
    }
}
