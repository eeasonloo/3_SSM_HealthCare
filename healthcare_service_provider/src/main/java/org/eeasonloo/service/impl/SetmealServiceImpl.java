package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.eeasonloo.dao.SetmealDao;
import org.eeasonloo.pojo.Setmeal;
import org.eeasonloo.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        // 1. add setmeal and get LAST_INSERTED_ID
        setmealDao.add(setmeal);
        // 2. add assocations in table setmeal and checkgroup
        if (checkgroupIds.length > 0 && checkgroupIds != null) {
            addAssociations(setmeal.getId(), checkgroupIds);
        }
    }

    private void addAssociations(Integer setmealId, Integer[] checkgroupIds) {
        Map<String, Integer> map = new HashMap<>();

        for (Integer checkgroupId : checkgroupIds) {
            map.put("setmeal_id", setmealId);
            map.put("checkgroup_id", checkgroupId);
            setmealDao.addAssociationsSetmealAndCheckgroup(map);
        }


    }
}
