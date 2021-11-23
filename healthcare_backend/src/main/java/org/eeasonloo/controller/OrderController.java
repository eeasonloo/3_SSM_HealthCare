package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.QueryPageBean;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.OrderList;
import org.eeasonloo.pojo.Setmeal;
import org.eeasonloo.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @RequestMapping("/findById4MoreDetail")
    public Result findById4MoreDetail(){
        try {
            List orderLists = orderService.findById4MoreDetail();
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,orderLists);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){

        try {
            orderService.delete(id);
        } catch (RuntimeException r) {
            return new Result(false,r.getMessage());
        }catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_ORDER_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_ORDER_SUCCESS);

    }

    @RequestMapping("/findByIdforEdit")
    public Result findById(Integer orderId){
        try {
            OrderList orderList = orderService.findByIdforEdit(orderId);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,orderList);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @RequestMapping("/findSetmealIdbyOrderId")
    public Result findSetmealIdbyOrderId(Integer orderId){
        try {

            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,orderService.findSetmealIdbyOrderId(orderId));
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
