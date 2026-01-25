package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mes_supplier_material")
@Schema(description = "供应商物料关联实体")
public class MesSupplierMaterial {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
