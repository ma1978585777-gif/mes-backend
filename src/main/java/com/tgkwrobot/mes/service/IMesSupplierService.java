package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesSupplier;

import java.util.List;

public interface IMesSupplierService extends IService<MesSupplier> {

    /**
     * 分页查询供应商（包含关联物料信息）
     * @param page 分页参数
     * @return 供应商分页数据
     */
    IPage<MesSupplier> getSupplierPage(IPage<MesSupplier> page);

    /**
     * 获取供应商详情（包含关联物料信息）
     * @param id 供应商ID
     * @return 供应商详情
     */
    MesSupplier getSupplierDetail(Long id);

    /**
     * 创建供应商（包含物料关联）
     * @param supplier 供应商信息
     * @return 是否成功
     */
    boolean createSupplier(MesSupplier supplier);

    /**
     * 更新供应商（包含物料关联）
     * @param supplier 供应商信息
     * @return 是否成功
     */
    boolean updateSupplier(MesSupplier supplier);

    /**
     * 绑定物料
     * @param supplierId 供应商ID
     * @param materialIds 物料ID列表
     */
    void bindMaterials(Long supplierId, List<Long> materialIds);
}
