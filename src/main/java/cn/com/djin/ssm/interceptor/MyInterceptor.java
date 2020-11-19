package cn.com.djin.ssm.interceptor;

import cn.com.djin.ssm.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
* 自定义的拦截器
* 作用：   未登录访问首页会被拦截
* */
public class MyInterceptor implements HandlerInterceptor {
    //拦截器的核心方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //根据request容器得到session容器
        HttpSession session = request.getSession();
        //从session中取到登陆的用户
        User loginUser = (User) session.getAttribute("loginUser");
        //判断loginUser是不是空
        if (loginUser != null) {
            return true;//放行，继续执行其他拦截器
        }else{
            request.setAttribute("loginMsg","loginMsg");
            request.getRequestDispatcher("/model/loginUI").forward(request,response);//请求转发到登录页面
            return false;//阻止请求继续向下执行 将请求拦截下来
        }

    }
    //拦截post请求要执行的方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被执行了");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被执行了");
    }
}
