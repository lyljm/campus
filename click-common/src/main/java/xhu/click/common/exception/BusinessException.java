package xhu.click.common.exception;

import lombok.Getter;
import xhu.click.common.entity.enums.ResultCode;

@Getter
public class BusinessException extends RuntimeException{
    private int code;
    private String msg;

    public BusinessException(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public BusinessException(ResultCode resultCode){
        this.code=resultCode.getCode();
        this.msg=resultCode.getMsg();
    }
}
