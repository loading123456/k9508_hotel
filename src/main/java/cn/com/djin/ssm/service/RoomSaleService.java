package cn.com.djin.ssm.service;

import cn.com.djin.ssm.entity.RoomSale;

import java.util.List;
import java.util.Map;

//消费记录的业务层接口
public interface RoomSaleService extends BaseService<RoomSale> {
        //查询房间的销售金额，返回map
    Map<String, List<Object>> findPriceByRoomNum() throws Exception;
}
