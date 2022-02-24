package com.hdd.server.controller;


import com.hdd.server.pojo.Joblevel;
import com.hdd.server.pojo.RespBean;
import com.hdd.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/jbl")
public class JoblevelController {
    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation("获取所有职称等级")
    @GetMapping("/")
    public List<Joblevel> getJoblevels(){
        return joblevelService.list();
    }
    @ApiOperation("添加职称等级")
    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }
    @ApiOperation("更新职称等级")
    @PutMapping("/")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){
        if (joblevelService.updateById(joblevel)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
    @ApiOperation("删除职称等级")
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(Integer id){
        if (joblevelService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @ApiOperation("批量删除职称等级")
    @DeleteMapping("/")
    public RespBean deleteJoblevels(Integer[] ids){
        if (joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
