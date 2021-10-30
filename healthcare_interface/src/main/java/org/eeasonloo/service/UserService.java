package org.eeasonloo.service;

import org.eeasonloo.pojo.User;

public interface UserService {

    public User findByUsername(String username);
}
