package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterialStock;
import com.tgkwrobot.mes.mapper.MesMaterialStockMapper;
import com.tgkwrobot.mes.service.IMesMaterialStockService;
import org.springframework.stereotype.Service;

@Service
public class MesMaterialStockServiceImpl extends ServiceImpl<MesMaterialStockMapper, MesMaterialStock> implements IMesMaterialStockService {
}
