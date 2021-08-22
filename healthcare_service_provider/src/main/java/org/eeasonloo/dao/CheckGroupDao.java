package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String,Integer> map);

    public Page<CheckGroup> findByCondition(String queryString);

    public CheckGroup findById(Integer checkgroupId);

    public List<Integer> findCheckItemIdsbyCheckGroupId(Integer checkgroupId);

    public void edit(CheckGroup checkGroup);

    public void deleteAssociations(Integer checkgroupId);

    public List<CheckGroup> findAll();
}
