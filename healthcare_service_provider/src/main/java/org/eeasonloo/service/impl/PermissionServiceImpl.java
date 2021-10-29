package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.eeasonloo.dao.CheckItemDao;
import org.eeasonloo.dao.PermissionDao;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.Permission;
import org.eeasonloo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //PageHelper input 3 elements get 2 results(dataList, totalPage)
        PageHelper.startPage(currentPage,pageSize);
        Page<Permission> page = permissionDao.selectByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) throws RuntimeException{
        //1. Check from t_checkitem_checkgroup table, whether current id is referenced.
        //if referenced, count > 0, cant delete, throw Runtime Exception. Vice versa.
        int count = permissionDao.findCountByPermissionId(id);

        if(count > 0) throw new RuntimeException("The Checkitem is currently referenced to CheckGroup, it cant be delete!");


        //2. if not referenced, Delete operations invoke.
        permissionDao.deleteById(id);
    }

    @Override
    public Permission findById(Integer id) {
        return permissionDao.findById(id);
    }

    @Override
    public void edit(Permission permission) {
        permissionDao.edit(permission);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }


}
