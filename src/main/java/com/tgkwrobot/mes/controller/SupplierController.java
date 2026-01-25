package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesSupplier;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesSupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "供应商管理")
@RestController
@RequestMapping("/mes/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final IMesSupplierService supplierService;

    @Operation(summary = "新增供应商")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesSupplier supplier) {
        return Result.success(supplierService.createSupplier(supplier));
    }

    @Operation(summary = "修改供应商")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesSupplier supplier) {
        return Result.success(supplierService.updateSupplier(supplier));
    }

    @Operation(summary = "绑定物料")
    @PostMapping("/{id}/bindMaterials")
    public Result<Void> bindMaterials(@PathVariable Long id, @RequestBody List<Long> materialIds) {
        supplierService.bindMaterials(id, materialIds);
        return Result.success();
    }

    @Operation(summary = "删除供应商")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(supplierService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesSupplier> get(@PathVariable Long id) {
        return Result.success(supplierService.getSupplierDetail(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<Page<MesSupplier>> page(Page<MesSupplier> page) {
        return Result.success((Page<MesSupplier>) supplierService.getSupplierPage(page));
    }
}
