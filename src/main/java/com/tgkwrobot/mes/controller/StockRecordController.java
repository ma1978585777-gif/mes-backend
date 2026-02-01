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

        // 【核心改进】如果 params 为空，直接给一个空的实体对象，彻底规避后续的 NPE
        MesStockRecord params = pageRequest.getParams() != null ? pageRequest.getParams() : new MesStockRecord();

        return Result.success(stockRecordService.page(page, new LambdaQueryWrapper<MesStockRecord>()
                // 既然 params 保证不为空，直接判断属性即可，代码整洁多了
                .eq(params.getType() != null, MesStockRecord::getType, params.getType())
                .like(StringUtils.hasText(params.getMaterialCode()), MesStockRecord::getMaterialCode, params.getMaterialCode())
                .like(StringUtils.hasText(params.getBatchNo()), MesStockRecord::getBatchNo, params.getBatchNo())
                .eq(params.getWarehouseId() != null, MesStockRecord::getWarehouseId, params.getWarehouseId())
                .orderByDesc(MesStockRecord::getCreateTime)
        ));
    }
}
