package pers.demo.design.adapter;

/**
 * SendMsgService 目标端口发送短信
 *
 * @author: hh
 * @since: 2021/6/20 17:37
 */
public interface SendMsgService {

    String sendMsg(String msg, String phone, String token);

}
