package cn.com.djin.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    /** 主键 */
    private Integer id;

    /** 账号 */
    private String username;

    /** 密码 */
    private String pwd;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    /** 启用状态：1启用，0禁用 */
    private String useStatus;

    /** 1超级管理员，0普通管理员 */
    private String isAdmin;

    /** 角色id */
    private Integer roleId;

    //权限的字符串
    private String authName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createDate=" + createDate +
                ", useStatus='" + useStatus + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", roleId=" + roleId +
                ", authName='" + authName + '\'' +
                '}';
    }
}