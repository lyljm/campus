package xhu.click.wx.login.entity.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxResponse {
    /**
     * openid
     */
    private  String openid;
    /**
     * session_key
     */
    private  String session_key;
    /**
     * unionid
     */
    private String unionid;
    /**
     * errcode
     */
    private String errcode;
    /**
     * errmsg
     */
    private String errmsg;
}
