package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.OrderSetting;
import org.eeasonloo.service.OrderSettingService;
import org.eeasonloo.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile")MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            if(list.size()> 0 && list != null){
                List<OrderSetting> dateAndOrdersList = new ArrayList<OrderSetting>();

                for (String[] dateAndOrders : list) {
                    dateAndOrdersList.add(new OrderSetting(new Date(dateAndOrders[0]),
                                            Integer.parseInt(dateAndOrders[1])));
                }
                orderSettingService.add(dateAndOrdersList);
            }

        } catch (IOException e) {
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }


    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){

        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }


    }
}
