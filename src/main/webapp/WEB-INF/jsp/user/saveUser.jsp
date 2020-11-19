<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--http://localhost:8080/-->
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <!--引用基础路径-->
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息添加页面</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="lib/layui/css/layui.css">
    <!--引入layui的js文件-->
    <script src="lib/layui/layui.js"></script>
    <style type="text/css">
        .layui-form-item{
            margin-top: 32px;
        }
    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>用户信息添加</legend>
</fieldset>
<form class="layui-form layui-form-pane" action="">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-inline" style="width: 300px;">
                <input type="text" id="username" name="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">密码：</label>
        <div class="layui-input-inline" style="width: 300px;">
            <input type="text" id="pwd" name="pwd" placeholder="请输入密码" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色：</label>
        <div class="layui-input-inline" style="width: 300px;">
            <select name="roleId" lay-filter="roleId" lay-verify="required">
                <option value="">---请选择角色类型---</option>
                <option value="1">超级管理员</option>
                <option value="2">主管</option>
                <option value="3">前台</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-left: 100px;margin-top: 40px;">
        <button class="layui-btn" lay-submit="" lay-filter="demo2">确认添加</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>
</body>
<!--引入自定义的js文件-->
<script src="js/saveUser.js"></script>
</html>
