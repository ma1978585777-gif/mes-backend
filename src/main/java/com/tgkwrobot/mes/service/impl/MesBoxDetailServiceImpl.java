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
}