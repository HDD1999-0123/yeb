package com.hdd.server.service;

import com.hdd.server.pojo.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hdd.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface IAdminRoleService extends IService<AdminRole> {
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
