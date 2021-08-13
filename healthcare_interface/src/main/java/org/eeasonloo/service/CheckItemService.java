package org.eeasonloo.service;

import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.CheckItem;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
}
