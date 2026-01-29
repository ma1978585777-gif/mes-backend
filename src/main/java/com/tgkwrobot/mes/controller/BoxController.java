package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesBox;
import com.tgkwrobot.mes.entity.MesBoxDetail;
import com.tgkwrobot.mes.framework.web.PageRequest;
import com.tgkwrobot.mes.framework.web.Result;
import com.tgkwrobot.mes.service.CodeRuleService;
import com.tgkwrobot.mes.service.IMesBoxDetailService;
import com.tgkwrobot.mes.service.IMesBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;

@Tag(name = "打包管理")
@RestController
@RequestMapping("/mes/box")
@RequiredArgsConstructor
public class BoxController {

    private final IMesBoxService boxService;
    private final IMesBoxDetailService boxDetailService;
    private final CodeRuleService codeRuleService;

    @Operation(summary = "创建箱信息")
    @PostMapping
    public Result<MesBox> createBox(@RequestBody MesBox box) {
        box.setBoxCode(codeRuleService.generateSn());
        boxService.save(box);
        box = boxService.getById(box.getId());
        return Result.success(box);
    }

    @Operation(summary = "修改箱信息")
    @PutMapping
    public Result<Boolean> updateBox(@RequestBody MesBox box) {
        return Result.success(boxService.updateById(box));
    }

    @Operation(summary = "分页查询箱列表")
    @PostMapping("/page")
    public Result<Page<MesBox>> page(@RequestBody PageRequest<MesBox> pageRequest) {
        Page<MesBox> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        MesBox box = pageRequest.getParams();
        return Result.success(boxService.page(page, new LambdaQueryWrapper<MesBox>()
                .like(box != null && StringUtils.hasText(box.getBoxCode()), MesBox::getBoxCode, box != null ? box.getBoxCode() : null)
                .like(box != null && StringUtils.hasText(box.getProjectNo()), MesBox::getProjectNo, box != null ? box.getProjectNo() : null)
                .like(box != null && StringUtils.hasText(box.getPalletNo()), MesBox::getPalletNo, box != null ? box.getPalletNo() : null)
                .like(box != null && StringUtils.hasText(box.getContainerNo()), MesBox::getContainerNo, box != null ? box.getContainerNo() : null)
        ));
    }

    @Operation(summary = "查询箱详情")
    @GetMapping("/{id}")
    public Result<MesBox> get(@PathVariable Long id) {
        return Result.success(boxService.getById(id));
    }

    @Operation(summary = "添加箱明细")
    @PostMapping("/detail")
    public Result<Boolean> addDetail(@RequestBody MesBoxDetail detail) {
        return Result.success(boxDetailService.save(detail));
    }

    @Operation(summary = "批量添加箱明细")
    @PostMapping("/detail/batch")
    public Result<Boolean> addBatchDetails(@RequestBody List<MesBoxDetail> details) {
        return Result.success(boxDetailService.saveBatchDetails(details));
    }

    @Operation(summary = "根据箱编码查询明细")
    @GetMapping("/detail/list")
    public Result<List<MesBoxDetail>> getDetails(@RequestParam String boxCode) {
        return Result.success(boxDetailService.getDetailsByBoxCode(boxCode));
    }
    
    @Operation(summary = "删除箱明细")
    @DeleteMapping("/detail/{id}")
    public Result<Boolean> removeDetail(@PathVariable Long id) {
        return Result.success(boxDetailService.removeById(id));
    }

    @Operation(summary = "根据boxCode查询箱信息")
    @GetMapping("/code/{boxCode}")
    public Result<MesBox> getBoxByCode(@PathVariable String boxCode) {
        return Result.success(boxService.getBoxByCode(boxCode));
    }
}