package cn.com.djin.ssm.service.impl;

import cn.com.djin.ssm.entity.Vip;
import cn.com.djin.ssm.service.BaseService;
import cn.com.djin.ssm.service.VipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//vip业务层实现类
@Service
@Transactional(readOnly = false)//表明所注解的方法或类是增加，删除，修改数据
public class VipServiceImpl extends BaseServiceImpl<Vip> implements VipService {
}
