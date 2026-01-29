package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesStockRecord;

public interface IMesStockRecordService extends IService<MesStockRecord> {
    /**
     * 创建出入库记录
     * @param stockRecord 记录信息
     * @return 是否成功
     */
    boolean createRecord(MesStockRecord stockRecord);
}
