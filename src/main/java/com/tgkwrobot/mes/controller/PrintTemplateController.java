package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesPrintTemplate;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesPrintTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "打印模板管理")
@RestController
@RequestMapping("/mes/printTemplate")
@RequiredArgsConstructor
public class PrintTemplateController {

    private final IMesPrintTemplateService printTemplateService;

    @Operation(summary = "新增打印模板")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesPrintTemplate printTemplate) {
        return Result.success(printTemplateService.save(printTemplate));
    }

    @Operation(summary = "修改打印模板")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesPrintTemplate printTemplate) {
        return Result.success(printTemplateService.updateById(printTemplate));
    }

    @Operation(summary = "删除打印模板")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(printTemplateService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesPrintTemplate> get(@PathVariable Long id) {
        return Result.success(printTemplateService.getTemplateDetail(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<Page<MesPrintTemplate>> page(Page<MesPrintTemplate> page) {
        return Result.success((Page<MesPrintTemplate>) printTemplateService.getTemplatePage(page));
    }
}
