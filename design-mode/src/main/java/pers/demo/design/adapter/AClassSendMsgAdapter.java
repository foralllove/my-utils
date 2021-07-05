package pers.demo.design.adapter;

/**
 * AClassSendMsgAdapter
 *
 * @author: hh
 * @since: 2021/6/20 18:02
 */
public class AClassSendMsgAdapter extends ASendMsgService implements SendMsgService {
    @Override
    public String sendMsg(String msg, String phone, String token) {
        String[] split = msg.split("\\|");
        String[] tokenSplit = token.split("\\|");
        this.sendMsg(split[0], split[1], tokenSplit[0], tokenSplit[1], phone);
        return "成功";
    }
}
