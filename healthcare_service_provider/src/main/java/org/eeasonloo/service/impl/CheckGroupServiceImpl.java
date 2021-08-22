package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.eeasonloo.dao.CheckGroupDao;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.CheckGroup;
import org.eeasonloo.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(Integer[] checkItemIds, CheckGroup checkGroup) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(),checkItemIds);

    }


    private void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkItemIds) {

        if(checkItemIds != null && checkItemIds.length > 0){
            Map<String,Integer> checkGroup_checkItemMap = new HashMap<>();

            for (Integer checkItemId : checkItemIds) {
                checkGroup_checkItemMap.put("checkgroup_id", checkGroupId);
                checkGroup_checkItemMap.put("checkitem_id",checkItemId);
                checkGroupDao.setCheckGroupAndCheckItem(checkGroup_checkItemMap);
            }
        }
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(Integer checkgroupId) {
        return checkGroupDao.findById(checkgroupId);
    }

    @Override
    public List<Integer> findCheckItemIdsbyCheckGroupId(Integer checkgroupId) {
        return checkGroupDao.findCheckItemIdsbyCheckGroupId(checkgroupId);
    }

    @Override
    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        //1. update t_checkgroup
        checkGroupDao.edit(checkGroup);

        //2. delete association in t_checkgroup_checkitem when id = current checkGroup Id
        checkGroupDao.deleteAssociations(checkGroup.getId());

        //3. add association in t_checkgroup_checkitem
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);


    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
