package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesUserFunction;
import com.tgkwrobot.mes.mapper.MesUserFunctionMapper;
import com.tgkwrobot.mes.service.IMesUserFunctionService;
import org.springframework.stereotype.Service;

@Service
public class MesUserFunctionServiceImpl extends ServiceImpl<MesUserFunctionMapper, MesUserFunction> implements IMesUserFunctionService {
}
