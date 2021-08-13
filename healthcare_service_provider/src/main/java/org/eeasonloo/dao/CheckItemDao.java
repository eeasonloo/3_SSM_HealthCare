package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.CheckItem;

public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);
}
