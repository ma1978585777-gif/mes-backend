package com.tgkwrobot.mes.framework.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "分页请求参数")
public class PageRequest<T> {

    @Schema(description = "当前页码", defaultValue = "1")
    private long page = 1;

    @Schema(description = "每页数量", defaultValue = "10")
    private long size = 10;

    @Schema(description = "查询参数实体")
    private T params;
}
