package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.pojo.CheckGroup;

import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String,Integer> map);

    public Page<CheckGroup> findByCondition(String queryString);

    public CheckGroup findById(Integer checkgroupId);
}
