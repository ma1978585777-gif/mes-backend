package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesBox;
import com.tgkwrobot.mes.entity.MesBoxDetail;
import com.tgkwrobot.mes.mapper.MesBoxDetailMapper;
import com.tgkwrobot.mes.mapper.MesBoxMapper;
import com.tgkwrobot.mes.service.IMesBoxDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesBoxDetailServiceImpl extends ServiceImpl<MesBoxDetailMapper, MesBoxDetail> implements IMesBoxDetailService {

    private final MesBoxMapper boxMapper;

    @Override
    public List<MesBoxDetail> getDetailsByBoxCode(String boxCode) {
        return this.list(new LambdaQueryWrapper<MesBoxDetail>().eq(MesBoxDetail::getBoxCode, boxCode));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(MesBoxDetail detail) {
        // 自动填充boxId
        if (detail.getBoxId() == null && detail.getBoxCode() != null) {
            MesBox box = boxMapper.selectOne(new LambdaQueryWrapper<MesBox>().eq(MesBox::getBoxCode, detail.getBoxCode()));
            if (box != null) {
                detail.setBoxId(box.getId());
            } else {
                throw new RuntimeException("箱编码不存在");
            }
        }
        return super.save(detail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatchDetails(List<MesBoxDetail> details) {
        if (details == null || details.isEmpty()) {
            return false;
        }
        // 假设同一批次的boxCode相同，取第一个查询即可，或者逐个查询
        // 为保证准确性，这里逐个处理或按boxCode分组处理
        // 简单起见，循环处理（因为数量通常不多）
        for (MesBoxDetail detail : details) {
            if (detail.getBoxId() == null && detail.getBoxCode() != null) {
                MesBox box = boxMapper.selectOne(new LambdaQueryWrapper<MesBox>().eq(MesBox::getBoxCode, detail.getBoxCode()));
                if (box != null) {
                    detail.setBoxId(box.getId());
                } else {
                    throw new RuntimeException("箱编码 " + detail.getBoxCode() + " 不存在");
                }
            }
        }
        return this.saveBatch(details);
    }
}