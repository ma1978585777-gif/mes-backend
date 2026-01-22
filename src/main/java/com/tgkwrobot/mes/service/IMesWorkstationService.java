package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesWorkstation;

public interface IMesWorkstationService extends IService<MesWorkstation> {
    
    /**
     * 扫描物料
     * @param workstationCode 工作台编码
     * @param materialCode 物料编码
     */
    void scanMaterial(String workstationCode, String materialCode);

    /**
     * 绑定职能
     * @param workstationId 工作台ID
     * @param functionId 职能ID
     */
    void bindFunction(Long workstationId, Long functionId);

    /**
     * 绑定用户
     * @param workstationId 工作台ID
     * @param userId 用户ID
     */
    void bindUser(Long workstationId, Long userId);
}
