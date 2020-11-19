package cn.com.djin.ssm.service.impl;

import cn.com.djin.ssm.entity.InRoomInfo;
import cn.com.djin.ssm.entity.Orders;
import cn.com.djin.ssm.entity.RoomSale;
import cn.com.djin.ssm.entity.Rooms;
import cn.com.djin.ssm.service.OrdersService;
import cn.com.djin.ssm.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaSize;


//订单业务层实现类
@Service
@Transactional(readOnly = false)
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {
    //重写订单添加方法（控制在一个事务当中）

    @Override
    public String saveT(Orders orders) throws Exception {
        //1.完成入住信息的修改（根据主键动态修改）
            //新建一个入住信息对象
        InRoomInfo inRoomInfo = new InRoomInfo();
        inRoomInfo.setId(orders.getIriId());
        inRoomInfo.setOutRoomStatus("1");
        int updINI = inRoomInfoMapper.updateByPrimaryKeySelective(inRoomInfo);
        //2.完成订单数据的添加
        int insOrd=baseMapper.insert(orders);
        //3.房屋状态修改
        InRoomInfo inRoomInfo1 = inRoomInfoMapper.selectByPrimaryKey(orders.getIriId());
        Rooms rooms = new Rooms();
        rooms.setId(inRoomInfo1.getRoomId());
        rooms.setRoomStatus("2");   //2是打扫状态
        int updRooms = roomsMapper.updateByPrimaryKeySelective(rooms);
        if (updINI>0 && insOrd>0 && updRooms>0) {
            return "success";
        }else {
            return "fail";
        }
    }

    /*
      自定义一个方法。。。支付完成后的操作
    * 1.订单状态的修改
    * 2.生成消费记录
    *
    * */

    @Override
    public String payOrdersAfter(Orders orders) throws Exception {
        //根据条件查询订单数据
        Orders orders1 = baseMapper.selectTByPramas(orders); //根据订单编号查到了订单数据
        orders1.setOrderStatus("1");                          //把订单状态改成1
        int updOrdersStatus = baseMapper.updateByPrimaryKeySelective(orders1); //在数据库中修改
        //添加消费记录
        RoomSale roomSale = new RoomSale();
        //得到相关数据
        String[] orderOthers = orders1.getOrderOther().split(",");
        //退房时的各种金额  数组
        String[] orderPrices = orders1.getOrderPrice().split(",");
        //设置消费记录对象的相关属性值
        roomSale.setRoomNum(orderOthers[0]);
        roomSale.setCustomerName(orderOthers[1]);
        roomSale.setStartDate(DateUtil.stringToDate(orderOthers[2]));
        roomSale.setEndDate(DateUtil.stringToDate(orderOthers[3]));
        roomSale.setDays(Integer.valueOf(orderOthers[4]));
        roomSale.setRoomPrice(Double.valueOf(orderPrices[0]));
        roomSale.setOtherPrice(Double.valueOf(orderPrices[1]));
        roomSale.setRentPrice(Double.valueOf(orderPrices[2]));
        roomSale.setSalePrice(roomSale.getRentPrice()+roomSale.getOtherPrice());
        roomSale.setDiscountPrice(roomSale.getRoomPrice()*roomSale.getDays()-roomSale.getRentPrice());

        int inRoomSale = roomSaleMapper.insert(roomSale);
        if(updOrdersStatus>0 && inRoomSale>0 ){
            return "success";
        }else{
            return "fail";
        }
    }


}
