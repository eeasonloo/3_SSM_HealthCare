package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.Result;
import org.eeasonloo.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private MemberService memberService;
    /**
     * 会员数量统计
     * @return
     */
    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-12);//获得当前日期之前12个月的日期

        List<String> list = new ArrayList<String>();
        for(int i=0;i<12;i++){
            calendar.add(Calendar.MONTH,1);
            list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("months",list);

        List<Integer> memberCount = memberService.findMemberCountByMonth(list);
        map.put("memberCount",memberCount);

        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }
}
