package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.eeasonloo.dao.OrderSettingDao;
import org.eeasonloo.pojo.OrderSetting;
import org.eeasonloo.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    public void add(List<OrderSetting> dateAndOrdersList){

        if(dateAndOrdersList != null && dateAndOrdersList.size() > 0){
            for (OrderSetting orderSetting : dateAndOrdersList) {
                int count = orderSettingDao.findCountByDate(orderSetting.getOrderDate());

                if(count > 0){
                    // Means current date existed, need to edit number
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else{
                    // Means current date doesnt existed, need to add both date, number
                    orderSettingDao.add(orderSetting);
                }
            }

        }

    }


}
