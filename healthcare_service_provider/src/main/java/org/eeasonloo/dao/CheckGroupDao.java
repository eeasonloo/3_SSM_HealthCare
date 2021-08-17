package org.eeasonloo.dao;

import org.eeasonloo.pojo.CheckGroup;

import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String,Integer> map);
}
