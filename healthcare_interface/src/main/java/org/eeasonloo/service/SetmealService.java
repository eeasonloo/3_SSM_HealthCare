package org.eeasonloo.service;

import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.CheckGroup;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {

    public void add(Integer[] checkgroupIds, Setmeal setmeal);

    public PageResult findPage(int pageSize, int currentPage, String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

    public void delete(Integer id);

    public List<Integer> findCheckGroupIdsbySetmealId(Integer setmealId);

    public void edit(Integer[] checkgroupIds, Setmeal setmeal);

    public List<Map<String,Object>> findSetmealCount();
}
