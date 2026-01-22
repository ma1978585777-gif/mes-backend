package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterialType;
import com.tgkwrobot.mes.mapper.MesMaterialTypeMapper;
import com.tgkwrobot.mes.service.IMesMaterialTypeService;
import org.springframework.stereotype.Service;

@Service
public class MesMaterialTypeServiceImpl extends ServiceImpl<MesMaterialTypeMapper, MesMaterialType> implements IMesMaterialTypeService {
}
