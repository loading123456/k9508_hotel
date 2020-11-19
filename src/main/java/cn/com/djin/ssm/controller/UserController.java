package cn.com.djin.ssm.controller;

import cn.com.djin.ssm.entity.User;
import cn.com.djin.ssm.utils.MD5;
import cn.com.djin.ssm.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//用户控制器
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User>{
    //产生动态验证码
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        //产生验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(5);
        //将字符串转化成小写存在session中
        session.setAttribute("verifyCode",verifyCode.toLowerCase());
        //验证码图片的宽度 高度 图片的输出流对象 验证码
        VerifyCodeUtils.outputImage(230,50,response.getOutputStream(),verifyCode);
    }

    //进行验证码验证
    @RequestMapping("/userLoginVerify")
    public @ResponseBody String userLoginVerify(String verify, HttpSession session){
        verify = verify.toLowerCase();
        //取出session中的验证码
        String verifyCode = (String) session.getAttribute("verifyCode");
        if(verify.equals(verifyCode)){
            return "success";
        }else{
            return "fail";
        }
    }

    //进行登录
    @RequestMapping("/login")
    public @ResponseBody String login(User user,HttpSession session){
        System.out.println(user.toString());

        //将用户传来的密码进行md5加密操作
        String pwd = MD5.md5crypt(user.getPwd()); //进行加密
        user.setPwd(pwd);//将加密后的密码重新设置到登录用户对象中
        try {
            User loginUser = baseService.findTByPramas(user);
            if(loginUser!=null){
                session.setAttribute("loginUser",loginUser);   //将名为loginUser的用户对象存入session
                return "success";
            }else{
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //退出登录
    @RequestMapping("/exit")
    public @ResponseBody String exit(HttpSession session){
        try {
            session.removeAttribute("loginUser");//移除session域里的user对象
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    //修改用户密码
    @RequestMapping("/updUsername")
    public @ResponseBody String updUsername(User user){
        try {
            if (baseService.updByPrimaryKeySelective(user).equals("success")){
                return user.getUsername();
            }else{
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/getCountByPramas")
    public @ResponseBody Integer getCountByPramas(User user) {
        try {
            return baseService.getCountByPramas(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
