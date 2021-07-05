package pers.demo.design.adapter;

/**
 * ObjectSendmsgAdapter
 *
 * @author: hh
 * @since: 2021/6/20 18:21
 */
public class AObjectSendMsgAdapter implements SendMsgService {
    private ASendMsgService aSendMsgService;

    public AObjectSendMsgAdapter(ASendMsgService aSendMsgService){
        this.aSendMsgService = aSendMsgService;
    }

    @Override
    public String sendMsg(String msg, String phone, String token) {
        String[] split = msg.split("\\|");
        String[] tokenSplit = token.split("\\|");
        aSendMsgService.sendMsg(split[0], split[1], tokenSplit[0], tokenSplit[1], phone);
        return "成功";
    }

}
