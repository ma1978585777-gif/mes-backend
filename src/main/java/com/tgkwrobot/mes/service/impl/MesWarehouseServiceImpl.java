package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesWarehouse;
import com.tgkwrobot.mes.mapper.MesWarehouseMapper;
import com.tgkwrobot.mes.service.IMesWarehouseService;
import org.springframework.stereotype.Service;

@Service
public class MesWarehouseServiceImpl extends ServiceImpl<MesWarehouseMapper, MesWarehouse> implements IMesWarehouseService {
}
