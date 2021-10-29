package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.pojo.CheckItem;
import org.eeasonloo.pojo.Permission;

import java.util.List;

public interface PermissionDao {

    public void add(Permission permission);

    public Page<Permission> selectByCondition(String queryString);

    public Integer findCountByPermissionId(Integer id);

    public void deleteById(Integer id);

    public Permission findById(Integer id);

    public void edit(Permission permission);

    public List<Permission> findAll();
}
