package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesMaterialType;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesMaterialTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;

@Tag(name = "物料类型管理")
@RestController
@RequestMapping("/mes/materialType")
@RequiredArgsConstructor
public class MaterialTypeController {

    private final IMesMaterialTypeService materialTypeService;

    @Operation(summary = "新增物料类型")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesMaterialType materialType) {
        return Result.success(materialTypeService.save(materialType));
    }

    @Operation(summary = "修改物料类型")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesMaterialType materialType) {
        return Result.success(materialTypeService.updateById(materialType));
    }

    @Operation(summary = "删除物料类型")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(materialTypeService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesMaterialType> get(@PathVariable Long id) {
        return Result.success(materialTypeService.getById(id));
    }

    @Operation(summary = "分页查询")
    @PostMapping("/page")
    public Result<Page<MesMaterialType>> page(@RequestBody PageRequest<MesMaterialType> pageRequest) {
        Page<MesMaterialType> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesMaterialType materialType = pageRequest.getParams();
        return Result.success(materialTypeService.page(page, new LambdaQueryWrapper<MesMaterialType>()
                .like(materialType != null && StringUtils.hasText(materialType.getName()), MesMaterialType::getName, materialType != null ? materialType.getName() : null)
                .like(materialType != null && StringUtils.hasText(materialType.getCode()), MesMaterialType::getCode, materialType != null ? materialType.getCode() : null)
        ));
    }
}
