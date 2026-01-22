package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesProcess;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "流程管理")
@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {

    private final IMesProcessService processService;

    @Operation(summary = "创建流程")
    @PostMapping
    public Result<Long> createProcess(@RequestBody MesProcess process) {
        return Result.success(processService.createProcess(process));
    }

    @Operation(summary = "修改流程")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesProcess process) {
        return Result.success(processService.updateById(process));
    }

    @Operation(summary = "删除流程")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(processService.removeById(id));
    }

    @Operation(summary = "根据ID查询（包含节点）")
    @GetMapping("/{id}")
    public Result<MesProcess> get(@PathVariable Long id) {
        return Result.success(processService.getProcessDetail(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<Page<MesProcess>> page(Page<MesProcess> page) {
        return Result.success(processService.page(page));
    }
}
