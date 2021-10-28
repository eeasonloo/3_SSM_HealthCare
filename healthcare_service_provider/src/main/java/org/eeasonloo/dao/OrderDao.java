package org.eeasonloo.dao;

import org.eeasonloo.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    public void add(Order order);
    public List<Order> findByCondition(Order order);
    public Map findById4Detail(Integer id);
}
