package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesMaterialStock;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.entity.MesStockRecord;
import com.tgkwrobot.mes.service.IMesMaterialStockService;
import com.tgkwrobot.mes.service.IMesStockRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "库存管理")
@RestController
@RequestMapping("/mes/stock")
@RequiredArgsConstructor
public class MaterialStockController {

    private final IMesMaterialStockService materialStockService;
    private final IMesStockRecordService stockRecordService;

    @Operation(summary = "入库")
    @PostMapping("/inbound")
    public Result<Boolean> inbound(@RequestBody MesStockRecord record) {
        record.setType(1); // 入库
        return Result.success(stockRecordService.createRecord(record));
    }

    @Operation(summary = "出库")
    @PostMapping("/outbound")
    public Result<Boolean> outbound(@RequestBody MesStockRecord record) {
        record.setType(2); // 出库
        return Result.success(stockRecordService.createRecord(record));
    }

    @Operation(summary = "分页查询库存")
    @PostMapping("/page")
    public Result<Page<MesMaterialStock>> page(@RequestBody PageRequest<MesMaterialStock> pageRequest) {
        Page<MesMaterialStock> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesMaterialStock params = pageRequest.getParams();
        assert params != null;
        return Result.success(materialStockService.page(page, new LambdaQueryWrapper<MesMaterialStock>()
                .like(StringUtils.hasText(params.getMaterialCode()), MesMaterialStock::getMaterialCode, params.getMaterialCode())
                .like(StringUtils.hasText(params.getMaterialName()), MesMaterialStock::getMaterialName, params.getMaterialName())
                .eq(params.getWarehouseId() != null, MesMaterialStock::getWarehouseId, params.getWarehouseId())
        ));
    }
}
