package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterial;
import com.tgkwrobot.mes.entity.MesPrintTemplate;
import com.tgkwrobot.mes.mapper.MesPrintTemplateMapper;
import com.tgkwrobot.mes.service.IMesMaterialService;
import com.tgkwrobot.mes.service.IMesPrintTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MesPrintTemplateServiceImpl extends ServiceImpl<MesPrintTemplateMapper, MesPrintTemplate> implements IMesPrintTemplateService {

    private final IMesMaterialService materialService;

    @Override
    public IPage<MesPrintTemplate> getTemplatePage(IPage<MesPrintTemplate> page, MesPrintTemplate queryParams) {
        LambdaQueryWrapper<MesPrintTemplate> wrapper = new LambdaQueryWrapper<>();
        if (queryParams != null) {
            wrapper.like(StringUtils.hasText(queryParams.getName()), MesPrintTemplate::getName, queryParams.getName())
                   .like(StringUtils.hasText(queryParams.getCode()), MesPrintTemplate::getCode, queryParams.getCode());
        }
        IPage<MesPrintTemplate> result = this.page(page, wrapper);
        populateMaterialNames(result.getRecords());
        return result;
    }


    @Override
    public MesPrintTemplate getTemplateDetail(Long id) {
        MesPrintTemplate template = this.getById(id);
        if (template != null) {
            populateMaterialNames(Collections.singletonList(template));
        }
        return template;
    }

    /**
     * 批量填充物料名称
     */
    private void populateMaterialNames(List<MesPrintTemplate> templates) {
        if (templates == null || templates.isEmpty()) {
            return;
        }

        List<Long> materialIds = templates.stream()
                .map(MesPrintTemplate::getMaterialId)
                .filter(id -> id != null)
                .distinct()
                .collect(Collectors.toList());

        if (materialIds.isEmpty()) {
            return;
        }

        List<MesMaterial> materials = materialService.listByIds(materialIds);
        Map<Long, String> materialNameMap = materials.stream()
                .collect(Collectors.toMap(MesMaterial::getId, MesMaterial::getName));

        for (MesPrintTemplate template : templates) {
            if (template.getMaterialId() != null) {
                template.setMaterialName(materialNameMap.get(template.getMaterialId()));
            }
        }
    }
}
