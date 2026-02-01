package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesMaterialStock;

import java.math.BigDecimal;

public interface IMesMaterialStockService extends IService<MesMaterialStock> {
    /**
     * 根据仓库号和物料编码查询库存数量
     * @param warehouseCode 仓库号
     * @param materialCode 物料编码
     * @return 库存数量
     */
    BigDecimal getQuantity(String warehouseCode, String materialCode);
}
