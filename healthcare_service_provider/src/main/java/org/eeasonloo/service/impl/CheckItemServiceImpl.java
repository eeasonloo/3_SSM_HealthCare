package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.record.DVALRecord;
import org.eeasonloo.dao.CheckItemDao;
import org.eeasonloo.entity.PageResult;
import org.eeasonloo.pojo.CheckItem;
import org.eeasonloo.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //PageHelper input 3 elements get 2 results(dataList, totalPage)
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) throws RuntimeException{
        //1. Check from t_checkitem_checkgroup table, whether current id is referenced.
        //if referenced, count > 0, cant delete, throw Runtime Exception. Vice versa.
        int count = checkItemDao.findCountByCheckItemId(id);

        if(count > 0) throw new RuntimeException("The Checkitem is currently referenced to CheckGroup, it cant be delete!");


        //2. if not referenced, Delete operations invoke.
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }


}
