package com.hdd.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdd.server.pojo.Menu;
import com.hdd.server.pojo.MenuRole;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.pojo.Role;
import com.hdd.server.service.IMenuRoleService;
import com.hdd.server.service.IMenuService;
import com.hdd.server.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hedd
 * @create 2021/4/25
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;
    @ApiOperation("查询所有角色")
    @GetMapping("/role")
    public List<Role> getRoles(){
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if (roleService.save(role)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable Integer id){
        if (roleService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(" 查询所有菜单")
    @GetMapping("/menu")
    public List<Menu> getMenus(){
        return menuService.getMenus();
    }

    @ApiOperation("根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMids(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid))
                .stream()
                .map(MenuRole::getMid)
                .collect(Collectors.toList());
    }

    @ApiOperation("更新角色菜单")
    @PutMapping("/menuRole")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }
}
