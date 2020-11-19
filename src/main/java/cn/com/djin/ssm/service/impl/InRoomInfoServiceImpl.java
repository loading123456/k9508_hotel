package cn.com.djin.ssm.service.impl;

import cn.com.djin.ssm.entity.InRoomInfo;
import cn.com.djin.ssm.entity.Rooms;
import cn.com.djin.ssm.service.InRoomInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *   房屋入住信息业务层实现类
 */
@Service
@Transactional(readOnly = false)
public class InRoomInfoServiceImpl extends BaseServiceImpl<InRoomInfo> implements InRoomInfoService {
        //重写入住信息添加的业务层方法
    @Override
    public String saveT(InRoomInfo inRoomInfo) throws Exception {
        //入住信息添加，同时修改房屋状态
        //1.房屋状态修改
        Rooms rooms = new Rooms();
        rooms.setId(inRoomInfo.getRoomId());
        rooms.setRoomStatus("1"); //"1"为已入住状态
        Integer updRooms = roomsMapper.updateByPrimaryKeySelective(rooms); //房屋状态改为1（入住） 或2（打扫）后要在页面中禁用那个房屋
        //2.完成入住信息添加
        Integer insINI = baseMapper.insert(inRoomInfo);
        if(updRooms>0 && insINI>0){
            return "success";
        }else{
            return "fail";
        }
    }
}
