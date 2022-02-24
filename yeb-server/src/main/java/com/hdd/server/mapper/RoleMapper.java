package com.hdd.server.mapper;

import com.hdd.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id获取角色
     * @param adminId
     * @return
     */
    List<Role> getRolesById(Integer adminId);
}
