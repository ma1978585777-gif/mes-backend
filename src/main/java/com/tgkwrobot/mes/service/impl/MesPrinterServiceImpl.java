package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesPrinter;
import com.tgkwrobot.mes.mapper.MesPrinterMapper;
import com.tgkwrobot.mes.service.IMesPrinterService;
import org.springframework.stereotype.Service;

@Service
public class MesPrinterServiceImpl extends ServiceImpl<MesPrinterMapper, MesPrinter> implements IMesPrinterService {
}
