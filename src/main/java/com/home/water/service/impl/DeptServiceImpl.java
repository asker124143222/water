package com.home.water.service.impl;

import com.home.water.common.EntityStatus;
import com.home.water.dao.DeptDao;
import com.home.water.entity.Dept;
import com.home.water.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门(Dept)表服务实现类
 *
 * @author xu.dm
 * @since 2020-09-24 13:55:48
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dept queryById(Long id) {
        return this.deptDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Dept> queryAllByLimit(int offset, int limit) {
        return this.deptDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept insert(Dept dept) {
        this.deptDao.insert(dept);
        return dept;
    }

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept update(Dept dept) {
        this.deptDao.update(dept);
        return this.queryById(dept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.deptDao.deleteById(id) > 0;
    }
}