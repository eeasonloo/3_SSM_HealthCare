package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.eeasonloo.dao.SetmealDao;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.entity.RedisConstant;
import org.eeasonloo.pojo.CheckGroup;
import org.eeasonloo.pojo.Setmeal;
import org.eeasonloo.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        // 1. add setmeal and get LAST_INSERTED_ID
        setmealDao.add(setmeal);
        // 2. add assocations in table setmeal and checkgroup
        if (checkgroupIds.length > 0 && checkgroupIds != null) {
            addAssociations(setmeal.getId(), checkgroupIds);
        }

        //3. Add imgFilename to Redis(connected to Database[not garbage image])
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    @Override
    public PageResult findPage(int pageSize, int currentPage, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    private void addAssociations(Integer setmealId, Integer[] checkgroupIds) {
        Map<String, Integer> map = new HashMap<>();

        for (Integer checkgroupId : checkgroupIds) {
            map.put("setmeal_id", setmealId);
            map.put("checkgroup_id", checkgroupId);
            setmealDao.addAssociationsSetmealAndCheckgroup(map);
        }
    }

    @Override
    public void delete(Integer id) {
        int count = setmealDao.findCountBySetmealId(id);

        if(count > 0) throw new RuntimeException("The Setmeal is currently binded to some CheckGroup, it cant be delete!");


        //2. if not referenced, Delete operations invoke.
        setmealDao.deleteById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsbySetmealId(Integer setmealId) {
        return setmealDao.findCheckGroupIdsbySetmealId(setmealId);
    }

    @Override
    public void edit(Integer[] checkgroupIds, Setmeal setmeal) {
        //1. update t_setmeal
        setmealDao.edit(setmeal);

        //2. delete association in t_setmeal_checkgroup when id = current setmeal Id
        setmealDao.deleteAssociations(setmeal.getId());

        //3. add association in t_setmeal_checkgroup
        setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);

    }

    private void setSetmealAndCheckGroup(Integer setmealId, Integer[] checkGroupIds) {

        if(checkGroupIds != null && checkGroupIds.length > 0){
            Map<String,Integer> setmeal_checkGroupMap = new HashMap<>();

            for (Integer checkGroupId : checkGroupIds) {
                setmeal_checkGroupMap.put("setmeal_id", setmealId);
                setmeal_checkGroupMap.put("checkgroup_id",checkGroupId);
                setmealDao.setSetmealAndCheckGroup(setmeal_checkGroupMap);
            }
        }
    }
}
