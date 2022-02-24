package com.hdd.server.service;

import com.hdd.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hdd.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getDepartments();

    RespBean addDepartment(Department department);

    RespBean deleteDepartment(Department department);
}
