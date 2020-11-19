layui.use(['jquery','layer','table','form','laydate'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        laydate = layui.laydate;

    //ture可用 false不可用
    var usernameIf = false

    //验证用户名唯一性
    $("#username").blur(function () {
        //封装查询的条件
        if($(this).val()!=''){
            var selCountUser = {};
            selCountUser['username'] = $(this).val();
            checkUsername(selCountUser);
        }
    });

    //添加用户信息
    form.on('submit(demo2)',function (data) {
        if(usernameIf ){
            var saveJsonUser=data.field;
            saveJsonUser['createDate'] = new Date(); //这里可能有问题
            saveUser(saveJsonUser);
        }else {
            layer.msg("此用户名已被使用,请重新填写",{icon:2,time:2000,anim:6});
        }

        return false;
    });

    /******************************自定义方法************************************************************/
    //根据用户名查询用户的数据条数
    function checkUsername(selCountUser) {
        $.ajax({
            type:'POST',
            url:'user/getCountByPramas',
            async:false,  //允许外部变量取到ajax里面的数据
            data:selCountUser,
            success:function (data) {
                if (data == 1){
                    layer.tips('此用户名已被使用！', '#username', {tips: [2,'#fc1505'], time:3000});  //吸附框
                    usernameIf = false;
                }else{
                    layer.tips('用户名可用！', '#username', {tips: [2,'#2bfc46'], time:3000});  //吸附框
                    usernameIf = true;
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //添加用户信息
    function saveUser(saveJsonUser) {
        $.ajax({
            type:'POST',
            url:'user/saveT',
            data:saveJsonUser,
            success:function (data) {
                if (data == 'success'){
                    layer.msg("添加用户信息成功！！",{icon:1,time:2000,anim:4});
                    setTimeout('window.location="model/toShowUser"',2000);
                }else{
                    layer.msg("添加用户信息失败！！",{icon:2,time:2000,anim:3});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }
});