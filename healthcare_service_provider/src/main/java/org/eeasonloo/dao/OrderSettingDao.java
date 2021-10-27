package org.eeasonloo.dao;

import org.eeasonloo.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {


    public void editNumberByOrderDate(OrderSetting orderSetting);
    //更新已预约人数
    public void editReservationsByOrderDate(OrderSetting orderSetting);
    public void add(OrderSetting orderSetting);

    public int findCountByDate(Date orderDate);

    public List<OrderSetting> getOrderSettingByMonth(Map map);

    //根据预约日期查询预约设置信息
    public OrderSetting findByOrderDate(Date orderDate);
}
