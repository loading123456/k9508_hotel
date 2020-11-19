package cn.com.djin.ssm.controller;


import cn.com.djin.ssm.entity.Authority;
import cn.com.djin.ssm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController<Authority>{

    @RequestMapping("/toIndex")
    public String toIndex(Model model, HttpSession session){

        User loginUser = (User) session.getAttribute("loginUser");//从session中取出loginUser
        try {
            //根据登录的用户信息查询出拥有的用户权限，放入model对象带入平台首页
            model.addAttribute("listMap",authService.findAuthByLogin(loginUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
