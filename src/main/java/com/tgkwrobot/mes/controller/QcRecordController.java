package com.tgkwrobot.mes.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tgkwrobot.mes.entity.MesMaterial;
import com.tgkwrobot.mes.entity.MesQcRecord;
import com.tgkwrobot.mes.framework.web.PageRequest;
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
    public Result<MesQcRecord> save(@RequestBody MesQcRecord qcRecord) {
        qcRecordService.save(qcRecord);
        qcRecord = qcRecordService.getById(qcRecord.getId());
        return Result.success(qcRecord);
    }

    @Operation(summary = "根据SN码更新状态为已质检")
    @PostMapping("/updateStatusBySnCode")
    public Result<Boolean> updateStatusBySnCode(@RequestParam String snCode) {
        return Result.success(qcRecordService.updateStatusBySnCode(snCode));
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
        return Result.success(qcRecordService.getQcRecordDetail(id));
    }

    @Operation(summary = "根据SN码查询")
    @GetMapping("/sn/{snCode}")
    public Result<MesQcRecord> getBySnCode(@PathVariable String snCode) {
        return Result.success(qcRecordService.getQcRecordBySnCode(snCode));
    }

    @Operation(summary = "根据SN码查询物料信息")
    @GetMapping("/sn/{snCode}/material")
    public Result<MesMaterial> getMaterialBySnCode(@PathVariable String snCode) {
        return Result.success(qcRecordService.getMaterialBySnCode(snCode));
    }

    @Operation(summary = "分页查询")
    @PostMapping("/page")
    public Result<Page<MesQcRecord>> page(@RequestBody PageRequest<MesQcRecord> pageRequest) {
        Page<MesQcRecord> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        return Result.success((Page<MesQcRecord>) qcRecordService.getQcRecordPage(page, pageRequest.getParams()));
    }
}