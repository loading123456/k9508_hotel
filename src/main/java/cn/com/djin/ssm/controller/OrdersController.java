package cn.com.djin.ssm.controller;

import cn.com.djin.ssm.entity.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


//订单控制器层
@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController<Orders>{
    //去支付
    @RequestMapping("/toPay")
    public String toPay(String orderNum, Double orderMoney, HttpServletRequest request){
        //带上要支付的订单编号和金额
        request.setAttribute("orderNum",orderNum);
        request.setAttribute("orderMoney",orderMoney);
        return "alipay/ordersPay";
    }

    /*支付成功之后的操作
    * 1.订单状态的修改   把orderStatus从0改成1
    * 2.生成消费记录
    * 3.跳转到平台首页
    * */
    @RequestMapping("/myOrdersPay")
    public @ResponseBody
    String myOrdersPay(String orderNum){
        System.out.println(orderNum);
        Orders orders = new Orders();
        orders.setOrderNum(orderNum);  //这个实体类对象只有一个订单编号
        try {
            return ordersService.payOrdersAfter(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        //return "redirect:/model/toIndex";//重定向到主页
    }
}
