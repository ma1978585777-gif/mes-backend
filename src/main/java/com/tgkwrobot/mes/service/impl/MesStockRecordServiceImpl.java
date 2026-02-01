package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterialStock;
import com.tgkwrobot.mes.entity.MesStockRecord;
import com.tgkwrobot.mes.mapper.MesStockRecordMapper;
import com.tgkwrobot.mes.service.IMesMaterialStockService;
import com.tgkwrobot.mes.service.IMesStockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tgkwrobot.mes.entity.MesWarehouse;
import com.tgkwrobot.mes.service.IMesWarehouseService;

@Service
@RequiredArgsConstructor
public class MesStockRecordServiceImpl extends ServiceImpl<MesStockRecordMapper, MesStockRecord> implements IMesStockRecordService {

    private final IMesMaterialStockService materialStockService;
    private final IMesWarehouseService warehouseService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createRecord(MesStockRecord stockRecord) {
        // 1. 生成单号 (如果未提供)
        if (!StringUtils.hasText(stockRecord.getRecordNo())) {
            stockRecord.setRecordNo("REC" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int)(Math.random() * 9000 + 1000));
        }

        // 填充仓库名称
        if (stockRecord.getWarehouseId() != null && !StringUtils.hasText(stockRecord.getWarehouseName())) {
            MesWarehouse warehouse = warehouseService.getById(stockRecord.getWarehouseId());
            if (warehouse != null) {
                stockRecord.setWarehouseName(warehouse.getWarehouseName());
            }
        }

        // 2. 保存记录
        boolean saved = this.save(stockRecord);
        if (!saved) {
            return false;
        }

        // 3. 更新库存
        MesMaterialStock stock = materialStockService.lambdaQuery()
                .eq(MesMaterialStock::getMaterialId, stockRecord.getMaterialId())
                .eq(MesMaterialStock::getWarehouseId, stockRecord.getWarehouseId())
                .one();

        if (stock == null) {
            // 如果库存记录不存在，且是入库，则新增
            if (stockRecord.getType() == 1) { // 1:入库
                stock = new MesMaterialStock();
                stock.setMaterialId(stockRecord.getMaterialId());
                stock.setMaterialCode(stockRecord.getMaterialCode());
                stock.setMaterialName(stockRecord.getMaterialName());
                stock.setWarehouseId(stockRecord.getWarehouseId());
                stock.setWarehouseName(stockRecord.getWarehouseName()); // 设置仓库名称
                stock.setQuantity(stockRecord.getQuantity());
                materialStockService.save(stock);
            } else {
                // 出库但无库存记录，抛出异常或处理
                throw new RuntimeException("库存不足，无法出库");
            }
        } else {
            // 更新库存
            BigDecimal currentQty = stock.getQuantity();
            if (stockRecord.getType() == 1) { // 入库
                stock.setQuantity(currentQty.add(stockRecord.getQuantity()));
            } else { // 出库
                if (currentQty.compareTo(stockRecord.getQuantity()) < 0) {
                    throw new RuntimeException("库存不足，无法出库");
                }
                stock.setQuantity(currentQty.subtract(stockRecord.getQuantity()));
            }
            // 确保仓库名称同步更新（防止仓库改名）
            if (StringUtils.hasText(stockRecord.getWarehouseName())) {
                stock.setWarehouseName(stockRecord.getWarehouseName());
            }
            materialStockService.updateById(stock);
        }

        return true;
    }
}
