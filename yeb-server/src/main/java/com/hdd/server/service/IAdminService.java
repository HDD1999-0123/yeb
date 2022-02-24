package com.hdd.server.service;

import com.hdd.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.pojo.Role;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 登录之后返回token
     *
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id获取角色
     * @param adminId
     * @return
     */
    List<Role> getRolesById(Integer adminId);

    List<Admin> getAllAdmin(String keyWords);

    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}
