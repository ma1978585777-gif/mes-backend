package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesFunction;
import com.tgkwrobot.mes.mapper.MesFunctionMapper;
import com.tgkwrobot.mes.service.IMesFunctionService;
import org.springframework.stereotype.Service;

@Service
public class MesFunctionServiceImpl extends ServiceImpl<MesFunctionMapper, MesFunction> implements IMesFunctionService {
}
