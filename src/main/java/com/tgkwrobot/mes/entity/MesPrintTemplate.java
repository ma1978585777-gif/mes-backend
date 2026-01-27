package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mes_print_template")
@Schema(description = "打印模板实体")
public class MesPrintTemplate {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "模板编码")
    private String code;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "关联物料ID")
    private Long materialId;

    @TableField(exist = false)
    @Schema(description = "关联物料名称")
    private String materialName;

    @Schema(description = "模板配置(JSON)")
    private String configJson;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
