package com.hdd.server.controller;


import com.hdd.server.pojo.Admin;
import com.hdd.server.pojo.AdminRole;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.pojo.Role;
import com.hdd.server.service.IAdminRoleService;
import com.hdd.server.service.IAdminService;
import com.hdd.server.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.description.field.FieldDescription;
import org.apache.ibatis.annotations.Update;
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
@RequestMapping("/system/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IAdminRoleService adminRoleService;
    @ApiOperation(value = "查询所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmin(String keyWords){
        return adminService.getAllAdmin(keyWords);
    }
    @ApiOperation(value = "操作员更新操作")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if (adminService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/role")
    public List<Role> getRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/adminRole")
    public RespBean updateAdminRole(@RequestParam("adminId") Integer adminId,@RequestParam("rids") Integer[] rids){
        return adminRoleService.updateAdminRole(adminId,rids);
    }
}
