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
    <title>入住信息查询</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="lib/layui/css/layui.css">
    <!--引入layui的js文件-->
    <script src="lib/layui/layui.js"></script>
    <style type="text/css">
        .layui-table td{
            height: 60px;
        }
        .layui-table td img{
            width:60px;
            height: 60px;
        }
    </style>
</head>
<body>
    <jsp:include page="exitRooms.jsp"/><%--包含页面--%>
    <div align="center">
        <h1>入住信息查询</h1>
        <table id="demo" lay-filter="test"></table>
    </div>
</body>
    <!--引入自定义的js文件-->
    <script src="js/showInRoomInfo.js"></script>
    <!--性别自定义模板-->
    <script type="text/html" id="genderTpl">
        {{#  if(d.gender == 1){ }}
        <font color="blue">男</font>
        {{#  } else { }}
        <font color="#ffc0cb">女</font>
        {{#  } }}
    </script>
    <!--是否会员自定义模板-->
    <script type="text/html" id="isVipTpl">
        {{#  if(d.isVip == 1){ }}
        <font color="red">是</font>
        {{#  } else { }}
        <font color="gray">否</font>
        {{#  } }}
    </script>
    <!--房屋入住信息自定义模板-->
    <script type="text/html" id="outRoomStatusTpl">
        {{#  if(d.outRoomStatus == 1){ }}
        <font color="green">已退房</font>
        {{#  } else { }}
        <font color="red">未退房</font>
        {{#  } }}
    </script>
    <!--工具条-->
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
        {{#  if(d.outRoomStatus == 0){ }}
        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="edit">退房</a>
        {{#  } else { }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        {{#  } }}
    </script>
</html>