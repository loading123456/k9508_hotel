package cn.com.djin.ssm.test;

import cn.com.djin.ssm.entity.InRoomInfo;
import cn.com.djin.ssm.entity.RoomSale;
import cn.com.djin.ssm.service.InRoomInfoService;
import cn.com.djin.ssm.service.RoomSaleService;
import cn.com.djin.ssm.service.impl.InRoomInfoServiceImpl;
import cn.com.djin.ssm.service.impl.RoomSaleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class RoomSaleServiceTest {
    //日志对象
    private static final Logger log = LogManager.getLogger(InRoomInfoServiceTest.class);
    //依赖引入消费记录业务层对象
    private RoomSaleService roomSaleService;
    //读取spring.xml文件
    @Before
    public void init() {
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("spring-main.xml");
        roomSaleService = cxt.getBean("roomSaleServiceImpl", RoomSaleServiceImpl.class);
    }

    //测试根据条件查询入住信息数据
    @Test
    public void test01(){
        RoomSale roomSale = new RoomSale();
        try {
            Map<String, Object> map = roomSaleService.findPageTByPramas(1, 3, roomSale);
            log.info(map.get("count"));
            List<RoomSale> roomSales = (List<RoomSale>) map.get("data");
            for (RoomSale r:roomSales) {
               log.info(r);
            }
        } catch (Exception e){

        }

    }
}
