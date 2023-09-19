package xhu.click.common.entity.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xhu.click.common.entity.enums.ResultCode;

@ApiModel("统一返回结果")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO {
    @ApiModelProperty("是否成功，1成功，0和其他数字失败")
    private Integer success;
    @ApiModelProperty("错误信息")
    private String errorMsg;
    @ApiModelProperty("数据")
    private Object data;

    public static ResultVO ok() {
        return new ResultVO(1, null, null);
    }

    public static ResultVO ok(Object data) {
        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static ResultVO error(ResultCode resultCode, Object data) {
        return new ResultVO(resultCode.getCode(), resultCode.getMsg(), data);
    }
}
