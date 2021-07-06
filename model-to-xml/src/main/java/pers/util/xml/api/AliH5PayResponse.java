package pers.util.xml.api;

import lombok.Data;

/**
 * AliH5PayResponse
 *
 * @author hh
 * @since 2020/12/22 19:04
 */
@Data
public class AliH5PayResponse {
    private String msg;
    private String code;
    private String orderId;
    private String body;

    public boolean success() {
        return "0".equals(code);
    }
}
