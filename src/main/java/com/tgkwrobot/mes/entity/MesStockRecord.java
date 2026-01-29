package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("mes_stock_record")
@Schema(description = "出入库记录实体")
public class MesStockRecord {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "记录单号")
    private String recordNo;

    @Schema(description = "类型 1:入库 2:出库")
    private Integer type;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "物料编码")
    private String materialCode;

    @Schema(description = "物料名称")
    private String materialName;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "数量")
    private BigDecimal quantity;

    @Schema(description = "批次号")
    private String batchNo;

    @Schema(description = "操作人")
    private String operator;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;
}
