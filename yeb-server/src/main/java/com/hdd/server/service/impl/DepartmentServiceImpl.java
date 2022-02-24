package com.hdd.server.service.impl;

import com.hdd.server.pojo.Department;
import com.hdd.server.mapper.DepartmentMapper;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDeparments(-1);
    }

    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if (1 == department.getResultCode()){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @Override
    public RespBean deleteDepartment(Department department) {
        departmentMapper.deleteDepartment(department);
        if (1==department.getResultCode()){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
