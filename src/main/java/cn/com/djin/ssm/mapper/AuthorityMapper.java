package cn.com.djin.ssm.mapper;

import cn.com.djin.ssm.entity.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *   权限Mapper代理对象
 */
public interface AuthorityMapper extends BaseMapper<Authority>{
    //根据角色id和parent（是否第一权限）查询一级权限
    List<Authority> selAuthByRoleIdandParent(@Param("roleId") Integer roleId, @Param("parent") Integer parent) throws Exception;
}