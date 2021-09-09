package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void addAssociationsSetmealAndCheckgroup(Map<String, Integer> map);

    public Page<Setmeal> findByCondition(String queryString);

    public List<Setmeal> findAll();

}
