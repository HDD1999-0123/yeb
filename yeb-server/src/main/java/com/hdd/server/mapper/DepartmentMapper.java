package com.hdd.server.mapper;

import com.hdd.server.pojo.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getDeparments(int parentId);

    void addDepartment(Department department);

    void deleteDepartment(Department department);
}
