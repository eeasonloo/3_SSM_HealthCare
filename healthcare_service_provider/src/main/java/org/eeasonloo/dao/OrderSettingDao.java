package org.eeasonloo.dao;

import org.eeasonloo.pojo.OrderSetting;

import java.util.Date;

public interface OrderSettingDao {

    public int findCountByDate(Date orderDate);
    public void editNumberByOrderDate(OrderSetting orderSetting);
    public void add(OrderSetting orderSetting);
}
