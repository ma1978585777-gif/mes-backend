package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesProcess;
import com.tgkwrobot.mes.entity.MesProcessNode;
import com.tgkwrobot.mes.mapper.MesProcessMapper;
import com.tgkwrobot.mes.service.IMesProcessNodeService;
import com.tgkwrobot.mes.service.IMesProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesProcessServiceImpl extends ServiceImpl<MesProcessMapper, MesProcess> implements IMesProcessService {

    private final IMesProcessNodeService processNodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createProcess(MesProcess process) {
        this.save(process);
        
        if (process.getNodes() != null && !process.getNodes().isEmpty()) {
            for (MesProcessNode node : process.getNodes()) {
                node.setProcessId(process.getId());
            }
            processNodeService.saveBatch(process.getNodes());
        }
        return process.getId();
    }

    @Override
    public MesProcess getProcessDetail(Long id) {
        MesProcess process = this.getById(id);
        if (process != null) {
            List<MesProcessNode> nodes = processNodeService.list(new LambdaQueryWrapper<MesProcessNode>()
                    .eq(MesProcessNode::getProcessId, id)
                    .orderByAsc(MesProcessNode::getSequence));
            process.setNodes(nodes);
        }
        return process;
    }
}
