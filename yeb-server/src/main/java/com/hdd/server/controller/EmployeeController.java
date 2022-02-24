package com.hdd.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.hdd.server.pojo.*;
import com.hdd.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private INationService nationService;
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IJoblevelService joblevelService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @ApiOperation(value = "获取所有员工")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee, LocalDate[] beginDateScope){

        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);
    }
    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping(value = "/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }
    @ApiOperation(value = "获取所有职位")
    @GetMapping(value = "/position")
    public List<Position> getAllPosition(){
        return positionService.list();
    }
    @ApiOperation(value = "获取所有职称")
    @GetMapping(value = "/jobLevel")
    public List<Joblevel> getAllJobLevel(){
        return joblevelService.list();
    }
    @ApiOperation(value = "获取所有民族")
    @GetMapping(value = "/nation")
    public List<Nation> getAllNation(){
        return nationService.list();
    }
    @ApiOperation(value = "获取工号")
    @GetMapping(value = "/workId")
    public RespBean getWorkId(){
        return employeeService.getWorkId();
    }
    @ApiOperation(value = "添加员工")
    @PostMapping(value = "/")
    public RespBean insertEmp(@RequestBody Employee employee){
        return employeeService.insertEmp(employee);
    }
    @ApiOperation(value = "删除员工")
    @DeleteMapping(value = "/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        boolean b = employeeService.removeById(id);
        if (b){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @ApiOperation(value = "导出员工")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        //获取所有员工数据
        List<Employee> employees = employeeService.getEmployee(null);
        //准备导出参数，，文件名，sheet名字，excel类型（xls，xlxs）
        ExportParams params = new ExportParams("员工表","员工表", ExcelType.HSSF);
        //使用导出工具类导出，，导出参数，导出对象，数据
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, employees);
        ServletOutputStream outputStream =null;
        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("员工表.xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //将Employee中引入的实体参数重写hashcode，只要name，通过name拿id
    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile file){
        ImportParams params = new ImportParams();
        //去掉标题行
        params.setTitleRows(1);
        List<Nation> nations = nationService.list();
        List<PoliticsStatus> politicsStatuses = politicsStatusService.list();
        List<Department> departments = departmentService.list();
        List<Joblevel> joblevels = joblevelService.list();
        List<Position> positions = positionService.list();
        try {
            List<Employee> employees = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            employees.forEach(employee -> {
                //民族id
                employee.setNationId(nations.get(nations.indexOf(new Nation(employee.getNation().getName()))).getId());
                employee.setPoliticId(politicsStatuses.get(politicsStatuses.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                employee.setDepartmentId(departments.get(departments.indexOf(new Department(employee.getDepartment().getName()))).getId());
                employee.setJobLevelId(joblevels.get(joblevels.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                employee.setPosId(positions.get(positions.indexOf(new Position(employee.getPosition().getName()))).getId());
            });
            if (employeeService.saveBatch(employees)){
                //发送信息
                Employee emp = employees.get(0);
                rabbitTemplate.convertAndSend("mail.welcome",emp);
                return RespBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");
    }

}
