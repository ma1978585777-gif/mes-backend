package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesBox;

public interface IMesBoxService extends IService<MesBox> {
    MesBox getBoxByCode(String boxCode);
}