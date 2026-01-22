package com.tgkwrobot.mes.framework.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "通用返回结果")
public class Result<T> {

    @Schema(description = "状态码: 200成功")
    private Integer code;

    @Schema(description = "提示信息")
    private String msg;

    @Schema(description = "返回数据")
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.setCode(500);
        r.setMsg(msg);
        return r;
    }
}