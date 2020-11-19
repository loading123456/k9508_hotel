package cn.com.djin.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *   控制页面跳转的控制器
 */
@Controller
@RequestMapping("/model")
public class ModelController {

    //去到平台首页
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    //去到入住信息显示页面
    @RequestMapping("/toShowInRoomInfo")
    public String toShowInRoomInfo(){
        return "inRoomInfo/showInRoomInfo";
    }

    //跳转到订单显示页面
    @RequestMapping("/toShowOrders")
    public String toShowOrders(){
        return "orders/showOrders";
    }

    //去到入住信息添加页面
    @RequestMapping("/toSaveInRoomInfo")
    public String toSaveInRoomInfo(){
        return "inRoomInfo/saveInRoomInfo";
    }

    //去到消费记录信息显示页面
    @RequestMapping("/toShowRoomSale")
    public String toShowRoomSale(){
        return "roomSale/showRoomSale";
    }

    //去到会员信息显示页面
    @RequestMapping("/toShowVip")
    public String toShowVip(){
        return "vip/showVip";
    }

    //去到会员信息添加页面
    @RequestMapping("/toSaveVip")
    public String toSaveVip(){ return "vip/saveVip"; }

    //去到房屋信息显示页面
    @RequestMapping("/toShowRooms")
    public String toShowRooms(){ return "rooms/showRooms"; }

    //去到登录页面
    @RequestMapping("/loginUI")
    public String loginUI(){ return "login/login"; }

    //去到角色信息显示页面
    @RequestMapping("/toShowRole")
    public String toShowRole(){ return "role/showRole"; }

    //去用户信息显示页面
    @RequestMapping("/toShowUser")
    public String toShowUser(){
        return "user/showUser";
    }

    //去用户信息添加页面
    @RequestMapping("/toSaveUser")
    public String toSaveUser(){
        return "user/saveUser";
    }

    //去数据分析页面
    @RequestMapping("/toShowIdd")
    public String toShowIdd(){
        return "idd/showIdd";
    }

}
