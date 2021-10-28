package org.eeasonloo.controller;

import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.constant.RedisMessageConstant;
import org.eeasonloo.entity.Result;
import org.eeasonloo.utils.MailUtils;
import org.eeasonloo.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RequestMapping("/validateCode")
@RestController
public class ValidateCodeContoller {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String emailAddress){
        Integer code = ValidateCodeUtils.generateValidateCode(4);

        try {
            MailUtils.sendShortMessage(MailUtils.VALIDATE_CODE,emailAddress,code.toString());
        } catch (Exception e) {
            e.printStackTrace();

            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }

        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(
                emailAddress + RedisMessageConstant.SENDTYPE_ORDER,5 * 60,code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }


    //手机快速登录时发送手机验证码
    @RequestMapping("/send4Login")
    public Result send4Login(String email){
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验证码
        try {
            //发送短信
            MailUtils.sendShortMessage(MailUtils.VALIDATE_CODE,email,code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(email+RedisMessageConstant.SENDTYPE_LOGIN,
                5 * 60,
                code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }



}
