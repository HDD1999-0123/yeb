package com.hdd.server.service;

import com.hdd.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * 获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    List<Employee> getEmployee(Integer id);

    RespBean insertEmp(Employee employee);

    RespBean getWorkId();
}
