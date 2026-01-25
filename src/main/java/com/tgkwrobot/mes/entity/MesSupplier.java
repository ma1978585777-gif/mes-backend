package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("mes_supplier")
@Schema(description = "供应商实体")
public class MesSupplier {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "供应商编码")
    private String code;

    @Schema(description = "供应商名称")
    private String name;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    
    @TableField(exist = false)
    @Schema(description = "关联物料ID列表")
    private List<Long> materialIds;

    @TableField(exist = false)
    @Schema(description = "关联物料名称列表")
    private List<String> materialNames;
}
