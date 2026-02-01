package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterialStock;
import com.tgkwrobot.mes.mapper.MesMaterialStockMapper;
import com.tgkwrobot.mes.service.IMesMaterialStockService;
import com.tgkwrobot.mes.entity.MesWarehouse;
import com.tgkwrobot.mes.service.IMesWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MesMaterialStockServiceImpl extends ServiceImpl<MesMaterialStockMapper, MesMaterialStock> implements IMesMaterialStockService {

    private final IMesWarehouseService warehouseService;

    @Override
    public BigDecimal getQuantity(String warehouseCode, String materialCode) {
        MesWarehouse warehouse = warehouseService.lambdaQuery().eq(MesWarehouse::getWarehouseCode, warehouseCode).one();
        if (warehouse == null) {
            return BigDecimal.ZERO;
        }
        MesMaterialStock stock = lambdaQuery()
                .eq(MesMaterialStock::getWarehouseId, warehouse.getId())
                .eq(MesMaterialStock::getMaterialCode, materialCode)
                .one();
        return stock != null ? stock.getQuantity() : BigDecimal.ZERO;
    }
}
