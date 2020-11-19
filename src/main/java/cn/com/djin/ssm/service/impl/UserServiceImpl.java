package cn.com.djin.ssm.service.impl;

import cn.com.djin.ssm.entity.Authority;
import cn.com.djin.ssm.entity.Roles;
import cn.com.djin.ssm.entity.User;
import cn.com.djin.ssm.mapper.RolesMapper;
import cn.com.djin.ssm.service.UserService;
import cn.com.djin.ssm.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//用户业务层实现类
@Service
@Transactional(readOnly = false)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    //重写用户的分页方法
    @Override
    public Map<String, Object> findPageTByPramas(Integer page, Integer limit, User user) throws Exception {
        Map<String, Object> map = super.findPageTByPramas(page, limit, user);
        List<User> userList = (List<User>) map.get("data");//取出用户list
        //对集合进行遍历
        for (User  user1: userList) {
            //根据角色id查询一级权限
            List<Authority> authorities = authorityMapper.selAuthByRoleIdandParent(user1.getRoleId(), 0);
            String authName ="";
            //拼接一级权限 存回去
            for (Authority authority :authorities) {
                authName += authority.getAuthorityName()+",";
            }
            authName = authName.substring(0,authName.length()-1);
            user1.setAuthName(authName);
        }
        return map;
    }

    @Override
    public String saveT(User user) throws Exception {
        user.setUseStatus("1");
        Integer roleId = user.getRoleId();//获得roleId
        String roleName = "";
        if (roleId == 1) {
            roleName = "超级管理员";
        }else if(roleId == 2){
            roleName = "主管";
        }else{
            roleName = "前台";
        }
        user.setIsAdmin(roleName);//存入roleName
        String pwd = MD5.md5crypt(user.getPwd()); //进行加密
        user.setPwd(pwd);//将加密后的密码重新设置到登录用户对象中
        return super.saveT(user);
    }
}
