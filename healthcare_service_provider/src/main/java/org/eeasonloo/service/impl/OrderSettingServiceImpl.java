package org.eeasonloo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.eeasonloo.service.OrderSettingService;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl {


}
