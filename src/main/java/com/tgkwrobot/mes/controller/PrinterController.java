package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesPrinter;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesPrinterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;

@Tag(name = "打印机管理")
@RestController
@RequestMapping("/mes/printer")
@RequiredArgsConstructor
public class PrinterController {

    private final IMesPrinterService printerService;

    @Operation(summary = "新增打印机")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesPrinter printer) {
        return Result.success(printerService.save(printer));
    }

    @Operation(summary = "修改打印机")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesPrinter printer) {
        return Result.success(printerService.updateById(printer));
    }

    @Operation(summary = "删除打印机")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(printerService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesPrinter> get(@PathVariable Long id) {
        return Result.success(printerService.getById(id));
    }

    @Operation(summary = "分页查询")
    @PostMapping("/page")
    public Result<Page<MesPrinter>> page(@RequestBody PageRequest<MesPrinter> pageRequest) {
        Page<MesPrinter> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesPrinter printer = pageRequest.getParams();
        return Result.success(printerService.page(page, new LambdaQueryWrapper<MesPrinter>()
                .like(printer != null && StringUtils.hasText(printer.getName()), MesPrinter::getName, printer != null ? printer.getName() : null)
                .like(printer != null && StringUtils.hasText(printer.getCode()), MesPrinter::getCode, printer != null ? printer.getCode() : null)
                .eq(printer != null && printer.getStatus() != null, MesPrinter::getStatus, printer != null ? printer.getStatus() : null)
        ));
    }
}
