package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesQcRecord;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesQcRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "质检记录管理")
@RestController
@RequestMapping("/mes/qcRecord")
@RequiredArgsConstructor
public class QcRecordController {

    private final IMesQcRecordService qcRecordService;

    @Operation(summary = "新增记录")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesQcRecord qcRecord) {
        return Result.success(qcRecordService.save(qcRecord));
    }

    @Operation(summary = "修改记录")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesQcRecord qcRecord) {
        return Result.success(qcRecordService.updateById(qcRecord));
    }

    @Operation(summary = "删除记录")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(qcRecordService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesQcRecord> get(@PathVariable Long id) {
        return Result.success(qcRecordService.getById(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<Page<MesQcRecord>> page(Page<MesQcRecord> page) {
        return Result.success(qcRecordService.page(page));
    }
}