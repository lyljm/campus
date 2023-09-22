package xhu.click.common.entity.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xhu.click.common.entity.enums.ResultCode;

@ApiModel("统一返回结果")
@Data
public class ResultVO<T> {
    @ApiModelProperty("是否成功，1成功，0和其他数字失败")
    private Integer success;
    @ApiModelProperty("信息")
    private String Msg;
    @ApiModelProperty("数据")
    private T data;

    /**
     * 不允许直接创建ResultVo
     */
    private ResultVO(){

    }

    private ResultVO(Integer success, String errorMsg, T data) {
        this.success = success;
        this.Msg = errorMsg;
        this.data = data;
    }

    public static ResultVO ok() {
        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static ResultVO ok(Object data) {
        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static ResultVO error(int code,String errorMsg) {
        return new ResultVO(code, errorMsg, null);
    }

    public static ResultVO error(int code,String errorMsg,Object data) {
        return new ResultVO(code, errorMsg, data);
    }

    public static ResultVO error(ResultCode resultCode) {
        return new ResultVO(resultCode.getCode(), resultCode.getMsg(), null);
    }
    public static ResultVO error(ResultCode resultCode, Object data) {
        return new ResultVO(resultCode.getCode(), resultCode.getMsg(), data);
    }
}
