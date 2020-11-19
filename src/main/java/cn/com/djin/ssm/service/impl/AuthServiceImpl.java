package cn.com.djin.ssm.service.impl;

import cn.com.djin.ssm.entity.Authority;
import cn.com.djin.ssm.entity.User;
import cn.com.djin.ssm.mapper.AuthorityMapper;
import cn.com.djin.ssm.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//权限业务层实现类
@Service
@Transactional(readOnly = false)
public class AuthServiceImpl extends BaseServiceImpl<Authority> implements AuthService {

    //根据用户信息对象loginUser查询权限数据
    @Override
    public List<Map<String,Object>> findAuthByLogin(User loginUser) throws Exception {
//        loginUser = new User();    //这是为了跳过登录页面  new了一个用户对象
//        loginUser.setRoleId(1);

        //1.新建一个一级权限及其包含的二级权限的ListMap集合0
        List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
        //2.查询出此用户拥有的一级权限
        List<Authority> firstAuths = authorityMapper.selAuthByRoleIdandParent(loginUser.getRoleId(), 0);
        //3.循环遍历一级权限 并在此循环中创建dataMap
        for (Authority firstAuth:firstAuths) {
            //4.新建map
            Map<String,Object> dataMap = new HashMap<String,Object>();
            //5.装一级权限的名字和id
            dataMap.put("pName",firstAuth.getAuthorityName());
            dataMap.put("pId",firstAuth.getId());
            //6.查询此一级权限下的二级权限（list形式）
            List<Authority> secondAuths = authorityMapper.selAuthByRoleIdandParent(loginUser.getRoleId(), firstAuth.getId());
            //7.把二级权限装入dataMap
            dataMap.put("secondAuths",secondAuths);
            //8.把dataMap装入最外层的List集合
            listMap.add(dataMap);
        }


        return listMap;
    }
}
