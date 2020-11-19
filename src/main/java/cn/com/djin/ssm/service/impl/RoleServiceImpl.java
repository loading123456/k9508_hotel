package cn.com.djin.ssm.service.impl;

import cn.com.djin.ssm.entity.Authority;
import cn.com.djin.ssm.entity.Roles;
import cn.com.djin.ssm.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = false)
//角色业务层实现类
public class RoleServiceImpl extends BaseServiceImpl<Roles> implements RoleService {
    //重写角色的分页方法
    @Override
    public Map<String, Object> findPageTByPramas(Integer page, Integer limit, Roles roles) throws Exception {
        Map<String, Object> map = super.findPageTByPramas(page, limit, roles);
        //取出数据集合
        List<Roles> rolesList = (List<Roles>) map.get("data");
        //对角色集合进行遍历分别获取权限
        for (Roles role:rolesList) {
            //根据角色id查一级权限
            List<Authority> authorities = authorityMapper.selAuthByRoleIdandParent(role.getId(), 0);
            String authName = "";
            for (Authority authority:authorities) {
                authName += authority.getAuthorityName() + ",";
            }
            authName = authName.substring(0 ,authName.length()-1);
            role.setAuthNames(authName);
        }
        return map;
    }
}
