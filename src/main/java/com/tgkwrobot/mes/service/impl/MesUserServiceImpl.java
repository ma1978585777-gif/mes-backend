package com.tgkwrobot.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tgkwrobot.mes.entity.MesFunction;
import com.tgkwrobot.mes.entity.MesUser;
import com.tgkwrobot.mes.entity.MesUserFunction;
import com.tgkwrobot.mes.mapper.MesUserMapper;
import com.tgkwrobot.mes.service.IMesFunctionService;
import com.tgkwrobot.mes.service.IMesUserFunctionService;
import com.tgkwrobot.mes.service.IMesUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MesUserServiceImpl extends ServiceImpl<MesUserMapper, MesUser> implements IMesUserService {

    private final IMesUserFunctionService userFunctionService;
    private final IMesFunctionService functionService;

    @Override
    public MesUser login(String empId) {
        MesUser user = this.getOne(new LambdaQueryWrapper<MesUser>().eq(MesUser::getEmpId, empId));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return getUserDetail(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindFunctions(Long userId, List<Long> functionIds) {
        // 先删除旧绑定
        userFunctionService.remove(new LambdaQueryWrapper<MesUserFunction>().eq(MesUserFunction::getUserId, userId));
        
        if (functionIds == null || functionIds.isEmpty()) {
            return;
        }

        // 添加新绑定
        List<MesUserFunction> list = functionIds.stream().map(fid -> {
            MesUserFunction uf = new MesUserFunction();
            uf.setUserId(userId);
            uf.setFunctionId(fid);
            return uf;
        }).collect(Collectors.toList());
        
        userFunctionService.saveBatch(list);
    }

    @Override
    public MesUser getUserDetail(Long id) {
        MesUser user = this.getById(id);
        if (user != null) {
            populateUserFunctions(Collections.singletonList(user));
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUser(MesUser user) {
        boolean success = this.save(user);
        if (success && user.getFunctionIds() != null && !user.getFunctionIds().isEmpty()) {
            this.bindFunctions(user.getId(), user.getFunctionIds());
        }
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(MesUser user) {
        boolean success = this.updateById(user);
        if (success) {
            if (user.getFunctionIds() != null) {
                this.bindFunctions(user.getId(), user.getFunctionIds());
            }
        }
        return success;
    }

    @Override
    public IPage<MesUser> getUserPage(IPage<MesUser> page) {
        IPage<MesUser> result = this.page(page);
        populateUserFunctions(result.getRecords());
        return result;
    }

    /**
     * 批量填充用户职能信息
     */
    public void populateUserFunctions(List<MesUser> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        List<Long> userIds = userList.stream().map(MesUser::getId).collect(Collectors.toList());
        
        // 查询所有关联关系
        List<MesUserFunction> ufs = userFunctionService.list(new LambdaQueryWrapper<MesUserFunction>().in(MesUserFunction::getUserId, userIds));
        
        if (ufs.isEmpty()) {
            return;
        }

        // 获取所有涉及的职能ID
        List<Long> allFunctionIds = ufs.stream().map(MesUserFunction::getFunctionId).distinct().collect(Collectors.toList());
        if (allFunctionIds.isEmpty()) {
            return;
        }

        // 查询职能信息
        List<MesFunction> functions = functionService.listByIds(allFunctionIds);
        Map<Long, String> functionNameMap = functions.stream().collect(Collectors.toMap(MesFunction::getId, MesFunction::getName));

        // 按用户ID分组
        Map<Long, List<MesUserFunction>> userFunctionMap = ufs.stream().collect(Collectors.groupingBy(MesUserFunction::getUserId));

        // 填充数据
        for (MesUser user : userList) {
            List<MesUserFunction> myUfs = userFunctionMap.get(user.getId());
            if (myUfs != null) {
                List<Long> fids = myUfs.stream().map(MesUserFunction::getFunctionId).collect(Collectors.toList());
                List<String> fnames = fids.stream().map(functionNameMap::get).filter(name -> name != null).collect(Collectors.toList());
                user.setFunctionIds(fids);
                user.setFunctionNames(fnames);
            }
        }
    }
}
