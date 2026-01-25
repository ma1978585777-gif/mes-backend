package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesUser;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.IMesUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/mes/user")
@RequiredArgsConstructor
public class UserController {

    private final IMesUserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<MesUser> login(@RequestParam String empId) {
        return Result.success(userService.login(empId));
    }

    @Operation(summary = "绑定职能")
    @PostMapping("/{id}/bindFunctions")
    public Result<Void> bindFunctions(@PathVariable Long id, @RequestBody List<Long> functionIds) {
        userService.bindFunctions(id, functionIds);
        return Result.success();
    }

    @Operation(summary = "新增用户")
    @PostMapping
    public Result<Boolean> save(@RequestBody MesUser user) {
        return Result.success(userService.createUser(user));
    }

    @Operation(summary = "修改用户")
    @PutMapping
    public Result<Boolean> update(@RequestBody MesUser user) {
        return Result.success(userService.updateUser(user));
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.success(userService.removeById(id));
    }

    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public Result<MesUser> get(@PathVariable Long id) {
        return Result.success(userService.getUserDetail(id));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<Page<MesUser>> page(Page<MesUser> page) {
        return Result.success((Page<MesUser>) userService.getUserPage(page));
    }
}
