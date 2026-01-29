package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mes_warehouse")
@Schema(description = "仓库实体")
public class MesWarehouse {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称")
    private String warehouseName;

    @Schema(description = "位置")
    private String location;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
