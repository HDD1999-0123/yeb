package com.hdd.server.controller;

import com.hdd.server.pojo.Admin;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author hedd
 * @create 2021/6/15 17:05
 * @Desc 个人中心
 */
@RestController
public class AdminInfoController {
    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/admin/info")
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication){
        if (adminService.updateById(admin)){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,
                    null,authentication.getAuthorities()));
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @ApiOperation(value = "更新密码")
    @PutMapping("/admin/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String,Object> info){
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }
//    @ApiOperation(value = "更新头像")
//    @PostMapping("/admin/userface")
//    public RespBean updateAdminUserFace(MultipartFile file,Integer id,Authentication authentication){
//        String[] filePath = FastDFSutils.upload(file);
//        String url = FastDFSUtils.getTrackerUrl()+filePath[0]+"/"+filePath[1];
//        return adminService.updateAdminUserFace(url,id,authentication);
//    }
}
