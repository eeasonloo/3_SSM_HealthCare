package org.eeasonloo.service;

import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;

public interface SetmealService {

    public void add(Integer[] checkgroupIds, Setmeal setmeal);

    public PageResult findPage(int pageSize, int currentPage, String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

}
