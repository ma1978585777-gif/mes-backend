package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesBox;
import com.tgkwrobot.mes.mapper.MesBoxMapper;
import com.tgkwrobot.mes.service.IMesBoxService;
import com.tgkwrobot.mes.service.IMesBoxDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MesBoxServiceImpl extends ServiceImpl<MesBoxMapper, MesBox> implements IMesBoxService {

    private final IMesBoxDetailService boxDetailService;

    @Override
    public MesBox getBoxByCode(String boxCode) {
        MesBox box = lambdaQuery().eq(MesBox::getBoxCode, boxCode).one();
        if (box != null) {
            box.setDetails(boxDetailService.getDetailsByBoxCode(boxCode));
        }
        return box;
    }
}