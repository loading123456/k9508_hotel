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
    <title>用户显示页面</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="lib/layui/css/layui.css">
    <!--引入layui的js文件-->
    <script src="lib/layui/layui.js"></script>
    <style type="text/css">
        .layui-table td{
            height: 60px;
        }
    </style>
</head>
<body>
<div align="center">
    <h1>用户显示页面</h1>
    <table id="demo" lay-filter="test"></table>
</div>
</body>
<!--引入layui的js文件-->
<script src="js/showUser.js"></script>
<!--工具条-->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
</script>
<!--是否可用自定义模板-->
<script type="text/html" id="useStatusTpl">
    {{#  if(d.useStatus == 1){ }}
    <font color="green">可用</font>
    {{#  } else { }}
    <font color="red">不可用</font>
    {{#  } }}
</script>
</html>