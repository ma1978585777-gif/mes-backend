package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesProcess;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;

@Tag(name = "流程管理")
@RestController
@RequestMapping("/mes/process")
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
    @PostMapping("/page")
    public Result<Page<MesProcess>> page(@RequestBody PageRequest<MesProcess> pageRequest) {
        Page<MesProcess> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesProcess process = pageRequest.getParams();
        return Result.success(processService.page(page, new LambdaQueryWrapper<MesProcess>()
                .like(process != null && StringUtils.hasText(process.getName()), MesProcess::getName, process != null ? process.getName() : null)
                .like(process != null && StringUtils.hasText(process.getCode()), MesProcess::getCode, process != null ? process.getCode() : null)
        ));
    }
}
