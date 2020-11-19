package cn.com.djin.ssm.service.impl;

import cn.com.djin.ssm.entity.RoomType;
import cn.com.djin.ssm.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/*
* 房屋类型实现类
* */
@Service
@Transactional(readOnly = false)
public class RoomTypeServiceImpl extends BaseServiceImpl<RoomType> implements RoomTypeService {

}
