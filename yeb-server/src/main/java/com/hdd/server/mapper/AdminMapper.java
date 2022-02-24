package com.hdd.server.mapper;

import com.hdd.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> getAllAdmin(@Param("keyWords") String keyWords,@Param("adminId") Integer adminId);
}
