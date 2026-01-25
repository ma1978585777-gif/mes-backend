package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesQcRecord;
import com.tgkwrobot.mes.mapper.MesQcRecordMapper;
import com.tgkwrobot.mes.service.IMesQcRecordService;
import org.springframework.stereotype.Service;

@Service
public class MesQcRecordServiceImpl extends ServiceImpl<MesQcRecordMapper, MesQcRecord> implements IMesQcRecordService {
}