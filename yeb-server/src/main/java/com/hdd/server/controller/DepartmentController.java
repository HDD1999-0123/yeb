package com.hdd.server.controller;


import com.hdd.server.pojo.Department;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.service.IDepartmentService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("查询全部部门")
    @GetMapping("/")
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }
    @ApiOperation("添加部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }
    @ApiOperation("删除部门")
    @DeleteMapping("/")
    public RespBean deleteDepartment(@RequestBody Department department){
        return departmentService.deleteDepartment(department);
    }
}
