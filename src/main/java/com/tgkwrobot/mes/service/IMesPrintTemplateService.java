package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesPrintTemplate;

public interface IMesPrintTemplateService extends IService<MesPrintTemplate> {

    /**
     * 分页查询打印模板（包含物料名称）
     * @param page 分页参数
     * @param queryParams 查询参数
     * @return 打印模板分页数据
     */
    IPage<MesPrintTemplate> getTemplatePage(IPage<MesPrintTemplate> page, MesPrintTemplate queryParams);

    /**
     * 获取打印模板详情（包含物料名称）
     * @param id 模板ID
     * @return 打印模板详情
     */
    MesPrintTemplate getTemplateDetail(Long id);
}
