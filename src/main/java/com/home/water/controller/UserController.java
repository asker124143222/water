package com.home.water.controller;


import com.github.pagehelper.PageInfo;
import com.home.water.entity.User;
import com.home.water.common.Result;
import com.home.water.common.StatusCode;
import com.home.water.model.UserVO;
import com.home.water.model.UserWeather;
import com.home.water.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author xu.dm
 * @since 2020-03-30 22:41:44
 */
@Api(tags={"用户操作接口"})
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());
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
    public List<UserVO> selectAll() {
        return userService.getAll();
    }

    /***
     *
     * @param offset 起始位移
     * @param limit 返回条数
     * @return
     */
    @GetMapping("/limit/{offset}/{limit}")
    public List<UserVO> queryAllByLimit(@PathVariable int offset,@PathVariable int limit){
        return userService.queryAllByLimit(offset,limit);
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    public PageInfo<UserVO> queryByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        return userService.queryByPage(pageNum,pageSize);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询用户",notes = "根据id查询单个用户")
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

    @ApiOperation(value = "新增用户",notes = "新增之后返回对象")
    @PostMapping
    public Result insert(@RequestBody User user) {
        if (userService.insert(user) > 0)
            return new Result(true,StatusCode.OK,"添加成功");
        else return new Result(false,StatusCode.ERROR,"添加失败");
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改用户信息",notes = "根据成员id修改单个用户")
    @PutMapping
    public Result update(@RequestBody User user) {
        if (userService.update(user) > 0)
            return new Result(true,StatusCode.OK,"修改成功");
        else return new Result(false,StatusCode.ERROR,"修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Integer> delete(@RequestParam("idList") List<Integer> idList) {
        logger.info("delete user data："+idList.toString());
        if(idList.contains(1)) {
            return new Result<>(false,StatusCode.ERROR,"删除失败",0);
        }
        int count = 0;
        for (int i : idList) {
            count += userService.delete(i);
        }
        if (count > 0)
            return new Result<>(true,StatusCode.OK,"删除成功",count);
        else return new Result<>(false,StatusCode.ERROR,"删除失败",0);
    }

    @GetMapping("/AllUserWeather")
    public List<UserWeather> getAllUserAndWeather() {
        return userService.getAllUserAndWeather();
    }

    @GetMapping("/AllUserWeather/{id}")
    public List<UserWeather> getAllUserAndWeather(@PathVariable Integer id) {
        return userService.getUserAndWeatherByID(id);
    }
}