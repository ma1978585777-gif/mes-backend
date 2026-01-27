package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesBox;
import com.tgkwrobot.mes.mapper.MesBoxMapper;
import com.tgkwrobot.mes.service.IMesBoxService;
import org.springframework.stereotype.Service;

@Service
public class MesBoxServiceImpl extends ServiceImpl<MesBoxMapper, MesBox> implements IMesBoxService {
}