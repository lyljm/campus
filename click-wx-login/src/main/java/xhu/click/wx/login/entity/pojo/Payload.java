package xhu.click.wx.login.entity.pojo;

import lombok.Data;

@Data
public class Payload {
    private String iss;
    private String user;
    private String iat;
    private Long exp;
}
