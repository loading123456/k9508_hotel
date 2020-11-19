layui.use(['jquery','layer','form','laydate'],function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laydate = layui.laydate;

        //初始化可入住的房屋
    loadRoomsByPramas();

    //执行一个laydate实例
    laydate.render({
        elem: '#create_date', //指定元素
        format: 'yyyy/MM/dd HH:mm:ss',//日期的字符串格式
        value:new Date(),     //显示当前时间
        type:'datetime',      //选择格式（精确到秒）
        min:0    //只能选择当前时间之后的时间
    });

        //监听是不是会员单选框
    form.on('radio(isVip)',function (data) {
            if (data.value=="1"){
                //除开输入会员卡号的输入框外，其他的个人信息输入框均不可以输入
                $("#vip_num").removeAttr("disabled");
                $("#customerName").attr("disabled","disabled");
                $("input:radio:gt(1)").attr("disabled","disabled");
                $("#idcard").attr("disabled","disabled");
                $("#phone").attr("disabled","disabled");
                layer.msg("会员。。");
            }else{
                //除开输入会员卡号的输入框不可输入外，其他的个人信息输入框均可以输入
                $("#vip_num").attr("disabled","disabled");
                $("#customerName").removeAttr("disabled");

                $("#genderDiv").replaceWith('<div class="layui-input-block" id="genderDiv">\n' +
                    '                <input type="radio" name="gender" value="1" title="男" checked="checked"/>\n' +
                    '                <input type="radio" name="gender" value="0" title="女"/>\n' +
                    '            </div>');
                form.render("radio");
                $("input:radio").removeAttr("disabled");
                $("#idcard").removeAttr("disabled");
                $("#phone").removeAttr("disabled");
                $("form").eq(0).find("input:gt(1)").val("");
                layer.msg("非会员。。");
            }
        });

    //查询会员信息
    $("#vip_num").blur(function () {
        var vipNum = $(this).val();
        if (vipNum !=''){
            if(vipNum.length == 16){
                //根据会员卡号查询会员信息
                loadVipByPramas(vipNum);
            }else{
                //鼠标悬浮时提示消息
                layer.tips('卡号格式输入有误！', '#vip_num', {tips: [2,'#fc1505'], time:3000});
            }
        }

    });

    //表单验证
    form.verify({
        money: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value<0) {
                return '押金金额不能小于0';
            }
        },
        vip_num: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value<0) {
                return '会员卡号不能为负数';
            }
        }
    });

    //提交按钮 点击事件
    form.on('submit(demo1)',function (data) {
        var saveJsonINI=data.field;
        saveJsonINI['status'] = "1";
        saveJsonINI['outRoomStatus'] = "0";
        saveINI(saveJsonINI);//添加入住信息
        return false;//阻止表单跳转
    });

    /*------------------------------自定义的方法------------------------------------------*/

     //查询可以入住的房间信息
    function loadRoomsByPramas() {
            $.ajax({
                type:'POST',
                url:'rooms/loadManyTByPramas',
                data:{"roomStatus":"0"},
                success:function (data) {
                    var roomStr='<option value="">--请选择房间--</option>';
                    $.each(data,function (i,rooms) {
                        roomStr += '<option value="'+rooms.id+'">'+rooms.roomNum+'-'+rooms.roomType.roomTypeName+'-'+rooms.roomType.roomPrice+'</option>';
                    })
                    $("#selRoomNumId").html(roomStr);
                    form.render("select");     //渲染表单的下拉框
                },
                error:function () {
                    layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
                }
            });
    }

    //根据会员卡号查询会员信息
    function loadVipByPramas(vipNum){
        $.ajax({
            type:'POST',
            url:'vip/loadTByPramas',
            data:{"vipNum":vipNum},
            success:function (data) {
                if (data==""){
                    layer.tips('没有查到该会员信息！', '#vip_num', {tips: [2,'red'], time:3000});
                    $("form").eq(0).find("input:gt(1)").val("");
                } else{
                    layer.tips('已查到该会员信息！', '#vip_num', {tips: [2,'green'], time:3000});
                    //给表单赋值
                    form.val("example", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                        "customerName": data.customerName
                        ,"gender": data.gender
                        ,"idcard": data.idcard
                        ,"phone": data.phone
                    });
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //添加入住信息
    function saveINI(saveJsonINI){
        $.ajax({
            type:'POST',
            url:'inRoomInfo/saveT',
            data:saveJsonINI,
            success:function (data) {
                if(data=="success"){
                    layer.msg("入住信息添加成功",{icon:1,time:2000,anim:3});
                }else{
                    layer.msg("入住信息添加失败",{icon:2,time:2000,anim:4});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }
});