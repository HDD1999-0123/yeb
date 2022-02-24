package com.hdd.server.service;

import com.hdd.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 通过用户id获取menu列表
     * @return
     */
    List<Menu> getMenusByAdminId();
    /**
     * 通过角色获取菜单列表
     */
    List<Menu> getMenusByRole();

    List<Menu> getMenus();
}
