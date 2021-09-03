package org.eeasonloo.service;

import org.eeasonloo.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    public void add(List<OrderSetting> dateAndOrdersList);

    public List<Map> getOrderSettingByMonth(String date);
}
