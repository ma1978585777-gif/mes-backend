package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mes_material")
@Schema(description = "物料实体")
public class MesMaterial {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "物料编码")
    private String code;

    @Schema(description = "物料名称")
    private String name;

    @Schema(description = "物料类型ID")
    private Long typeId;

    @Schema(description = "供应商")
    private String supplier;

    @Schema(description = "规格型号")
    private String spec;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
