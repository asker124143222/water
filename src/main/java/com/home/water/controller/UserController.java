package com.home.water.controller;


import com.home.water.entity.User;
import com.home.water.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author xu.dm
 * @since 2020-03-30 22:41:44
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping
    public List<User> selectAll() {
        return userService.getAll();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public User selectOne(@PathVariable Integer id) {
        return userService.getOne(id);
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping("/check")
    public User getOneByNameAndPassword(@RequestBody User user) {
        return userService.getOneByNameAndPassword(user);
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public User insert(@RequestBody User user) {
        if(userService.insert(user)>0)
            return user;
        else return null;
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public User update(@RequestBody User user) {
        return null;
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public User delete(@RequestParam("idList") List<Integer> idList) {
        return null;
    }
}