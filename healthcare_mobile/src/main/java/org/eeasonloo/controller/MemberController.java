package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.constant.RedisMessageConstant;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.Member;
import org.eeasonloo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RequestMapping("/member")
@RestController
public class MemberController {
    @Reference
    private MemberService memberService;
    @Autowired
    private JedisPool jedisPool;

    //使用手机号和验证码登录
    @RequestMapping("/login")
    public Result login(HttpServletResponse response, @RequestBody Map map){
        String email = (String) map.get("email");
        String validateCode = (String) map.get("validateCode");
        //从Redis中获取缓存的验证码
        String codeInRedis =
                jedisPool.getResource().get(email+ RedisMessageConstant.SENDTYPE_LOGIN);
        if(codeInRedis == null || !codeInRedis.equals(validateCode)){
            //验证码输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }else{
            //验证码输入正确
            //判断当前用户是否为会员
            Member member = memberService.findByEmail(email);
            if(member == null){
                //当前用户不是会员，自动完成注册
                member = new Member();
                member.setEmail(email);
                member.setRegTime(new Date());
                memberService.add(member);
            }
            //登录成功
            //写入Cookie，跟踪用户
            Cookie cookie = new Cookie("login_member_email",email);
            cookie.setPath("/");//路径
            cookie.setMaxAge(60*60*24*30);//有效期30天
            response.addCookie(cookie);
            //保存会员信息到Redis中
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(email,60*30,json);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        }
    }

    @RequestMapping("/findByEmail")
    public Result findByEmail(@CookieValue(name = "login_member_email") String email){
        String userInfo = jedisPool.getResource().get(email);

        if(userInfo != null){
            return new Result(true, MessageConstant.QUERY_MEMBER_SUCCESS, JSON.parse(userInfo));
        }else{
            return new Result(false,MessageConstant.QUERY_MEMBER_FAIL);
        }

    }





}
