package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterial;
import com.tgkwrobot.mes.mapper.MesMaterialMapper;
import com.tgkwrobot.mes.service.IMesMaterialService;
import org.springframework.stereotype.Service;

@Service
public class MesMaterialServiceImpl extends ServiceImpl<MesMaterialMapper, MesMaterial> implements IMesMaterialService {
}
