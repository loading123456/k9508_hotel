package cn.com.djin.ssm.test;

import cn.com.djin.ssm.entity.InRoomInfo;
import cn.com.djin.ssm.service.InRoomInfoService;
import cn.com.djin.ssm.service.impl.InRoomInfoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class InRoomInfoServiceTest {
    //日志对象
    private static final Logger log = LogManager.getLogger(InRoomInfoServiceTest.class);
    //依赖引入员工业务层对象
    private InRoomInfoService inRoomInfoService;
    //读取spring.xml文件
    @Before
    public void init() {
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("spring-main.xml");
        inRoomInfoService = cxt.getBean("inRoomInfoServiceImpl", InRoomInfoServiceImpl.class);
    }
    //测试根据条件查询入住信息数据
    @Test
    public void test01(){
        InRoomInfo inRoomInfo=new InRoomInfo();
        try {
            Map<String, Object> map = inRoomInfoService.findPageTByPramas(1, 2, inRoomInfo);
            log.info("总的数据条数："+map.get("count")+"条");
            List<InRoomInfo> inRoomInfos = (List<InRoomInfo>) map.get("data");
            for (InRoomInfo i:inRoomInfos) {
                log.info(i);
                log.info(i.getRooms());
                log.info(i.getRooms().getRoomType());
            }
        } catch (Exception e){

        }

    }
}
