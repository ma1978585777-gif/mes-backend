package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mes_qc_record")
@Schema(description = "质检记录实体")
public class MesQcRecord {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "SN码")
    private String snCode;

    @Schema(description = "批次号")
    private String batchNo;

    @Schema(description = "质检员")
    private String qcInspector;

    @Schema(description = "电工")
    private String electrician;

    @Schema(description = "钳工")
    private String fitter;

    @Schema(description = "生产日期/创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}