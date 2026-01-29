package com.tgkwrobot.mes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tgkwrobot.mes.entity.MesUser;

import java.util.List;

public interface IMesUserService extends IService<MesUser> {
    
    /**
     * 用户登录
     * @param empId 工号
     * @return 用户信息
     */
    MesUser login(String empId);

    /**
     * 绑定职能
     * @param userId 用户ID
     * @param functionIds 职能ID列表
     */
    void bindFunctions(Long userId, List<Long> functionIds);
    
    /**
     * 获取用户详情（包含职能）
     * @param id 用户ID
     * @return 用户详情
     */
    MesUser getUserDetail(Long id);

    /**
     * 创建用户（包含职能绑定）
     * @param user 用户信息
     * @return 是否成功
     */
    boolean createUser(MesUser user);

    /**
     * 更新用户（包含职能绑定）
     * @param user 用户信息
     * @return 是否成功
     */
    boolean updateUser(MesUser user);

    /**
     * 分页查询用户（包含职能）
     * @param page 分页参数
     * @param queryParams 查询参数
     * @return 用户分页数据
     */
    IPage<MesUser> getUserPage(IPage<MesUser> page, MesUser queryParams);
}
