package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesMaterial;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;

@Tag(name = "物料管理")
@RestController
@RequestMapping("/mes/material")
@RequiredArgsConstructor
public class MaterialController {

    private final IMesMaterialService materialService;

    @Operation(summary = "新增物料")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesMaterial material) {
        return Result.success(materialService.save(material));
    }

    @Operation(summary = "修改物料")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesMaterial material) {
        return Result.success(materialService.updateById(material));
    }

    @Operation(summary = "删除物料")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(materialService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesMaterial> get(@PathVariable Long id) {
        return Result.success(materialService.getById(id));
    }

    @Operation(summary = "分页查询")
    @PostMapping("/page")
    public Result<Page<MesMaterial>> page(@RequestBody PageRequest<MesMaterial> pageRequest) {
        Page<MesMaterial> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesMaterial material = pageRequest.getParams();
        return Result.success(materialService.page(page, new LambdaQueryWrapper<MesMaterial>()
                .like(material != null && StringUtils.hasText(material.getName()), MesMaterial::getName, material != null ? material.getName() : null)
                .like(material != null && StringUtils.hasText(material.getCode()), MesMaterial::getCode, material != null ? material.getCode() : null)
                .eq(material != null && material.getTypeId() != null, MesMaterial::getTypeId, material != null ? material.getTypeId() : null)
        ));
    }
}
