package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.QueryPageBean;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.CheckGroup;
import org.eeasonloo.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.add(checkitemIds,checkGroup);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
    }

    @RequestMapping("/findById")
    public Result findById(Integer checkgroupId){
        try {
            CheckGroup checkGroup = checkGroupService.findById(checkgroupId);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findCheckItemIdsbyCheckGroupId")
    public Result findCheckItemIdsbyCheckGroupId(Integer checkgroupId){
        try {
            List<Integer> checkItemIds = checkGroupService.findCheckItemIdsbyCheckGroupId(checkgroupId);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("edit")
    public Result edit(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.edit(checkitemIds, checkGroup);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

}
