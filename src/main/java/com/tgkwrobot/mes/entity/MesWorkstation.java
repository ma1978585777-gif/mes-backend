package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mes_workstation")
@Schema(description = "工作台实体")
public class MesWorkstation {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "工作台编码")
    private String code;

    @Schema(description = "工作台名称")
    private String name;

    @Schema(description = "绑定职能ID")
    private Long functionId;

    @Schema(description = "绑定用户ID")
    private Long userId;

    @Schema(description = "当前物料编码")
    private String currentMaterialCode;

    @Schema(description = "上一个物料编码")
    private String lastMaterialCode;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
