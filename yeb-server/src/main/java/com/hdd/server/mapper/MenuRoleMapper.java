package com.hdd.server.mapper;

import com.hdd.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertBatch(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
