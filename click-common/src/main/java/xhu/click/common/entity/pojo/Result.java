package xhu.click.common.entity.pojo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("返回结果")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @ApiModelProperty("是否成功，1成功，0和其他数字失败")
    private Boolean success;
    @ApiModelProperty("错误信息")
    private String errorMsg;
    @ApiModelProperty("数据")
    private Object data;

    public static Result ok(){
        return new Result(true, null, null);
    }
    public static Result ok(Object data){
        return new Result(true, null, data);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(true, null, data);
    }
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null);
    }
}
