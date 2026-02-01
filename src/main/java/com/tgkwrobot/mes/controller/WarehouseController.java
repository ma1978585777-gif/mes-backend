package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesWarehouse;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesWarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "仓库管理")
@RestController
@RequestMapping("/mes/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final IMesWarehouseService warehouseService;

    @Operation(summary = "新增仓库")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesWarehouse warehouse) {
        return Result.success(warehouseService.save(warehouse));
    }

    @Operation(summary = "修改仓库")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesWarehouse warehouse) {
        return Result.success(warehouseService.updateById(warehouse));
    }

    @Operation(summary = "删除仓库")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(warehouseService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesWarehouse> get(@PathVariable Long id) {
        return Result.success(warehouseService.getById(id));
    }

    @Operation(summary = "查询所有仓库")
    @GetMapping("/list")
    public Result<List<MesWarehouse>> list() {
        return Result.success(warehouseService.list());
    }

    @Operation(summary = "分页查询")
    @PostMapping("/page")
    public Result<Page<MesWarehouse>> page(@RequestBody PageRequest<MesWarehouse> pageRequest) {
        Page<MesWarehouse> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesWarehouse params = pageRequest.getParams();

        return Result.success(warehouseService.page(page, new LambdaQueryWrapper<MesWarehouse>()
                // 使用 Optional 安全提取值，避免在 params 为 null 时调用其 getter 方法
                .like(params != null && StringUtils.hasText(params.getWarehouseCode()),
                        MesWarehouse::getWarehouseCode,
                        Optional.ofNullable(params).map(MesWarehouse::getWarehouseCode).orElse(null))

                .like(params != null && StringUtils.hasText(params.getWarehouseName()),
                        MesWarehouse::getWarehouseName,
                        Optional.ofNullable(params).map(MesWarehouse::getWarehouseName).orElse(null))
        ));
    }
}
