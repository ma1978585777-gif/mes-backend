package com.tgkwrobot.mes.service;

public interface CodeRuleService {
    /**
     * 生成SN码
     * 规则: SN + yyyyMMddHHmmss + 4位流水
     */
    String generateSn();

    /**
     * 生成批次号
     * 规则: 工厂编码 + "-" + yyyyMMdd + "-" + HHmm + "-" + 3位流水
     */
    String generateBatch(String factoryCode);
}