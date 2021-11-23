package org.eeasonloo.service;

import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.OrderList;
import org.eeasonloo.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface OrderService {
    //体检预约
    public Result order(Map map) throws Exception;

    public Map findById(Integer id) throws Exception;

    public List findById4MoreDetail();

    public void delete(Integer id);

    public OrderList findByIdforEdit(Integer id);

    public Integer findSetmealIdbyOrderId(Integer id);

    public List findById4AppointmentList(Integer id);

}
