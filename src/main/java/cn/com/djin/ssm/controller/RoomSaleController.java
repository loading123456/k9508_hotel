package cn.com.djin.ssm.controller;

import cn.com.djin.ssm.entity.RoomSale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

//消费记录控制器
@Controller
@RequestMapping("/roomSale")
public class RoomSaleController extends BaseController<RoomSale> {
    //做数据分析（每个房间的销售金额）
    @RequestMapping("/loadPriceByRoomNum")
    public @ResponseBody Map<String, List<Object>> loadPriceByRoomNum() {
        try {
            return roomSaleService.findPriceByRoomNum();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
