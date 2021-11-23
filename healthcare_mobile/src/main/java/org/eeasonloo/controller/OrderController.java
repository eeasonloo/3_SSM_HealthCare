package org.eeasonloo.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.constant.RedisMessageConstant;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.Member;
import org.eeasonloo.pojo.Order;
import org.eeasonloo.service.MemberService;
import org.eeasonloo.service.OrderService;
import org.eeasonloo.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Reference
    private OrderService orderService;
    @Reference
    private MemberService memberService;
    @Autowired
    private JedisPool jedisPool;



    /**
     * 体检预约
     * @param map
     * @return
     */
    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map){
        String emailAddress = (String) map.get("email");
        //从Redis中获取缓存的验证码，key为手机号+RedisConstant.SENDTYPE_ORDER
        String codeInRedis = jedisPool.getResource().get(
                emailAddress + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        //校验手机验证码
        if(codeInRedis == null || !codeInRedis.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result =null;
        //调用体检预约服务
        try{
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(map);
        }catch (Exception e){
            e.printStackTrace();
            //预约失败
            return result;
        }
        if(result.isFlag()){
            //预约成功，发送短信通知
            String orderDate = (String) map.get("orderDate");
            try {
                MailUtils.sendShortMessage(MailUtils.ORDER_NOTICE,emailAddress,orderDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            //查询预约信息失败
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @RequestMapping("/findById4AppointmentList")
    public Result findById4AppointmentList(@CookieValue(name = "login_member_email") String email){

        try{
            Member member = memberService.findByEmail(email);
            int memberId = member.getId();

            System.out.println(memberId);

            List orderLists = orderService.findById4AppointmentList(memberId);

            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, orderLists);
        }catch (Exception e){
            return new Result(true, MessageConstant.QUERY_ORDER_FAIL);
        }

    }
}
