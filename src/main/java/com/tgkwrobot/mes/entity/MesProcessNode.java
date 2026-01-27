package com.tgkwrobot.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mes_process_node")
@Schema(description = "流程节点实体")
public class MesProcessNode {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "流程ID")
    private Long processId;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "绑定职能ID")
    private Long functionId;

    @Schema(description = "顺序")
    private Integer sequence;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;
}
