package com.hdd.server.utils;

import com.hdd.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author hedd
 * @create 2021/4/29
 */
public class AdminUtils {
    public static Admin getAdmin(){
        return (Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
