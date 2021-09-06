package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.eeasonloo.dao.OrderSettingDao;
import org.eeasonloo.pojo.OrderSetting;
import org.eeasonloo.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map> getOrderSettingByMonth(String date) {

        String dateBegin = date + "-1";
        String dateEnd = date + "-31";

        Map map = new HashMap();
        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(map);

        List<Map> resultAll = new ArrayList<>();

        for (OrderSetting orderSetting : orderSettingList) {
            Map resultPart = new HashMap<>();
            resultPart.put("date", orderSetting.getOrderDate().getDate());
            resultPart.put("number", orderSetting.getNumber());
            resultPart.put("reservations", orderSetting.getReservations());
            resultAll.add(resultPart);
        }

        return resultAll;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        // 1.Check whether orderDate exist
        int count = orderSettingDao.findCountByDate(orderSetting.getOrderDate());

        if(count > 0){
            //2. If date exist, edit numbers,
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //3. If date not exist, add date and numbers.
            orderSettingDao.add(orderSetting);
        }
    }


}
