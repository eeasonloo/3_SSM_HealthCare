package org.eeasonloo.dao;

import org.eeasonloo.pojo.Role;

import java.util.Set;

public interface RoleDao {

    public Set<Role> findByUserId(int id);
}
