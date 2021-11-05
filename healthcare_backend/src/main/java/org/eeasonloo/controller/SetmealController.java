package org.eeasonloo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.QueryPageBean;
import org.eeasonloo.entity.RedisConstant;
import org.eeasonloo.entity.Result;
import org.eeasonloo.pojo.CheckGroup;
import org.eeasonloo.pojo.Setmeal;
import org.eeasonloo.service.CheckGroupService;
import org.eeasonloo.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            String fileSavePath = "C:\\my_java\\repository\\healthcare_parent\\healthcare_backend\\src\\main\\webapp\\upload";
            // Generate a unique UUID name for image + suffix(.jpg)
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(lastIndexOf);
            String newFilename = UUID.randomUUID().toString() + suffix;

            File uploadSaveFile = new File(fileSavePath, newFilename);
            imgFile.transferTo(uploadSaveFile);

            // Add to Redis, so we can recognise the garbage image(doesnt link to database)
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,newFilename);

            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, newFilename);

        } catch (IOException e) {
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(Integer[] checkgroupIds, @RequestBody Setmeal setmeal) {
        try {
            setmealService.add(checkgroupIds, setmeal);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = setmealService.findPage(queryPageBean.getPageSize(),
                    queryPageBean.getCurrentPage(), queryPageBean.getQueryString());
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    @RequestMapping("/delete")
    public Result delete(Integer id){

        try {
            setmealService.delete(id);
        } catch (RuntimeException r) {
            return new Result(false,r.getMessage());
        }catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }

    @RequestMapping("/findById")
    public Result findById(Integer setmealId){
        try {
            Setmeal setmeal = setmealService.findById(setmealId);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,setmeal);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Setmeal> setmealList = setmealService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, setmealList);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

    @RequestMapping("/findCheckGroupIdsbySetmealId")
    public Result findCheckGroupIdsbySetmealId(Integer setmealId){
        try {
            List<Integer> checkGroupIds = setmealService.findCheckGroupIdsbySetmealId(setmealId);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkGroupIds);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] checkgroupIds, @RequestBody Setmeal setmeal){
        try {
            setmealService.edit(checkgroupIds, setmeal);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

}
