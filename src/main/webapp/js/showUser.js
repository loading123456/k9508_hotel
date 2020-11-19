layui.use(['jquery','layer','table','form','laydate'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        laydate = layui.laydate;

    var UserObj; //全局

    table.render({
        elem: '#demo'
        , height: 512
        , url: 'user/loadPageTByPramas' //数据接口
        , limit: 3
        , limits: [2, 3, 5, 8, 10, 15]
        , page: true //开启分页
        , even: true
        , cols: [[ //表头
            {type: 'checkbox'}
            , {field: 'id', title: 'ID', width: 80, align: 'center', sort: true}
            , {field: 'username', title: '用户名', align: 'center', width: 100,event: 'setUsername'}
            , {field: 'pwd', title: '密码', align: 'center', width: 300}
            , {field: 'isAdmin', title: '角色', align: 'center', width: 110, sort: true}
            , {field: 'authName', title: '权限', align: 'center', sort: true, width: 600, sort: true}
            , {field: 'createDate', title: '创建时间', align: 'center', width: 215, sort: true}
            , {field: 'useStatus', title: '是否可用', align: 'center', sort: true, width: 130, sort: true,templet: '#useStatusTpl'}
            , {fixed: 'right', width: 110, title: '操作', align: 'center', toolbar: '#barDemo'}
        ]]
    });

    //监听单元格事件
    table.on('tool(test)', function(obj){
        UserObj = obj;
        var data = obj.data;
        if(obj.event === 'setUsername'){
            layer.prompt({  //弹框
                formType: 2
                ,title: '修改 ID 为 ['+ data.id +'] 的用户名'
                ,value: data.username
            }, function(value, index){
                //这里一般是发送修改的Ajax请求
                updUsername(data.id,value);
                layer.close(index);
            });
        }
    });

    function updUsername(id,username) {
        $.ajax({
            type:'POST',
            url:'user/updUsername',
            data:{"id":id,"username":username},
            success:function (data) {
                if(data!=''){
                    layer.msg("用户名修改成功。。",{icon:1,time:2000,anim:4});
                    //同步更新表格和缓存对应的值
                    UserObj.update({
                        username: data
                    });
                }else {
                    layer.msg("用户名修改失败！！",{icon:2,time:2000,anim:3});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

});