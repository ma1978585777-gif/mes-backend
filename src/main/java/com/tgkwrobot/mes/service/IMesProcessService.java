package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesProcess;

public interface IMesProcessService extends IService<MesProcess> {
    
    /**
     * 创建流程（包含节点）
     * @param process 流程信息
     * @return 创建后的流程ID
     */
    Long createProcess(MesProcess process);
    
    /**
     * 获取流程详情（包含节点）
     * @param id 流程ID
     * @return 流程详情
     */
    MesProcess getProcessDetail(Long id);
}
