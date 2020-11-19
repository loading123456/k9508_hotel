package cn.com.djin.ssm.service;

import cn.com.djin.ssm.entity.Authority;
import cn.com.djin.ssm.entity.User;

import java.util.List;
import java.util.Map;

//权限业务层接口
public interface AuthService extends BaseService<Authority> {
    //根据登录后获取此用户的菜单权限
    List<Map<String,Object>> findAuthByLogin(User loginUser) throws Exception;
}
