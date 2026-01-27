package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesBoxDetail;

import java.util.List;

public interface IMesBoxDetailService extends IService<MesBoxDetail> {
    
    /**
     * 根据箱编码获取明细
     * @param boxCode 箱编码
     * @return 明细列表
     */
    List<MesBoxDetail> getDetailsByBoxCode(String boxCode);
}