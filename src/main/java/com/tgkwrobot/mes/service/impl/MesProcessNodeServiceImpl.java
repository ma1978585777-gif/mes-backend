package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesProcessNode;
import com.tgkwrobot.mes.mapper.MesProcessNodeMapper;
import com.tgkwrobot.mes.service.IMesProcessNodeService;
import org.springframework.stereotype.Service;

@Service
public class MesProcessNodeServiceImpl extends ServiceImpl<MesProcessNodeMapper, MesProcessNode> implements IMesProcessNodeService {
}
