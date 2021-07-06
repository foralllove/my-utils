package pers.util.xml.api;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：TestController
 *
 * @author 归墟
 * @email huanghe@shzx.com
 * @date 2021/3/1 14:11
 * @company 数海掌讯
 */
@Controller
public class TestController {
    @GetMapping("test")
    @ResponseBody
    public String test() {
        String json =
                "{\"msg\":\"用户预下单成功！\",\"code\":\"0\",\"orderId\":\"1614579880806283602\",\"body\":\"<form name=\\\"punchout_form\\\" method=\\\"post\\\" action=\\\"https://openapi.alipay.com/gateway.do?charset=UTF-8&method=alipay.trade.wap.pay&sign=Ij0VkGtMCr07E6uKD91yPHVNj6PbvBBNQJtncY%2B8ras4J1J7FlS3OVnbfmDC4jG5ObJotY%2FiPjtlmU2PzMFsPUwhaAOkLp3l1L3CDKEQENZMtXd6hq%2BrkbO362GcVsF%2FXiZ1tTae%2FPYYioFz6NM90XUnlLjqe2ZRgximl9P2Loys6dpgv%2F3vH5aIUNnaB7X4aKBc0ZRNimBrbcJv6Y6hKeleXh9VvRFqNdiIDSx65whhSgRWPM06WOd1VOtSBgf4Tr5cdgz4UNoZgH%2BFgZJN0feKRG8OV5z%2BM6arr%2FykirkEVfMhqmCTquG7Y0HD09UCxznwh2ifOu90XrSSlrV7JA%3D%3D&version=1.0&app_id=2021002120620053&sign_type=RSA2&timestamp=2021-03-01+14%3A24%3A40&alipay_sdk=alipay-sdk-java-3.7.26.ALL&format=json\\\">\\n<input type=\\\"hidden\\\" name=\\\"app_auth_token\\\" value=\\\"202102BB360400e3d6524c3cac8dc11056deeX73\\\">\\n<input type=\\\"hidden\\\" name=\\\"biz_content\\\" value=\\\"{&quot;out_trade_no&quot;:&quot;1614579880806283602&quot;,&quot;product_code&quot;:&quot;QUICK_WAP_WAY&quot;,&quot;quit_url&quot;:&quot;http:\\\\/\\\\/192.168.1.10:8888\\\\/#\\\\/orderCenter&quot;,&quot;subject&quot;:&quot;数海掌讯充值中心-测试充值&quot;,&quot;total_amount&quot;:&quot;0.01&quot;}\\\">\\n<input type=\\\"submit\\\" value=\\\"立即支付\\\" style=\\\"display:none\\\" >\\n</form>\\n<script>document.forms[0].submit();</script>\"}";
        AliH5PayResponse aliH5PayResponse = JSON.parseObject(json, AliH5PayResponse.class);
        return aliH5PayResponse.getBody();
    }
}
