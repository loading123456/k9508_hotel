layui.use(['jquery','layer','table','form','laydate'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        laydate = layui.laydate;

    var selJsonVip; //定义全局变量 修改的字段 传给后台的数据
    var vipObj;    //定义成全局变量 可以不传obj

    loadVip(selJsonVip);//初始化

    function loadVip(selJsonVip){
        table.render({
            elem: '#demo'
            ,height: 512
            ,url: 'vip/loadPageTByPramas' //数据接口

            ,limit: 3
            ,limits: [2,3,5,8,10,15]
            ,page: true //开启分页
            ,even: true
            ,where:selJsonVip
            ,cols: [[ //表头
                {type:'checkbox'}
                ,{field: 'id', title: 'ID', width:80, align:'center',sort: true}
                ,{field: 'vipNum', title: '会员卡号', align:'center',width:220}
                ,{field: 'customerName', title: '客人姓名', sort: true,align:'center', width: 120 ,edit:'text'}
                ,{field: 'gender', title: '性别',align:'center', width: 120, sort: true,templet: '#genderTpl'}
                ,{field: 'vipRate', title: '会员类型',align:'center',sort: true, width: 215, sort: true,templet: '#vipRateTpl'}
                ,{field: 'idcard', title: '身份证号',align:'center',sort: true, width: 215, sort: true ,edit:'text'}
                ,{field: 'phone', title: '手机号',align:'center', width: 120, sort: true }
                ,{field: 'createDate', title: '创建时间', align:'center',width: 250}
                ,{fixed: 'right', width:220,  title: '操作',align:'center', toolbar: '#barDemo'}

            ]]
        });
    }

    //监听表单提交
    form.on('submit(demo1)',function (data) {
        selJsonVip = data.field;
        console.log(selJsonVip);
        loadVip(selJsonVip);
        return false;//组织表单跳转
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        vipObj=obj;
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if(layEvent === 'query'){ //查看
            layer.msg("查看"+data.id);

        } else if(layEvent === 'edit'){ //编辑
            //会员用户的信息修改
            //1.数据回显
            $("#phone").val(data.phone);
            if(data.vipRate==0.8){
                var vipTypeStr = '<option value="0.8" >超级会员</option><option value="0.9" >普通会员</option>'
                $("#vipRate").html(vipTypeStr);
                form.render('select');
            }else{
                var vipTypeStr = '<option value="0.9" >普通会员</option><option value="0.8" >超级会员</option>'
                $("#vipRate").html(vipTypeStr);
                form.render('select');
            }
            //2.弹框
            layer.open({
                type:1,
                title:'会员信息修改界面',
                area:['500px','300px'],
                shade:0.5,
                content:$("#updVipDiv"),
                anim:4
            });
            //3.监听提交修改  demo3是监听的弹框中的修改提交键的lay-filter
            form.on('submit(demo3)',function (vipData) {
                var updJsonVip = vipData.field;  //获取修改完的数据字段（手机号和会员类型）
                updJsonVip['id'] = data.id;
                updVip(updJsonVip);
                layer.closeAll();  //关闭所有弹框
                return false;//组织表单跳转
            });
        }
    });

    //监听单元格编辑    test是监听的表格table的lay-filter
    table.on('edit(test)', function(obj){
        var value = obj.value //得到修改后的值  这里的value只有客人姓名和身份证号  没有手机号
            ,data = obj.data //得到所在行所有键值
            ,field = obj.field; //得到字段
        //layer.msg('[ID:'+data.id+']'+ field + '字段更改为：'+ value)
        var updJsonVip = {};
        updJsonVip['id'] = data.id; //把id填进去
        updJsonVip[field] = value;  //把修改后的值填进去  没有手机号 因此手机号为null
        updVip(updJsonVip);
    });
    /***************************************自定义方法**************************************************************/
    function updVip(updJsonVip){
        $.ajax({
            type:'POST',
            url:'vip/updByPrimaryKeySelective',
            data:updJsonVip,
            success:function (data) {
                if(data=='success'){
                    layer.msg("修改成功",{icon:1,time:2000,anim:4});
                    //修改成功之后 同步更新缓存对应的值 这是点击修改方法才需要更新缓存
                    if(updJsonVip.phone!='' && updJsonVip.phone!=null){
                        vipObj.update({
                            phone:updJsonVip.phone
                            ,vipRate:updJsonVip.vipRate
                        });
                    }
                }else {
                    layer.msg("修改失败！！",{icon:2,time:2000,anim:3});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }
});