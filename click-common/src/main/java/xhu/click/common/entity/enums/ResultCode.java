package xhu.click.common.entity.enums;

import lombok.Getter;

/**
 * 响应结果码
 */
@Getter
public enum ResultCode {

    SUCCESS(1, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    FILE_UPLOAD_ERROR(10003,"文件上传错误"),

    FILE_DOWNLOAD_ERROR(10004,"文件下载错误"),

    FILE_DELETE_ERROR(10005,"文件删除错误"),

    ERROR(5000, "未知错误");



    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}