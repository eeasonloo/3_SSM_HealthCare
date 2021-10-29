package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.QueryPageBean;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.CheckItem;
import org.eeasonloo.pojo.Permission;
import org.eeasonloo.service.CheckItemService;
import org.eeasonloo.service.PermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    @RequestMapping("/add")
    public Result add(@RequestBody Permission permission){
        try{
            permissionService.add(permission);
        }catch(Exception e){
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

            PageResult pageResult = permissionService.findPage(queryPageBean.getCurrentPage(),
                    queryPageBean.getPageSize(),queryPageBean.getQueryString());

        return pageResult;
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        List<Permission> permissionList;
        permissionList = permissionService.findAll();
        if(permissionList != null && permissionList.size()> 0){
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,permissionList);
        }
        return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
    }


    @RequestMapping("/delete")
    public Result delete(Integer id){

        try {
            permissionService.delete(id);
        } catch (RuntimeException r) {
            return new Result(false,r.getMessage());
        }catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        Permission permission;
        try {
           permission = permissionService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,permission);
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Permission permission){
        try {
            permissionService.edit(permission);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }




}
