package com.home.water.controller;

import com.home.water.entity.Dept;
import com.home.water.service.DeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 部门(Dept)表控制层
 *
 * @author xu.dm
 * @since 2020-09-24 13:55:48
 */
@RestController
@RequestMapping("dept")
public class DeptController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Dept selectOne(Long id) {
        return this.deptService.queryById(id);
    }

}