package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.pojo.CheckGroup;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void addAssociationsSetmealAndCheckgroup(Map<String, Integer> map);

    public Page<Setmeal> findByCondition(String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

    public Integer findCountBySetmealId(Integer id);

    public void deleteById(Integer id);

    public List<Integer> findCheckGroupIdsbySetmealId(Integer setmealId);

    public void edit(Setmeal setmeal);

    public void deleteAssociations(Integer setmealId);

    public void setSetmealAndCheckGroup(Map<String,Integer> map);

}
