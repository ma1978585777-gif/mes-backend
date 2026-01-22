package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesWorkstation;
import com.tgkwrobot.mes.mapper.MesWorkstationMapper;
import com.tgkwrobot.mes.service.IMesWorkstationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MesWorkstationServiceImpl extends ServiceImpl<MesWorkstationMapper, MesWorkstation> implements IMesWorkstationService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void scanMaterial(String workstationCode, String materialCode) {
        MesWorkstation workstation = this.getOne(new LambdaQueryWrapper<MesWorkstation>().eq(MesWorkstation::getCode, workstationCode));
        if (workstation == null) {
            throw new RuntimeException("工作台不存在");
        }
        
        // 记录上一个物料编码
        workstation.setLastMaterialCode(workstation.getCurrentMaterialCode());
        // 设置当前物料编码
        workstation.setCurrentMaterialCode(materialCode);
        
        this.updateById(workstation);
    }

    @Override
    public void bindFunction(Long workstationId, Long functionId) {
        MesWorkstation workstation = new MesWorkstation();
        workstation.setId(workstationId);
        workstation.setFunctionId(functionId);
        this.updateById(workstation);
    }

    @Override
    public void bindUser(Long workstationId, Long userId) {
        MesWorkstation workstation = new MesWorkstation();
        workstation.setId(workstationId);
        workstation.setUserId(userId);
        this.updateById(workstation);
    }
}
