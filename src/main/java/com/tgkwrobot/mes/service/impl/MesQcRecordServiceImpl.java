package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterial;
import com.tgkwrobot.mes.entity.MesQcRecord;
import com.tgkwrobot.mes.mapper.MesQcRecordMapper;
import com.tgkwrobot.mes.service.IMesMaterialService;
import com.tgkwrobot.mes.service.IMesQcRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MesQcRecordServiceImpl extends ServiceImpl<MesQcRecordMapper, MesQcRecord> implements IMesQcRecordService {

    private final IMesMaterialService materialService;

    @Override
    public IPage<MesQcRecord> getQcRecordPage(IPage<MesQcRecord> page) {
        IPage<MesQcRecord> result = this.page(page);
        populateMaterialName(result.getRecords());
        return result;
    }

    @Override
    public MesQcRecord getQcRecordDetail(Long id) {
        MesQcRecord record = this.getById(id);
        if (record != null) {
            populateMaterialName(Collections.singletonList(record));
        }
        return record;
    }

    @Override
    public boolean save(MesQcRecord entity) {
        if (entity.getStatus() == null) {
            entity.setStatus(0); // 默认未质检
        }
        return super.save(entity);
    }

    @Override
    public boolean updateStatusBySnCode(String snCode) {
        return this.update(new LambdaUpdateWrapper<MesQcRecord>()
                .eq(MesQcRecord::getSnCode, snCode)
                .set(MesQcRecord::getStatus, 1));
    }

    @Override
    public MesQcRecord getQcRecordBySnCode(String snCode) {
        MesQcRecord record = this.getOne(new LambdaQueryWrapper<MesQcRecord>().eq(MesQcRecord::getSnCode, snCode));
        if (record != null) {
            populateMaterialName(Collections.singletonList(record));
        }
        return record;
    }

    @Override
    public MesMaterial getMaterialBySnCode(String snCode) {
        MesQcRecord record = this.getOne(new LambdaQueryWrapper<MesQcRecord>().eq(MesQcRecord::getSnCode, snCode));
        if (record == null || record.getMaterialId() == null) {
            return null;
        }
        return materialService.getById(record.getMaterialId());
    }

    private void populateMaterialName(List<MesQcRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }

        List<Long> materialIds = records.stream()
                .map(MesQcRecord::getMaterialId)
                .filter(id -> id != null)
                .distinct()
                .collect(Collectors.toList());

        if (materialIds.isEmpty()) {
            return;
        }

        Map<Long, String> materialNameMap = materialService.listByIds(materialIds).stream()
                .collect(Collectors.toMap(MesMaterial::getId, MesMaterial::getName));

        for (MesQcRecord record : records) {
            if (record.getMaterialId() != null) {
                record.setMaterialName(materialNameMap.get(record.getMaterialId()));
            }
        }
    }
}