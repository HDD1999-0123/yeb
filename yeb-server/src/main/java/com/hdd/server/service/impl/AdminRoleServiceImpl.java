package com.hdd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdd.server.pojo.AdminRole;
import com.hdd.server.mapper.AdminRoleMapper;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.service.IAdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Override
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        super.baseMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        int result = adminRoleMapper.addAdminRole(adminId,rids);
        if(rids.length == result){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
