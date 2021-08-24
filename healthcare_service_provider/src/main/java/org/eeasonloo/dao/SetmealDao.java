package org.eeasonloo.dao;

import org.eeasonloo.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void addAssociationsSetmealAndCheckgroup(Map<String, Integer> map);
}
