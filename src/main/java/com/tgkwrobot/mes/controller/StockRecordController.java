package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesStockRecord;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesStockRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "出入库记录")
@RestController
@RequestMapping("/mes/stock-record")
@RequiredArgsConstructor
public class StockRecordController {

    private final IMesStockRecordService stockRecordService;

    @Operation(summary = "创建出入库记录")
    @PostMapping
    public Result<Boolean> create(@RequestBody MesStockRecord stockRecord) {
        return Result.success(stockRecordService.createRecord(stockRecord));
    }

    @Operation(summary = "分页查询记录")
    @PostMapping("/page")
    public Result<Page<MesStockRecord>> page(@RequestBody PageRequest<MesStockRecord> pageRequest) {
        Page<MesStockRecord> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesStockRecord params = pageRequest.getParams();
        return Result.success(stockRecordService.page(page, new LambdaQueryWrapper<MesStockRecord>()
                .eq(params != null && params.getType() != null, MesStockRecord::getType, params != null ? params.getType() : null)
                .like(params != null && StringUtils.hasText(params.getMaterialCode()), MesStockRecord::getMaterialCode, params.getMaterialCode())
                .like(StringUtils.hasText(params.getBatchNo()), MesStockRecord::getBatchNo, params.getBatchNo())
                .eq(params.getWarehouseId() != null, MesStockRecord::getWarehouseId, params.getWarehouseId())
                .orderByDesc(MesStockRecord::getCreateTime)
        ));
    }
}
