package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesWorkstation;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesWorkstationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "工作台管理")
@RestController
@RequestMapping("/workstation")
@RequiredArgsConstructor
public class WorkstationController {

    private final IMesWorkstationService workstationService;

    @Operation(summary = "扫描物料")
    @PostMapping("/scan")
    public Result<Void> scanMaterial(@RequestParam String workstationCode, @RequestParam String materialCode) {
        workstationService.scanMaterial(workstationCode, materialCode);
        return Result.success();
    }

    @Operation(summary = "绑定职能")
    @PostMapping("/{id}/bindFunction")
    public Result<Void> bindFunction(@PathVariable Long id, @RequestParam Long functionId) {
        workstationService.bindFunction(id, functionId);
        return Result.success();
    }

    @Operation(summary = "绑定用户")
    @PostMapping("/{id}/bindUser")
    public Result<Void> bindUser(@PathVariable Long id, @RequestParam Long userId) {
        workstationService.bindUser(id, userId);
        return Result.success();
    }

    @Operation(summary = "新增工作台")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesWorkstation workstation) {
        return Result.success(workstationService.save(workstation));
    }

    @Operation(summary = "修改工作台")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesWorkstation workstation) {
        return Result.success(workstationService.updateById(workstation));
    }

    @Operation(summary = "删除工作台")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(workstationService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesWorkstation> get(@PathVariable Long id) {
        return Result.success(workstationService.getById(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<Page<MesWorkstation>> page(Page<MesWorkstation> page) {
        return Result.success(workstationService.page(page));
    }
}
