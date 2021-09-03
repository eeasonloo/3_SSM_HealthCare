package org.eeasonloo.dao;

import org.eeasonloo.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {

    public int findCountByDate(Date orderDate);
    public void editNumberByOrderDate(OrderSetting orderSetting);
    public void add(OrderSetting orderSetting);
    public List<OrderSetting> getOrderSettingByMonth(Map map);
}
