package org.eeasonloo.service;

import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;

public interface CheckGroupService {

    public void add(Integer[] checkItemIds, @RequestBody CheckGroup checkGroup);

    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    public CheckGroup findById(Integer checkgroupId);
}
