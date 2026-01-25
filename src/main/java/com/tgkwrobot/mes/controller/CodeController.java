package com.tgkwrobot.mes.controller;

import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.CodeRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mes/code")
@Tag(name = "编码生成", description = "编码生成")
public class CodeController {

    @Resource
    private CodeRuleService codeRuleService;

    @GetMapping("/sn")
    @Operation(summary = "生成SN码", description = "格式: SN + 时间(14位) + 流水(4位)")
    public Result<String> genSn() {
        return Result.success(codeRuleService.generateSn());
    }

    @GetMapping("/batch")
    @Operation(summary = "生成批次号", description = "格式: 工厂-日期-时分-流水")
    public Result<String> genBatch(@RequestParam String factoryCode) {
        return Result.success(codeRuleService.generateBatch(factoryCode));
    }
}