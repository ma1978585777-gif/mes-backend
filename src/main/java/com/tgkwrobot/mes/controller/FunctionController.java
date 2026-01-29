package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesFunction;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesFunctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;

@Tag(name = "职能管理")
@RestController
@RequestMapping("/mes/function")
@RequiredArgsConstructor
public class FunctionController {

    private final IMesFunctionService functionService;

    @Operation(summary = "新增职能")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesFunction function) {
        return Result.success(functionService.save(function));
    }

    @Operation(summary = "修改职能")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesFunction function) {
        return Result.success(functionService.updateById(function));
    }

    @Operation(summary = "删除职能")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(functionService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesFunction> get(@PathVariable Long id) {
        return Result.success(functionService.getById(id));
    }

    @Operation(summary = "分页查询")
    @PostMapping("/page")
    public Result<Page<MesFunction>> page(@RequestBody PageRequest<MesFunction> pageRequest) {
        Page<MesFunction> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesFunction function = pageRequest.getParams();
        return Result.success(functionService.page(page, new LambdaQueryWrapper<MesFunction>()
                .like(function != null && StringUtils.hasText(function.getName()), MesFunction::getName, function != null ? function.getName() : null)
                .like(function != null && StringUtils.hasText(function.getCode()), MesFunction::getCode, function != null ? function.getCode() : null)
        ));
    }
}
