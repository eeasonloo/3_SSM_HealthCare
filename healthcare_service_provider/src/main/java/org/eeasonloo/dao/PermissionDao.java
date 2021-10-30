package org.eeasonloo.dao;

import com.github.pagehelper.Page;
import org.eeasonloo.pojo.CheckItem;
import org.eeasonloo.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionDao {

    public void add(Permission permission);

    public Page<Permission> selectByCondition(String queryString);

    public Integer findCountByPermissionId(Integer id);

    public void deleteById(Integer id);

    public Permission findById(Integer id);

    public void edit(Permission permission);

    public List<Permission> findAll();

    public Set<Permission> findByRoleId(int roleId);

}
