package org.eeasonloo.service;

import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.Permission;

import java.util.List;

public interface PermissionService {

    public void add(Permission permission);

    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    public void delete(Integer id);

    public Permission findById(Integer id);

    public void edit(Permission permission);

    public List<Permission> findAll();
}
