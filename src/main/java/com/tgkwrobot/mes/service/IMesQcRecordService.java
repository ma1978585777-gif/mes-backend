package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesMaterial;
import com.tgkwrobot.mes.entity.MesQcRecord;

public interface IMesQcRecordService extends IService<MesQcRecord> {

    /**
     * 分页查询质检记录（包含物料名称）
     * @param page 分页参数
     * @param queryParams 查询参数
     * @return 质检记录分页数据
     */
    IPage<MesQcRecord> getQcRecordPage(IPage<MesQcRecord> page, MesQcRecord queryParams);

    /**
     * 获取质检记录详情（包含物料名称）
     * @param id 记录ID
     * @return 质检记录详情
     */
    MesQcRecord getQcRecordDetail(Long id);

    /**
     * 根据SN码更新质检状态为已质检
     * @param snCode SN码
     * @return 是否成功
     */
    boolean updateStatusBySnCode(String snCode);

    /**
     * 根据SN码查询质检记录详情（包含物料名称）
     * @param snCode SN码
     * @return 质检记录详情
     */
    MesQcRecord getQcRecordBySnCode(String snCode);

    /**
     * 根据SN码查询关联的物料信息
     * @param snCode SN码
     * @return 物料信息
     */
    MesMaterial getMaterialBySnCode(String snCode);
}