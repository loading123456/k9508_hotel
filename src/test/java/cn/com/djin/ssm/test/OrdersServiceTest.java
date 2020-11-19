package cn.com.djin.ssm.test;


import cn.com.djin.ssm.entity.Orders;
import cn.com.djin.ssm.service.InRoomInfoService;
import cn.com.djin.ssm.service.OrdersService;
import cn.com.djin.ssm.service.impl.InRoomInfoServiceImpl;
import cn.com.djin.ssm.service.impl.OrdersServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

    //订单测试类
    public class OrdersServiceTest {
    //日志对象
    private static final Logger log = LogManager.getLogger(OrdersServiceTest.class);
    //依赖引入员工业务层对象
    private OrdersService ordersService;
    //读取spring.xml文件
    @Before
    public void init() {
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("spring-main.xml");
        ordersService = cxt.getBean("ordersServiceImpl", OrdersServiceImpl.class);
    }

    //测试批量删除
        @Test
    public void test1(){
        Integer ids[] ={32,33,34};
        Orders orders = new Orders();
        orders.setFlag("0");
        try {
            String s = ordersService.updBatchByPrimaryKeySelective(ids, orders);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
