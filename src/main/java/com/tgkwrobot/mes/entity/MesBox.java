package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("mes_box")
@Schema(description = "箱信息实体")
public class MesBox {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "箱编码")
    private String boxCode;

    @Schema(description = "项目号")
    private String projectNo;

    @Schema(description = "设备号")
    private String deviceNo;

    @Schema(description = "箱拖号")
    private String palletNo;

    @Schema(description = "集装箱号")
    private String containerNo;

    @Schema(description = "包装时间")
    private Date packTime;

    @Schema(description = "复核人")
    private String reviewer;

    @Schema(description = "整托净重")
    private BigDecimal netWeight;

    @Schema(description = "整托毛重")
    private BigDecimal grossWeight;

    @Schema(description = "长")
    private String length;

    @Schema(description = "宽")
    private String width;

    @Schema(description = "高")
    private String height;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}