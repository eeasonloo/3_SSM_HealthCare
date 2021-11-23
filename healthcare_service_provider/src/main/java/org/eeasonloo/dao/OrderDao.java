package org.eeasonloo.dao;

import org.eeasonloo.pojo.Order;
import org.eeasonloo.pojo.OrderList;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    public void add(Order order);
    public List<Order> findByCondition(Order order);
    public Map findById4Detail(Integer id);

    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();

    public OrderList findById4MoreDetail(Integer id);
    public List<Integer> findAllOrderIds();
    public void deleteById(Integer id);

    public OrderList findByIdforEdit(Integer id);
    public Integer findSetmealIdbyOrderId(Integer id);

    public List<Integer> findAllOrderIdsbyMemberId(Integer id);

}
