package org.eeasonloo.dao;

import org.eeasonloo.pojo.Order;

import java.util.List;

public interface OrderDao {

    public void add(Order order);
    public List<Order> findByCondition(Order order);
}
