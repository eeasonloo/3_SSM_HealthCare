package org.eeasonloo.dao;

import org.eeasonloo.pojo.User;

public interface UserDao {

    public User findByUsername(String username);
}
