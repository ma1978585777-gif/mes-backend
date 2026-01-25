package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesMaterial;
import com.tgkwrobot.mes.entity.MesSupplier;
import com.tgkwrobot.mes.entity.MesSupplierMaterial;
import com.tgkwrobot.mes.mapper.MesSupplierMapper;
import com.tgkwrobot.mes.service.IMesMaterialService;
import com.tgkwrobot.mes.service.IMesSupplierMaterialService;
import com.tgkwrobot.mes.service.IMesSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MesSupplierServiceImpl extends ServiceImpl<MesSupplierMapper, MesSupplier> implements IMesSupplierService {

    private final IMesSupplierMaterialService supplierMaterialService;
    private final IMesMaterialService materialService;

    @Override
    public IPage<MesSupplier> getSupplierPage(IPage<MesSupplier> page) {
        IPage<MesSupplier> result = this.page(page);
        populateMaterials(result.getRecords());
        return result;
    }

    @Override
    public MesSupplier getSupplierDetail(Long id) {
        MesSupplier supplier = this.getById(id);
        if (supplier != null) {
            populateMaterials(Collections.singletonList(supplier));
        }
        return supplier;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createSupplier(MesSupplier supplier) {
        boolean success = this.save(supplier);
        if (success && supplier.getMaterialIds() != null && !supplier.getMaterialIds().isEmpty()) {
            bindMaterials(supplier.getId(), supplier.getMaterialIds());
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSupplier(MesSupplier supplier) {
        boolean success = this.updateById(supplier);
        if (success) {
            if (supplier.getMaterialIds() != null) {
                bindMaterials(supplier.getId(), supplier.getMaterialIds());
            }
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindMaterials(Long supplierId, List<Long> materialIds) {
        // 先删除旧绑定
        supplierMaterialService.remove(new LambdaQueryWrapper<MesSupplierMaterial>().eq(MesSupplierMaterial::getSupplierId, supplierId));

        if (materialIds == null || materialIds.isEmpty()) {
            return;
        }

        // 添加新绑定
        List<MesSupplierMaterial> list = materialIds.stream().map(mid -> {
            MesSupplierMaterial sm = new MesSupplierMaterial();
            sm.setSupplierId(supplierId);
            sm.setMaterialId(mid);
            return sm;
        }).collect(Collectors.toList());

        supplierMaterialService.saveBatch(list);
    }

    /**
     * 批量填充物料信息
     */
    private void populateMaterials(List<MesSupplier> suppliers) {
        if (suppliers == null || suppliers.isEmpty()) {
            return;
        }

        List<Long> supplierIds = suppliers.stream().map(MesSupplier::getId).collect(Collectors.toList());
        
        // 查询关联关系
        List<MesSupplierMaterial> sms = supplierMaterialService.list(new LambdaQueryWrapper<MesSupplierMaterial>().in(MesSupplierMaterial::getSupplierId, supplierIds));
        
        if (sms.isEmpty()) {
            return;
        }

        // 获取所有涉及的物料ID
        List<Long> materialIds = sms.stream().map(MesSupplierMaterial::getMaterialId).distinct().collect(Collectors.toList());
        if (materialIds.isEmpty()) {
            return;
        }

        // 查询物料信息
        List<MesMaterial> materials = materialService.listByIds(materialIds);
        Map<Long, String> materialNameMap = materials.stream().collect(Collectors.toMap(MesMaterial::getId, MesMaterial::getName));

        // 按供应商ID分组
        Map<Long, List<MesSupplierMaterial>> supplierMaterialMap = sms.stream().collect(Collectors.groupingBy(MesSupplierMaterial::getSupplierId));

        // 填充数据
        for (MesSupplier supplier : suppliers) {
            List<MesSupplierMaterial> mySms = supplierMaterialMap.get(supplier.getId());
            if (mySms != null) {
                List<Long> mids = mySms.stream().map(MesSupplierMaterial::getMaterialId).collect(Collectors.toList());
                List<String> mnames = mids.stream().map(materialNameMap::get).filter(name -> name != null).collect(Collectors.toList());
                supplier.setMaterialIds(mids);
                supplier.setMaterialNames(mnames);
            }
        }
    }
}
