package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.eeasonloo.dao.CheckItemDao;
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
}
