package com.yen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yen.entity.Business;
import com.yen.service.BusinessService;
import com.yen.mapper.BusinessMapper;
import org.springframework.stereotype.Service;

/**
* @author Yossi
* @description 针对表【business】的数据库操作Service实现
* @createDate 2024-03-22 15:51:17
*/
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business>
    implements BusinessService{

}




