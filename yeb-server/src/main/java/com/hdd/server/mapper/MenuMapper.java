package com.hdd.server.mapper;

import com.hdd.server.pojo.Admin;
import com.hdd.server.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户id查询菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    List<Menu> getMenusByRole();

    List<Menu> getMenus();
}
