package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.QueryPageBean;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.Member;
import org.eeasonloo.pojo.Setmeal;
import org.eeasonloo.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Reference
    MemberService memberService;

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = memberService.findPage(queryPageBean.getPageSize(),
                    queryPageBean.getCurrentPage(), queryPageBean.getQueryString());
            return new Result(true, MessageConstant.QUERY_MEMBER_SUCCESS,pageResult);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_MEMBER_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer memberId){
        try {
            Member member = memberService.findById(memberId);
            return new Result(true, MessageConstant.QUERY_MEMBER_SUCCESS,member);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_MEMBER_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){

        try {
            memberService.delete(id);
        } catch (RuntimeException r) {
            return new Result(false,r.getMessage());
        }catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_MEMBER_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_MEMBER_SUCCESS);

    }

    @RequestMapping("/edit")
    public Result edit( @RequestBody Member member){
        try {
            memberService.edit(member);
            return new Result(true, MessageConstant.EDIT_MEMBER_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_MEMBER_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Member member) {
        try {
            memberService.add(member);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);
        }
        return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
    }
}
